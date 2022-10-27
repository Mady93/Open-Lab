package it.jdk.ship.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jdk.ship.dto.CustomerDTO;
import it.jdk.ship.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
		CustomerDTO dtoResponse = customerService.create(customerDTO);
		return new ResponseEntity<CustomerDTO>(dtoResponse, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
		CustomerDTO dtoResponse = customerService.update(customerDTO);
		return new ResponseEntity<CustomerDTO>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<CustomerDTO> delete(@PathVariable(value = "id") Integer userId) {
		customerService.delete(userId);
		return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
	}
}
