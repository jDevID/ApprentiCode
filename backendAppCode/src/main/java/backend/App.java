package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"backend.domain.entity", "backend.security.domain.entity"})
@EnableJpaRepositories(basePackages = {"backend.repository", "backend.security.repository"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
