package com.cn.ben.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 通知监控配置
 *
 * @author Chen Nan
 */
@Component
@ConfigurationProperties(prefix = "monitor.notify")
@Data
public class NotifyMonitorConfig {
    /**
     * 通知监听器会监控未完成（未通知、通知异常、业务方处理失败）的通知消息总数，当数量达到阈值则发送告警邮件
     */
    private Integer notFinishCount = 1000;

    /**
     * 未完成通知消息数的增长速率超过阈值则发送告警邮件
     */
    private Integer addRate = 100;
}
