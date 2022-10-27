package it.jdk.project.repository;

import java.util.List;
import java.util.Optional;

import it.jdk.project.models.ProductDTO;

/**
 *  @author  name
 *  @version 1.0
 *  @since   date
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
