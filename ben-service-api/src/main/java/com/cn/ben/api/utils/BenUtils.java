package com.cn.ben.api.utils;

import cn.hutool.json.JSONUtil;
import com.cn.ben.api.enums.MethodEnum;
import com.cn.ben.api.enums.ParamTypeEnum;
import com.cn.ben.api.model.BenNotify;
import com.cn.ben.api.model.dto.NotifyTask;
import com.cn.ben.api.model.po.NotifyRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/4/19.
 */
@Slf4j
public class BenUtils {

    /**
     * NotifyRecord转成NotifyTask
     *
     * @param notifyRecord 通知记录
     * @return 通知任务
     */
    @SuppressWarnings("unchecked")
    public static NotifyTask coverToNotifyTask(NotifyRecord notifyRecord) {
        NotifyTask task = new NotifyTask();
        BeanUtils.copyProperties(notifyRecord, task);
        task.setNotifyMethod(MethodEnum.parse(notifyRecord.getNotifyMethod()));
        task.setNotifyHeader(JSONUtil.toBean(notifyRecord.getNotifyHeader(), Map.class));
        task.setNotifyParam(JSONUtil.toBean(notifyRecord.getNotifyParam(), Map.class));
        task.setNotifyParamType(ParamTypeEnum.parse(notifyRecord.getNotifyParamType()));
        return task;
    }

    /**
     * NotifyRecord转成NotifyTask
     *
     * @param notifyRecord 通知记录
     * @param msg          通知消息
     * @return 通知任务
     */
    public static NotifyTask coverToNotifyTask(NotifyRecord notifyRecord, BenNotify msg) {
        NotifyTask notifyTask = new NotifyTask();
        BeanUtils.copyProperties(notifyRecord, notifyTask);
        notifyTask.setNotifyMethod(msg.getNotifyMethod());
        notifyTask.setNotifyHeader(msg.getNotifyHeader());
        notifyTask.setNotifyParam(msg.getNotifyParam());
        notifyTask.setNotifyParamType(msg.getNotifyParamType());
        notifyTask.setNotifyTimes((short) 0);
        return notifyTask;
    }
}
