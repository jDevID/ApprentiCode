package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"backend.domain.entity"})
@EnableJpaRepositories(basePackages = "backend.repository")
@ComponentScan(basePackages = {"backend.service", "backend.mapper", "backend.domain.validation", "backend.repository"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
