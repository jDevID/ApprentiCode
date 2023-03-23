package dev.id.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"dev.id.backend.data.entities"})
@EnableJpaRepositories(basePackages = "dev.id.backend.data.repositories")
@ComponentScan(basePackages = {"dev.id.backend.logic.services", "dev.id.backend.logic.mappers", "dev.id.backend.data.validation", "dev.id.backend.data.repositories"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
