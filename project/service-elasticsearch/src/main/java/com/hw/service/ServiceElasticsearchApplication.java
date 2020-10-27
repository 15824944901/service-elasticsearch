package com.hw.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Date: 2020/8/28 11:54
 */
@EnableAsync
@SpringBootApplication
public class ServiceElasticsearchApplication {

    /**
     * 启动类
     *
     * @param args ...
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceElasticsearchApplication.class,args);

    }
}
