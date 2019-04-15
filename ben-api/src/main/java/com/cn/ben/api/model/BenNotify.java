package com.cn.ben.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/4/11.
 */
@Getter
@Setter
@ToString
public class BenNotify {
    /**
     * 通知请求地址
     */
    private String notifyUrl;
    /**
     * 通知请求头
     */
    private Map<String, String> notifyHeader;
    /**
     * 通知请求参数
     */
    private Map<String, Object> notifyContent;
    /**
     * 通知请求超时时长，单位：毫秒。默认5秒
     */
    private Short notifyTimeout = 5000;

    /**
     * 本次通知的业务名称
     * 非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
     */
    private String businessName = "";
    /**
     * 本次通知的业务方记录ID
     * 非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
     */
    private String businessId = "";
}
