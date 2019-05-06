# ben

#### 介绍
ben(best-effort-notify),分布式事务解决方案之最大努力通知方案，开发完成，待发布

------------

#### Maven模块描述

| 模块名称 | 描述 |
| --- | --- |
| ben-api | 提供业务系统使用的Ben实体类等 |
| ben-service-api | 基础通知服务接口、系统工具类、实体类封装 |
| ben-service | 基础通知服务接口实现、通知管理子系统服务接口实现 |
| ben-cms-api | 通知管理子系统服务接口、实体类封装 |
| ben-cms | 通知管理子系统，提供通知记录、通知日志的管理后台 |
| ben-dal | 数据库访问层： sql语句|

------------

#### 更多

| 框架 | 描述 | 状态 |
| --- | --- | --- |
| [cn-rmq](https://gitee.com/NuLiing/reliable-message "cn-rmq") | 基于可靠消息的最终一致性方案 | 已发布 |
| [cn-ben](https://gitee.com/NuLiing/cn-ben "cn-ben") | 最大努力通知方案 | 开发完成，待发布 |
