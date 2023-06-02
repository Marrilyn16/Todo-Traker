package com.example.week8task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Week8TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week8TaskApplication.class, args);

    }

}
