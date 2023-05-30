package com.cclu.project.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ChangCheng Lu
 * @date 2023/5/15 22:46
 */

@Component
@PropertySource("classpath:clientConfig.yml")
@ConfigurationProperties(prefix = "openapi.analysis.top")
@Data
public class ClientConfig {
    @Value("${limit}")
    private int limit;
}
