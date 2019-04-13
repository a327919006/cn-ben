package com.cn.ben.api.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/4/12.
 */
@Getter
@Setter
@ToString
public class NotifyTask implements Delayed {
    private LocalDateTime executeTime;

    private String notifyUrl;

    private Map<String, String> notifyHeader;

    private String notifyContent;

    private Short notifyTimeout;

    private Short notifyTimes;

    private String businessName;

    private String businessId;

    private LocalDateTime updateTime;

    /**
     * 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期，否则还没到期
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime.compareTo(LocalDateTime.now()), TimeUnit.SECONDS);
    }

    /**
     * 自定义实现比较方法返回 1 0 -1三个参数
     */
    @Override
    public int compareTo(Delayed o) {
        NotifyTask task = (NotifyTask) o;
        return this.executeTime.compareTo(task.executeTime);
    }
}
