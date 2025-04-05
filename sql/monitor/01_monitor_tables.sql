-- 在线用户记录
CREATE TABLE IF NOT EXISTS sys_user_online (
    sessionId VARCHAR(50) NOT NULL COMMENT '用户会话id',
    login_name VARCHAR(50) DEFAULT '' COMMENT '登录账号',
    dept_name VARCHAR(50) DEFAULT '' COMMENT '部门名称',
    ipaddr VARCHAR(128) DEFAULT '' COMMENT '登录IP地址',
    login_location VARCHAR(255) DEFAULT '' COMMENT '登录地点',
    browser VARCHAR(50) DEFAULT '' COMMENT '浏览器类型',
    os VARCHAR(50) DEFAULT '' COMMENT '操作系统',
    status VARCHAR(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
    start_timestamp DATETIME DEFAULT NULL COMMENT 'session创建时间',
    last_access_time DATETIME DEFAULT NULL COMMENT 'session最后访问时间',
    expire_time INT DEFAULT 0 COMMENT '超时时间，单位为分钟',
    PRIMARY KEY (sessionId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='在线用户记录';

-- 定时任务调度表
CREATE TABLE IF NOT EXISTS sys_job (
    job_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '任务ID',
    job_name VARCHAR(64) NOT NULL COMMENT '任务名称',
    job_group VARCHAR(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
    invoke_target VARCHAR(500) NOT NULL COMMENT '调用目标字符串',
    cron_expression VARCHAR(255) DEFAULT '' COMMENT 'cron执行表达式',
    misfire_policy VARCHAR(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    concurrent CHAR(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
    status CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME DEFAULT NULL COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME DEFAULT NULL COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT '' COMMENT '备注信息',
    PRIMARY KEY (job_id, job_name, job_group)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务调度表';

-- 定时任务调度日志表
CREATE TABLE IF NOT EXISTS sys_job_log (
    job_log_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
    job_name VARCHAR(64) NOT NULL COMMENT '任务名称',
    job_group VARCHAR(64) NOT NULL COMMENT '任务组名',
    invoke_target VARCHAR(500) NOT NULL COMMENT '调用目标字符串',
    job_message VARCHAR(500) DEFAULT NULL COMMENT '日志信息',
    status CHAR(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
    exception_info VARCHAR(2000) DEFAULT '' COMMENT '异常信息',
    create_time DATETIME DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (job_log_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务调度日志表';

-- 服务器监控表
CREATE TABLE IF NOT EXISTS sys_server (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    cpu_usage DECIMAL(10,2) DEFAULT NULL COMMENT 'CPU使用率',
    memory_usage DECIMAL(10,2) DEFAULT NULL COMMENT '内存使用率',
    disk_usage DECIMAL(10,2) DEFAULT NULL COMMENT '磁盘使用率',
    create_time DATETIME DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务器监控表'; 