package it.jdk.gestione;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan({"it.jdk.gestione.entities"})
@EnableJpaRepositories(basePackages = "it.jdk.gestione.repository")
public class GestioneApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioneApplication.class, args);
	}

}
