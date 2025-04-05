package com.mis.common.constant;

public interface Constants {
    /**
     * 成功标记
     */
    Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    Integer FAIL = 500;

    /**
     * 删除
     */
    String STATUS_DEL = "1";

    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 禁用
     */
    String STATUS_DISABLE = "1";

    /**
     * 超级管理员
     */
    String SUPER_ADMIN = "admin";

    /**
     * 当前页码
     */
    String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    String PAGE_SIZE = "pageSize";

    /**
     * 排序字段
     */
    String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序方向 "desc" 或者 "asc"
     */
    String IS_ASC = "isAsc";

    /**
     * 验证码有效期（分钟）
     */
    Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    String JWT_USERNAME = "sub";

    /**
     * 用户头像
     */
    String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    String JWT_CREATED = "created";

    /**
     * 验证码 redis key
     */
    String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 防重提交 redis key
     */
    String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 登录账户密码错误次数 redis key
     */
    String PWD_ERR_CNT_KEY = "pwd_err_cnt:";
} 