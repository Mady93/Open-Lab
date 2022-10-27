package it.jdk.projectPostman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "it.jdk.projectPostman.services")
@EnableJpaRepositories(basePackages = "it.jdk.projectPostman.repositories")
@EntityScan(basePackages = "it.jdk.projectPostman.entities")
public class ProjectPostmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPostmanApplication.class, args);
	}

}
