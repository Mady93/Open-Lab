
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

package it.mobileapp.service.contract;

import it.mobileapp.dmodel.ProductDTO;
import it.mobileapp.service.exceptions.ServiceException;
import java.util.List;
import java.util.Optional;

/**
 * The product service contract. This
 * contract specifies which operations, about a product,
 * must be defined by a service.
 *
 *  @author  Alessandro Zoia
 *  @version 1.0
 *  @since   13/08/2022
 */
public interface IProductService {

    /**
     * Create a new product.
     *
     * @param productDTO DTO product data
     * @return DTO product data
     * @throws ServiceException A Product cannot be created
     * a) A Product with the same name already exist CODE 0010
     * b) General error CODE 0000
     */
    ProductDTO createProduct(ProductDTO productDTO) throws ServiceException;

    /**
     * Update a product.
     *
     * @param productDTO DTO product data
     * @return DTO product data
     * @throws ServiceException A Product cannot be updated
     * a) if required product info does not provided (invalid data) CODE 0018
     * b) The Product does not exist CODE 0011
     * c) General error CODE 0000
     */
    
    ProductDTO updateProduct(ProductDTO productDTO) throws ServiceException;

    /**
     * Delete a  Product
     *
     * @param productDTO DTO product data
     * @throws ServiceException A Product cannot be deleted
     * a) if required product info does not provided (invalid data) CODE 0018
     * b) The Product does not exist CODE 0011
     * c) The Product has at least one sim on which is activated CODE 0012
     * d) General error CODE 0000
     */
    // manca un controllo per il nome
    
    void deleteProduct(ProductDTO productDTO) throws ServiceException;

    /**
     * Products list
     *
     * @return The Products list or an empty value
     * @throws ServiceException Cannot get products list:
     * a) General error CODE 0000
     */
    Optional<List<ProductDTO>> findProducts() throws ServiceException;


}