package com.cn.ben.service.service;

import com.cn.ben.api.model.po.NotifyRecord;
import com.cn.ben.api.service.INotifyRecordService;
import com.cn.ben.dal.mapper.NotifyRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * 消息服务实现
 *
 * @author Chen Nan
 * @date 2019/3/11.
 */
@Service
@Slf4j
public class NotifyRecordServiceImpl extends BaseServiceImpl<NotifyRecordMapper, NotifyRecord, String>
        implements INotifyRecordService {

}
