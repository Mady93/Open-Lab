package it.jdk.project.controllers;

import java.util.List;

import javax.persistence.Tuple;

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

import it.jdk.project.entities.Cliente;
import it.jdk.project.models.AutoDTO;
import it.jdk.project.models.ClienteDTO;
import it.jdk.project.rest.RestResponseEntity;
import it.jdk.project.rest.RestResponseEntityBuilder;
import it.jdk.project.rest.ServiceException;
import it.jdk.project.services.ClientService;

@RestController
@RequestMapping(value="/cliente")
public class ClientController {
	private final ClientService service;
	private final RestResponseEntityBuilder restResponseEntityBuilder;

	@Autowired
	public ClientController(ClientService service, RestResponseEntityBuilder restResponseEntityBuilder) {
		this.service = service;
		this.restResponseEntityBuilder = restResponseEntityBuilder;
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<ClienteDTO,String,String>> create(@RequestBody ClienteDTO dto) throws ServiceException   {
        return restResponseEntityBuilder.buildData(service.create(dto), HttpStatus.CREATED);
    }
	
	
	@GetMapping(value="/findByName/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>> findByName(@PathVariable String nome) throws Exception {
		List<ClienteDTO> dtoResponse = service.findfindByName(nome);
		return new ResponseEntity<List<ClienteDTO>>(dtoResponse, HttpStatus.OK);
		}
	
	/*
	 @GetMapping(value = "/findById/{id}")
    public ResponseEntity<RestResponseEntity<BusinessUserDTO,String, String>> findById(@PathVariable Integer id) throws ServiceException {
        return restResponseEntityBuilder.buildOptional(userService.findById(id),HttpStatus.OK);
    }
    
    
    
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<BusinessUserDTO,String,String>> update(@RequestBody BusinessUserDTO businessUserDTO) throws ServiceException {
        return restResponseEntityBuilder.buildData(userService.updateUser(businessUserDTO), HttpStatus.OK);
    }
	
	
	@DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<BusinessUserDTO,String,String>> delete(@RequestBody BusinessUserDTO businessUserDTO) throws ServiceException {
        userService.deleteUser(businessUserDTO);
        return restResponseEntityBuilder.buildData(businessUserDTO,HttpStatus.NO_CONTENT);
    }
	*/
	
	
}
