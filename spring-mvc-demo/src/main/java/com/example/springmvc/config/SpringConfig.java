package com.example.springmvc.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig /*extends SpringBootServletInitializer*/ {
//    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 设置启动类，用于独立tomcat运行的入口
        return builder.sources(SpringConfig.class);
    }
}
