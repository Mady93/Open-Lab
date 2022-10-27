
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
import it.mobileapp.repository.contract.ISimRepository;
import it.mobileapp.repository.contract.IValidationRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import it.mobileapp.dmodel.ValidationDTO;
import it.mobileapp.service.contract.IValidationService;
import it.mobileapp.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * The valdiation service contract implementation.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
@Service
public class ValidationService extends CommonServices implements IValidationService {

    @Autowired
    public ValidationService(IBusinessUserRepository iBusinessUserRepository,
                             ISimRepository iSimRepository, IProductRepository iProductRepository,
                             IValidationRepository iValidationRepository){
        super(iBusinessUserRepository, iSimRepository, iProductRepository, iValidationRepository);
    }
    
    /*
     Con riferimento al progetto mobile-app-service-contract-impl,
      permettere la verifica di un un utente già validato inserendo il seguente codice di programmazione:

Optional<ValidationDTO> validation = iValidationRepository.getUserValidation(validationDTO.getUser().getId());
if(!validation.isEmpty())
   throw new ServiceException("BusinessUser already validated", "CODICE DA DEFINIRE");


all'interno del metodo:

public void validateUser(ValidationDTO validationDTO) throws ServiceException {..}


della classe ValidationService prima della chiamata:


iValidationRepository.validateUser(validationDTO).
     */
    
    

    @Override
    public void validateUser(ValidationDTO validationDTO) throws ServiceException{
    	
    	try {
            isNullControl(new Object[]{validationDTO}, new String[]{"validation object"});
            isNullControl(new Object[]{validationDTO.getUser()}, new String[]{"validation user object"});
            isNullControl(new Object[]{validationDTO.getDate(), validationDTO.getUser().getId(),
                    validationDTO.getUser().getVersion()},
                    new String[]{"date", "userId", "userVersion"});
            
             Optional<ValidationDTO> validation = iValidationRepository.getUserValidation(validationDTO.getUser().getId());
				if(!validation.isEmpty())
					
   				throw new ServiceException("BusinessUser already validated", "CODICE DA DEFINIRE");
            iValidationRepository.validateUser(validationDTO);
        } catch (RepoException e) {
            // log eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    
    	}
    

    
    
// ######################################################################################
    @Override
    public Optional<ValidationDTO> getValidation(Integer userId) throws ServiceException {
        try {
            isNullControl(new Object[]{userId}, new String[]{"userId"});
            return iValidationRepository.getValidation(userId);
        } catch (RepoException e) {
            // log eccezione
            throw new ServiceException(GENERIC_ERROR_MESSAGE, "0000");
        }
    }
}
