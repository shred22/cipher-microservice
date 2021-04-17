package com.spboot.cipher.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "encryption.spec")
@Getter
@Setter
public class CipherConfigurationProperties {
    private String algo;
    private String transformation;
    private int keyLength;
}
