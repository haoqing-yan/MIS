-- AI聊天记录表
CREATE TABLE IF NOT EXISTS ai_chat_history (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    provider VARCHAR(20) NOT NULL COMMENT 'AI提供商（openai/ollama）',
    model VARCHAR(50) NOT NULL COMMENT '使用的模型',
    prompt TEXT NOT NULL COMMENT '用户输入',
    response TEXT NOT NULL COMMENT 'AI响应',
    tokens INT DEFAULT 0 COMMENT '消耗的token数量',
    cost DECIMAL(10,6) DEFAULT 0 COMMENT '消耗的费用（美元）',
    create_time DATETIME DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (id),
    KEY idx_user_id (user_id),
    KEY idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI聊天记录表';

-- AI模型配置表
CREATE TABLE IF NOT EXISTS ai_model_config (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    provider VARCHAR(20) NOT NULL COMMENT 'AI提供商（openai/ollama）',
    model_name VARCHAR(50) NOT NULL COMMENT '模型名称',
    model_version VARCHAR(50) DEFAULT NULL COMMENT '模型版本',
    max_tokens INT DEFAULT 2048 COMMENT '最大token数',
    temperature DECIMAL(3,2) DEFAULT 0.7 COMMENT '温度参数',
    top_p DECIMAL(3,2) DEFAULT 1.0 COMMENT 'top_p参数',
    frequency_penalty DECIMAL(3,2) DEFAULT 0.0 COMMENT '频率惩罚',
    presence_penalty DECIMAL(3,2) DEFAULT 0.0 COMMENT '存在惩罚',
    status CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME DEFAULT NULL COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME DEFAULT NULL COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (id),
    UNIQUE KEY uk_provider_model (provider, model_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI模型配置表';

-- AI使用统计表
CREATE TABLE IF NOT EXISTS ai_usage_stats (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    provider VARCHAR(20) NOT NULL COMMENT 'AI提供商（openai/ollama）',
    model VARCHAR(50) NOT NULL COMMENT '使用的模型',
    total_tokens INT DEFAULT 0 COMMENT '总token数',
    total_cost DECIMAL(10,6) DEFAULT 0 COMMENT '总费用（美元）',
    total_requests INT DEFAULT 0 COMMENT '总请求数',
    stat_date DATE NOT NULL COMMENT '统计日期',
    create_time DATETIME DEFAULT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_provider_model_date (user_id, provider, model, stat_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI使用统计表'; 