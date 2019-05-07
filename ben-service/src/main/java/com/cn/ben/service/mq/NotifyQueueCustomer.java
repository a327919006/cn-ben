package com.cn.ben.service.mq;

import com.cn.ben.api.model.BenNotify;
import com.cn.ben.api.model.Constants;
import com.cn.ben.api.model.dto.NotifyTask;
import com.cn.ben.api.model.po.NotifyRecord;
import com.cn.ben.api.service.INotifyRecordService;
import com.cn.ben.api.utils.BenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 通知队列消息监听器
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

        // 添加通知记录
        NotifyRecord notifyRecord = notifyRecordService.insertNotifyRecord(msg);

        // 通知加入延时任务队列
        NotifyTask notifyTask = BenUtils.coverToNotifyTask(notifyRecord, msg);
        taskHandler.addTask(notifyTask);

        log.info("【NotifyQueue】处理消息成功,id=" + notifyRecord.getId());

    }
}
