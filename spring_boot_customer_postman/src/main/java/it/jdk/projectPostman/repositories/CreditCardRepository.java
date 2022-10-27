package it.jdk.projectPostman.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.jdk.projectPostman.entities.CreditCardEntity;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Integer>{
/*
	@Query(value="select c from CreditCard c where id =:id")
	public Optional<CreditCardEntity> findById(Integer id);
	*/
}
