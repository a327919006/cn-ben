package com.cn.ben.api.model.dto;

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
     * 获取状态为 0待通知 2已通知(业务方处理失败) 3Http请求异常
     * 通知记录，继续通知，即通知成功与通知失败的不再通知
     */
    private Integer notifying;

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
