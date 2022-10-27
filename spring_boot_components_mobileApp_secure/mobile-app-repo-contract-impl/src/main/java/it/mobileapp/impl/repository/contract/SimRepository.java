
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

import it.mobileapp.dmodel.SimDTO;
import it.mobileapp.impl.repository.jpa.proxy.JPASimEntityRepositoryProxy;
import it.mobileapp.repository.contract.ISimRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * Sim Repository contract implementation
 */
@Component
public class SimRepository extends CommonControls implements ISimRepository {

    private final JPASimEntityRepositoryProxy jpaSimEntityRepositoryProxy;

    @Autowired
    public SimRepository(JPASimEntityRepositoryProxy jpaSimEntityRepositoryProxy){
        this.jpaSimEntityRepositoryProxy = jpaSimEntityRepositoryProxy;
    }

    @Override
    public SimDTO createSim(SimDTO simDTO) throws RepoException {
        try {
            checkNotNull(simDTO.getId());
            return jpaSimEntityRepositoryProxy.createSim(simDTO);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public SimDTO acquiredByUser(SimDTO simDTO, Integer userId) throws RepoException {
        try {
            checkNull(simDTO.getId(), userId);
            return jpaSimEntityRepositoryProxy.acquiredByUser(simDTO, userId);
        } catch (OptimisticLockingFailureException e) {
            throw new RepoException(e.getMessage(),"R0001");
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public boolean simAlreadyAssigned(Integer simId) throws RepoException {
        try {
            checkNull(simId);
            return jpaSimEntityRepositoryProxy.simAlreadyAssigned(simId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public boolean productAlreadyActivated(Integer simId, Integer productId) throws RepoException {
        try {
            checkNull(simId, productId);
            return jpaSimEntityRepositoryProxy.productAlreadyActivated(simId, productId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public SimDTO activateProduct(Integer simId, Integer productId) throws RepoException {
        try {
            checkNull(simId, productId);
            return jpaSimEntityRepositoryProxy.activateProduct(simId, productId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public SimDTO deActivateProduct(Integer simId, Integer productId) throws RepoException {
        try {
            checkNull(simId, productId);
            return jpaSimEntityRepositoryProxy.deActivateProduct(simId, productId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<List<SimDTO>> findSimByUser(Integer userId) throws RepoException {
        try {
            checkNull(userId);
            return jpaSimEntityRepositoryProxy.findSimByUser(userId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<SimDTO> findSimById(Integer simId) throws RepoException {
        try {
            checkNull(simId);
            return jpaSimEntityRepositoryProxy.findSimById(simId);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Long coundByMsisdnAndImsi(String msisdn, String imsi) throws RepoException {
        try {
            checkNull(msisdn, imsi);
            return jpaSimEntityRepositoryProxy.coundSimByMsisdnAndImsi(msisdn, imsi);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }

    @Override
    public Optional<List<SimDTO>> freeSims(Integer blockSize) throws RepoException {
        try{
            if(blockSize<=0)
                throw new RepoException("Block size bad value", "R0000");
            return jpaSimEntityRepositoryProxy.freeSims(blockSize);
        } catch (RuntimeException e) {
            throw new RepoException(e.getMessage(),"R0000");
        }
    }
}
