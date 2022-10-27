package it.qm.anagrafica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages =  {"it.qm.anagrafica.repositories"})
public class AnagraficaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnagraficaApplication.class, args);
	}

}
