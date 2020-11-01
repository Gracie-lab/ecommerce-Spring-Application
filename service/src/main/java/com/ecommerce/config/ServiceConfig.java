package com.ecommerce.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan(basePackages = {"com.desirehealth.**"})
@EnableAutoConfiguration
@EnableAsync
public class ServiceConfig {
}
