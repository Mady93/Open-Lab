package it.jdk.gestione.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jdk.gestione.entities.Messaggio;

@Repository
public interface MessaggioRepository extends JpaRepository<Messaggio,Integer>{

}
