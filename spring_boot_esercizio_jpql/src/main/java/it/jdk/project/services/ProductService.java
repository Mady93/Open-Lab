package it.jdk.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.project.dto.ProductDTO;
import it.jdk.project.entities.Product;
import it.jdk.project.repositories.IProductRepository;

@Service
public class ProductService {
	private final IProductRepository repository;

	@Autowired
	public ProductService(IProductRepository repository) {
		this.repository = repository;
	}
	
	
	

	@Transactional(readOnly = false)
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        repository.save(product);
        productDTO.setId(product.getId());
        productDTO.setVersion(0);
        return productDTO;
    }
	
	
	
	
	
	
	
	
	
	
	
	@Transactional(readOnly = false)
    public ProductDTO update(ProductDTO productDTO) throws Exception {
        Optional<Product> product = repository.findById(productDTO.getId());
        
        if(!product.isEmpty()) {
        	
        	 if(product.get().getVersion() != productDTO.getVersion()) {
                 throw new OptimisticLockingFailureException("Entity ID and Dto ID mismatch");
                 
             }else {
            	 product.get().setName(productDTO.getName());
                 productDTO.setVersion(product.get().getVersion()+1);
                 return productDTO;
        	 
             	}
        	}else {
        	throw new Exception("Product not found!");
        }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@Transactional(readOnly = false)
    public void delete(ProductDTO productDTO) {
        Optional<Product> product = repository.findById(productDTO.getId());
        if(product.get().getVersion() != productDTO.getVersion()) {
            throw new OptimisticLockingFailureException("Entity ID and Dto ID mismatch");
        }
        repository.delete(product.get());
    }
	
	
	
	
	
	
	public Optional<List<ProductDTO>> list()  {
        List<Product> products = repository.findAll();
        try {
            List<ProductDTO> dtoList = new ArrayList<>();
            for(Product p: products){
                dtoList.add(new ProductDTO(p.getId(), p.getName(), p.getVersion()));
            }
            return Optional.of(dtoList);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
	
	
	
	
	
	public Optional<ProductDTO> findById(Integer productId) {
        Optional<Product> product = repository.findById(productId);
        try {
            return Optional.of(new ProductDTO(product.get().getId(), product.get().getName(), product.get().getVersion()));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
	
	
	
	
	
	public Optional<ProductDTO> findByName(String name) {
        Optional<Product> product = repository.findByName(name);
        try{
            return Optional.of(new ProductDTO(product.get().getId(), product.get().getName(), product.get().getVersion()));
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

	
	
	
	
	public boolean seEsisteSimConProductAttivi(Integer id) {
        Product product = new Product();
        product.setId(id);
        Long sims = repository.seEsisteSimConProductAttivi(product);
        return sims>0?true:false;
    }
	
}
