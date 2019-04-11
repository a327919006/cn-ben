package com.cn.ben.sample.controller;

import cn.hutool.json.JSONUtil;
import com.cn.ben.api.model.BenMessage;
import com.cn.ben.sample.model.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * <p>消息管理制器</p>
 *
 * @author Chen Nan
 * @date 2019/3/11.
 */
@RestController
@RequestMapping(value = "/sample")
@Slf4j
public class SampleController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @PostMapping("/test")
    public Object resend(@RequestBody TestMessage req) {
        log.info("【sample】start -> " + req);

        String queue = "notify.queue";
        BenMessage message = new BenMessage();
        message.setType("test");
        message.setContent(JSONUtil.toJsonStr(req));
        jmsMessagingTemplate.convertAndSend(queue, message);

        log.info("【sample】success");
        return "SUCCESS";
    }
}
