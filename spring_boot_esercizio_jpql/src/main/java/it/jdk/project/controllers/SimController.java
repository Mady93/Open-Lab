package it.jdk.project.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.jdk.project.dto.SimDTO;
import it.jdk.project.services.SimService;

@RestController
@RequestMapping(value="/sim")
public class SimController {

final SimService service;
	
	@Autowired
	public SimController(SimService service) {
		this.service = service;
	}
	
	
	
	
	
	@PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SimDTO> create(@RequestBody SimDTO dto) throws Exception {
		SimDTO dtoResponse = service.create(dto);
		return new ResponseEntity<SimDTO>(dtoResponse, HttpStatus.CREATED);
}
	
	
	
	
	//sim.setUser(user);
	@PutMapping(value="/attivareSimPerUtente/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimDTO> attivareSimPerUtente(@RequestBody SimDTO simDTO, @PathVariable Integer userId) {
		SimDTO dtoResponse = service.attivareSimPerUtente(simDTO, userId);
		return new ResponseEntity<SimDTO>(dtoResponse, HttpStatus.OK);
	}
	
	
	
	

	@PutMapping(value="/attivareProductAllaSim/{productId}/{simId}")
	    public ResponseEntity<SimDTO> attivareProductAllaSim(@PathVariable Integer simId, @PathVariable Integer productId){
	        SimDTO dtoResponse = service.attivareProductAllaSim(simId, productId);
			return new ResponseEntity<SimDTO>(dtoResponse, HttpStatus.OK);
	 }
	
	
	
	
	
	@PutMapping(value="/disattivareProductAllaSim/{productId}/{simId}")
    public ResponseEntity<SimDTO> disattivareProductAllaSim(@PathVariable Integer simId, @PathVariable Integer productId) {
		 SimDTO dtoResponse = service.disattivareProductAllaSim(simId, productId);
			return new ResponseEntity<SimDTO>(dtoResponse, HttpStatus.OK);
    }
	
	
	
	
	@GetMapping(value = "/findSimByUser/{userId}")
    public ResponseEntity<List<SimDTO>> findSimByUser(@PathVariable Integer userId) {
		Optional<List<SimDTO>> dtoResponse = service.findSimByUser(userId);
		return new ResponseEntity<List<SimDTO>>(dtoResponse.get(), HttpStatus.OK);
    }
	
	
	
	
	
	@GetMapping(value = "/findSimById/{userId}")
    public ResponseEntity<SimDTO> findSimById(@PathVariable Integer userId) {
		Optional<SimDTO> dtoResponse = service.findSimById(userId);
		return new ResponseEntity<SimDTO>(dtoResponse.get(),HttpStatus.OK);
	}
	
	
	
	
	
	
	@GetMapping(value = "/countSimByMsisdnAndImsi/{msisdn}/{imsi}")
    public ResponseEntity<Long> countSimByMsisdnAndImsi(@PathVariable String msisdn, @PathVariable String imsi) {
		Optional<Long> dtoResponse = Optional.of(service.countSimByMsisdnAndImsi(msisdn, imsi));
		return new ResponseEntity<Long>(dtoResponse.get(),HttpStatus.OK);
}
	
	
	
	
	
/*	@GetMapping(value = "/findSimById/{blockSize}")
    public ResponseEntity<List<SimDTO>> simLibere(@PathVariable Integer blockSize) {
		Optional<List<SimDTO>> dtoResponse = service.simLibere(blockSize);
		return new ResponseEntity<List<SimDTO>>(dtoResponse.get(),HttpStatus.OK);
}*/
	
	
	
	
	@GetMapping(value = "/simAlreadyAssigned/{simId}")
    public ResponseEntity<Boolean> countSimByMsisdnAndImsi(@PathVariable Integer simId) {
		Optional<Boolean> dtoResponse = Optional.of(service.simAlreadyAssigned(simId));
		return new ResponseEntity<Boolean>(dtoResponse.get(),HttpStatus.OK);
}
	
	
	
	
	
	@GetMapping(value = "/productAlreadyActivated/{simId}/{productId}")
    public ResponseEntity<Boolean> countSimByMsisdnAndImsi(@PathVariable Integer simId, @PathVariable Integer productId) {
		Optional<Boolean> dtoResponse = Optional.of(service.productAlreadyActivated(simId, productId));
		return new ResponseEntity<Boolean>(dtoResponse.get(),HttpStatus.OK);
}
}
