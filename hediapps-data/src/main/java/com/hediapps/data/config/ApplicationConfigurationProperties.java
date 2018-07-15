package com.hediapps.data.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "appconfig")
public class ApplicationConfigurationProperties {
}
