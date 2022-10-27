
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

import it.mobileapp.repository.contract.IProductRepository;
import it.mobileapp.repository.contract.ISimRepository;
import it.mobileapp.repository.contract.IValidationRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.dmodel.BusinessUserFilterDTO;
import it.mobileapp.dmodel.BusinessUserPageDTO;
import it.mobileapp.dmodel.ValidationDTO;
import it.mobileapp.repository.contract.IBusinessUserRepository;
import it.mobileapp.service.contract.IBusinessUserService;
import it.mobileapp.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * The business user service contract implementation.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
@Service
public class BusinessUserService extends CommonServices implements IBusinessUserService {

    @Autowired
    public BusinessUserService(IBusinessUserRepository iBusinessUserRepository,
                               ISimRepository iSimRepository, IProductRepository iProductRepository,
                               IValidationRepository iValidationRepository){
        super(iBusinessUserRepository, iSimRepository, iProductRepository, iValidationRepository);
    }

    @Override
    public BusinessUserDTO createUser(BusinessUserDTO user) throws ServiceException {
        try {
            isNullControl(new Object[]{user}, new String[]{"user object"});
            isNullControl(new Object[]{user.getFirstName(), user.getLastName(), user.getFiscalCode()},
                    new String[]{"userName","lastName","fiscalCode"});
            existUserWithFiscalCodeControl(user);
            return iBusinessUserRepository.createUser(user);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }

    @Override
    public BusinessUserDTO updateUser(BusinessUserDTO user) throws ServiceException{
        try {
            isNullControl(new Object[]{user}, new String[]{"user object"});
            isNullControl(new Object[]{user.getId(),user.getFirstName(), user.getLastName(),
                            user.getFiscalCode(), user.getVersion()},
                    new String[]{"id","userName","lastName","fiscalCode","version"});
            //manca il controllo del codice fiscale e l'id del utente diverso dal suo
            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            userExistControl(user.getId());
            if(iBusinessUserRepository.coundByIdAndFiscalCode(user.getId(), user.getFiscalCode()) > 0){
				throw new ServiceException("BusinessUser already validated with this fiscal code", "2938");
            }else {
            
            return iBusinessUserRepository.updateUser(user);
        } 
            }
            catch (RepoException e) {
            // log dell'eccezione
            e.printStackTrace();
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }
        

    @Override
    public void deleteUser(BusinessUserDTO user) throws ServiceException {
        try {
            isNullControl(new Object[]{user}, new String[]{"user object"});
            isNullControl(new Object[]{user.getId(), user.getVersion()},
                    new String[]{"id","version"});
            userExistControl(user.getId());
            userWithSimControl(user.getId());
            iBusinessUserRepository.deleteUser(user);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }

    @Override
    public Optional<BusinessUserDTO> findById(Integer id) throws ServiceException {
        try {
            isNullControl(new Object[]{id}, new String[]{"id"});
            return iBusinessUserRepository.findById(id);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }

    @Override
    public Optional<BusinessUserPageDTO> findUsers(int start, int max) throws ServiceException {
        try {
            return iBusinessUserRepository.findUsers(start, max);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }

    @Override
    public Optional<BusinessUserPageDTO> findUsersByFilter(int start, int max, BusinessUserFilterDTO filter) throws ServiceException {
        try {
            isNullControl(new Object[]{filter}, new String[]{"filter object"});
            return iBusinessUserRepository.findUsersByFilter(start, max, filter);
        } catch (RepoException e) {
            // log dell'eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }
//*************************************************************************************

	
	
}
