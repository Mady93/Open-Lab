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
import it.mobileapp.dmodel.SimDTO;
import it.mobileapp.service.contract.ISimService;
import it.mobileapp.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The product controller.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
@RestController
@RequestMapping("/sim")
public class SimController {

    private final ISimService simService;
    private final RestResponseEntityBuilder restResponseEntityBuilder;

    /**
     * Constructor.
     *
     * @param simService Sim service contract dependency
     * @param restResponseEntityBuilder Rest response builder dependency
     */
    @Autowired
    public SimController(ISimService simService, RestResponseEntityBuilder restResponseEntityBuilder){
        this.simService = simService;
        this.restResponseEntityBuilder = restResponseEntityBuilder;
    }

    /**
     * Create a new sim using JSON data.
     *
     * @param simDTO DTO info
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<SimDTO, String, String>> createSim(@RequestBody SimDTO simDTO) throws ServiceException {
        return restResponseEntityBuilder.buildData(simService.createSim(simDTO), HttpStatus.CREATED);
    }

    /**
     * Acquiring a sim by a user.
     *
     * @param simDTO DTO info
     * @param userId User identifier
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @PutMapping(value="/user/activate/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<SimDTO, String, String>> acquiredByUser(@RequestBody SimDTO simDTO, @PathVariable Integer userId) throws {
        return restResponseEntityBuilder.buildData(simService.acquiredByUser(simDTO, userId), HttpStatus.OK);
    }

    /**
     * Activating a product on a sim.
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @PutMapping(value="/activate/{productId}/{simId}")
    public ResponseEntity<RestResponseEntity<SimDTO, String, String>> activateProduct(@PathVariable Integer simId, @PathVariable Integer productId) throws ServiceException {
        return restResponseEntityBuilder.buildData(simService.activateProduct(simId, productId), HttpStatus.OK);
    }

    /**
     * Deactivating a product on a sim.
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @PutMapping(value="/deactivate/{productId}/{simId}")
    public ResponseEntity<RestResponseEntity<SimDTO, String, String>> deactivateProduct(@PathVariable Integer simId, @PathVariable Integer productId) throws ServiceException {
        return restResponseEntityBuilder.buildData(simService.deActivateProduct(simId, productId), HttpStatus.OK);
    }

    /**
     * Find SIMs using user identifier.
     *
     * @param userId User identifier
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<RestResponseEntity<List<SimDTO>, String, String>> findSimByUser(@PathVariable Integer userId) throws ServiceException{
        return restResponseEntityBuilder.buildOptional(simService.findSimByUser(userId), HttpStatus.OK);
    }
}
