package com.cn.ben.api.service;

import com.cn.ben.api.enums.NotifyStatusEnum;
import com.cn.ben.api.model.dto.NotifyTask;
import com.cn.ben.api.model.po.NotifyRecord;

/**
 * 通知记录服务接口
 *
 * @author Chen Nan
 */
public interface INotifyRecordService extends IBaseService<NotifyRecord, String> {
    /**
     * 更新通知记录状态
     *
     * @param notifyTask   通知信息
     * @param notifyStatus 状态
     */
    void updateNotifyStatus(NotifyTask notifyTask, NotifyStatusEnum notifyStatus);
}
