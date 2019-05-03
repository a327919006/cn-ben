package com.cn.ben.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import com.cn.ben.api.cms.model.dto.DataGrid;
import com.cn.ben.api.cms.model.dto.notify.CmsNotifyRecordDto;
import com.cn.ben.api.cms.model.vo.notify.CmsNotifyRecordVo;
import com.cn.ben.api.cms.service.ICmsNotifyRecordService;
import com.cn.ben.api.enums.NotifyStatusEnum;
import com.cn.ben.api.exceptions.CheckException;
import com.cn.ben.api.model.Constants;
import com.cn.ben.api.model.dto.NotifyTask;
import com.cn.ben.api.model.dto.RspBase;
import com.cn.ben.api.model.po.NotifyRecord;
import com.cn.ben.api.service.INotifyLogService;
import com.cn.ben.api.service.INotifyRecordService;
import com.cn.ben.api.utils.BenUtils;
import com.cn.ben.dal.mapper.NotifyRecordMapper;
import com.cn.ben.service.mq.NotifyTaskHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CMS通知记录服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class CmsNotifyRecordServiceImpl extends BaseServiceImpl<NotifyRecordMapper, NotifyRecord, String>
        implements ICmsNotifyRecordService {

    @Reference
    private INotifyRecordService notifyRecordService;
    @Reference
    private INotifyLogService notifyLogService;
    @Autowired
    private NotifyTaskHandler notifyTaskHandler;

    @Override
    public DataGrid listPage(CmsNotifyRecordDto req) {
        Page<Object> pageInfo = PageHelper.startPage(req.getPage(), req.getRows());
        List<CmsNotifyRecordVo> list = mapper.cmsListPage(req);

        DataGrid dataGrid = new DataGrid();
        dataGrid.setRows(list);
        dataGrid.setTotal(pageInfo.getTotal());
        return dataGrid;
    }

    @Override
    public RspBase notifyAgain(String id) {
        NotifyRecord notifyRecord = notifyRecordService.selectByPrimaryKey(id);
        if (notifyRecord == null) {
            throw new CheckException("通知记录不存在");
        }

        NotifyTask notifyTask = BenUtils.coverToNotifyTask(notifyRecord);
        try {
            log.info("【NotifyTask】开始通知,id={},times={}", notifyTask.getId(), notifyTask.getNotifyTimes());
            HttpResponse response = notifyTaskHandler.sendNotify(notifyTask);

            // 修改任务的通知次数与最近通知时间
            notifyTask.setNotifyTimes((short) (notifyTask.getNotifyTimes() + 1));
            notifyTask.setUpdateTime(LocalDateTime.now());

            // 判断Http请求状态码
            if (response.isOk()) {
                return handleRequestSuccess(response, notifyTask);
            } else {
                return handleRequestFail(response, notifyTask);
            }
        } catch (Exception e) {
            return handleRequestError(e, notifyTask);
        }
    }

    /**
     * 处理通知Http请求成功
     */
    private RspBase handleRequestSuccess(HttpResponse response, NotifyTask notifyTask) {
        String result = response.body();
        // 通知成功标识
        // 1、上层业务系统未指定成功标识，则只需Http状态码为2xx，即通知成功
        // 2、上层业务系统已指定成功标识，则响应内容中需包含成功标识，即通知成功
        boolean successFlag = StrUtil.isBlank(notifyTask.getSuccessFlag())
                || StrUtil.containsAnyIgnoreCase(notifyTask.getSuccessFlag(), result);
        if (successFlag) {
            log.info("【NotifyTask】通知成功,id={},result={}", notifyTask.getId(), result);
            notifyRecordService.updateNotifyStatus(notifyTask, NotifyStatusEnum.SUCCESS);

            notifyLogService.insertNotifyLog(notifyTask, response.getStatus(), result);
            return new RspBase().msg("通知成功");
        } else {
            log.info("【NotifyTask】业务方处理失败,id={},result={}", notifyTask.getId(), result);
            notifyRecordService.updateNotifyStatus(notifyTask, NotifyStatusEnum.BUSINESS_FAIL);

            notifyLogService.insertNotifyLog(notifyTask, response.getStatus(), result);
            return new RspBase().code(Constants.CODE_FAILURE).msg("业务方处理失败：" + result);
        }
    }

    /**
     * 处理通知Http请求失败
     */
    private RspBase handleRequestFail(HttpResponse response, NotifyTask notifyTask) {
        log.info("【NotifyTask】通知请求失败,id={},code={}", notifyTask.getId(), response.getStatus());
        notifyRecordService.updateNotifyStatus(notifyTask, NotifyStatusEnum.REQUEST_FAIL);

        notifyLogService.insertNotifyLog(notifyTask, response.getStatus());
        return new RspBase().code(Constants.CODE_FAILURE).msg("通知失败, httpStatus=" + response.getStatus());
    }

    /**
     * 处理通知Http请求失败
     */
    private RspBase handleRequestError(Exception e, NotifyTask notifyTask) {
        log.error("【NotifyTask】通知请求异常,id=" + notifyTask.getId() + ":", e);
        notifyRecordService.updateNotifyStatus(notifyTask, NotifyStatusEnum.REQUEST_FAIL);

        notifyLogService.insertNotifyLog(notifyTask, e.getMessage());
        return new RspBase().code(Constants.CODE_FAILURE).msg("通知异常:" + e.getMessage());
    }
}
