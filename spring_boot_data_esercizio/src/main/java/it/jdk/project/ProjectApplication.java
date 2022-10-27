package it.jdk.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
/*
@Configuration
@PropertySource(
        ignoreResourceNotFound = false,
        value = "classpath:mobileapp-repo-contract-impl.properties")
@ComponentScan({"it.mobileapp.impl.repository"})
@EnableJpaRepositories({"it.mobileapp.impl.repository.spring.jpa"})
@EntityScan({"it.jdk.project.entities"})
*/
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
