
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

import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.dmodel.ValidationDTO;
import it.mobileapp.impl.repository.entities.BusinessUser;
import it.mobileapp.impl.repository.entities.Validation;
import it.mobileapp.impl.repository.spring.jpa.IBusinessUserEntityRepository;
import it.mobileapp.impl.repository.spring.jpa.IValidationEntityRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Optional;

/**
 * Proxy Repository JPA
 * This class allows us to manage persistence exceptions
 */
@Component
@Transactional(readOnly = true)
public class JPAValidationEntityRepositoryProxy extends JPAProxyCommonControls {

    private final IValidationEntityRepository iValidationEntityRepository;
    private final IBusinessUserEntityRepository iBusinessUserEntityRepository;

    /**
     * Constructor
     * @param iValidationEntityRepository Spring Data Repository
     * @param iBusinessUserEntityRepository Spring Data Repository
     */
    public JPAValidationEntityRepositoryProxy(IValidationEntityRepository iValidationEntityRepository,
                                              IBusinessUserEntityRepository iBusinessUserEntityRepository) {
        this.iValidationEntityRepository = iValidationEntityRepository;
        this.iBusinessUserEntityRepository = iBusinessUserEntityRepository;
    }

    /**
     * Validate a User.
     * @param validationDTO DTO Validation info
     * @return DTO validation info
     */
    @Transactional(readOnly = false)
    public ValidationDTO validateUser(ValidationDTO validationDTO) {
        Validation validation = new Validation();
        validation.setDate(validationDTO.getDate());
        BusinessUser businessUser = iBusinessUserEntityRepository.getById(validationDTO.getUser().getId());
        if(businessUser.getVersion() != validationDTO.getUser().getVersion()) {
            throw new OptimisticLockingFailureException("Entity ID and Dto ID mismatch");
        }
        validation.setBusinessUser(businessUser);
        iValidationEntityRepository.save(validation);
        BusinessUserDTO businessUserDTO = new BusinessUserDTO(businessUser.getId(),
                businessUser.getFirstName(), businessUser.getLastName(),
                businessUser.getFiscalCode(), businessUser.getVersion());
        return new ValidationDTO(validation.getId(), validation.getDate(),
                businessUserDTO, validation.getVersion());
    }

    /**
     * Getting validation data.
     *
     * @param validationId Validation identifier
     * @return Validation data if present
     */
    public Optional<ValidationDTO> getValidation(Integer validationId) {
        try {
            Optional<Validation> validation = iValidationEntityRepository.findById(validationId);
            checkEmptyError(validation);
            BusinessUser user = validation.get().getBusinessUser();
            BusinessUserDTO userDTO = new BusinessUserDTO(user.getId(), user.getFirstName(),
                    user.getLastName(), user.getFiscalCode(),user.getVersion());
            return Optional.of(new ValidationDTO(validation.get().getId(), validation.get().getDate(),
                    userDTO, validation.get().getVersion()));
        } catch (NoResultException e){
            return Optional.empty();
        }
    }
    //*************************************

	public Optional<ValidationDTO> getUserValidation(Integer userId) {
		 try {
	            Optional<Validation> validation = iValidationEntityRepository.findByUserId(userId);
	            checkEmptyError(validation);
	            BusinessUser user = validation.get().getBusinessUser();
	            BusinessUserDTO userDTO = new BusinessUserDTO(user.getId(), user.getFirstName(),
	                    user.getLastName(), user.getFiscalCode(),user.getVersion());
	            return Optional.of(new ValidationDTO(validation.get().getId(), validation.get().getDate(),
	                    userDTO, validation.get().getVersion()));
	        } catch (NoResultException e){
	            return Optional.empty();
	        }
	}
	}

