package com.cn.ben.sample.controller;

import cn.hutool.json.JSONUtil;
import com.cn.ben.api.enums.MethodEnum;
import com.cn.ben.api.enums.ParamTypeEnum;
import com.cn.ben.api.model.BenNotify;
import com.cn.ben.sample.model.TestMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>消息管理制器</p>
 *
 * @author Chen Nan
 * @date 2019/3/11.
 */
@RestController
@Api(tags = "收件人手机号", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(value = "/sample")
@Slf4j
public class SampleController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @ApiOperation("测试-发送通知")
    @PostMapping("/test/send")
    public Object sendNotify(@RequestBody TestMessage req) {
        log.info("【sample】发送通知 -> " + req);


        // 发送通知-请求方式POST，请求参数类型Body
        sendPostBodyNotify(req);
        // 发送通知-请求方式POST，请求参数类型FORM表单
//        sendPostFormNotify(req);
        // 发送通知-请求方式GET
//        sendGetNotify(req);
        // 发送通知-默认请求
//        sendNotifyDefault();

        log.info("【sample】发送通知成功");
        Map<String, Object> rsp = new HashMap<>();
        rsp.put("code", 0);
        rsp.put("msg", "SUCCESS");
        return rsp;
    }

    @ApiOperation("测试-接收通知-POST-JsonBody")
    @PostMapping("/notify/post/body")
    public Object notifyPostBody(@RequestBody(required = false) TestMessage req,
                                 @RequestHeader(required = false) String token) {
        log.info("【sample】notifyPostBody, 接收通知 -> {}, token={}", req, token);

        log.info("【sample】处理通知成功");
        return "SUCCESS";
    }

    @ApiOperation("测试-接收通知-POST-FORM")
    @PostMapping("/notify/post/form")
    public Object notifyPostForm(@ModelAttribute TestMessage req,
                                 @RequestHeader(required = false) String token) {
        log.info("【sample】notifyPostForm, 接收通知 -> {}, token={}", req, token);

        log.info("【sample】处理通知成功");
        return "SUCCESS";
    }

    @ApiOperation("测试-接收通知-GET")
    @GetMapping("/notify/get")
    public Object notifyGet(@ModelAttribute TestMessage req,
                            @RequestHeader(required = false) String token) {
        log.info("【sample】notifyGet, 接收通知 -> {}, token={}", req, token);

        log.info("【sample】处理通知成功");
        return "SUCCESS";
    }

    /**
     * 发送通知-请求方式POST，请求参数类型Body
     */
    private void sendPostBodyNotify(TestMessage req) {
        // 构造通知对象
        BenNotify notify = new BenNotify();

        // 通知-请求地址，必须指定
        notify.setNotifyUrl("http://127.0.0.1:10081/sample/notify/post/body");

        // 通知-请求方式，可不指定，默认POST
        notify.setNotifyMethod(MethodEnum.POST);

        // 通知-请求参数类型，可不指定，默认FORM
        notify.setNotifyParamType(ParamTypeEnum.BODY);

        // 通知-请求参数，无请求参数时可不指定，
        // 当参数类型为BODY时，可传入任意字符串，如Json、xml。或Map<String, Object>，会自动转成Json字符串
        // 当参数类型为FORM时，支持传入Map<String, Object>
        notify.setNotifyParam(JSONUtil.toJsonStr(req));

        // 通知-请求头，无请求头参数时可不指定, 支持传入Map<String, String>
        Map<String, String> header = new HashMap<>();
        header.put("token", "123456");
        notify.setNotifyHeader(header);

        // 通知-请求超时时长，单位：毫秒，可不指定，默认5000毫秒
        notify.setNotifyTimeout((short) 5000);

        // 通知-成功响应标识（http请求响应包含此内容时即通知成功），可不指定，默认null
        // （为null或空字符串时，http响应码为2xx即通知成功）
        notify.setSuccessFlag("SUCCESS");

        // 通知-Http请求成功，但业务方未返回成功响应标识时（即业务方处理失败），是否继续通知，默认true
        notify.setBusinessFailContinue(true);

        // 本次通知的业务名称，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessName("test");
        // 本次通知的业务方记录ID，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessId("t0000001");

        // 发送通知
        jmsMessagingTemplate.convertAndSend(BenNotify.QUEUE, notify);
    }

    /**
     * 发送通知-请求方式POST，请求参数类型FORM表单
     */
    private void sendPostFormNotify(TestMessage req) {
        // 构造通知对象
        BenNotify notify = new BenNotify();

        // 通知-请求地址
        notify.setNotifyUrl("http://127.0.0.1:10081/sample/notify/post/form");

        // 通知-请求参数
        Map<String, Object> param = new HashMap<>();
        param.put("title", req.getTitle());
        param.put("content", req.getContent());
        notify.setNotifyParam(param);

        // 通知-请求头
        Map<String, String> header = new HashMap<>();
        header.put("token", "123456");
        notify.setNotifyHeader(header);

        // 通知-成功响应标识（http请求响应包含此内容时即通知成功）（设为空或空字符串时，http响应码为2xx即通知成功）
        notify.setSuccessFlag("SUCCESS");

        // 本次通知的业务名称，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessName("test");
        // 本次通知的业务方记录ID，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessId("t0000001");

        // 发送通知
        jmsMessagingTemplate.convertAndSend(BenNotify.QUEUE, notify);
    }

    /**
     * 发送通知-请求方式GET
     */
    private void sendGetNotify(TestMessage req) {
        // 构造通知对象
        BenNotify notify = new BenNotify();
        // 通知-请求地址
        notify.setNotifyUrl("http://127.0.0.1:10081/sample/notify/get");
        // 通知-请求方式
        notify.setNotifyMethod(MethodEnum.GET);
        // 通知-请求参数
        Map<String, Object> param = new HashMap<>();
        param.put("title", req.getTitle());
        param.put("content", req.getContent());
        notify.setNotifyParam(param);

        // 通知-请求头
        Map<String, String> header = new HashMap<>();
        header.put("token", "123456");
        notify.setNotifyHeader(header);

        // 通知-成功响应标识（http请求响应包含此内容时即通知成功）（设为空或空字符串时，http响应码为2xx即通知成功）
        notify.setSuccessFlag("SUCCESS");

        // 本次通知的业务名称，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessName("test");
        // 本次通知的业务方记录ID，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessId("t0000001");

        // 发送通知
        jmsMessagingTemplate.convertAndSend(BenNotify.QUEUE, notify);
    }

    /**
     * 发送通知-请求方式POST，请求参数类型FORM表单
     */
    private void sendNotifyDefault() {
        // 默认请求方式POST,无请求参数，无请求头参数
        BenNotify notify = new BenNotify();
        notify.setNotifyUrl("http://127.0.0.1:10081/sample/notify/post/form");

        // 发送通知
        jmsMessagingTemplate.convertAndSend(BenNotify.QUEUE, notify);
    }
}
