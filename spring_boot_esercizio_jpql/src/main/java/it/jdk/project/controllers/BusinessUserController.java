package it.jdk.project.controllers;

import java.util.List;
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
import it.jdk.project.dto.BusinessUserDTO;
import it.jdk.project.dto.BusinessUserFilterDTO;
import it.jdk.project.dto.BusinessUserPageDTO;
import it.jdk.project.services.BusinessUserService;

@RestController
@RequestMapping(value="/user")
public class BusinessUserController {
 
	final BusinessUserService service;
	
	@Autowired
	public BusinessUserController(BusinessUserService service) {
		this.service = service;
	}
	
	@PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessUserDTO> create(@RequestBody BusinessUserDTO dto) throws Exception {
		BusinessUserDTO dtoResponse = service.create(dto);
		return new ResponseEntity<BusinessUserDTO>(dtoResponse, HttpStatus.CREATED);
}

	
	
	
	@GetMapping(value = "/findUserByFiscalCode/{fiscalCode}")
	public  ResponseEntity<BusinessUserDTO> findUserByFiscalCode(@PathVariable(value = "fiscalCode") String fiscalCode) throws Exception{
		Optional<BusinessUserDTO> dtoResponse = service.findUserByFiscalCode(fiscalCode);
		return new ResponseEntity<BusinessUserDTO>(dtoResponse.get() , HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping(value = "/findUsers/{page}/{pageSize}")
	public  ResponseEntity <BusinessUserPageDTO> findUsers(@PathVariable(value = "page") int page , @PathVariable(value = "pageSize") int pageSize){
		Optional<BusinessUserPageDTO> dtoResponse = service.findUsers(page, pageSize);
		return new ResponseEntity<BusinessUserPageDTO>(dtoResponse.get() , HttpStatus.OK);	
		}
	
	
		
	@GetMapping(value = "/findByFilter/{start}/{max}")
	public ResponseEntity<BusinessUserPageDTO> findByFilter(@PathVariable(value = "start") int start , @PathVariable(value = "max") int max, @RequestBody BusinessUserFilterDTO filter) {
		Optional<BusinessUserPageDTO> dtoResponse = service.findUserByFilter(start, max, filter);
		return new ResponseEntity<BusinessUserPageDTO>(dtoResponse.get() , HttpStatus.OK);	
		}
		
	
	@GetMapping(value="/findUsersList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BusinessUserDTO>> findUsersList() throws Exception {
		List<BusinessUserDTO> dtoResponse = service.findUsersList();
		return new ResponseEntity<List<BusinessUserDTO>>(dtoResponse, HttpStatus.OK);
		}
	
	
		
	
	@PutMapping(value="/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessUserDTO> updateUser(@RequestBody BusinessUserDTO dto) throws Exception {
		BusinessUserDTO dtoResponse = service.updateUser(dto);
		return new ResponseEntity<BusinessUserDTO>(dtoResponse, HttpStatus.OK);
}
	
	
	
	
	@DeleteMapping(value = "/deleteUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessUserDTO> delete(@RequestBody BusinessUserDTO dto) throws Exception {
	                 service.deleteUser(dto);
		return new ResponseEntity<BusinessUserDTO>(HttpStatus.NO_CONTENT);
}
	
	
	
	                           // Il primo metodo conta ed è usato nel seecondo metodo
	@GetMapping(value = "/seEsisteUtentiDiversiCheHannoLoStessoFiscalCode/{id}/{fiscalCode}")
    public ResponseEntity<Long> seEsisteUtentiDiversiCheHannoLoStessoFiscalCode(@PathVariable Integer id, @PathVariable String fiscalCode) {
		Optional<Long> dtoResponse = Optional.of(service.seEsisteUtentiDiversiCheHannoLoStessoFiscalCode(id, fiscalCode));
		return new ResponseEntity<Long>(dtoResponse.get(),HttpStatus.OK);
	}
	/*
	@PutMapping(value="/updateUserIfFiscalCodeIsDifferent/{id}/{fiscalCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessUserDTO> updateUserIfFiscalCodeIsDifferent(@RequestBody BusinessUserDTO dto, @PathVariable(value = "id") Integer id, @PathVariable(value = "fiscalCode") String fiscalCode) throws Exception {
		BusinessUserDTO dtoResponse = service.updateUserIfFiscalCodeIsDifferent(dto, id, fiscalCode);
		return new ResponseEntity<BusinessUserDTO>(dtoResponse, HttpStatus.OK);
}*/
	
	
}
