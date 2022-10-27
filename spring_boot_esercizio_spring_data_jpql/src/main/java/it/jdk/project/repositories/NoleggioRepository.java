package it.jdk.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jdk.project.entities.Noleggio;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Integer> {
	
}
