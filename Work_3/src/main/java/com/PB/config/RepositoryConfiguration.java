package com.PB.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class RepositoryConfiguration {
    @Bean
    AtomicInteger atomicIntegerAutoId(@Value("#{ ${application.auto-id.start:0} / 10}")
                                              Integer startId) {
        return new AtomicInteger(startId);
    }
}