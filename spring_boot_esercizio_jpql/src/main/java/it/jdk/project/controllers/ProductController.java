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
import it.jdk.project.dto.ProductDTO;
import it.jdk.project.services.ProductService;

@RestController
@RequestMapping(value="/product")
public class ProductController {

final ProductService service;
	
	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
		ProductDTO dtoResponse = service.create(dto);
		return new ResponseEntity<ProductDTO>(dtoResponse, HttpStatus.CREATED);
    }
	
	
	
	
	@PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto) throws Exception{
		ProductDTO dtoResponse = service.update(dto);
		return new ResponseEntity<ProductDTO>(dtoResponse, HttpStatus.OK);	
}
	
	
	
	@DeleteMapping(value="/delete")
    public ResponseEntity<ProductDTO> delete(@RequestBody ProductDTO productDTO) {
        service.delete(productDTO);
		return new ResponseEntity<ProductDTO>(HttpStatus.OK);	
    }
	
	
	
	@GetMapping(value="/list")
    public ResponseEntity<List<ProductDTO>> findProducts() {
		Optional<List<ProductDTO>> dtoResponse = service.list();
		return new ResponseEntity<List<ProductDTO>>(dtoResponse.get(), HttpStatus.OK);    
		}
	
	
	
	@GetMapping(value="/findById/{productId}")
    public ResponseEntity<ProductDTO> findById(@PathVariable(value="productId") Integer productId) {
		Optional<ProductDTO> dtoResponse = service.findById(productId);
		return new ResponseEntity<ProductDTO>(dtoResponse.get(), HttpStatus.OK);    
		}
	
	
	
	@GetMapping(value="/findByName/{name}")
    public ResponseEntity<ProductDTO> findByName(@PathVariable(value="name") String name) {
		Optional<ProductDTO> dtoResponse = service.findByName(name);
		return new ResponseEntity<ProductDTO>(dtoResponse.get(), HttpStatus.OK);    
		}
	
	@GetMapping(value="/seEsisteSimConProductAttivi/{id}")
    public ResponseEntity<Optional<Boolean>> seEsisteSimConProductAttivi(@PathVariable(value="id") Integer id) {
		Optional<Boolean> dtoResponse = Optional.of(service.seEsisteSimConProductAttivi(id));
		return new ResponseEntity<Optional<Boolean>>(dtoResponse,HttpStatus.OK);    
		}
}
