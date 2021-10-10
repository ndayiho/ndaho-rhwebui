package com.ndaho.rhwebui.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.ndaho.rhwebui")
public class CustomProperties {
    private String ApiUrl;
}
