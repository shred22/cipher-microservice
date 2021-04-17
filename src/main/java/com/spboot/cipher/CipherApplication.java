package com.spboot.cipher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CipherApplication {
    public static void main(String[] args) {
        SpringApplication.run(CipherApplication.class, args);
    }

}
