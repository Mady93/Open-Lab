package it.qm.anagrafica.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

	@Bean
	public RestResponseEntityBuilder controllerBuilder() {
		return new RestResponseEntityBuilder();
	}
}
