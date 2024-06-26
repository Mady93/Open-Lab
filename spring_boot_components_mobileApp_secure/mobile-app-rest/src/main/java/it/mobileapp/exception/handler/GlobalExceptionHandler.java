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

package it.mobileapp.exception.handler;

import it.mobileapp.response.RestResponseEntity;
import it.mobileapp.response.RestResponseEntityBuilder;
import it.mobileapp.service.exceptions.ServiceException;
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
    public ResponseEntity<RestResponseEntity<Object, String, String>> handleServiceException(ServiceException serviceException) {
        // Qui si fa log dell'eccezione
        return  controllerBuilder.buildError(serviceException);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<RestResponseEntity<Object, String, String>> handleOtherExceptions(RuntimeException runtimeException) {
        // Qui si fa log dell'eccezione
        return  controllerBuilder.buildError(new ServiceException("Unexpected internal error","0000"));
    }
}
