
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
import it.mobileapp.dmodel.ValidationDTO;
import it.mobileapp.repository.contract.exceptions.RepoException;

import java.util.Optional;

/**
 * The Validation repository contract. This
 * contract specifies which operations, about a validation,
 * must be defined by a repository.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
public interface IValidationRepository {
    /**
     * Validating a user.
     *
     * @param validationDTO DTO validation info
     * @return DTO validation info
     * @throws RepoException Cannot validate User:
     * a) General error CODE R0000
     */
    ValidationDTO validateUser(ValidationDTO validationDTO) throws RepoException;

    /**
     * Get user validation info.
     *
     * @param userId User identifier
     * @return DTO user validation info
     * @throws RepoException Cannot get user validation info:
     *  a) General error CODE R0000
     */
    Optional<ValidationDTO> getValidation(Integer userId) throws RepoException;    
    //usare questo metodo e lavorare a livello di logic bussiness per il controllo del nome
 
    
    
    
    //°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
                                            //MODIFICATO
    Optional<ValidationDTO> getUserValidation(Integer userId) throws RepoException;
}
