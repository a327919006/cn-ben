package com.cn.ben.dal.mapper;

import com.cn.ben.api.cms.model.dto.notify.CmsNotifyLogDto;
import com.cn.ben.api.cms.model.vo.notify.CmsNotifyLogVo;
import com.cn.ben.api.model.po.NotifyLog;

import java.util.List;

/**
 * @author Chen Nan
 */
public interface NotifyLogMapper extends BaseMapper<NotifyLog, Long> {
    /**
     * CMS获取通知日志列表
     *
     * @param req 请求参数
     * @return 通知日志列表
     */
    List<CmsNotifyLogVo> cmsListPage(CmsNotifyLogDto req);
}