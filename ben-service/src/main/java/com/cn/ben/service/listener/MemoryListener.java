package com.cn.ben.service.listener;

import cn.hutool.system.HostInfo;
import com.cn.ben.service.config.TaskHandlerConfig;
import com.cn.ben.service.mq.NotifyTaskHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * 用于监控系统内存，发送告警邮件
 * <p>
 * 背景：业务主动方（通知消息生产方）产生消息的速率超过业务被动方（通知消息消费方）的消费速率，
 * 就会导致消息驻留在内存中，为了防止内存溢出等情况，提前发送告警邮件。
 *
 * @author Chen Nan
 */
@Component
@Slf4j
public class MemoryListener {

    @Autowired
    private TaskHandlerConfig config;
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${spring.mail.receiver}")
    private String mailTo;
    /**
     * 最近一次内存告警邮件发送日期
     */
    private static LocalDate lastSendWarnMailDate;


    @Scheduled(cron = "0 0/1 * * * ? ")
    public void task() {
        log.info("【MemoryListener】开始");

        judgeMemoryLess();

        log.info("【MemoryListener】完成");
    }

    /**
     * 判断JVM内存是否不足
     */
    public void judgeMemoryLess() {
        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        long vmTotal = rt.totalMemory() / byteToMb;
        long vmFree = rt.freeMemory() / byteToMb;
        long vmMax = rt.maxMemory() / byteToMb;
        long vmUse = vmTotal - vmFree;
        long threshold = vmMax * config.getMemoryLessPercent() / 100;
        if (vmMax - vmTotal < threshold) {
            sendWarnMail(vmTotal, vmFree, vmMax, vmUse, threshold);
        }
    }

    /**
     * 发送内存告警邮件
     */
    private void sendWarnMail(long vmTotal, long vmFree, long vmMax, long vmUse, long threshold) {
        boolean needSendMailFlag = lastSendWarnMailDate == null || LocalDate.now().compareTo(lastSendWarnMailDate) > 0;
        if (needSendMailFlag) {
            int size = NotifyTaskHandler.delayQueue.size();
            log.info("【MemoryListener】内存不足，积压消息数：" + size);
            log.info("【MemoryListener】JVM已用内存空间为：" + vmUse + " MB");
            log.info("【MemoryListener】JVM空闲内存空间为：" + vmFree + " MB");
            log.info("【MemoryListener】JVM总内存空间为：" + vmTotal + " MB");
            log.info("【MemoryListener】JVM最大内存空间为：" + vmMax + " MB");
            log.info("【MemoryListener】JVM内存阈值：" + threshold + " MB");
            lastSendWarnMailDate = LocalDate.now();

            try {
                log.info("【MemoryListener】发送告警邮件-开始");

                HostInfo hostInfo = new HostInfo();
                String title = "【BenService】内存不足";
                String content = "";
                content += "主机IP：" + hostInfo.getAddress() + "\n";
                content += "主机名：" + hostInfo.getName() + "\n";
                content += "积压消息数：" + size + "\n";
                content += "JVM已用内存空间为：" + vmUse + " MB\n";
                content += "JVM空闲内存空间为：" + vmFree + " MB\n";
                content += "JVM总内存空间为：" + vmTotal + " MB\n";
                content += "JVM最大内存空间为：" + vmMax + " MB\n";
                content += "JVM内存阈值为：" + threshold + " MB\n";

                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(mailFrom);
                message.setTo(mailTo);
                message.setSubject(title);
                message.setText(content);
                mailSender.send(message);

                log.info("【MemoryListener】发送告警邮件-完成");
            } catch (Exception e) {
                log.error("【MemoryListener】发送告警邮件-异常:", e);
            }
        }
    }
}
