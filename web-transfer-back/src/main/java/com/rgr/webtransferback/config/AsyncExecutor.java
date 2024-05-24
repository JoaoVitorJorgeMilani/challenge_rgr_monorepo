package com.rgr.webtransferback.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncExecutor implements Executor {

    private final ThreadPoolTaskExecutor executor;

    public AsyncExecutor(){
        this.executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize();    
    }

    @Bean(name = "taskExecutor")
    public Executor getTaskExecutor() {
        return executor;
    }

    @Override
    public void execute(Runnable command) {
        executor.execute(command);
    }    
}
