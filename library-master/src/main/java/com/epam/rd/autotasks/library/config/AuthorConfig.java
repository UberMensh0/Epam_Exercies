package com.epam.rd.autotasks.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.epam.rd.autotasks.library.Author;

@Configuration
public class AuthorConfig {
    @Bean
    public Author author() {
        return new Author("John Doe");
    }
}
