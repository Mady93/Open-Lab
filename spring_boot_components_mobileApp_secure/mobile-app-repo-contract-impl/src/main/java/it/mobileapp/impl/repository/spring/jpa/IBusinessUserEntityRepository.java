
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

package it.mobileapp.impl.repository.spring.jpa;

import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.impl.repository.entities.BusinessUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Spring Data Repository
 */
// l'ultimo estende le criterie API --- JpaSpecificationExecutor<BusinessUser>
public interface IBusinessUserEntityRepository extends JpaRepository<BusinessUser, Integer>, JpaSpecificationExecutor<BusinessUser> {

    /**
     * Finding a user by id.
     *
     * @param id User Id
     * @return Business User DTO info
     */
	
	// oggetti trasformer per tirar fuori la gerarchia di DTO
	//it.mobileapp.dmodel. --- package
	//.BusinessUserDTO --- classe
    @Query("select new it.mobileapp.dmodel.BusinessUserDTO(c.id,c.firstName,c.lastName,c.fiscalCode,c.version) from BusinessUser c where c.id=:id")
    Optional<BusinessUserDTO> findUserById(@Param("id") Integer id);

    /**
     * Getting a page of users.
     *
     * @param pageable Pageable object
     * @return Users page
     */
    @Query("select c from BusinessUser c")
    Page<BusinessUser> findUsers(Pageable pageable);

    /**
     * Getting a page for users using a filter.
     *
     * @param pageable Pageable object
     * @return Users page
     */
    // Specification<BusinessUser> -- a lvl di citerie API--  la dinamicita serve per i filtri
    Page<BusinessUser> findAll(Specification<BusinessUser> spec, Pageable pageable);

    /**
     * Finding a user by fiscal code.
     *
     * @param fiscalCode Fiscal code
     * @return BusinessUser
     */
    
    //rallenta il servizio
    Optional<BusinessUser> findByFiscalCode(String fiscalCode);

    
    //çççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççççç
    
    @Query("count(c) from BusinessUser c where c.filcalCode=:fiscalCode ad c.id<>:id")
    Long countByIdAndFiscalCode(@Param("id") Integer id , @Param("fiscalCode") String fiscalCode);

}
