package com.cn.ben.dal.mapper;

import com.cn.ben.api.cms.model.dto.notify.CmsNotifyRecordDto;
import com.cn.ben.api.cms.model.vo.notify.CmsNotifyRecordVo;
import com.cn.ben.api.model.po.NotifyRecord;

import java.util.List;

/**
 * @author Chen Nan
 */
public interface NotifyRecordMapper extends BaseMapper<NotifyRecord, String> {
    /**
     * CMS获取通知记录列表
     *
     * @param req 请求参数
     * @return 通知记录列表
     */
    List<CmsNotifyRecordVo> cmsListPage(CmsNotifyRecordDto req);
}