package it.qm.anagrafica.controllers;

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
import it.qm.anagrafica.filter.FilterAnagraficaDTO;
import it.qm.anagrafica.models.AnagraficaDTO;
import it.qm.anagrafica.page.PageDTO;
import it.qm.anagrafica.rest.RestResponseEntity;
import it.qm.anagrafica.rest.RestResponseEntityBuilder;
import it.qm.anagrafica.rest.ServiceException;
import it.qm.anagrafica.services.AnagraficaService;

@RestController
@RequestMapping(value = "/anagrafica")
public class AnagraficaController {
	private final AnagraficaService service;
	private final RestResponseEntityBuilder restResponseEntityBuilder;

	@Autowired
	public AnagraficaController(AnagraficaService service, RestResponseEntityBuilder restResponseEntityBuilder) {
		this.service = service;
		this.restResponseEntityBuilder = restResponseEntityBuilder;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseEntity<AnagraficaDTO, String, String>> create(@RequestBody AnagraficaDTO dto)
			throws ServiceException {
		return restResponseEntityBuilder.buildData(service.create(dto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/codiceFiscaleGiaAssociato/{codiceFiscale}/{id}")
	public ResponseEntity<Long> codiceFiscaleGiaAssociato(@PathVariable String codiceFiscale, @PathVariable Long id) {
		Optional<Long> dtoResponse = Optional.of(service.codiceFiscaleGiaAssociato(codiceFiscale, id));
		return new ResponseEntity<Long>(dtoResponse.get(), HttpStatus.OK);
	}

	@PutMapping(value = "/update/{codiceFiscale}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseEntity<AnagraficaDTO, String, String>> update(@RequestBody AnagraficaDTO dto,
			@PathVariable String codiceFiscale, @PathVariable Long id) throws ServiceException {
		return restResponseEntityBuilder.buildData(service.update(dto, codiceFiscale, id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseEntity<AnagraficaDTO, String, String>> delete(@RequestBody AnagraficaDTO dto)
			throws ServiceException {
		service.delete(dto);
		return restResponseEntityBuilder.buildData(dto, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/findAnagraficaByCodiceFiscale/{codiceFiscale}")
	public ResponseEntity<AnagraficaDTO> findAnagraficaByCodiceFiscale(
			@PathVariable(value = "codiceFiscale") String codiceFiscale) throws ServiceException {
		Optional<AnagraficaDTO> dtoResponse = service.findAnagraficaByCodiceFiscale(codiceFiscale);
		return new ResponseEntity<AnagraficaDTO>(dtoResponse.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/findPage/{number}/{size}")
	public ResponseEntity<PageDTO> findPage(@PathVariable(value = "number") int number,
			@PathVariable(value = "size") int size) throws ServiceException {
		Optional<PageDTO> dtoResponse = service.findPage(number, size);
		return new ResponseEntity<PageDTO>(dtoResponse.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/findByFilter/{number}/{size}")
	public ResponseEntity<PageDTO> findByFilter(@PathVariable(value = "number") int number,
			@PathVariable(value = "size") int size, @RequestBody FilterAnagraficaDTO filter) throws Exception {
		try {
			Optional<PageDTO> dtoResponse = service.findByFilter(number, size, filter);
			return new ResponseEntity<PageDTO>(dtoResponse.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<PageDTO>(new PageDTO(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/findList/{tipoCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AnagraficaDTO>> findList(@PathVariable(value = "tipoCliente") String tipoCliente)
			throws Exception {
		List<AnagraficaDTO> dtoResponse = service.findList(tipoCliente);
		return new ResponseEntity<List<AnagraficaDTO>>(dtoResponse, HttpStatus.OK);
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<AnagraficaDTO> findById(@PathVariable(value = "id") Long id) throws Exception {
		Optional<AnagraficaDTO> dtoResponse = service.findById(id);
		return new ResponseEntity<AnagraficaDTO>(dtoResponse.get(), HttpStatus.OK);
	}
}
