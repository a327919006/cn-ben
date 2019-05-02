package com.cn.ben.api.cms.service;

import com.cn.ben.api.cms.model.dto.DataGrid;
import com.cn.ben.api.cms.model.dto.notify.CmsNotifyRecordDto;
import com.cn.ben.api.model.dto.RspBase;
import com.cn.ben.api.model.po.NotifyRecord;
import com.cn.ben.api.service.IBaseService;

/**
 * 消息服务接口
 *
 * @author Chen Nan
 */
public interface ICmsNotifyRecordService extends IBaseService<NotifyRecord, String> {

    /**
     * 分页查询
     *
     * @param req 查询条件
     * @return 数据列表
     */
    DataGrid listPage(CmsNotifyRecordDto req);

    /**
     * 再次通知
     *
     * @param id 通知记录ID
     * @return 通知结果
     */
    RspBase notifyAgain(String id);
}
