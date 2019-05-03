package com.cn.ben.cms.controller;

import com.cn.ben.api.cms.model.dto.DataGrid;
import com.cn.ben.api.cms.model.dto.notify.CmsNotifyLogDto;
import com.cn.ben.api.cms.service.ICmsNotifyLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>消息管理制器</p>
 *
 * @author Chen Nan
 * @date 2019/3/11.
 */
@RestController
@RequestMapping(value = "/notify/log")
@Slf4j
public class NotifyLogController {

    @Reference
    private ICmsNotifyLogService cmsNotifyLogService;

    @GetMapping("/page")
    public Object page(@ModelAttribute CmsNotifyLogDto req) {
        log.info("【NotifyLog】page start：" + req);
        DataGrid rsp = cmsNotifyLogService.listPage(req);
        log.info("【NotifyLog】page success");
        return rsp;
    }
}
