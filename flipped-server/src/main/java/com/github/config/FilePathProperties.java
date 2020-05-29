package com.github.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "file-path")
@Component
public class FilePathProperties {
    private String excel;
    private String image;
}
