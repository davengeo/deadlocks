package org.davengeo.deadlocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class DeadlocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeadlocksApplication.class, args);
    }

    @Bean
    TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(100);
        threadPoolTaskExecutor.setThreadNamePrefix("deadlock*");
        return threadPoolTaskExecutor;
    }
}
