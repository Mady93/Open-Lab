package it.jdk.project.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.jdk.project.entities.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer>{

	                         // ProductDTO non ha alcun riferimento esterno
	
	
	
	
	
	                          //cercare il prototto attraverso il nome
    Optional<Product> findByName(String name);

    
    
    
    
                         // se esiste almeno una sim con il prodotto attivo
    @Query("select count(c) from Sim c join c.products p where :product member of c.products")
    Long seEsisteSimConProductAttivi(@Param("product") Product product);

	
}
