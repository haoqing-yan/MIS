package com.mis.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Base64;

/**
 * 安全工具类
 */
public class SecurityUtils {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 密码加密
     */
    public static String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * 密码验证
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * Base64编码
     */
    public static String base64Encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    /**
     * Base64解码
     */
    public static String base64Decode(String text) {
        return new String(Base64.getDecoder().decode(text));
    }

    /**
     * URL安全的Base64编码
     */
    public static String base64UrlEncode(String text) {
        return Base64.getUrlEncoder().encodeToString(text.getBytes());
    }

    /**
     * URL安全的Base64解码
     */
    public static String base64UrlDecode(String text) {
        return new String(Base64.getUrlDecoder().decode(text));
    }
} 