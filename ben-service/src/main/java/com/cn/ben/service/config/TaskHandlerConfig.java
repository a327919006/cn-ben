package com.cn.ben.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Title:</p>
 * <p>Description:
 * 消息确认定时任务配置
 * </p>
 *
 * @author Chen Nan
 * @date 2019/3/18.
 */
@Component
@ConfigurationProperties(prefix = "task.handler")
@Data
public class TaskHandlerConfig {
    /**
     * 线程池最小线程数
     */
    private Integer corePoolSize = 10;
    /**
     * 线程池最大线程数
     */
    private Integer maxPoolSize = 100;
    /**
     * 线程运行的空闲时间
     */
    private Integer keepAliveTime = 60000;
    /**
     * 缓存队列大小
     */
    private Integer queueCapacity = 10;

    /**
     * 处理器睡眠时间
     * 当处理通知任务的线程池耗尽时，通知任务处理器将睡眠一段时间
     */
    private Integer handlerSleep = 1000;

    /**
     * 重复通知时间间隔（单位：分钟）
     * 举例： [0, 1, 4, 10, 30, 60, 120, 360]
     * 第一次立即通知，如果业务方没有返回成功，则1分钟后再次通知。
     * 如果业务方还是没有返回成功，则4分钟后再次通知。
     * 以此类推
     */
    private List<Long> interval;
}
