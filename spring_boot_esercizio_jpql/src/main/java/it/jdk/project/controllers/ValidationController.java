package it.jdk.project.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jdk.project.dto.ValidationDTO;
import it.jdk.project.services.ValidationService;

@RestController
@RequestMapping(value="/validation")
public class ValidationController {

final ValidationService service;
	
	@Autowired
	public ValidationController(ValidationService service) {
		this.service = service;
	}
	
	
	
	@PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationDTO> create(@RequestBody ValidationDTO validationDTO){
		ValidationDTO response =  service.validateUser(validationDTO);
        return new ResponseEntity<ValidationDTO>(response, HttpStatus.CREATED);
    }
	
	
	
	
	
	@GetMapping(value = "/getValidation/{id}")
    public ResponseEntity<ValidationDTO> getValidation(@PathVariable Integer id) {
        Optional<ValidationDTO> response =  service.getValidationById(id);
        return new ResponseEntity<ValidationDTO>(response.get(), HttpStatus.OK);
    }
	
	
	
	
	
    @GetMapping(value = "/getValidationByIdUser/{id}")
    public ResponseEntity<ValidationDTO>getValidationByIdUser(@PathVariable Integer id) {
        Optional<ValidationDTO> response =  service.getValidationByIdUser(id);
        return new ResponseEntity<ValidationDTO>(response.get(), HttpStatus.OK);    
        }
    
}
