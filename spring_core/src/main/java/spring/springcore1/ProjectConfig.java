package spring.springcore1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//@Configuration

/*@Configuration
@ComponentScan(basePackages={"spring.springcore1"})*/      // togliere commento
public class ProjectConfig {
	
/*@Bean
public Parrot parrot() {
	var p = new Parrot();
	p.setName("Loko");
	return p;
}

@Bean
public String hello() {
	return "hello";
}

@Bean
public int ten() {
	return 10;
}*/
	/*ESERCITAZIONE 3 Aggiungiamo più bean dello stesso tipo e capiamo come riferirci alla particolare istanza.
	
	@Bean
	public Parrot parrot1() {
	var p = new Parrot();
	p.setName("Koko");
	return p;
	}
	@Bean
	public Parrot parrot2() {
	var p = new Parrot();
	p.setName("Pichi");
	return p;
	}
	@Bean
	public Parrot parrot3() {
	var p = new Parrot();
	p.setName("Riki");
	return p;
	}
	*/
	
	/*
	 * ESERCITAZIONE 4 Aggiungiamo più bean dello stesso tipo e capiamo come riferirci alla particolare istanza.
	 */
	

	@Primary
	@Bean
	public B b1() {
	return new B("b1");
	}
	
	@Bean
	public B b2() {
	return new B("b2");
	}
	
}
