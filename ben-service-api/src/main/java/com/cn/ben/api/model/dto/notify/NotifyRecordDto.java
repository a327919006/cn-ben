package com.cn.ben.api.model.dto.notify;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 */
@Getter
@Setter
@ToString
public class NotifyRecordDto implements Serializable {
    /**
     * 获取状态为【0待通知 2已通知(业务方处理失败) 3Http请求异常】的通知记录
     */
    private Integer notifying;

    /**
     * 获取状态为【2已通知(业务方处理失败) 3Http请求异常】的通知记录
     */
    private Integer notifyingExceptWait;

    /**
     * 创建时间起
     */
    private String createStartTime;

    /**
     * 创建时间止
     */
    private String createEndTime;

    /**
     * 分页页码
     */
    private Integer pageNum;
    /**
     * 分页数量
     */
    private Integer pageSize;
    /**
     * 是否需要计算总数
     */
    private Boolean count;
    /**
     * 排序
     */
    private String orderBy;
}
