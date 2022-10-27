package it.jdk.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.jdk.project.dto.BusinessUserDTO;
import it.jdk.project.dto.BusinessUserPageDTO;
import it.jdk.project.entities.BusinessUser;

@Repository
public interface IBusinessUserRepository extends JpaRepository<BusinessUser, Integer>, JpaSpecificationExecutor<BusinessUser>{
	
	
	                                     //NEL DTO NO CI SONO RIFERIMENTI AD ALTRE CLASSI
	
	 //@Query("select new it.mobileapp.dmodel.BusinessUserDTO(c.id,c.firstName,c.lastName,c.fiscalCode,c.version) from BusinessUser c where c.id=:id")
	

	                           //findById
	@Query("select c.id, c.firstName, c.lastName, c.fiscalCode, c.version from BusinessUser c where c.id=:id")
    Optional<BusinessUserDTO> findUserById(@Param("id") Integer id);
	
	

	
	
	                           //CRITERIA API COMBO PAGEABLE
		Page<BusinessUser> findAll(Specification<BusinessUser> spec, Pageable pageable);


		
		
		
							         //PAGE USERS
		@Query(value="Select u from BusinessUser u")
		Optional<BusinessUserPageDTO> findUsers(int start, int max);
		
		
		
		
		
		                             //Trovare user attraverso il nome
		Optional <BusinessUser> findByFiscalCode(String fiscalCode);
		
		
		
		
		
		
		             //Contare gli utenti che hanno il cf in comune.Seve per l'errore
		@Query("Select count(c) from BusinessUser c where c.filcalCode=:fiscalCode and c.id<>:id")
	    Long seEsisteUtentiDiversiCheHannoLoStessoFiscalCode(@Param("id") Integer id , @Param("fiscalCode") String fiscalCode);

		/*
		@Query(value="Select b from BusinessUser where b.id=:id and b.fiscalCode=:fiscalCode")
		Optional<BusinessUser>updateUserIfFiscalCodeIsDifferent(BusinessUserDTO businessUserDTO,Integer id, String fiscalCode);*/
	}



