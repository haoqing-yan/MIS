package com.mis.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 认证服务
                .route("mis-auth", r -> r.path("/auth/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://mis-auth"))
                // 系统服务
                .route("mis-system", r -> r.path("/system/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://mis-system"))
                // 监控服务
                .route("mis-monitor", r -> r.path("/monitor/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://mis-monitor"))
                .build();
    }
} 