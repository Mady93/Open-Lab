package it.jdk.gestione.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jdk.gestione.dto.MessaggioDTO;
import it.jdk.gestione.services.MessaggioService;

@RestController
@RequestMapping("/messaggio")
public class MessaggioController {

	private final MessaggioService service;

	@Autowired
	public MessaggioController(MessaggioService service) {
		this.service = service;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessaggioDTO> create(@RequestBody MessaggioDTO dto) throws Exception {
		MessaggioDTO dtoResponse = service.create(dto);
		return new ResponseEntity<MessaggioDTO>(dtoResponse, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessaggioDTO> update(@RequestBody MessaggioDTO dto) throws Exception {
		MessaggioDTO dtoResponse = service.update(dto);
		return new ResponseEntity<MessaggioDTO>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<MessaggioDTO> delete(@PathVariable(value = "id") Integer userId) throws Exception {
		service.delete(userId);
		return new ResponseEntity<MessaggioDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<MessaggioDTO> find(@PathVariable(value = "id") Integer userId) throws Exception {
		MessaggioDTO dtoResponse = service.find(userId);
		return new ResponseEntity<MessaggioDTO>(dtoResponse,HttpStatus.FOUND);	}
}

