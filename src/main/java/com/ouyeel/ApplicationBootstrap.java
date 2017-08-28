package com.ouyeel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * 代码生成器启动类
 *
 * @author dujiayong
 * @create 2017-08-28 16:45
 */
@SpringBootApplication(scanBasePackages = {"com.ouyeel"})
public class ApplicationBootstrap extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationBootstrap.class);
    }
}
