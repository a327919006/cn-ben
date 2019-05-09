package com.cn.ben.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 内存监控配置
 *
 * @author Chen Nan
 */
@Component
@ConfigurationProperties(prefix = "monitor.memory")
@Data
public class MemoryMonitorConfig {
    /**
     * 内存不足百分比
     * 计算公式：(vmMax - vmTotal + vmFree) < (vmMax * Percent / 100)，即：剩余内存 < 最大内存*Percent
     * 内存不足时，系统有两个应对策略：
     * 1、发送告警邮件。
     * 2、通知任务在保存到数据库后，只执行一次通知,
     * 无论通知成功或失败，更新通知结果到数据库，并将任务信息从内存中释放,
     * 内存监听器将休眠一段时候后重新计算内存情况，直到内存足够后，从数据库中读取休眠期间未通知成功的通知记录，继续通知。
     */
    private Integer lessPercent = 15;
    /**
     * 内存不足休眠时间（单位：毫秒）
     */
    private Integer sleepTime = 120000;
}
