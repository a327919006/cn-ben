package com.cn.ben.service.listener;

import com.cn.ben.service.mq.NotifyTaskHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/4/20.
 */
@Slf4j
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        NotifyTaskHandler notifyTaskHandler = applicationContext.getBean(NotifyTaskHandler.class);
        notifyTaskHandler.init();
        notifyTaskHandler.startResumeNotify();
    }
}