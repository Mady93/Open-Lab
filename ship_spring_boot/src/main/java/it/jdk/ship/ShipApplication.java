package it.jdk.ship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"it.jdk.ship.entities"})
@EnableJpaRepositories(basePackages = "it.jdk.ship.repository")
public class ShipApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipApplication.class, args);
	}

}
