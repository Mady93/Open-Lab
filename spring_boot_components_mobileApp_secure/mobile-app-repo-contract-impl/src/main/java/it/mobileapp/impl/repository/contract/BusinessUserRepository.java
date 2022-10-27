
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

import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.dmodel.BusinessUserFilterDTO;
import it.mobileapp.dmodel.BusinessUserPageDTO;
import it.mobileapp.impl.repository.jpa.proxy.JPABusinessUserEntityRepositoryProxy;
import it.mobileapp.repository.contract.IBusinessUserRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Sim Repository contract implementation
 */
@Component
public class BusinessUserRepository extends CommonControls implements IBusinessUserRepository {

    private final JPABusinessUserEntityRepositoryProxy iBusinessUserEntityRepositoryProxy;

    @Autowired
    public BusinessUserRepository(JPABusinessUserEntityRepositoryProxy iBusinessUserEntityRepositoryProxy){
        this.iBusinessUserEntityRepositoryProxy = iBusinessUserEntityRepositoryProxy;
    }

    @Override
    public BusinessUserDTO createUser(BusinessUserDTO businessUserDTO) throws RepoException {
        try {
            checkNotNull(businessUserDTO.getId());
            return iBusinessUserEntityRepositoryProxy.createUser(businessUserDTO);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public BusinessUserDTO updateUser(BusinessUserDTO businessUserDTO) throws RepoException {
        try {
            checkNull(businessUserDTO.getId());
            return iBusinessUserEntityRepositoryProxy.updateUser(businessUserDTO);
        } catch (OptimisticLockingFailureException e) {
            throw new RepoException(e.getMessage(),"R0001");
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public void deleteUser(BusinessUserDTO businessUserDTO) throws RepoException {
        try {
            checkNull(businessUserDTO.getId());
            iBusinessUserEntityRepositoryProxy.deleteUser(businessUserDTO);
        } catch (OptimisticLockingFailureException e) {
            throw new RepoException(e.getMessage(),"R0001");
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public boolean existsUserWithFiscalCode(String fiscalCode) throws RepoException {
        try {
            checkNull(fiscalCode);
            return iBusinessUserEntityRepositoryProxy.existsUserWithFiscalCode(fiscalCode);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<BusinessUserDTO> findById(Integer userId) throws RepoException {
        try {
            checkNull(userId);
            return iBusinessUserEntityRepositoryProxy.findById(userId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<BusinessUserPageDTO> findUsers(int page, int pageSize) throws RepoException {
        try {
            return iBusinessUserEntityRepositoryProxy.findUsers(page, pageSize);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<BusinessUserPageDTO> findUsersByFilter(int page, int pageSize, BusinessUserFilterDTO businessUserFilterDTO) throws RepoException {
        try {
            checkNull(businessUserFilterDTO);
            return iBusinessUserEntityRepositoryProxy.findUsersByFilter(page, pageSize, businessUserFilterDTO);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }
//##################################################################################################

	@Override
	public Long coundByIdAndFiscalCode(Integer id, String fiscalCode) throws RepoException {
		 try {
	            checkNull(id, fiscalCode);
	            return iBusinessUserEntityRepositoryProxy.coundByIdAndFiscalCode(id, fiscalCode);
	        } catch (RuntimeException e) {
	            throw new RepoException(e.getMessage(),"R0000");
	        }
	}

	

	

	

	

	

	
}
