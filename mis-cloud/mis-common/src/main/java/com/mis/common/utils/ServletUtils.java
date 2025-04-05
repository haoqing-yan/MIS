package com.mis.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet工具类
 */
public class ServletUtils {
    /**
     * 获取RequestAttributes
     */
    public static RequestAttributes getRequestAttributes() {
        return RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes() != null ? ((ServletRequestAttributes) getRequestAttributes()).getRequest() : null;
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes() != null ? ((ServletRequestAttributes) getRequestAttributes()).getResponse() : null;
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest() != null ? getRequest().getSession() : null;
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest() != null ? getRequest().getParameter(name) : null;
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        String param = getParameter(name);
        return param != null ? param : defaultValue;
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return getParameterToInt(name, null);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        String value = getParameter(name);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name) {
        return getParameterToBool(name, null);
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name, Boolean defaultValue) {
        String value = getParameter(name);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    /**
     * 获取客户端IP
     */
    public static String getClientIP() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取请求头
     */
    public static String getHeader(String name) {
        return getRequest().getHeader(name);
    }

    /**
     * 获取请求URI
     */
    public static String getRequestURI() {
        return getRequest().getRequestURI();
    }

    /**
     * 获取请求URL
     */
    public static String getRequestURL() {
        return getRequest().getRequestURL().toString();
    }

    /**
     * 获取请求方法
     */
    public static String getMethod() {
        return getRequest().getMethod();
    }

    /**
     * 获取User-Agent
     */
    public static String getUserAgent() {
        return getHeader("User-Agent");
    }

    /**
     * 输出响应内容
     */
    public static void renderString(String text) {
        try {
            HttpServletResponse response = getResponse();
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 