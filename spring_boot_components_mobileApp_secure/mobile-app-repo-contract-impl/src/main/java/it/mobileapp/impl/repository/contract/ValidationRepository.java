
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

import it.mobileapp.dmodel.ValidationDTO;
import it.mobileapp.impl.repository.jpa.proxy.JPAValidationEntityRepositoryProxy;
import it.mobileapp.repository.contract.IValidationRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ValidationRepository implements IValidationRepository {

    private final JPAValidationEntityRepositoryProxy jpaValidationEntityRepositoryProxy;

    public ValidationRepository(JPAValidationEntityRepositoryProxy jpaValidationEntityRepositoryProxy){
        this.jpaValidationEntityRepositoryProxy = jpaValidationEntityRepositoryProxy;
    }

    @Override
    public ValidationDTO validateUser(ValidationDTO validationDTO) throws RepoException {
        try {
            return jpaValidationEntityRepositoryProxy.validateUser(validationDTO);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<ValidationDTO> getValidation(Integer userId) throws RepoException {
        try {
            return jpaValidationEntityRepositoryProxy.getValidation(userId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }
    
    
// ###################################################################################################
	@Override
	public Optional<ValidationDTO> getUserValidation(Integer userId) throws RepoException {
		try {
            return jpaValidationEntityRepositoryProxy.getUserValidation(userId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }	}  
    
	}

