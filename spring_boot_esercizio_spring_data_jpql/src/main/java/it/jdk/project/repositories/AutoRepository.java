package it.jdk.project.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import it.jdk.project.entities.Auto;
import it.jdk.project.models.AutoDTO;
import it.jdk.project.page_auto.PageDTO;

//@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer>, JpaSpecificationExecutor<Auto>{

	// find by id con la dto projection
	@Query("select new it.jdk.project.models.AutoDTO(c.id, c.targa, c.modello, c.version) from Auto c where c.id=:id")
	Optional<AutoDTO> findAutoById(@Param("id") Long id);
	
	
	Optional<Auto> findById(Long id);
	
	
	

	Optional<Auto> findAutoByTarga(String targa);

	
	
	                                // PAGE Auto
	@Query(value = "Select a from Auto a")
	Optional<PageDTO> findAuto(int number, int size);
	
	
	

	// CRITERIA API COMBO Page
	Page<Auto> findAll(Specification<Auto> spec, Pageable pageable);

	
	
	
	
	 @Query("Select count(a) from Auto a where a.targa=:targa and a.id<>:id")
	 Long seEsisteAutoDiverseCheHannoLaStessaTarga(@Param("targa") String targa, @Param("id") Long id);
	 
	 
	 
	 //Optional<Auto>findByTargaAndId(String targa, Long id);
}
