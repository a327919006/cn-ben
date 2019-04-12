package com.cn.ben.service.mq;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.cn.ben.api.model.BenNotify;
import com.cn.ben.api.model.Constants;
import com.cn.ben.api.model.po.NotifyRecord;
import com.cn.ben.api.service.INotifyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>Title:</p>
 * <p>Description:
 * 通知队列消息监听器
 * </p>
 *
 * @author Chen Nan
 */
@Component
@Slf4j
public class NotifyQueueCustomer {

    @Reference
    private INotifyRecordService notifyRecordService;

    @JmsListener(destination = Constants.QUEUE_NOTIFY)
    public void handleTestMsg(BenNotify msg) {
        log.info("【NotifyQueue】开始处理消息 -> " + msg);
        insertNotifyRecord(msg);
        try {
            String result = HttpRequest.post(msg.getNotifyUrl())
                    .addHeaders(msg.getNotifyHeader())
                    .body(msg.getNotifyContent())
                    .timeout(msg.getTimeout())
                    .execute()
                    .body();
            log.info("【NotifyQueue】处理消息成功 -> " + result);
        } catch (Exception e) {
            log.error("【NotifyQueue】Exception:", e);
        }
    }

    private void insertNotifyRecord(BenNotify msg){
        NotifyRecord notifyRecord = new NotifyRecord();
        BeanUtils.copyProperties(msg, notifyRecord);
        notifyRecord.setId(IdUtil.simpleUUID());
        notifyRecord.setNotifyHeader(JSONUtil.toJsonStr(msg.getNotifyHeader()));
        notifyRecord.setCreateTime(LocalDateTime.now());
        notifyRecord.setUpdateTime(LocalDateTime.now());
        notifyRecordService.insertSelective(notifyRecord);
    }
}
