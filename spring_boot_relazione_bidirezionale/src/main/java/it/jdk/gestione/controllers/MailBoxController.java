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

import it.jdk.gestione.dto.MailBoxDTO;
import it.jdk.gestione.services.MailBoxService;

@RestController
@RequestMapping("/mailbox")
public class MailBoxController {

	private final MailBoxService service;

	@Autowired
	public MailBoxController(MailBoxService service) {
		this.service = service;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MailBoxDTO> create(@RequestBody MailBoxDTO dto) throws Exception {
		MailBoxDTO dtoResponse = service.create(dto);
		return new ResponseEntity<MailBoxDTO>(dtoResponse, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MailBoxDTO> update(@RequestBody MailBoxDTO dto) throws Exception {
		MailBoxDTO dtoResponse = service.update(dto);
		return new ResponseEntity<MailBoxDTO>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{nome}")
	public ResponseEntity<MailBoxDTO> delete(@PathVariable(value = "nome") String nome) throws Exception {
		service.delete(nome);
		return new ResponseEntity<MailBoxDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/find/{nome}")
	public ResponseEntity<MailBoxDTO> find(@PathVariable(value = "nome") String nome) throws Exception {
		MailBoxDTO dtoResponse = service.find(nome);
		return new ResponseEntity<MailBoxDTO>(dtoResponse,HttpStatus.FOUND);	}
}

