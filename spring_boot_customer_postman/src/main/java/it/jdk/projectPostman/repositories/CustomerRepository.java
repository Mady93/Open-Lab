package it.jdk.projectPostman.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.jdk.projectPostman.entities.CustomerEntity;
import it.jdk.projectPostman.models.CustomerPageDTO;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{


    //CRITERIA API IN COMBO CON PAGEABLE
	Page<CustomerEntity> findAll(Specification<CustomerEntity> spec, Pageable pageable);


	@Query(value="Select c from CustomerEntity c")
	Optional<CustomerPageDTO> findCustomers(int start, int max);
}
