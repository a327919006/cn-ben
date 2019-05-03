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
public class CmsNotifyLogVo implements Serializable {
    private Long id;

    private String notifyId;

    private Integer httpStatus;

    private String httpResponse;

    private String httpException;

    private String businessId;

    private String businessName;

    private LocalDateTime createTime;
}
