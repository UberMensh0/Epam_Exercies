package com.epam.rd.autotasks.library.config;

import com.epam.rd.autotasks.library.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {


    @Bean
    public Book ebook() {
        return new Book(
                "The Adventures of Spring (E-book Edition)",
                author(),
                publisher(),
                "E-book");
    }



    @Bean
    public Book printBook() {
        return new Book(
                "The Adventures of Spring (Print Edition)",
                author(),
                publisher(),
                "Print");
    }


    @Bean
    public Publisher publisher() {
        return new Publisher(
                "Spring Publishing House",
                "123 Spring St.",
                2000);
    }
    @Bean
    public Author author() {
        return new Author("John Doe");
    }


}
