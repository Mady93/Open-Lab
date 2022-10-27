package it.jdk.project.controllers;

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

import it.jdk.project.dto.UtenteDTO;
import it.jdk.project.services.UtenteService;

@RestController
@RequestMapping(value="/utente")
public class UtenteController {

	private final UtenteService service;

	@Autowired
	public UtenteController(UtenteService service) {
		this.service = service;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UtenteDTO> create(@RequestBody UtenteDTO dto) {
		UtenteDTO dtoResponse = service.create(dto);
		return new ResponseEntity<UtenteDTO>(dtoResponse, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UtenteDTO> update(@RequestBody UtenteDTO dto) throws Exception {
		UtenteDTO dtoResponse = service.update(dto);
		return new ResponseEntity<UtenteDTO>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<UtenteDTO> delete(@PathVariable(value = "id") Long userId) throws Exception {
		service.delete(userId);
		return new ResponseEntity<UtenteDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<UtenteDTO> find(@PathVariable(value = "id") Long userId) throws Exception {
		UtenteDTO dtoResponse = service.find(userId);
		return new ResponseEntity<UtenteDTO>(dtoResponse,HttpStatus.FOUND);	}
}
