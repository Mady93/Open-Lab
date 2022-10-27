package it.jdk.projectPostman.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.jdk.projectPostman.entities.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{
/*
	@Query(value="Select a from AddressEntity a join fetch a.customer where a.id=:id", nativeQuery = true)
	Optional<AddressEntity> findById(@Param("id") Integer id);
*/
}
