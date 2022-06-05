package com.hdumil.aiwriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})  // 删除spring自带的的文件上传配置
@ComponentScan
@EnableTransactionManagement
@MapperScan(value = ("com.hdumil.aiwriter.back.mapper"))
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);  // 用于使用独立tomcat服务器
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
