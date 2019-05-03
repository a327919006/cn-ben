package com.cn.ben.api.cms.service;

import com.cn.ben.api.cms.model.dto.DataGrid;
import com.cn.ben.api.cms.model.dto.notify.CmsNotifyLogDto;
import com.cn.ben.api.model.po.NotifyLog;
import com.cn.ben.api.service.IBaseService;

/**
 * CMS通知日志服务接口
 *
 * @author Chen Nan
 */
public interface ICmsNotifyLogService extends IBaseService<NotifyLog, Long> {

    /**
     * 分页查询
     *
     * @param req 查询条件
     * @return 数据列表
     */
    DataGrid listPage(CmsNotifyLogDto req);
}
