package it.jdk.projectPostman.controllers;

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

import it.jdk.projectPostman.models.CruiseDTO;
import it.jdk.projectPostman.models.ShipDTO;
import it.jdk.projectPostman.services.CruiseService;
import it.jdk.projectPostman.services.ShipService;

@RestController
@RequestMapping(value="/cruise")
public class CruiseController {


	private final CruiseService service;

	@Autowired
	public CruiseController(CruiseService service) {
		this.service = service;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CruiseDTO> create(@RequestBody CruiseDTO dto) throws Exception {
		CruiseDTO dtoResponse = service.create(dto);
		return new ResponseEntity<CruiseDTO>(dtoResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CruiseDTO> update(@RequestBody CruiseDTO dto) throws Exception {
		CruiseDTO dtoResponse = service.update(dto);
		return new ResponseEntity<CruiseDTO>(dtoResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<CruiseDTO> delete(@PathVariable(value = "id") Integer id) throws Exception {
		service.delete(id);
		return new ResponseEntity<CruiseDTO>(HttpStatus.NO_CONTENT);
	}
		
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<CruiseDTO> find(@PathVariable(value = "id") Integer id) throws Exception {
		CruiseDTO dtoResponse = service.getCruiseById(id);
		return new ResponseEntity<CruiseDTO>(dtoResponse,HttpStatus.OK);	
		}
	
	                                           //PERSONALIZZATO
	@GetMapping(value = "/find/name/id}")
	public ResponseEntity<CruiseDTO> find(@PathVariable(value = "name") String name,@PathVariable(value = "id") Integer id) throws Exception {
		CruiseDTO dtoResponse = service.findByNameAndId(name, id);
		return new ResponseEntity<CruiseDTO>(dtoResponse,HttpStatus.OK);	
	}
	
}

