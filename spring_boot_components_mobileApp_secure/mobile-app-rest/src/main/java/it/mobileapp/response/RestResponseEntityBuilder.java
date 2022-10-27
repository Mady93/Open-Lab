/******************************************************************************
 * Copyright © Alessandro Zoia                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the “Software”), *
 * to deal in the Software without restriction,including without limitation   *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES                       *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. *
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS                         *
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION  *
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR                    *
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE        *
 * SOFTWARE.                                                                  *
 ******************************************************************************/

package it.mobileapp.response;

import it.mobileapp.service.exceptions.ServiceException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

/**
 * Response entity builder
 * @author Alessandro Zoia
 * @version 1.0.0
 */
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
