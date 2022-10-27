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

package it.mobileapp.controller;

import it.mobileapp.response.RestResponseEntity;
import it.mobileapp.response.RestResponseEntityBuilder;
import it.mobileapp.dmodel.ValidationDTO;
import it.mobileapp.service.contract.IValidationService;
import it.mobileapp.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The validation controller.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
@RestController
@RequestMapping("/validation")
public class ValidationController {

    private final IValidationService validationService;
    private final RestResponseEntityBuilder restResponseEntityBuilder;

    /**
     * Constructor.
     *
     * @param validationService Validation service contract dependency
     * @param restResponseEntityBuilder Rest response builder dependency
     */
    @Autowired
    public ValidationController(IValidationService validationService, RestResponseEntityBuilder restResponseEntityBuilder) {
        this.validationService = validationService;
        this.restResponseEntityBuilder = restResponseEntityBuilder;
    }

    /**
     * Create a new validation using JSON data.
     *
     * @param validationDTO
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<ValidationDTO, String, String>> create(@RequestBody ValidationDTO validationDTO) throws ServiceException {
        validationService.validateUser(validationDTO);
        return restResponseEntityBuilder.buildData(validationDTO, HttpStatus.CREATED);
    }

    /**
     * Get validation info.
     *
     * @param userId User identifier
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<RestResponseEntity<ValidationDTO, String, String>> getValidation(@PathVariable Integer userId) throws ServiceException {
        return restResponseEntityBuilder.buildOptional(validationService.getValidation(userId), HttpStatus.OK);
    }
    
    //#################################################################################################
    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<RestResponseEntity<ValidationDTO, String, String>> getUserValidation(@PathVariable Integer userId) throws ServiceException {
        return restResponseEntityBuilder.buildOptional(validationService.getValidation(userId), HttpStatus.OK);
    }
}
