package com.cn.ben.sample.controller;

import cn.hutool.core.bean.BeanUtil;
import com.cn.ben.api.enums.MethodEnum;
import com.cn.ben.api.model.BenNotify;
import com.cn.ben.api.enums.ParamTypeEnum;
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
        sendNotify1(req);
        // 发送通知-请求方式POST，请求参数类型FORM表单
//        sendNotify2(req);
        // 发送通知-请求方式GET
//        sendNotify3(req);

        log.info("【sample】发送通知成功");
        Map<String, Object> rsp = new HashMap<>();
        rsp.put("code", 0);
        rsp.put("msg", "SUCCESS");
        return rsp;
    }

    @ApiOperation("测试-接收通知-POST-JsonBody")
    @PostMapping("/notify/post/body")
    public Object notify1(@RequestBody TestMessage req, @RequestHeader String token) {
        log.info("【sample1】接收通知 -> {}, token={}", req, token);

        log.info("【sample1】处理通知成功");
        return "SUCCESS";
    }

    @ApiOperation("测试-接收通知-POST-FORM")
    @PostMapping("/notify/post/form")
    public Object notify2(@ModelAttribute TestMessage req, @RequestHeader String token) {
        log.info("【sample2】接收通知 -> {}, token={}", req, token);

        log.info("【sample2】处理通知成功");
        return "SUCCESS";
    }

    @ApiOperation("测试-接收通知-GET")
    @GetMapping("/notify/get")
    public Object notify3(@ModelAttribute TestMessage req, @RequestHeader String token) {
        log.info("【sample3】接收通知 -> {}, token={}", req, token);

        log.info("【sample3】处理通知成功");
        return "SUCCESS";
    }

    /**
     * 发送通知-请求方式POST，请求参数类型Body
     */
    private void sendNotify1(TestMessage req){
        // 构造通知对象
        BenNotify notify = new BenNotify();
        // 通知-请求地址
        notify.setNotifyUrl("http://127.0.0.1:10081/sample/notify/post/body");
        // 通知-请求方式
        notify.setNotifyMethod(MethodEnum.POST);
        // 通知-请求参数
        notify.setNotifyParam(BeanUtil.beanToMap(req));
        // 通知-请求参数类型
        notify.setNotifyParamType(ParamTypeEnum.BODY);
        // 通知-请求头
        Map<String, String> header = new HashMap<>();
        header.put("token", "123456");
        notify.setNotifyHeader(header);
        // 通知-请求超时时长，单位：毫秒
        notify.setNotifyTimeout((short) 5000);
        // 通知-成功响应标识（http请求响应包含此内容时即通知成功）（设为空或空字符串时，http响应码为2xx即通知成功）
        notify.setSuccessFlag("SUCCESS");
        // 通知-Http请求成功，但业务方未返回成功响应标识时（即业务方处理失败），是否继续通知，默认true
        notify.setBusinessFailContinue(true);

        // 本次通知的业务名称，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessName("test");
        // 本次通知的业务方记录ID，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessId("t0000001");

        // 发送通知
        String queue = "notify.queue";
        jmsMessagingTemplate.convertAndSend(queue, notify);
    }

    /**
     * 发送通知-请求方式POST，请求参数类型FORM表单
     */
    private void sendNotify2(TestMessage req){
        // 构造通知对象
        BenNotify notify = new BenNotify();
        // 通知-请求地址
        notify.setNotifyUrl("http://127.0.0.1:10081/sample/notify/post/form");
        // 通知-请求方式
        notify.setNotifyMethod(MethodEnum.POST);
        // 通知-请求参数
        notify.setNotifyParam(BeanUtil.beanToMap(req));
        // 通知-请求参数类型
        notify.setNotifyParamType(ParamTypeEnum.FORM);
        // 通知-请求头
        Map<String, String> header = new HashMap<>();
        header.put("token", "123456");
        notify.setNotifyHeader(header);
        // 通知-请求超时时长，单位：毫秒
        notify.setNotifyTimeout((short) 1000);
        // 通知-成功响应标识（http请求响应包含此内容时即通知成功）（设为空或空字符串时，http响应码为2xx即通知成功）
        notify.setSuccessFlag("SUCCESS");

        // 本次通知的业务名称，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessName("test");
        // 本次通知的业务方记录ID，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessId("t0000001");

        // 发送通知
        String queue = "notify.queue";
        jmsMessagingTemplate.convertAndSend(queue, notify);
    }

    /**
     * 发送通知-请求方式GET
     */
    private void sendNotify3(TestMessage req){
        // 构造通知对象
        BenNotify notify = new BenNotify();
        // 通知-请求地址
        notify.setNotifyUrl("http://127.0.0.1:10081/sample/notify/get");
        // 通知-请求方式
        notify.setNotifyMethod(MethodEnum.GET);
        // 通知-请求参数
        notify.setNotifyParam(BeanUtil.beanToMap(req));
        // 通知-请求头
        Map<String, String> header = new HashMap<>();
        header.put("token", "123456");
        notify.setNotifyHeader(header);
        // 通知-请求超时时长，单位：毫秒
        notify.setNotifyTimeout((short) 1000);
        // 通知-成功响应标识（http请求响应包含此内容时即通知成功）（设为空或空字符串时，http响应码为2xx即通知成功）
        notify.setSuccessFlag("SUCCESS");

        // 本次通知的业务名称，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessName("test");
        // 本次通知的业务方记录ID，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessId("t0000001");

        // 发送通知
        String queue = "notify.queue";
        jmsMessagingTemplate.convertAndSend(queue, notify);
    }
}
