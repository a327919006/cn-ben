package com.cn.ben.sample.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
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
    @PostMapping("/test")
    public Object resend(@RequestBody TestMessage req) {
        log.info("【sample】发送通知 -> " + req);

        // 构造通知对象
        BenNotify notify = new BenNotify();
        // 通知请求地址
        notify.setNotifyUrl("http://127.0.0.1:10081/sample/notify");
        // 通知请求参数
        notify.setNotifyContent(BeanUtil.beanToMap(req));
        // 通知请求头
        Map<String, String> header = new HashMap<>();
        header.put("token", "123456");
        notify.setNotifyHeader(header);
        // 通知请求超时时长，单位：毫秒
        notify.setNotifyTimeout((short) 1000);

        // 本次通知的业务名称，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessName("test");
        // 本次通知的业务方记录ID，非必填，根据业务实际情况，用于后期流程跟踪或异常排查时使用
        notify.setBusinessId("t0000001");

        // 发送通知
        String queue = "notify.queue";
        jmsMessagingTemplate.convertAndSend(queue, notify);


        log.info("【sample】发送通知成功");
        Map<String, Object> rsp = new HashMap<>();
        rsp.put("code", 1);
        rsp.put("msg", "SUCCESS");
        return rsp;
    }

    @ApiOperation("测试-接收通知")
    @PostMapping("/notify")
    public Object notify(@RequestBody TestMessage req, @RequestHeader String token) {
        log.info("【sample】接收通知 -> {}, token={}", req, token);

        log.info("【sample】处理通知成功");
        return "SUCCESS";
    }
}
