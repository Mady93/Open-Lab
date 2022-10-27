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
import it.mobileapp.dmodel.ProductDTO;
import it.mobileapp.service.contract.IProductService;
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
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;
    private final RestResponseEntityBuilder restResponseEntityBuilder;

    /**
     * Constructor.
     *
     * @param productService Product service contract dependency
     * @param restResponseEntityBuilder  est response builder dependency
     */
    @Autowired
    public ProductController(IProductService productService, RestResponseEntityBuilder restResponseEntityBuilder){
        this.productService = productService;
        this.restResponseEntityBuilder = restResponseEntityBuilder;
    }

    /**
     * Create a new product using JSON data.
     *
     * @param productDTO DTO info
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<ProductDTO, String, String>> create(@RequestBody ProductDTO productDTO) throws ServiceException {
        return restResponseEntityBuilder.buildData(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    /**
     * Update a product using JSON data.
     *
     * @param productDTO DTO info
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponseEntity<ProductDTO, String, String>> update(@RequestBody ProductDTO productDTO) throws ServiceException {
        return restResponseEntityBuilder.buildData(productService.updateProduct(productDTO), HttpStatus.OK);
    }

    /**
     * Delete a product using JSON data.
     *
     * @param productDTO  DTO info
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @DeleteMapping(value="/delete")
    public ResponseEntity<RestResponseEntity<ProductDTO, String, String>> delete(@RequestBody ProductDTO productDTO) throws ServiceException {
        productService.deleteProduct(productDTO);
        return restResponseEntityBuilder.buildData(productDTO, HttpStatus.NO_CONTENT);
    }

    /**
     * Find products.
     *
     * @return ResponseEntity
     * @throws ServiceException if we have a problem with the underlying service
     */
    @GetMapping(value="/list")
    public ResponseEntity<RestResponseEntity<List<ProductDTO>, String, String>> findProducts() throws ServiceException {
        return restResponseEntityBuilder.buildOptional(productService.findProducts(), HttpStatus.OK);
    }

}
