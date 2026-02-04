package com.exlog.exlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ExLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExLogApplication.class, args);
    }

}
