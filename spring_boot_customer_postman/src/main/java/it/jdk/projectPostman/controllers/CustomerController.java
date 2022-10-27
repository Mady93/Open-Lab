package it.jdk.projectPostman.controllers;

import java.util.Optional;

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

import it.jdk.projectPostman.models.CustomerDTO;
import it.jdk.projectPostman.models.CustomerPageDTO;
import it.jdk.projectPostman.models.FilterDTO;
import it.jdk.projectPostman.services.CustomerService;

@RestController
@RequestMapping(value="/customer")
public class CustomerController {

	private final CustomerService service;
	
	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO dto) throws Exception {
		CustomerDTO dtoResponse = service.create(dto);
		return new ResponseEntity<CustomerDTO>(dtoResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO dto) throws Exception {
		CustomerDTO dtoResponse = service.update(dto);
		return new ResponseEntity<CustomerDTO>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<CustomerDTO> delete(@PathVariable(value = "id") Integer id) throws Exception {
		service.delete(id);
		return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<CustomerDTO> find(@PathVariable(value = "id") Integer id) throws Exception {
		CustomerDTO dtoResponse = service.findById(id);
		return new ResponseEntity<CustomerDTO>(dtoResponse,HttpStatus.OK);	
		}
		
	@GetMapping(value = "/find/{page}/{pageSize}")
	public  ResponseEntity <CustomerPageDTO> findCustomers(@PathVariable(value = "page") int page , @PathVariable(value = "pageSize") int pageSize) throws Exception {
		Optional<CustomerPageDTO> dtoResponse = service.findCustomers(page, pageSize);
		return new ResponseEntity<CustomerPageDTO>(dtoResponse.get() , HttpStatus.OK);	
		}
		
	@PostMapping(value = "/findCustomerByFilter/{page}/{pageSize}")
	public ResponseEntity<CustomerPageDTO> findCustomer(@PathVariable(value = "page") int page , @PathVariable(value = "pageSize") int pageSize, @RequestBody FilterDTO customerFilterDTO) throws Exception {
		Optional<CustomerPageDTO> dtoResponse = service.findCustomerByFilter(page, pageSize, customerFilterDTO);
		return new ResponseEntity<CustomerPageDTO>(dtoResponse.get() , HttpStatus.OK);	
		}
}


