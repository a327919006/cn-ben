package com.cn.ben.service.mq;

import com.cn.ben.api.model.BenMessage;
import com.cn.ben.api.model.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/3/31.
 */
@Component
@Slf4j
public class NotifyQueueCustomer {


    @JmsListener(destination = Constants.QUEUE_NOTIFY)
    public void handleTestMsg(BenMessage msg) {
        try {
            log.info("【NotifyQueue】开始处理消息 -> " + msg);

            log.info("【NotifyQueue】处理消息成功");
        } catch (Exception e) {
            log.error("【NotifyQueue】Exception:", e);
        }
    }
}
