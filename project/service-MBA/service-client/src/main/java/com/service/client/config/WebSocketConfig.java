package com.service.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Date: 2021/2/8
 *
 * @author WX964987
 */
@Configuration
public class WebSocketConfig {
    /**
     * 扫描并注册带有@ServerEndpoint注解的所有服务端
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
