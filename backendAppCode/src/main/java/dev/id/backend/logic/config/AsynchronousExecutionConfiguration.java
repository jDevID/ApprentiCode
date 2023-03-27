package dev.id.backend.logic.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
/*
    AsynchronousExecutionConfiguration: This configuration class enables asynchronous execution using Spring's @Async annotation and provides a custom thread pool executor.

To use this configuration, you can annotate methods that you want to execute asynchronously with the @Async annotation. For example, in one of your services:

java

@Service
public class MyService {

    @Async("taskExecutor")
    public void asyncMethod() {
        // Perform some long-running task
    }
}

 */



@Configuration
@EnableAsync
public class AsynchronousExecutionConfiguration {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}
