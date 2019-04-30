package com.cn.ben.api.model;

import com.cn.ben.api.enums.MethodEnum;
import com.cn.ben.api.enums.ParamTypeEnum;
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
     * 通知-请求地址
     */
    private String notifyUrl;
    /**
     * 通知-请求方式 0:GET, 1:POST, 2:HEAD, 3:OPTIONS, 4:PUT, 5:DELETE, 6:TRACE, 7:CONNECT, 8:PATCH
     */
    private MethodEnum notifyMethod = MethodEnum.POST;
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
    private ParamTypeEnum notifyParamType = ParamTypeEnum.FORM;
    /**
     * 通知-请求超时时长，单位：毫秒。默认5秒
     */
    private Short notifyTimeout = 5000;
    /**
     * 通知-成功响应标识（http请求响应包含此内容时即通知成功）（设为空或空字符串时，http响应码为2xx即通知成功）
     */
    private String successFlag;
    /**
     * 通知-Http请求成功，但业务方未返回成功响应标识时（即业务方处理失败），是否继续通知
     */
    private Boolean businessFailContinue = true;

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
