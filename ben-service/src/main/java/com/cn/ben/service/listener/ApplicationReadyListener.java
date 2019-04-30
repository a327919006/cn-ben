package com.cn.ben.service.listener;

import com.cn.ben.service.mq.NotifyTaskHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/4/20.
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {
    private String[] args;

    @SuppressWarnings("NullableProblems")
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        NotifyTaskHandler notifyTaskHandler = applicationContext.getBean(NotifyTaskHandler.class);
        notifyTaskHandler.init();
        boolean resumeFlag = args.length == 0 || "1".equals(args[0]);
        if (resumeFlag) {
            notifyTaskHandler.startResumeNotify();
        } else {
            log.info("【ResumeNotify】无需恢复未完成的通知");
            ThreadPoolExecutor resumeExecutor = applicationContext.getBean("resumeExecutor", ThreadPoolExecutor.class);
            resumeExecutor.shutdown();
        }
    }
}
