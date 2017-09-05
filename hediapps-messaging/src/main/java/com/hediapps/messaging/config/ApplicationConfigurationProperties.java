package com.hediapps.messaging.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "appconfig")
public class ApplicationConfigurationProperties {
}