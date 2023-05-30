package com.cclu.qqclient.config;

import com.cclu.qqclient.client.QqClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChangCheng Lu
 * @date 2023/5/23 15:28
 */
@Configuration
@ConfigurationProperties("openapi.client.qq")
@Data
@ComponentScan
public class QqClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public QqClient qqClient() {
        return new QqClient(accessKey, secretKey);
    }

}
