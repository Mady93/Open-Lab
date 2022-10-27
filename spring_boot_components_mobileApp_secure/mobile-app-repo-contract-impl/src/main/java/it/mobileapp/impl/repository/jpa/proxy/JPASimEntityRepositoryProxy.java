
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

import it.mobileapp.dmodel.*;
import it.mobileapp.impl.repository.entities.BusinessUser;
import it.mobileapp.impl.repository.entities.Product;
import it.mobileapp.impl.repository.entities.Sim;
import it.mobileapp.impl.repository.spring.jpa.IBusinessUserEntityRepository;
import it.mobileapp.impl.repository.spring.jpa.IProductEntityRepository;
import it.mobileapp.impl.repository.spring.jpa.ISimEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.*;

/**
 * Proxy Repository JPA
 * This class allows us to manage persistence exceptions.
 */
@Component
@Transactional(readOnly = true)
public class JPASimEntityRepositoryProxy extends JPAProxyCommonControls {

    private final ISimEntityRepository iSimEntityRepository;
    private final IBusinessUserEntityRepository iBusinessUserEntityRepository;
    private final IProductEntityRepository iProductEntityRepository;

    /**
     * Constructor
     * @param iSimEntityRepository Spring Data Repository
     * @param iBusinessUserEntityRepository Spring Data Repository
     * @param iProductEntityRepository Spring Data Repository
     */
    @Autowired
    public JPASimEntityRepositoryProxy(ISimEntityRepository iSimEntityRepository,
                                       IBusinessUserEntityRepository iBusinessUserEntityRepository,
                                       IProductEntityRepository iProductEntityRepository){
        this.iSimEntityRepository = iSimEntityRepository;
        this.iBusinessUserEntityRepository = iBusinessUserEntityRepository;
        this.iProductEntityRepository = iProductEntityRepository;
    }

    /**
     * Create a new sim.
     *
     * @param simDTO DTO sim info
     * @return DTO sim info
     */
    @Transactional
    public SimDTO createSim(SimDTO simDTO) {
        Sim sim = new Sim();
        sim.setImsi(simDTO.getImsi());
        sim.setMsisdn(simDTO.getMsisdn());
        iSimEntityRepository.save(sim);
        simDTO.setId(sim.getId());
        simDTO.setVersion(0);
        return simDTO;
    }

    /**
     * Activating a sim to a user.
     *
     * @param simDTO Sim identifier
     * @param userId User identifier
     * @return DTO sim data
     */
    @Transactional(readOnly = false)
    public SimDTO acquiredByUser(SimDTO simDTO, Integer userId) {
        Optional<Sim> simEntity = iSimEntityRepository.findById(simDTO.getId());
        checkEmptyError(simEntity);

        if(simEntity.get().getVersion() != simDTO.getVersion()) {
            throw new OptimisticLockingFailureException("Entity ID and Dto ID mismatch");
        }

        Optional<BusinessUser> businessUserEntity = iBusinessUserEntityRepository.findById(userId);
        checkEmptyError(businessUserEntity);

        BusinessUser businessUser = businessUserEntity.get();
        Sim sim = simEntity.get();
        sim.setBusinessUser(businessUser);
        return new SimDTO(sim.getId(), sim.getMsisdn(), sim.getImsi(), sim.getVersion());
    }

    /**
     * Check if the sim is already assigned.
     *
     * @param simId Sim identifier
     * @return ture if assigned false otherwise
     */
    public boolean simAlreadyAssigned(Integer simId) {
        Optional<Sim> sim = iSimEntityRepository.findById(simId);
        checkEmptyError(sim);
        if(sim.get().getBusinessUser()!=null)
            return true;
        return false;
    }

    /**
     * Check if the product is already activated.
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return true if the product is activated false otherwise
     */
    public boolean productAlreadyActivated(Integer simId, Integer productId) {
        try {
            Product p = new Product();
            p.setId(productId);
            Optional<Sim> sim = iSimEntityRepository.findSimWithProduct(simId, p);
            checkEmptyError(sim);
        } catch (NoResultException e){
            return false;
        }
        return true;
    }

    /**
     * Activating a product on the sim.
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return DTO Sim Data
     */
    @Transactional(readOnly = false)
    public SimDTO activateProduct(Integer simId, Integer productId) {
        Optional<Sim> sim = iSimEntityRepository.findSimWithProducts(simId);
        checkEmptyError(sim);

        Sim simData =  sim.get();
        Set<Product> products = simData.getProducts();
        if(products != null) {
            for (Product p : products) {
                if (p.getId() == productId)
                    throw new DataAccessException("Product already activated") {};
            }
        }

        Optional<Product> product = iProductEntityRepository.findById(productId);
        checkEmptyError(product);

        simData.getProducts().add(product.get());
        SimDTO simDTO = new SimDTO(simData.getId(), simData.getMsisdn(), simData.getImsi(), simData.getVersion());
        List<ProductDTO> dtoProducts = new ArrayList<>();

        for(Product p: products) {
            dtoProducts.add(new ProductDTO(p.getId(), p.getName(), p.getVersion()));
        }
        simDTO.setProducts(dtoProducts);
        return simDTO;
    }

    /**
     * Deactivating a product.
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return DTO Sim Data
     */
    @Transactional(readOnly = false)
    public SimDTO deActivateProduct(Integer simId, Integer productId) {
        Product p = new Product();
        p.setId(productId);
        Optional<Sim> sim = iSimEntityRepository.findSimWithProduct(simId, p);
        checkEmptyError(sim);

        Sim simData = sim.get();
        Optional<Product> product = iProductEntityRepository.findById(productId);
        checkEmptyError(product);

        Product productData = product.get();
        simData.getProducts().remove(productData);

        SimDTO simDTO = new SimDTO(sim.get().getId(), sim.get().getMsisdn(), sim.get().getImsi(),
                sim.get().getVersion());

        List<ProductDTO> dtoProducts = new ArrayList<>();

        for(Product pr: simData.getProducts()) {
            dtoProducts.add(new ProductDTO(pr.getId(), pr.getName(), pr.getVersion()));
        }
        simDTO.setProducts(dtoProducts);

        return simDTO;
    }

    /**
     * Finding a sim by user identifier.
     *
     * @param userId User identifier
     * @return DTO List with complete SIMs data
     */
    public Optional<List<SimDTO>> findSimByUser(Integer userId) {
        try{
            Optional<List<Sim>> sims = iSimEntityRepository.findSimByUserId(userId);
            checkEmptyError(sims);
            List<SimDTO> dtoSims = new ArrayList<>();
            for(Sim s: sims.get()){

                SimDTO simDTO = new SimDTO(s.getId(), s.getMsisdn(), s.getImsi(), s.getVersion());
                dtoSims.add(simDTO);

                List<ProductDTO> dtoProducts = new ArrayList<>();
                for(Product pr: s.getProducts()) {
                    dtoProducts.add(new ProductDTO(pr.getId(), pr.getName(), pr.getVersion()));
                }

                simDTO.setProducts(dtoProducts);
            }
            return Optional.of(dtoSims);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Finding a sim by identifier.
     *
     * @param simId Sim identifier
     * @return DTO sim data
     */
    public Optional<SimDTO> findSimById(Integer simId) {
        try{
            Optional<Sim> sim = iSimEntityRepository.findById(simId);
            checkEmptyError(sim);
            return Optional.of(new SimDTO(sim.get().getId(), sim.get().getMsisdn(),
                    sim.get().getImsi(), sim.get().getVersion()));
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    /**
     * Finding SIMs using msisdn and imsi.
     *
     * @param msisdn Sim msisdn
     * @param imsi Sim imsi
     * @return Number of SIMs
     */
    public Long coundSimByMsisdnAndImsi(String msisdn, String imsi) {
        return iSimEntityRepository.coundByMsisdnAndImsi(msisdn,imsi);
    }

    /**
     * Getting a block of free SIMs.
     *
     * @param blockSize Block size
     * @return DTO SIMs list
     */
    public Optional<List<SimDTO>> freeSims(Integer blockSize) {
        Optional<List<Sim>> sim = iSimEntityRepository.freeSims(PageRequest.of(0, blockSize));
        if(sim.isEmpty())
            return Optional.empty();
        List<SimDTO> dto = new ArrayList<>();
        for(Sim s: sim.get()){
            dto.add(new SimDTO(s.getId(), s.getMsisdn(), s.getImsi(), s.getVersion()));
        }
        return Optional.of(dto);
    }
}
