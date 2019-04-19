package com.cn.ben.service.impl;

import com.cn.ben.api.enums.NotifyStatusEnum;
import com.cn.ben.api.model.dto.NotifyTask;
import com.cn.ben.api.model.po.NotifyRecord;
import com.cn.ben.api.service.INotifyRecordService;
import com.cn.ben.dal.mapper.NotifyRecordMapper;
import com.cn.ben.service.config.TaskHandlerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * 通知记录服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class NotifyRecordServiceImpl extends BaseServiceImpl<NotifyRecordMapper, NotifyRecord, String>
        implements INotifyRecordService {
    @Autowired
    private TaskHandlerConfig config;

    @Override
    public boolean updateNotifyStatus(NotifyTask notifyTask, NotifyStatusEnum notifyStatus) {
        boolean notifyFail = judgeNotifyFail(notifyStatus, notifyTask.getNotifyTimes());

        NotifyRecord notifyRecord = new NotifyRecord();
        notifyRecord.setId(notifyTask.getId());
        if (notifyFail) {
            notifyRecord.setNotifyStatus(NotifyStatusEnum.FAIL.getValue());
            log.info("【NotifyTask】超过通知次数限制，标记为通知失败,id={}", notifyTask.getId());
        } else {
            notifyRecord.setNotifyStatus(notifyStatus.getValue());
        }
        notifyRecord.setNotifyTimes(notifyTask.getNotifyTimes());
        notifyRecord.setUpdateTime(LocalDateTime.now());
        mapper.updateByPrimaryKeySelective(notifyRecord);
        log.info("【NotifyTask】更新通知状态,id={},status={}", notifyRecord.getId(), notifyRecord.getNotifyStatus());
        return notifyFail;
    }

    /**
     * 判断是否通知失败
     *
     * @param notifyStatus    通知状态
     * @param currNotifyTimes 当前通知次数
     * @return 是否失败
     */
    private boolean judgeNotifyFail(NotifyStatusEnum notifyStatus, int currNotifyTimes) {
        if (notifyStatus == NotifyStatusEnum.WAIT
                || notifyStatus == NotifyStatusEnum.SUCCESS) {
            return false;
        }

        return currNotifyTimes >= config.getInterval().size();
    }
}
