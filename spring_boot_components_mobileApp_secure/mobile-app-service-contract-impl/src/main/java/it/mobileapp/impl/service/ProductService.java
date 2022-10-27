
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

package it.mobileapp.impl.service;

import it.mobileapp.repository.contract.IBusinessUserRepository;
import it.mobileapp.repository.contract.ISimRepository;
import it.mobileapp.repository.contract.IValidationRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import it.mobileapp.service.contract.IProductService;
import it.mobileapp.dmodel.ProductDTO;
import it.mobileapp.repository.contract.IProductRepository;
import it.mobileapp.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * The product service contract implementation.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
@Service
public class ProductService extends CommonServices implements IProductService {

    @Autowired
    public ProductService(IBusinessUserRepository iBusinessUserRepository,
                          ISimRepository iSimRepository, IProductRepository iProductRepository,
                          IValidationRepository iValidationRepository){
        super(iBusinessUserRepository, iSimRepository, iProductRepository, iValidationRepository);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws ServiceException {
        try {
            isNullControl(new Object[]{productDTO}, new String[]{"product object"});
            isNullControl(new Object[]{productDTO.getName()}, new String[]{"name"});
            productExistControl(productDTO.getName());
            return iProductRepository.create(productDTO);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) throws ServiceException {
        try {
            isNullControl(new Object[]{productDTO}, new String[]{"product object"});
            isNullControl(new Object[]{productDTO.getId(), productDTO.getName(), productDTO.getVersion()},
                    new String[]{"id","name","version"});
            // manca un controllo per il nome del prodotto con un id diverso del prodotto
            productExistControl(productDTO.getId());
            return iProductRepository.update(productDTO);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) throws ServiceException {
        try {
            isNullControl(new Object[]{productDTO}, new String[]{"product object"});
            isNullControl(new Object[]{productDTO.getId(), productDTO.getVersion()},
                    new String[]{"id","version"});
            productExistControl(productDTO.getId());
            existSimWithThisProduct(productDTO.getId());
            iProductRepository.delete(productDTO);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }

    @Override
    public Optional<List<ProductDTO>> findProducts() throws ServiceException {
        try {
            return iProductRepository.list();
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }
}
