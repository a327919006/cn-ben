package com.cn.ben.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 用于监控系统堆积消息数，并发送告警邮件
 * <p>
 * 背景：如果业务被动方（通知消息消费方）停机，而业务主动方（通知消息生产方）依然继续产生通知消息
 * 可能会导致大量通知失败的消息驻留在内存中，为了防止内存溢出等情况，提前发送告警邮件。
 *
 * @author Chen Nan
 */
@Component
@Slf4j
public class NotifyFailMonitor {

    public void task() {
        log.info("【NotifyFailMonitor】开始");

        // TODO 1、监听堆积消息数，当达到数量达到阈值则发送告警邮件
        // TODO 2、监控堆积消息数增加的速率,当一分钟内新增的堆积消息数超过阈值则发送告警邮件
        // TODO 3、系统增加业务开关，可临时关闭某个异常业务的通知任务，保存到数据库但不进行通知，待业务恢复正常后再恢复通知任务
        log.info("【NotifyFailMonitor】完成");
    }
}
