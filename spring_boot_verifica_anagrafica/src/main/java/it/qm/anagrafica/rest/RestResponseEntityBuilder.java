package it.qm.anagrafica.rest;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponseEntityBuilder {

	public <T> ResponseEntity<RestResponseEntity<T, String, String>> buildOptional(Optional<T> optional,
			HttpStatus httpStatus) {
		if (!optional.isEmpty())
			return buildData(optional.get(), httpStatus);
		else
			return new ResponseEntity<>(RestResponseEntity.buildData("Data not found"), HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<RestResponseEntity<Object, String, String>> buildError(ServiceException e) {
		RestResponseEntity<Object, String, String> restResponseEntity = RestResponseEntity.buildError(e.getMessage(),
				e.getCode());
		return new ResponseEntity<>(restResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public <T> ResponseEntity<RestResponseEntity<T, String, String>> buildData(T data, HttpStatus httpStatus) {
		return new ResponseEntity<>(RestResponseEntity.buildData(data), httpStatus);
	}
}
