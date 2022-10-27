
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

package it.mobileapp.impl.repository.contract;

import it.mobileapp.dmodel.ProductDTO;
import it.mobileapp.impl.repository.jpa.proxy.JPAProductEntityRepositoryProxy;
import it.mobileapp.repository.contract.IProductRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository extends CommonControls implements IProductRepository {

    private final JPAProductEntityRepositoryProxy jpaProductEntityRepositoryProxy;

    @Autowired
    public ProductRepository(JPAProductEntityRepositoryProxy jpaProductEntityRepositoryProxy) {
        this.jpaProductEntityRepositoryProxy = jpaProductEntityRepositoryProxy;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) throws RepoException {
        try {
            checkNotNull(productDTO.getId());
            return jpaProductEntityRepositoryProxy.create(productDTO);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(), "R0000");
        }
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) throws RepoException {
        try {
            checkNull(productDTO.getId());
            return jpaProductEntityRepositoryProxy.update(productDTO);
        } catch (OptimisticLockingFailureException e) {
            throw new RepoException(e.getMessage(),"R0001");
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public void delete(ProductDTO productDTO) throws RepoException {
        try {
            checkNull(productDTO.getId());
            jpaProductEntityRepositoryProxy.delete(productDTO);
        } catch (OptimisticLockingFailureException e) {
            throw new RepoException(e.getMessage(),"R0001");
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<List<ProductDTO>> list() throws RepoException {
        try {
            return jpaProductEntityRepositoryProxy.list();
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<ProductDTO> findById(Integer productId) throws RepoException {
        try {
            return jpaProductEntityRepositoryProxy.findById(productId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<ProductDTO> findByName(String productName) throws RepoException {
        try {
            return jpaProductEntityRepositoryProxy.findByName(productName);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public boolean existASimWithThisProductActivated(Integer productId) throws RepoException {
        try {
            return jpaProductEntityRepositoryProxy.existASimWithThisProductActivated(productId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }
}
