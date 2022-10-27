package it.jdk.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.jdk.project.services.NoleggioService;

@RestController
@RequestMapping(value="/noleggio")
public class NoleggioController {

	private final NoleggioService service;

	@Autowired
	public NoleggioController(NoleggioService service) {
		this.service = service;
	}
}
