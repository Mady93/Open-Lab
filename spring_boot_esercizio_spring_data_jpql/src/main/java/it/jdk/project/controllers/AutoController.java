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

import it.jdk.project.filter_auto.FilterAutoDTO;
import it.jdk.project.models.AutoDTO;
import it.jdk.project.page_auto.PageDTO;
import it.jdk.project.services.AutoService;

@RestController
@RequestMapping(value="/auto")
public class AutoController {
	
private final AutoService service;

@Autowired
public AutoController(AutoService service) {
	this.service = service;
}

@PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<AutoDTO> create(@RequestBody AutoDTO dto) throws Exception {
	AutoDTO dtoResponse = service.create(dto);
	return new ResponseEntity<AutoDTO>(dtoResponse, HttpStatus.CREATED);
}




@GetMapping(value = "/findById/{id}")
public  ResponseEntity<AutoDTO> findById(@PathVariable(value = "id")Long id) throws Exception{
	Optional<AutoDTO> dtoResponse = service.findById(id);
	return new ResponseEntity<AutoDTO>(dtoResponse.get() , HttpStatus.OK);
}




@GetMapping(value = "/findAutoByTarga/{targa}")
public  ResponseEntity<AutoDTO> findUserByFiscalCode(@PathVariable(value = "targa") String targa) throws Exception{
	Optional<AutoDTO> dtoResponse = service.findAutoByTarga(targa);
	return new ResponseEntity<AutoDTO>(dtoResponse.get() , HttpStatus.OK);
}





@GetMapping(value = "/findPage/{number}/{size}")
public ResponseEntity<PageDTO> findPage(@PathVariable(value = "number") int number,
		@PathVariable(value = "size") int size) throws Exception {
	try {
		Optional<PageDTO> dtoResponse = service.findPage(number, size);
		return new ResponseEntity<PageDTO>(dtoResponse.get(), HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<PageDTO>(new PageDTO() , HttpStatus.BAD_REQUEST);
	}
}


	
@GetMapping(value = "/findByFilter/{number}/{size}")
public ResponseEntity<PageDTO> findByFilter(@PathVariable(value = "number") int number , @PathVariable(value = "size") int size, @RequestBody FilterAutoDTO filter) throws Exception{
	try {
	Optional<PageDTO> dtoResponse = service.findByFilter(number, size, filter);
	return new ResponseEntity<PageDTO>(dtoResponse.get() , HttpStatus.OK);	
	} catch (Exception e) {
		return new ResponseEntity<PageDTO>(new PageDTO() , HttpStatus.BAD_REQUEST);
	}
}
	

@GetMapping(value="/findList", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<AutoDTO>> findList() throws Exception {
	List<AutoDTO> dtoResponse = service.findList();
	return new ResponseEntity<List<AutoDTO>>(dtoResponse, HttpStatus.OK);
	}


	

@PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<AutoDTO> update(@RequestBody AutoDTO dto) throws Exception {
	AutoDTO dtoResponse = service.update(dto);
	return new ResponseEntity<AutoDTO>(dtoResponse, HttpStatus.OK);
}







                           // Il primo metodo conta ed è usato nel seecondo metodo
@GetMapping(value = "/seEsisteAutoDiverseCheHannoLaStessaTarga/{targa}/{id}")
public ResponseEntity<Long> seEsisteUtentiDiversiCheHseEsisteAutoDiverseCheHannoLaStessaTargaannoLoStessoFiscalCode(@PathVariable String targa, @PathVariable Long id) {
	Optional<Long> dtoResponse = Optional.of(service.seEsisteAutoDiverseCheHannoLaStessaTarga(targa,id));
	return new ResponseEntity<Long>(dtoResponse.get(),HttpStatus.OK);
}



@PutMapping(value="/updateSeTargaDifferente/{targa}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<AutoDTO> updateSeTargaDifferente(@RequestBody AutoDTO dto, @PathVariable(value = "targa") String targa, @PathVariable(value = "id") Long id) throws Exception {
	AutoDTO dtoResponse = service.updateSeTargaDifferente(dto, targa, id);
	return new ResponseEntity<AutoDTO>(dtoResponse, HttpStatus.OK);
}





@DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<AutoDTO> delete(@RequestBody AutoDTO dto) throws Exception {
                 service.delete(dto);
	return new ResponseEntity<AutoDTO>(HttpStatus.NO_CONTENT);
	}
}
