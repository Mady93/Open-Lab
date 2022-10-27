
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

package it.mobileapp.repository.contract;

import it.mobileapp.dmodel.ProductDTO;
import it.mobileapp.repository.contract.exceptions.RepoException;
import java.util.List;
import java.util.Optional;

/**
 * The Product repository contract. This
 * contract specifies which operations, about a product,
 * must be defined by a repository.
 *
 *
 *  @author  Alessandro Zoia
 *  @version 1.0
 *  @since   13/08/2022
 */
public interface IProductRepository {

    /**
     * Create a new Product using DTO info.
     *
     * @param productDTO DTO product data
     * @return DTO product data
     * @throws RepoException A product cannot be created:
     * a) General error CODE R0000
     */
    ProductDTO create(ProductDTO productDTO) throws RepoException;

    /**
     * Update a Product using DTO info.
     *
     * @param productDTO Product data
     * @return DTO product data
     * @throws RepoException A Product cannot be updated:
     * a) General error CODE R0000
     * b) Concurrent modification CODE R0001
     */
    ProductDTO update(ProductDTO productDTO) throws RepoException;

    /**
     * Delete a Product using DTO info.
     *
     * @param productDTO DTO product data
     * @throws RepoException A Product cannot be deleted:
     * a) General error CODE 0000
     * b) Concurrent modification CODE R0001
     */
    void delete(ProductDTO productDTO) throws RepoException;

    /**
     * Products list.
     *
     * @return The Products list or an empty value
     * @throws RepoException Cannot get products list:
     * a) General error CODE 0000
     */
    Optional<List<ProductDTO>> list() throws RepoException;

    /**
     * Find a specific Product by id.
     *
     * @param productId Product identifier
     * @return The Product if exist
     * @throws RepoException
     * a) General error CODE 0000
     */
    Optional<ProductDTO> findById(Integer productId) throws RepoException;

    /**
     * Find a specific Product by name.
     *
     * @param productName Product name
     * @return The Product if exist
     * @throws RepoException
     * a) General error CODE 0000
     */
    Optional<ProductDTO> findByName(String productName) throws RepoException;

    /**
     * Check if exist a sim with the input product activated.
     * @param productId Product identifier
     * @return true if the sim exist, false otherwise
     * @throws RepoException
     * a) General error CODE 0000
     */
    boolean existASimWithThisProductActivated(Integer productId) throws RepoException;
}
