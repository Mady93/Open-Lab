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

import it.jdk.projectPostman.models.CustomerPageDTO;
import it.jdk.projectPostman.models.ShipDTO;
import it.jdk.projectPostman.services.ShipService;

@RestController
@RequestMapping(value="/ship")
public class ShipController {

	private final ShipService service;

	@Autowired
	public ShipController(ShipService service) {
		this.service = service;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShipDTO> create(@RequestBody ShipDTO dto) throws Exception {
		ShipDTO dtoResponse = service.create(dto);
		return new ResponseEntity<ShipDTO>(dtoResponse, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShipDTO> update(@RequestBody ShipDTO dto) throws Exception {
		ShipDTO dtoResponse = service.update(dto);
		return new ResponseEntity<ShipDTO>(dtoResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ShipDTO> delete(@PathVariable(value = "id") Integer id) throws Exception {
		service.delete(id);
		return new ResponseEntity<ShipDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<ShipDTO> find(@PathVariable(value = "id") Integer id) throws Exception {
		ShipDTO dtoResponse = service.getShipById(id);
		return new ResponseEntity<ShipDTO>(dtoResponse,HttpStatus.OK);	
		}
}