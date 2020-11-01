package com.ecommerce.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@PropertySource("classpath:securityConstants.properties")
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${FRONTEND_ADDRESS}")
    private String frontEndAddress;

    //private final String eldersDashboardUrl = "localhost:8000";
//

    @Value("${SECOND_BACKEND}")
    private String eldersDashboardUrlHeroku;

//    @Value("${ELDER_DEV}")
//    private String eldersDashboardUrldev;
//
//    @Value("${ELDER_PROD}")
//    private String eldersDashboardUrlprod;


    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontEndAddress, eldersDashboardUrlHeroku )

                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}