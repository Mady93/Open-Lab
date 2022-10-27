package it.jdk.projectPostman.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.jdk.projectPostman.entities.Ship;
import it.jdk.projectPostman.models.CustomerPageDTO;

public interface ShipRepository extends JpaRepository<Ship, Integer> {

}
