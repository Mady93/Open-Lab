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

import it.mobileapp.dmodel.BusinessUserPageDTO;
import it.mobileapp.response.RestResponseEntity;
import it.mobileapp.response.RestResponseEntityBuilder;
import it.mobileapp.dmodel.BusinessUserFilterDTO;
import it.mobileapp.service.contract.IBusinessUserService;
import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The business user controller.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
@RestController
@RequestMapping("/user")
public class BusinessUserController {

    private final IBusinessUserService userService;
    private final RestResponseEntityBuilder restResponseEntityBuilder;

    /**
     * Constructor
     * @param userService Business user service contract dependency
     * @param restResponseEntityBuilder Rest response builder dependency
     */
    @Autowired
    public BusinessUserController(IBusinessUserService userService, RestResponseEntityBuilder restResponseEntityBuilder) {
        this.userService = userService;
        this.restResponseEntityBuilder = restResponseEntityBuilder;
    }

    /**
     * Create a new user using JSON data.
     *
     * @param businessUserDTO DTO info
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<BusinessUserDTO,String,String>> create(@RequestBody BusinessUserDTO businessUserDTO) throws ServiceException {
        return restResponseEntityBuilder.buildData(userService.createUser(businessUserDTO), HttpStatus.CREATED);
    }

    /**
     * Update a user using JSON data.
     *
     * @param businessUserDTO DTO info
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<BusinessUserDTO,String,String>> update(@RequestBody BusinessUserDTO businessUserDTO) throws ServiceException {
        return restResponseEntityBuilder.buildData(userService.updateUser(businessUserDTO), HttpStatus.OK);
    }

    /**
     * Delete a user using JSON data.
     *
     * @param businessUserDTO DTO info
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<BusinessUserDTO,String,String>> delete(@RequestBody BusinessUserDTO businessUserDTO) throws ServiceException {
        userService.deleteUser(businessUserDTO);
        return restResponseEntityBuilder.buildData(businessUserDTO,HttpStatus.NO_CONTENT);
    }

    /**
     * Find a user by id.
     *
     * @param id User identifier
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<RestResponseEntity<BusinessUserDTO,String, String>> findById(@PathVariable Integer id) throws ServiceException {
        return restResponseEntityBuilder.buildOptional(userService.findById(id),HttpStatus.OK);
    }

    /**
     * Find a page of users.
     *
     * @param start
     * @param max
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @GetMapping(value = "/findUsers/{start}/{max}")
    public ResponseEntity<RestResponseEntity<BusinessUserPageDTO,String, String>> findUsers(@PathVariable Integer start, @PathVariable Integer max) throws ServiceException {
        return restResponseEntityBuilder.buildOptional(userService.findUsers(start, max), HttpStatus.OK);
    }

    /**
     * Find a page of users using a filter.
     *
     * @param start The page
     * @param max Page size
     * @param businessUserFilterDTO DTO filer info
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @GetMapping(value = "/findUsersByFilter/{start}/{max}")
    public ResponseEntity<RestResponseEntity<BusinessUserPageDTO,String, String>> findUsersByFilter(@PathVariable Integer start, @PathVariable Integer max,
                                                                @RequestBody BusinessUserFilterDTO businessUserFilterDTO)  throws ServiceException {
        return restResponseEntityBuilder.buildOptional(userService.findUsersByFilter(start, max, businessUserFilterDTO), HttpStatus.OK);
    }
    
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @GetMapping(value = "/coundByIdAndFiscalCode/{id}/{fiscalCode}")
    public ResponseEntity<RestResponseEntity<BusinessUserPageDTO,String, String>> findUsers(@PathVariable Integer id, @PathVariable String fiscalCode) throws ServiceException {
        return restResponseEntityBuilder.buildOptional(userService.coundByIdAndFiscalCode(id,fiscalCode), HttpStatus.OK);
    }
}
