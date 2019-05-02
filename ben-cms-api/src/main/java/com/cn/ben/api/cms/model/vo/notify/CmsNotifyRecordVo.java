package com.cn.ben.api.cms.model.vo.notify;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/3/15.
 */
@Getter
@Setter
public class CmsNotifyRecordVo implements Serializable {
    private String id;

    private String notifyUrl;

    private Byte notifyMethod;
    private String notifyMethodName;

    private String notifyHeader;

    private Byte notifyParamType;
    private String notifyParamTypeName;

    private Short notifyTimeout;

    private String successFlag;

    private Short notifyTimes;

    private Byte notifyStatus;
    private String notifyStatusName;

    private String businessName;

    private String businessId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String notifyParam;
}
