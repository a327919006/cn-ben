package com.cn.ben.service.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/3/22.
 */
@Configuration
public class ThreadPoolConfig {

    @Autowired
    private TaskHandlerConfig config;

    /**
     * 单线程线程池
     * 用于一直运行通知任务处理器
     * @return 单线程线程池
     */
    @Bean
    public ThreadPoolExecutor handlerExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("task-handler").build();
        // 单线程线程池
        return new ThreadPoolExecutor(1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                namedThreadFactory);
    }

    /**
     * 单线程线程池
     * 用于恢复未完成通知的记录，完成恢复后关闭线程池
     * @return 单线程线程池
     */
    @Bean
    public ThreadPoolExecutor resumeExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("resume-task").build();
        //
        return new ThreadPoolExecutor(1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                namedThreadFactory);
    }

    /**
     * 多线程处理所有通知任务，线程数根据配置文件，可由业务量决定线程池大小
     * @return 线程池
     */
    @Bean
    public ThreadPoolExecutor taskExecutor() {
        // 为线程池起名
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("task-pool-").build();
        return new ThreadPoolExecutor(config.getCorePoolSize(),
                config.getMaxPoolSize(),
                config.getKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(config.getQueueCapacity()),
                namedThreadFactory);
    }
}
