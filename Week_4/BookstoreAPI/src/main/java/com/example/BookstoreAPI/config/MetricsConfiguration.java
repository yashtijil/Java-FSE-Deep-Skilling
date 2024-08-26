package com.example.BookstoreAPI.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {

    @Bean
    public MeterBinder customMetrics() {
        return (MeterRegistry registry) -> {
            registry.counter("bookstore.custom.metric", "type", "booksSold");
        };
    }
}