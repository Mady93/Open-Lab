package it.jdk.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.jdk.project.dto.ValidationDTO;
import it.jdk.project.entities.Validation;

/**
 *  @author  name
 *  @version 1.0
 *  @since   date
 */
@Repository
public interface IValidationRepository extends JpaRepository<Validation, Integer>{
   
	               // ValidationDTO ha riferimento a UserDTO. Esiste grazie all'utente quindi lo incorpora alla creazione
	
	
	
     	@Query(value="select v from Validation v join v.businessUser u where u.id=:id")
		Optional<Validation> findByUserId(Integer id);
}