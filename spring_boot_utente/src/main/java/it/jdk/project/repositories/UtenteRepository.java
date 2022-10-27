package it.jdk.project.repositories;

import java.util.Date;
import java.util.List;
import javax.persistence.Tuple;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.jdk.project.entities.Utente;


public interface UtenteRepository extends JpaRepository<Utente,Long> {

	
	 //     ######################## Query JPQL ############################
	 
	
	@Query(value ="SELECT Distinct u FROM Utente WHERE u.nome =: nome")
	public Utente select_name(String nome);
	
	
	Slice<Utente> findByCognome(String cognome, Pageable pageable);
	
	
	@Query(value ="Select distinct u.nome =: nome "
			+ "AND u.cognome =: cognome "
			+ "AND u.dataNascita =: dataNascita "
			+ "FROM Utente u")
	public List<Tuple> distinct_tuple(String nome,String cognome,Date dataNascita);

	
	@Query(value="select distinct u from Utente u" , countQuery = "select count(u) from Utente u")	
    public Page<Utente> getUtenti(Pageable pageable);
	public Page<Utente> findAll(Specification<Utente> spec, Pageable pageable);
}
