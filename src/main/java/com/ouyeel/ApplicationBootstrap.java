package com.ouyeel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * 代码生成器启动类
 * 继承SpringBootServletInitailizer类重写configure方法
 * 要以类似于web.xml文件配置的形式来启动Spring应用上下文
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
