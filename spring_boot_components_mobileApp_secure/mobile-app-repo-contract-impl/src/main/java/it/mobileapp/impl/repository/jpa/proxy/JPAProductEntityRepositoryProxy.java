
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

package it.mobileapp.impl.repository.jpa.proxy;

import it.mobileapp.dmodel.ProductDTO;
import it.mobileapp.impl.repository.entities.Product;
import it.mobileapp.impl.repository.spring.jpa.IProductEntityRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Proxy Repository JPA
 * This class allows us to manage persistence exceptions.
 */
@Component
@Transactional(readOnly = true)
public class JPAProductEntityRepositoryProxy extends JPAProxyCommonControls {

    private final IProductEntityRepository iProductEntityRepository;

    /**
     * Constructor
     * @param iProductEntityRepository Spring Data Repository
     */
    public JPAProductEntityRepositoryProxy(IProductEntityRepository iProductEntityRepository){
        this.iProductEntityRepository = iProductEntityRepository;
    }

    /**
     * Create a new product.
     *
     * @param productDTO DTO product Data
     * @return DTO product Data
     */
    @Transactional(readOnly = false)
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        iProductEntityRepository.save(product);
        productDTO.setId(product.getId());
        productDTO.setVersion(0);
        return productDTO;
    }

    /**
     * Update a product.
     *
     * @param productDTO DTO product Data
     * @return DTO product Data
     * @throws NoResultException if the product does not exist
     */
    @Transactional(readOnly = false)
    public ProductDTO update(ProductDTO productDTO) {
        Optional<Product> product = iProductEntityRepository.findById(productDTO.getId());
        checkEmptyError(product);
        if(product.get().getVersion() != productDTO.getVersion()) {
            throw new OptimisticLockingFailureException("Entity ID and Dto ID mismatch");
        }
        product.get().setName(productDTO.getName());
        productDTO.setVersion(product.get().getVersion()+1);
        return productDTO;
    }

    /**
     * Delete a product.
     *
     * @param productDTO DTO product data
     * @throws NoResultException if the product does not exist
     */
    @Transactional(readOnly = false)
    public void delete(ProductDTO productDTO) {
        Optional<Product> product = iProductEntityRepository.findById(productDTO.getId());
        checkEmptyError(product);
        if(product.get().getVersion() != productDTO.getVersion()) {
            throw new OptimisticLockingFailureException("Entity ID and Dto ID mismatch");
        }
        iProductEntityRepository.delete(product.get());
    }

    /**
     * Getting all products.
     *
     * @return List of products
     */
    public Optional<List<ProductDTO>> list()  {
        List<Product> products = iProductEntityRepository.findAll();
        try {
            checkEmptyList(products);
            List<ProductDTO> dtoList = new ArrayList<>();
            for(Product p: products){
                dtoList.add(new ProductDTO(p.getId(), p.getName(), p.getVersion()));
            }
            return Optional.of(dtoList);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Finding a product by id.
     *
     * @param productId Product identifier
     * @return The product if exist
     */
    public Optional<ProductDTO> findById(Integer productId) {
        Optional<Product> product = iProductEntityRepository.findById(productId);
        try {
            checkEmptyError(product);
            return Optional.of(new ProductDTO(product.get().getId(), product.get().getName(), product.get().getVersion()));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Finding a product by name
     * @param productName Product name
     * @return The product if exist
     */
    public Optional<ProductDTO> findByName(String productName) {
        Optional<Product> product = iProductEntityRepository.findByName(productName);
        try{
            checkEmptyError(product);
            return Optional.of(new ProductDTO(product.get().getId(), product.get().getName(), product.get().getVersion()));
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    /**
     * Check if exist a sim with this product activated.
     *
     * @param productId Product identifier
     * @return true if the sim exist false otherwise
     */
    public boolean existASimWithThisProductActivated(Integer productId) {
        Product p = new Product();
        p.setId(productId);
        Long sims = iProductEntityRepository.existASimWithThisProduct(p);
        return sims>0?true:false;
    }
}
