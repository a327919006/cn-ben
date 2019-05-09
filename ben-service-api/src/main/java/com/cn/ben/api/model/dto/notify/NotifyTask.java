package com.cn.ben.api.model.dto.notify;

import com.cn.ben.api.enums.MethodEnum;
import com.cn.ben.api.enums.ParamTypeEnum;
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
    /**
     * 延时任务执行时间
     */
    private LocalDateTime executeTime;

    /**
     * 通知记录ID
     */
    private String id;

    /**
     * 通知-请求地址
     */
    private String notifyUrl;
    /**
     * 通知-请求方式 0:GET, 1:POST, 2:HEAD, 3:OPTIONS, 4:PUT, 5:DELETE, 6:TRACE, 7:CONNECT, 8:PATCH
     */
    private MethodEnum notifyMethod;
    /**
     * 通知-请求头
     */
    private Map<String, String> notifyHeader;
    /**
     * 通知-请求参数
     */
    private Map<String, Object> notifyParam;
    /**
     * 通知-请求参数类型 0:FORM 1:BODY
     */
    private ParamTypeEnum notifyParamType;
    /**
     * 通知-请求超时时长，单位：毫秒。默认5秒
     */
    private Short notifyTimeout;
    /**
     * 通知-成功响应标识（http请求响应包含此内容时即通知成功）（设为空或空字符串时，http响应码为2xx即通知成功）
     */
    private String successFlag;
    /**
     * 通知-Http请求成功，但业务方未返回成功响应标识时（即业务方处理失败），是否继续通知
     */
    private Boolean businessFailContinue = true;

    /**
     * 已通知次数
     */
    private Short notifyTimes;

    /**
     * 本次通知的业务名称
     * 根据业务实际情况，用于后期流程跟踪或异常排查时使用
     */
    private String businessName;
    /**
     * 本次通知的业务方记录ID
     * 根据业务实际情况，用于后期流程跟踪或异常排查时使用
     */
    private String businessId;

    /**
     * 最后一次通知时间
     */
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
