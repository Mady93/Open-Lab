package it.qm.anagrafica.rest;

import it.qm.anagrafica.rest.RestResponseEntity;
import it.qm.anagrafica.rest.RestResponseEntityBuilder;
import it.qm.anagrafica.rest.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final RestResponseEntityBuilder controllerBuilder = new RestResponseEntityBuilder();

	@ExceptionHandler(value = { ServiceException.class })
	public ResponseEntity<RestResponseEntity<Object, String, String>> handleServiceException(
			ServiceException serviceException) {
		// Qui si fa log dell'eccezione
		return controllerBuilder.buildError(serviceException);
	}

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<RestResponseEntity<Object, String, String>> handleOtherExceptions(
			RuntimeException runtimeException) {
		// Qui si fa log dell'eccezione
		return controllerBuilder.buildError(new ServiceException("Unexpected internal error", "0000"));
	}
}