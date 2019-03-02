package com.github.krystiankowalik.sqlbatchextractor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskExecutorProvider {

    @Bean
    public TaskExecutor myTaskExecutor() {
        TaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        ((ThreadPoolTaskExecutor) taskExecutor).setCorePoolSize(15);
        ((ThreadPoolTaskExecutor) taskExecutor).setMaxPoolSize(75);
        ((ThreadPoolTaskExecutor) taskExecutor).setQueueCapacity(1000);
        return taskExecutor;

    }
}
