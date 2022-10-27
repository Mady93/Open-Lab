
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
import it.mobileapp.repository.contract.IProductRepository;
import it.mobileapp.repository.contract.IValidationRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import it.mobileapp.service.contract.ISimService;
import it.mobileapp.dmodel.SimDTO;
import it.mobileapp.repository.contract.ISimRepository;
import it.mobileapp.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * The sim service contract implementation.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
@Service
public class SimService extends CommonServices implements ISimService {

    @Autowired
    public SimService(IBusinessUserRepository iBusinessUserRepository,
                      ISimRepository iSimRepository, IProductRepository iProductRepository,
                      IValidationRepository iValidationRepository){
        super(iBusinessUserRepository, iSimRepository, iProductRepository, iValidationRepository);
    }

    @Override
    public SimDTO createSim(SimDTO simDTO) throws ServiceException {
        try {
            isNullControl(new Object[]{simDTO}, new String[]{"sim object"});
            isNullControl(new Object[]{simDTO.getMsisdn(), simDTO.getImsi()},
                    new String[]{"msisdn","imsi"});
            long count = iSimRepository.coundByMsisdnAndImsi(simDTO.getMsisdn(), simDTO.getImsi());
            if(count>0)
                throw new ServiceException("Service Error - A sim with the specified " +
                    "msisdn or imsi already exist", "0000");
            return iSimRepository.createSim(simDTO);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE,"0000");
        }
    }

    @Override
    public SimDTO acquiredByUser(SimDTO simDTO, Integer userId) throws ServiceException {
        try {
            isNullControl(new Object[]{simDTO}, new String[]{"sim object"});
            isNullControl(new Object[]{simDTO.getId(), userId},
                    new String[]{"simId","userId"});
            //manca validation
            userExistControl(userId);
            simExistControl(simDTO.getId());
            simAlreadyAssignedControl(simDTO.getId());
            return iSimRepository.acquiredByUser(simDTO, userId);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE,"0000");
        }
    }

    @Override
    public SimDTO activateProduct(Integer simId, Integer productId) throws ServiceException{
        try {
            isNullControl(new Object[]{simId, productId}, new String[]{"simId","productId"});
            simExistControl(simId);
            productExistControl(productId);
            productAlreadyActivatedControl(simId, productId);
            return iSimRepository.activateProduct(simId, productId);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE,"0000");
        }
    }

    @Override
    public SimDTO deActivateProduct(Integer simId, Integer productId) throws ServiceException{
        try {
            isNullControl(new Object[]{simId, productId}, new String[]{"simId","productId"});
            simExistControl(simId);
            productExistControl(productId);
            productNotActivatedControl(simId, productId);
            return iSimRepository.deActivateProduct(simId, productId);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE,"0000");
        }
    }

    @Override
    public Optional<List<SimDTO>> findSimByUser(Integer userId) throws ServiceException {
        try {
            isNullControl(new Object[]{userId}, new String[]{"userId"});
            //manca la verifica dei numeri interi se siano <= 0
            return iSimRepository.findSimByUser(userId);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE,"0000");
        }
    }
}
