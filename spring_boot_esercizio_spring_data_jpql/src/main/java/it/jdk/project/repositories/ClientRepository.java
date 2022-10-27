package it.jdk.project.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.jdk.project.entities.Cliente;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Integer>{

	/* @Query("Select count(c) from Client c where c.codiceFiscale=:codiceFiscale and c.id<>:id")
	 Long seEsisteAutoDiverseCheHannoLaStessaTarga(@Param("codiceFiscale") String codiceFiscale, @Param("id") Long id);
	 */
	
	@Query("SELECT c FROM Cliente c where c.nome =:nome")
	List<Cliente> findByName(String nome);
	
	/*@Query("select c.nome as n , c.cognome as cg from Cliente where n=:nome and cg=:cognome")
	List<Tuple> tupla (Tuple tuple);*/
	
	
	
}
