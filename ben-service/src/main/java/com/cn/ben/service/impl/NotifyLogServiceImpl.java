package com.cn.ben.service.impl;

import com.cn.ben.api.model.dto.notify.NotifyTask;
import com.cn.ben.api.model.po.NotifyLog;
import com.cn.ben.api.service.INotifyLogService;
import com.cn.ben.dal.mapper.NotifyLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * 通知日志服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class NotifyLogServiceImpl extends BaseServiceImpl<NotifyLogMapper, NotifyLog, Long>
        implements INotifyLogService {

    @Override
    public void insertNotifyLog(NotifyTask notifyTask, Integer httpStatus, String httpResponse, String exception) {
        NotifyLog notifyLog = new NotifyLog();
        BeanUtils.copyProperties(notifyTask, notifyLog);
        notifyLog.setNotifyId(notifyTask.getId());
        notifyLog.setHttpStatus(httpStatus);
        notifyLog.setHttpResponse(httpResponse);
        notifyLog.setHttpException(exception);
        notifyLog.setCreateTime(LocalDateTime.now());
        mapper.insertSelective(notifyLog);
    }

    @Override
    public void insertNotifyLog(NotifyTask notifyTask, Integer httpStatus, String httpResponse) {
        insertNotifyLog(notifyTask, httpStatus, httpResponse, null);
    }

    @Override
    public void insertNotifyLog(NotifyTask notifyTask, Integer httpStatus) {
        insertNotifyLog(notifyTask, httpStatus, null, null);
    }

    @Override
    public void insertNotifyLog(NotifyTask notifyTask, String exception) {
        insertNotifyLog(notifyTask, null, null, exception);
    }
}
