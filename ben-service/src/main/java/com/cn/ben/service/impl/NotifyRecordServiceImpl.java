package com.cn.ben.service.impl;

import com.cn.ben.api.model.po.NotifyRecord;
import com.cn.ben.api.service.INotifyRecordService;
import com.cn.ben.dal.mapper.NotifyRecordMapper;
import com.cn.ben.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * 通知记录服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class NotifyRecordServiceImpl extends BaseServiceImpl<NotifyRecordMapper, NotifyRecord, String>
        implements INotifyRecordService {

}
