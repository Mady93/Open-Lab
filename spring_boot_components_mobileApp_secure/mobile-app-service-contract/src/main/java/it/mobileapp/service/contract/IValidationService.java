
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
import it.mobileapp.dmodel.ValidationDTO;
import it.mobileapp.service.exceptions.ServiceException;

import java.util.Optional;

/**
 * The validation service contract. This
 * contract specifies which operations, about a validation,
 * must be defined by a service.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
public interface IValidationService {
	
	//validazione dell utente manca
	
	
    /**
     * Validate User.
     *
     * @param validationDTO Validation info
     * @throws ServiceException Cannot validate User:
     * a) User does not exist CODE 0003
     * b) General error CODE 0000
     * c) Invalid date CODE 0013
     */
    void validateUser(ValidationDTO validationDTO) throws ServiceException;

    /**
     * Get user validation info.
     *
     * @param userId User identifier
     * @return User Validation info
     * @throws ServiceException Cannot get User Validation info:
     *   a) General error CODE 0000
     */
    Optional<ValidationDTO> getValidation(Integer userId) throws ServiceException;
    
    
    //*****************************************************************************************************
    											//MODIFICATO
	/*Optional<ValidationDTO> getUserValidation(Integer userId) throws ServiceException;*/
}
