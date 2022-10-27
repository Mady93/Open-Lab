package it.jdk.projectPostman.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.jdk.projectPostman.entities.Cruise;

public interface CruiseRepository extends JpaRepository<Cruise, Integer>{
	
	@Query(value="SELECT DISTINCT name ,id FROM Cruise WHERE name=:name and id=:id")
	public List<Tuple> findByNameAndId(String name,Integer id);
}
