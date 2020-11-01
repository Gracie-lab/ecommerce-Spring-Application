/*
 Copyright (c) 2020. Semicolon Africa
 312 Herbert Macaulay Way, Yaba, Lagos.

 Project Name: lamp
 Class Name: com.lamp.config.Beans
 File Name: Beans.java
 File Path: /home/scv2003/IdeaProjects/lampOnboarding/service/src/main/java/com/lamp/config/Beans.java
 @author:  scv2003
 Last Modified: 14/05/2020, 3:55 AM.

 The contents of this file and project are not available to the public.


 */

package com.ecommerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class Beans {



        @Bean
    //@Primary
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())

                .registerModule(new JavaTimeModule());
    }

    @Bean("asyncHandler")
    public TaskExecutor getAsyncTaskExecutor(){

            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(20);
            executor.setMaxPoolSize(2000);
            executor.setWaitForTasksToCompleteOnShutdown(true);
            executor.setThreadNamePrefix("Async-");
            return executor;
    }



}
