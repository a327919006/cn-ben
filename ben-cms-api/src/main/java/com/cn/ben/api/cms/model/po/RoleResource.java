package com.cn.ben.api.cms.model.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RoleResource implements Serializable {
    private String roleResourceId;

    private String roleId;

    private String resourceId;

    private Date createTime;

    private Date updateTime;
}