
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
import it.mobileapp.dmodel.BusinessUserFilterDTO;
import it.mobileapp.dmodel.BusinessUserPageDTO;
import it.mobileapp.impl.repository.entities.BusinessUser;
import it.mobileapp.impl.repository.spring.jpa.BusinessUserSpec;
import it.mobileapp.impl.repository.spring.jpa.IBusinessUserEntityRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Proxy Repository JPA.
 * This class allows us to manage persistence exceptions.
 */
@Component
@Transactional(readOnly = true)
public class JPABusinessUserEntityRepositoryProxy extends JPAProxyCommonControls {

    private final IBusinessUserEntityRepository iBusinessUserEntityRepository;

    /**
     * Constructor
     * @param iBusinessUserEntityRepository Spring Data Repository
     */
    @Autowired
    public JPABusinessUserEntityRepositoryProxy(IBusinessUserEntityRepository iBusinessUserEntityRepository){
        this.iBusinessUserEntityRepository = iBusinessUserEntityRepository;
    }

    /**
     * Create a new user.
     *
     * @param businessUserDTO Business User DTO info
     * @return Business User DTO
     */
    @Transactional(readOnly = false)
    public BusinessUserDTO createUser(BusinessUserDTO businessUserDTO) {
        BusinessUser entity = new BusinessUser();
        entity.setFirstName(businessUserDTO.getFirstName());
        entity.setFiscalCode(businessUserDTO.getFiscalCode());
        entity.setLastName(businessUserDTO.getLastName());
        iBusinessUserEntityRepository.save(entity);
        businessUserDTO.setId(entity.getId());
        businessUserDTO.setVersion(0);
        return businessUserDTO;
    }

    /**
     * Update a user.
     *
     * @param businessUserDTO Business User DTO info
     * @return Business User DTO
     */
    @Transactional(readOnly = false)
    public BusinessUserDTO updateUser(BusinessUserDTO businessUserDTO){
        Optional<BusinessUser> entity = iBusinessUserEntityRepository.findById(businessUserDTO.getId());
        checkEmptyError(entity);
        BusinessUser entityValue = entity.get();
        if(entityValue.getVersion() != businessUserDTO.getVersion()) {
            throw new OptimisticLockingFailureException("Entity Version and Dto Version mismatch");
        }
        entityValue.setFirstName(businessUserDTO.getFirstName());
        entityValue.setLastName(businessUserDTO.getLastName());
        entityValue.setFiscalCode(businessUserDTO.getFiscalCode());
        businessUserDTO.setVersion(businessUserDTO.getVersion()+1);
        return businessUserDTO;
    }

    /**
     * Delete a user.
     *
     * @param businessUserDTO User DTO
     */
    @Transactional(readOnly = false)
    public void deleteUser(BusinessUserDTO businessUserDTO) {
        BusinessUser entity = iBusinessUserEntityRepository.getById(businessUserDTO.getId());
        if(entity.getVersion() != businessUserDTO.getVersion()) {
            throw new OptimisticLockingFailureException("Entity Version and Dto Version mismatch");
        }
        iBusinessUserEntityRepository.delete(entity);
    }

    /**
     * Check if an user with the specified fiscal code  exist.
     *
     * @param fiscalCode Fiscal code
     * @return true if the user exist false otherwise
     */
    public boolean existsUserWithFiscalCode(String fiscalCode) {
        return iBusinessUserEntityRepository.findByFiscalCode(fiscalCode).isEmpty()? false : true;
    }

    /**
     * Find a user by id.
     *
     * @param userId User identifier
     * @return Business User DTO
     */
    public Optional<BusinessUserDTO> findById(Integer userId) {
       return iBusinessUserEntityRepository.findUserById(userId);
    }

    /**
     * User page.
     *
     * @param page Page number
     * @param pageSize Page size
     * @return Business User Page DTO
     */
    public Optional<BusinessUserPageDTO> findUsers(int page, int pageSize) {
        Page<BusinessUser> pageUser = iBusinessUserEntityRepository.findUsers(PageRequest.of(page, pageSize));
        return makeDTO(pageUser, page, pageSize);
    }

    /**
     * User page model.
     *
     * @param page Page number
     * @param pageSize Page size
     * @return Business User Page DTO
     * @param businessUserFilterDTO Business User Page Filter DTO
     */
    public Optional<BusinessUserPageDTO> findUsersByFilter(int page, int pageSize, BusinessUserFilterDTO businessUserFilterDTO) {
        Specification<BusinessUser> spec = BusinessUserSpec.getUsersByFilter(businessUserFilterDTO);
        Page<BusinessUser> pageUser = iBusinessUserEntityRepository.findAll(spec, PageRequest.of(page, pageSize));
        return makeDTO(pageUser, page, pageSize);
    }

    /**
     * Make a DTO.
     *
     * @param pageUser Page user
     * @param page Page number
     * @param pageSize Page size
     * @return Business User Page DTO
     */
    private Optional<BusinessUserPageDTO> makeDTO(Page<BusinessUser> pageUser, int page, int pageSize){
        List<BusinessUserDTO> dtoList = new ArrayList<>();
        for(BusinessUser businessUser: pageUser.getContent()){
            dtoList.add(new BusinessUserDTO(businessUser.getId(),businessUser.getFirstName(),
                    businessUser.getLastName(),businessUser.getFiscalCode(), businessUser.getVersion()));
        }
        try {
            checkEmptyList(dtoList);
            return Optional.of(new BusinessUserPageDTO(pageUser.getTotalElements(),page,pageSize, dtoList));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    //#####################################################################################################
    public Long coundByIdAndFiscalCode(Integer id, String fiscalCode) {
		return iBusinessUserEntityRepository.countByIdAndFiscalCode(id,fiscalCode);
		
    	
    	
    }
   
   
    
    }

