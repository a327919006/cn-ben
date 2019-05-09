package com.cn.ben.service.listener;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.system.HostInfo;
import com.cn.ben.api.model.dto.memory.BenMemory;
import com.cn.ben.api.model.dto.notify.NotifyRecordDto;
import com.cn.ben.api.utils.BenUtils;
import com.cn.ben.api.utils.DateFormatUtils;
import com.cn.ben.service.config.MemoryMonitorConfig;
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
import java.time.LocalDateTime;

/**
 * 用于监控系统内存，并发送告警邮件
 * <p>
 * 背景：业务主动方（通知消息生产方）产生消息的速率超过业务被动方（通知消息消费方）的消费速率，
 * 就会导致消息驻留在内存中，为了防止内存溢出等情况，提前发送告警邮件。
 *
 * @author Chen Nan
 */
@Component
@Slf4j
public class MemoryMonitor {

    @Autowired
    private MemoryMonitorConfig config;
    @Autowired
    private NotifyTaskHandler notifyTaskHandler;

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
    /**
     * 内存不足标识
     */
    public static boolean memoryLessFlag = false;
    /**
     * 内存不足开始时间
     */
    private static LocalDateTime memoryLessStartTime;


    @Scheduled(cron = "0 0/1 * * * ? ")
    public void task() {
        log.info("【MemoryMonitor】开始");

        judgeMemoryLess();

        log.info("【MemoryMonitor】完成");
    }

    /**
     * 判断JVM内存是否不足
     */
    private void judgeMemoryLess() {
        BenMemory memory = BenUtils.getMemoryInfo();
        long threshold = memory.getMax() * config.getLessPercent() / 100;
        int size = NotifyTaskHandler.delayQueue.size();
        printMemoryInfo(memory, size, threshold);

        boolean memoryLess = memory.getMax() - memory.getTotal() + memory.getFree() < threshold;
        if (memoryLess) {
            memoryLess(memory, threshold);
        } else {
            memoryResume();
        }
    }

    /**
     * 内存不足
     *
     * @param memory    内存信息
     * @param threshold 内存阈值
     */
    private void memoryLess(BenMemory memory, long threshold) {
        log.info("【MemoryMonitor】内存不足");
        sendWarnMail(memory, threshold);
        if (!memoryLessFlag) {
            memoryLessFlag = true;
            memoryLessStartTime = LocalDateTime.now();
        }
        ThreadUtil.sleep(config.getSleepTime());
    }

    /**
     * 内存恢复正常
     */
    private void memoryResume() {
        log.info("【MemoryMonitor】内存正常");
        if (memoryLessFlag) {
            memoryLessFlag = false;
            NotifyRecordDto condition = new NotifyRecordDto();
            condition.setNotifyingExceptWait(1);
            condition.setCreateStartTime(DateFormatUtils.formatDateTime(memoryLessStartTime));
            condition.setCreateEndTime(DateFormatUtils.formatDateTime(LocalDateTime.now()));

            log.info("【MemoryMonitor】恢复内存不足期间未通知成功的记录-开始");
            notifyTaskHandler.pageResumeNotifyRecord(condition);
            log.info("【MemoryMonitor】恢复内存不足期间未通知成功的记录-完成");
        }
    }

    /**
     * 发送内存告警邮件
     */
    private void sendWarnMail(BenMemory memory, long threshold) {
        boolean needSendMailFlag = lastSendWarnMailDate == null || LocalDate.now().compareTo(lastSendWarnMailDate) > 0;
        if (needSendMailFlag) {
            try {
                log.info("【MemoryMonitor】发送告警邮件-开始");
                lastSendWarnMailDate = LocalDate.now();
                int size = NotifyTaskHandler.delayQueue.size();

                HostInfo hostInfo = new HostInfo();
                String title = "【BenService】内存不足";
                String content = "";
                content += "主机IP：" + hostInfo.getAddress() + "\n";
                content += "主机名：" + hostInfo.getName() + "\n";
                content += "积压消息数：" + size + "\n";
                content += "JVM已用内存：" + memory.getUse() + " MB\n";
                content += "JVM空闲内存：" + memory.getFree() + " MB\n";
                content += "JVM总内存：" + memory.getTotal() + " MB\n";
                content += "JVM最大内存：" + memory.getMax() + " MB\n";
                content += "JVM内存阈值：" + threshold + " MB\n";

                sendMail(title, content);
                log.info("【MemoryMonitor】发送告警邮件-完成");
            } catch (Exception e) {
                log.error("【MemoryMonitor】发送告警邮件-异常:", e);
            }
        }
    }

    /**
     * 发送邮件
     *
     * @param title   标题
     * @param content 内容
     */
    private void sendMail(String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(mailTo);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 打印内存和运行情况
     *
     * @param memory    JVM内存信息
     * @param taskCount 积压消息数
     * @param threshold JVM内存阈值
     */
    private void printMemoryInfo(BenMemory memory, int taskCount, long threshold) {
        String memoryInfo = "积压消息数：" + taskCount + ", ";
        memoryInfo += memory;
        memoryInfo += "JVM内存阈值：" + threshold + " MB";
        log.info("【MemoryMonitor】" + memoryInfo);
    }
}
