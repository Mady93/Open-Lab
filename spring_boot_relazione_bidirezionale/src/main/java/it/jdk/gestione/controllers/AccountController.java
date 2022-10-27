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

import it.jdk.gestione.dto.AccountDTO;
import it.jdk.gestione.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	private final AccountService service;

	@Autowired
	public AccountController(AccountService service) {
		this.service = service;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO dto) {
		AccountDTO dtoResponse = service.create(dto);
		return new ResponseEntity<AccountDTO>(dtoResponse, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO dto) throws Exception {
		AccountDTO dtoResponse = service.update(dto);
		return new ResponseEntity<AccountDTO>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{email}")
	public ResponseEntity<AccountDTO> delete(@PathVariable(value = "email") String email) throws Exception {
		service.delete(email);
		return new ResponseEntity<AccountDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/find/{email}")
	public ResponseEntity<AccountDTO> find(@PathVariable(value = "email") String email) throws Exception {
		AccountDTO dtoResponse = service.find(email);
		return new ResponseEntity<AccountDTO>(dtoResponse,HttpStatus.FOUND);	}
}
