package it.jpa.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"it.backend.entity"})
@ComponentScan({"it.backend.spring.data"})
@EnableJpaRepositories({"it.backend.spring.data"})
public class TestApplication {
}
