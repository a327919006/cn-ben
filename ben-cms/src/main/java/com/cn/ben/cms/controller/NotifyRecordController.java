package com.cn.ben.cms.controller;

import com.cn.ben.api.cms.model.dto.DataGrid;
import com.cn.ben.api.cms.model.dto.notify.CmsNotifyRecordDto;
import com.cn.ben.api.cms.model.vo.notify.CmsNotifyRecordVo;
import com.cn.ben.api.cms.service.ICmsNotifyRecordService;
import com.cn.ben.api.model.Constants;
import com.cn.ben.api.model.dto.RspBase;
import com.cn.ben.api.model.po.NotifyRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>消息管理制器</p>
 *
 * @author Chen Nan
 * @date 2019/3/11.
 */
@RestController
@RequestMapping(value = "/notify/record")
@Slf4j
public class NotifyRecordController {

    @Reference
    private ICmsNotifyRecordService cmsNotifyRecordService;

    @GetMapping("/page")
    public Object page(@ModelAttribute CmsNotifyRecordDto req) {
        log.info("【NotifyRecord】page start：" + req);
        DataGrid rsp = cmsNotifyRecordService.listPage(req);
        log.info("【NotifyRecord】page success");
        return rsp;
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable("id") String id) {
        log.info("【NotifyRecord】get start：" + id);
        NotifyRecord notifyRecord = cmsNotifyRecordService.selectByPrimaryKey(id);
        RspBase rspBase = new RspBase();
        if (notifyRecord != null) {
            CmsNotifyRecordVo notifyRecordVo = new CmsNotifyRecordVo();
            BeanUtils.copyProperties(notifyRecord, notifyRecordVo);
            rspBase.setData(notifyRecordVo);
            log.info("【NotifyRecord】get success：" + id);
        } else {
            log.info("【NotifyRecord】get fail, not exist：" + id);
            rspBase.code(Constants.CODE_FAILURE).msg("通知记录不存在或已删除");
        }
        return rspBase;
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") String id) {
        log.info("【NotifyRecord】delete start：" + id);
        cmsNotifyRecordService.deleteByPrimaryKey(id);
        RspBase rspBase = new RspBase();
        log.info("【NotifyRecord】delete success：" + id);
        return rspBase;
    }

    @PostMapping("/{id}/again")
    public Object resend(@PathVariable("id") String id) {
        log.info("【NotifyRecord】again start：" + id);
        RspBase rspBase = cmsNotifyRecordService.notifyAgain(id);
        log.info("【NotifyRecord】again success:" + id);
        return rspBase;
    }
}
