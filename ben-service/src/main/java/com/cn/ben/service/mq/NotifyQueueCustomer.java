package com.cn.ben.service.mq;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.cn.ben.api.model.BenNotify;
import com.cn.ben.api.model.Constants;
import com.cn.ben.api.model.dto.NotifyTask;
import com.cn.ben.api.model.po.NotifyRecord;
import com.cn.ben.api.service.INotifyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private NotifyTaskHandler taskHandler;

    @JmsListener(destination = Constants.QUEUE_NOTIFY)
    public void handleTestMsg(BenNotify msg) {
        log.info("【NotifyQueue】开始处理消息 -> " + msg);

        // 通知记录持久化
        NotifyRecord notifyRecord = insertNotifyRecord(msg);

        // 通知加入延时任务队列
        NotifyTask notifyTask = new NotifyTask();
        BeanUtils.copyProperties(notifyRecord, notifyTask);
        notifyTask.setNotifyHeader(msg.getNotifyHeader());
        notifyTask.setNotifyContent(msg.getNotifyContent());
        notifyTask.setNotifyTimes((short) 0);
        taskHandler.addTask(notifyTask);

        log.info("【NotifyQueue】处理消息成功,notifyRecordId=" + notifyRecord.getId());
    }

    /**
     * 通知消息插入通知记录表
     *
     * @param msg 通知消息
     * @return 通知记录对象
     */
    private NotifyRecord insertNotifyRecord(BenNotify msg) {
        NotifyRecord notifyRecord = new NotifyRecord();
        BeanUtils.copyProperties(msg, notifyRecord);
        notifyRecord.setId(IdUtil.simpleUUID());
        notifyRecord.setNotifyHeader(JSONUtil.toJsonStr(msg.getNotifyHeader()));
        notifyRecord.setNotifyContent(JSONUtil.toJsonStr(msg.getNotifyContent()));
        notifyRecord.setCreateTime(LocalDateTime.now());
        notifyRecord.setUpdateTime(LocalDateTime.now());
        notifyRecordService.insertSelective(notifyRecord);
        return notifyRecord;
    }
}
