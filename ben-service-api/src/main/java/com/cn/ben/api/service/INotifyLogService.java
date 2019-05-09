package com.cn.ben.api.service;

import com.cn.ben.api.model.dto.notify.NotifyTask;
import com.cn.ben.api.model.po.NotifyLog;

/**
 * 通知日志服务接口
 *
 * @author Chen Nan
 */
public interface INotifyLogService extends IBaseService<NotifyLog, Long> {
    /**
     * 添加通知日志
     *
     * @param notifyTask   通知任务信息
     * @param httpStatus   http请求状态
     * @param httpResponse http请求响应
     * @param exception    http请求异常
     */
    void insertNotifyLog(NotifyTask notifyTask, Integer httpStatus, String httpResponse, String exception);

    /**
     * 添加通知日志
     *
     * @param notifyTask   通知任务信息
     * @param httpStatus   http请求状态
     * @param httpResponse http请求响应
     */
    void insertNotifyLog(NotifyTask notifyTask, Integer httpStatus, String httpResponse);

    /**
     * 添加通知日志
     *
     * @param notifyTask 通知任务信息
     * @param httpStatus http请求状态
     */
    void insertNotifyLog(NotifyTask notifyTask, Integer httpStatus);

    /**
     * 添加通知日志
     *
     * @param notifyTask 通知任务信息
     * @param exception  http请求异常
     */
    void insertNotifyLog(NotifyTask notifyTask, String exception);
}
