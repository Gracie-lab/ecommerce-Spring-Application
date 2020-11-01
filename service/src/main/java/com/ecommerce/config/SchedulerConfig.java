package com.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
    public class SchedulerConfig implements SchedulingConfigurer {

    @Override
        public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
            ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        int POOL_SIZE = 10;
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
            threadPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
            threadPoolTaskScheduler.initialize();

            scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
        }
    }

