package com.cn.ben.api.service;

import com.cn.ben.api.enums.NotifyStatusEnum;
import com.cn.ben.api.model.BenNotify;
import com.cn.ben.api.model.dto.NotifyTask;
import com.cn.ben.api.model.po.NotifyRecord;

/**
 * 通知记录服务接口
 *
 * @author Chen Nan
 */
public interface INotifyRecordService extends IBaseService<NotifyRecord, String> {

    /**
     * 通知消息插入通知记录表
     *
     * @param msg 通知消息
     * @return 通知记录对象
     */
    NotifyRecord insertNotifyRecord(BenNotify msg);

    /**
     * 更新通知记录状态，如果已经到达通知次数上限则会更新为通知失败
     *
     * @param notifyTask   通知信息
     * @param notifyStatus 状态
     * @return 是否已经超过通知次数上限 true是 false否
     */
    boolean updateNotifyStatus(NotifyTask notifyTask, NotifyStatusEnum notifyStatus);

    /**
     * 更新通知记录状态
     *
     * @param notifyRecord 通知记录
     * @param notifyStatus 状态
     */
    void updateNotifyStatus(NotifyRecord notifyRecord, NotifyStatusEnum notifyStatus);
}
