package com.epam.rd.autotasks.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.epam.rd.autotasks.library.Publisher;

@Configuration
public class PublisherConfig {
    @Bean
    public Publisher publisher() {

        return new Publisher(
                "Spring Publishing House",
                "123 Spring St.",
                2000);
    }
}
