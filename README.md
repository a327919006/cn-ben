<p align="center">
<img src="https://www.showdoc.cc/server/api/common/visitfile/sign/9f91a0d4789a2837f86a697ec71ff631?showdoc=.jpg" ></img>
</p>

<p align="center">
    <a target="_blank" href="https://search.maven.org/search?q=g:%22com.gitee.nuliing%22%20AND%20a:%22ben-api%22">
        <img src="https://img.shields.io/maven-central/v/com.gitee.nuliing/ben-api.svg?label=Maven%20Central" ></img>
    </a>
    <a target="_blank" href="https://www.apache.org/licenses/LICENSE-2.0.html">
        <img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" ></img>
    </a>
    <a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
        <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
    </a>
    <a target="_blank" href="https://www.codacy.com/app/a327919006/cn-ben?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=a327919006/cn-ben&amp;utm_campaign=Badge_Grade">
        <img src="https://api.codacy.com/project/badge/Grade/11cbcaba03744805a679f8f47c298dc1"/>
    </a>
</p>

------------

#### 介绍

**BEN**（best-effort-notify）是**基于最大努力通知**的分布式事务解决方案。

# [中文文档](https://www.showdoc.cc/cnben "中文文档")
- 中文文档地址：[https://www.showdoc.cc/cnben](https://www.showdoc.cc/cnben "https://www.showdoc.cc/cnben")

------------

#### Maven模块描述

| 模块名称 | 描述 |
| --- | --- |
| ben-api | 提供业务系统使用的Ben实体类等 |
| ben-service-api | 基础通知服务接口、系统工具类、实体类封装 |
| ben-service | 通知服务接口实现、通知恢复子系统、通知监控子系统、通知任务处理器 |
| ben-cms-api | 通知管理子系统服务接口、实体类封装 |
| ben-cms | 通知管理子系统，提供通知记录、通知日志的管理后台 |
| ben-dal | 数据库访问层： sql语句|

------------

### 业务系统对接BEN
详细对接说明请查看《[快速入门](https://www.showdoc.cc/cnben?page_id=2039690399130400 "快速入门")》和《[对接示例说明](https://www.showdoc.cc/cnben?page_id=2039691528425578 "对接示例说明")》

#### maven依赖
在业务系统的pom文件中引入ben-api最新版本依赖：
```
# 中央仓库： https://search.maven.org/search?q=ben-api
<dependency>
  <groupId>com.gitee.nuliing</groupId>
  <artifactId>ben-api</artifactId>
  <version>${最新稳定版本}</version>
</dependency>
```

#### 发送通知
步骤：
- 构造通知参数**BenNotify**
- 往消息队列发送通知对象（队列名：**notify.queue**，已定义为静态变量**BenNotify.QUEUE**）

#### 更多分布式事务框架

| 框架 | 描述 | 状态 |
| --- | --- | --- |
| [cn-rmq](https://gitee.com/NuLiing/reliable-message "cn-rmq") | 基于可靠消息的最终一致性方案 | 已发布 |
| [cn-ben](https://gitee.com/NuLiing/cn-ben "cn-ben") | 最大努力通知方案 | 已发布 |
