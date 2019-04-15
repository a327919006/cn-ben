package com.cn.ben.service.mq;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.cn.ben.api.model.dto.NotifyTask;
import com.cn.ben.service.config.DelayQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>Title:</p>
 * <p>Description:
 * 通知-延时任务处理器
 * </p>
 *
 * @author Chen Nan
 */
@Component
@Slf4j
public class NotifyTaskHandler {
    private static DelayQueue<NotifyTask> delayQueue = new DelayQueue<>();

    @Autowired
    private DelayQueueConfig config;

    public NotifyTaskHandler(ThreadPoolExecutor singleThreadExecutor) {
        init(singleThreadExecutor);
    }

    /**
     * 添加通知任务
     *
     * @param notifyTask 通知任务信息
     */
    public void addTask(NotifyTask notifyTask) {
        log.info("【NotifyTask】添加任务到延时队列");

        int maxNotifyTimes = config.getInterval().size();
        int currNotifyTimes = notifyTask.getNotifyTimes();
        if (currNotifyTimes < maxNotifyTimes) {
            LocalDateTime executeTime = getExecuteTime(notifyTask);
            notifyTask.setExecuteTime(executeTime);
            delayQueue.add(notifyTask);
        } else {
            log.info("【NotifyTask】超过通知次数限制，标记为通知失败");
        }
    }

    /**
     * 计算本次通知任务执行时间
     *
     * @param notifyTask 通知任务信息
     * @return 执行时间
     */
    private LocalDateTime getExecuteTime(NotifyTask notifyTask) {
        LocalDateTime lastNotifyTime = notifyTask.getUpdateTime();
        int currNotifyTimes = notifyTask.getNotifyTimes();
        long interval = config.getInterval().get(currNotifyTimes) * 60 * 1000 + 1;
        return lastNotifyTime.plus(interval, ChronoUnit.MILLIS);
    }

    private void init(ThreadPoolExecutor singleThreadExecutor) {
        log.info("【NotifyTaskHandler】开始初始化");
        singleThreadExecutor.execute(() -> {
            while (true) {
                try {
                    NotifyTask task = delayQueue.take();
                    delayQueue.remove(task);

                    String result = HttpRequest.post(task.getNotifyUrl())
                            .addHeaders(task.getNotifyHeader())
                            .body(JSONUtil.toJsonStr(task.getNotifyContent()))
                            .timeout(task.getNotifyTimeout())
                            .execute()
                            .body();
                    log.info("【NotifyQueue】处理消息成功 -> " + result);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        log.info("【NotifyTaskHandler】初始化成功");
    }
}
