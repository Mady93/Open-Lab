package it.jdk.projectPostman.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.jdk.projectPostman.entities.PhoneEntity;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer>{
/*
	@Query(value="select c from phone c join fetch c.cutomer where id=:id")
	public Optional<PhoneEntity> findById(Integer id);
	*/
}
