package com.service.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Date: 2021/2/8
 *
 * @author WX964987
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

}
