package com.cn.ben.service.listener;

import cn.hutool.system.HostInfo;
import com.cn.ben.service.config.NotifyMonitorConfig;
import com.cn.ben.service.handler.MailHandler;
import com.cn.ben.service.handler.NotifyTaskHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 用于监控系统堆积通知任务数，并发送告警邮件（同类告警每天只告警一次）
 * <p>
 * 背景：如果业务被动方（通知消息消费方）停机，而业务主动方（通知消息生产方）依然继续产生通知消息
 * 可能会导致大量通知任务由于通知异常，驻留在内存中，为了防止内存溢出等情况，提前发送告警邮件。
 *
 * @author Chen Nan
 */
@Component
@Slf4j
public class NotifyCountMonitor {

    @Autowired
    private NotifyMonitorConfig config;
    @Autowired
    private MailHandler mailHandler;

    /**
     * 最近一次告警邮件发送日期
     */
    private static LocalDate addRateWarnDate;
    private static LocalDate countWarnDate;
    /**
     * 上次统计到的堆积消息数
     */
    private static int lastCount = -1;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void task() {
        log.info("【NotifyCountMonitor】开始");

        checkNotFinishCount();
        checkAddRate();

        log.info("【NotifyCountMonitor】完成");
    }

    /**
     * 监控堆积消息数的增加速率
     */
    private void checkAddRate() {
        // 当前堆积消息数
        int size = NotifyTaskHandler.delayQueue.size();
        // 阈值
        int threshold = config.getAddRate();
        int addCount = size - lastCount;
        if (lastCount > 0 && addCount >= threshold) {
            boolean needSendMailFlag = addRateWarnDate == null || LocalDate.now().compareTo(addRateWarnDate) > 0;
            if (needSendMailFlag) {
                try {
                    log.info("【NotifyCountMonitor】发送告警邮件-开始");
                    addRateWarnDate = LocalDate.now();

                    HostInfo hostInfo = new HostInfo();
                    String title = "【BenService】堆积消息数的增加速率达到阈值";
                    String content = "";
                    content += "主机IP：" + hostInfo.getAddress() + "\n";
                    content += "主机名：" + hostInfo.getName() + "\n";
                    content += "堆积消息数：" + size + "\n";
                    content += "增加数：" + addCount + "\n";
                    content += "阈值：" + threshold + "\n";

                    mailHandler.sendMail(title, content);
                    log.info("【NotifyCountMonitor】发送告警邮件-完成");
                } catch (Exception e) {
                    log.error("【NotifyCountMonitor】发送告警邮件-异常:", e);
                }
            }
        }
        lastCount = size;
    }

    /**
     * 监控未完成的消息数
     */
    private void checkNotFinishCount() {
        // 当前堆积消息数
        int size = NotifyTaskHandler.delayQueue.size();
        // 阈值
        int threshold = config.getNotFinishCount();
        if (size >= threshold) {
            boolean needSendMailFlag = countWarnDate == null || LocalDate.now().compareTo(countWarnDate) > 0;
            if (needSendMailFlag) {
                try {
                    log.info("【NotifyCountMonitor】发送告警邮件-开始");
                    countWarnDate = LocalDate.now();

                    HostInfo hostInfo = new HostInfo();
                    String title = "【BenService】未完成消息数达到阈值";
                    String content = "";
                    content += "主机IP：" + hostInfo.getAddress() + "\n";
                    content += "主机名：" + hostInfo.getName() + "\n";
                    content += "堆积消息数：" + size + "\n";
                    content += "阈值：" + threshold + "\n";

                    mailHandler.sendMail(title, content);
                    log.info("【NotifyCountMonitor】发送告警邮件-完成");
                } catch (Exception e) {
                    log.error("【NotifyCountMonitor】发送告警邮件-异常:", e);
                }
            }
        }
    }
}
