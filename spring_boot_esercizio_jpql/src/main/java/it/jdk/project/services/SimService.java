package it.jdk.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.project.dto.ProductDTO;
import it.jdk.project.dto.SimDTO;
import it.jdk.project.entities.BusinessUser;
import it.jdk.project.entities.Product;
import it.jdk.project.entities.Sim;
import it.jdk.project.repositories.IBusinessUserRepository;
import it.jdk.project.repositories.IProductRepository;
import it.jdk.project.repositories.ISimRepository;

@Service
public class SimService {
	private final ISimRepository repository;
	private final IBusinessUserRepository userRepository;
	private final IProductRepository productRepository;

	@Autowired
	public SimService(ISimRepository repository , IBusinessUserRepository userRepository , IProductRepository productRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}
	
	
	
	
	
	@Transactional
    public SimDTO create(SimDTO simDTO) {
        Sim sim = new Sim();
        sim.setImsi(simDTO.getImsi());
        sim.setMsisdn(simDTO.getMsisdn());
        repository.save(sim);
        simDTO.setId(sim.getId());
        simDTO.setVersion(0);
        return simDTO;
    }
	
	
	
	                                             //sim.setUser(user);
	@Transactional(readOnly = false)
    public SimDTO attivareSimPerUtente(SimDTO simDTO, Integer userId) {
		
        Optional<Sim> simEntity = repository.findById(simDTO.getId());

        if(simEntity.get().getVersion() != simDTO.getVersion()) {
            throw new OptimisticLockingFailureException("Entity ID and Dto ID mismatch");
        }

        Optional<BusinessUser> businessUserEntity = userRepository.findById(userId);

        BusinessUser businessUser = businessUserEntity.get();
        Sim sim = simEntity.get();
        sim.setBusinessUser(businessUser);
        return new SimDTO(sim.getId(), sim.getMsisdn(), sim.getImsi(), sim.getVersion());
    }

	 

	
	
	
	
	
	
                                                     //simDTO.setProduct(product);
    @Transactional(readOnly = false)
    public SimDTO attivareProductAllaSim(Integer simId, Integer productId) {
        Optional<Sim> sim = repository.findSimWithProducts(simId);

        Sim simData =  sim.get();
        Set<Product> products = simData.getProducts();
        if(products != null) {
            for (Product p : products) {
                if (p.getId() == productId)
                   System.out.print("Product already activated"); {};
            }
        }

        Optional<Product> product = productRepository.findById(productId);

        simData.getProducts().add(product.get());
        SimDTO simDTO = new SimDTO(simData.getId(), simData.getMsisdn(), simData.getImsi(), simData.getVersion());
        List<ProductDTO> dtoProducts = new ArrayList<>();

        for(Product p: products) {
            dtoProducts.add(new ProductDTO(p.getId(), p.getName(), p.getVersion()));
        }
        simDTO.setProducts(dtoProducts);
        return simDTO;
    }

	
	
	
	
	
 
    @Transactional(readOnly = false)
    public SimDTO disattivareProductAllaSim(Integer simId, Integer productId) {
        Product p = new Product();
        p.setId(productId);
        Optional<Sim> sim = repository.findSimWithProduct(simId, p);

        Sim simData = sim.get();
        Optional<Product> product = productRepository.findById(productId);

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
    
    
    
    
    

  
    public Optional<List<SimDTO>> findSimByUser(Integer userId) {
        try{
            Optional<List<Sim>> sims = repository.findSimByUserId(userId);
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

  
    
    
    
    
    
    
    public Optional<SimDTO> findSimById(Integer simId) {
        try{
            Optional<Sim> sim = repository.findById(simId);
            return Optional.of(new SimDTO(sim.get().getId(), sim.get().getMsisdn(),
                    sim.get().getImsi(), sim.get().getVersion()));
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

 
    
    
    
    
    
    public Long countSimByMsisdnAndImsi(String msisdn, String imsi) {
        return repository.countByMsisdnAndImsi(msisdn,imsi);
    }

    
    
    
   /*
    public Optional<List<SimDTO>> simLibere(Integer blockSize) {
        Optional<List<Sim>> sim = repository.freeSims(PageRequest.of(0, blockSize));
        if(sim.isEmpty())
            return Optional.empty();
        List<SimDTO> dto = new ArrayList<>();
        for(Sim s: sim.get()){
            dto.add(new SimDTO(s.getId(), s.getMsisdn(), s.getImsi(), s.getVersion()));
        }
        return Optional.of(dto);
    }
 */

	 
    public boolean simAlreadyAssigned(Integer simId) {
        Optional<Sim> sim = repository.findById(simId);
        if(sim.get().getBusinessUser()!=null)
            return true;
        return false;
    }

	
    public boolean productAlreadyActivated(Integer simId, Integer productId) {
        try {
            Product p = new Product();
            p.setId(productId);
            Optional<Sim> sim = repository.findSimWithProduct(simId, p);
        } catch (NoResultException e){
            return false;
        }
        return true;
    }
}
