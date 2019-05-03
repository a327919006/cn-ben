package com.cn.ben.service.impl;

import com.cn.ben.api.cms.model.dto.DataGrid;
import com.cn.ben.api.cms.model.dto.notify.CmsNotifyLogDto;
import com.cn.ben.api.cms.model.vo.notify.CmsNotifyLogVo;
import com.cn.ben.api.cms.service.ICmsNotifyLogService;
import com.cn.ben.api.model.po.NotifyLog;
import com.cn.ben.dal.mapper.NotifyLogMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * CMS通知日志服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class CmsNotifyLogServiceImpl extends BaseServiceImpl<NotifyLogMapper, NotifyLog, Long>
        implements ICmsNotifyLogService {

    @Override
    public DataGrid listPage(CmsNotifyLogDto req) {
        Page<Object> pageInfo = PageHelper.startPage(req.getPage(), req.getRows());
        List<CmsNotifyLogVo> list = mapper.cmsListPage(req);

        DataGrid dataGrid = new DataGrid();
        dataGrid.setRows(list);
        dataGrid.setTotal(pageInfo.getTotal());
        return dataGrid;
    }
}
