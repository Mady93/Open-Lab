package it.jdk.project.rest;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponseEntityBuilder {

    /**
     * Build a ResponseEntity using an Optional object
     * @param optional Optional object
     * @param <T> Encapsulated object type
     * @return ResponseEntity with an encapsulated value
     */
    public <T> ResponseEntity<RestResponseEntity<T,String, String>> buildOptional(Optional<T> optional,HttpStatus httpStatus) {
        if (!optional.isEmpty())
            return buildData(optional.get(), httpStatus);
        else
            return new ResponseEntity<>(
                    RestResponseEntity.buildData("Data not found"), HttpStatus.NOT_FOUND);
    }

    /**
     * Build a ResponseEntity using an Exception object
     * @param e Exception object
     * @return ResponseEntity with an encapsulated error data
     */
    public ResponseEntity<RestResponseEntity<Object,String,String>> buildError(ServiceException e) {
        RestResponseEntity<Object,String,String>
                restResponseEntity = RestResponseEntity.buildError(e.getMessage(), e.getCode());
        return new ResponseEntity<>(restResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Build a ResponseEntity using an object
     * @param data Object
     * @param <T> Object type
     * @return ResponseEntity with an encapsulated value
     */
    public <T> ResponseEntity<RestResponseEntity<T,String, String>> buildData(T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(RestResponseEntity.buildData(data), httpStatus);
    }
}
