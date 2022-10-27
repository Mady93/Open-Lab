package it.jdk.gestione.repositories;

import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.jdk.gestione.entities.MailBox;

@Repository
public interface MailBoxRepository extends JpaRepository<MailBox,String> {
	
	/*
	 * Il recuperare una mailbox con il suo account utilizzando il nome della mailbox
	 * 
       Il recuperare tutte le mailbox di uno specifico account tramite la mail dell'account
       
    Recuperare la lista di mailbox , con almeno un messaggio,  di uno specifico account tramite la mail dell'account
	 */
	
	
	
	
	/*
	@Query(value ="SELECT m FROM MailBox m join fetch m.account a WHERE m.nome =: nome")
	public MailBox mailBox_name(String nome);
*/
	
	@Query(value ="select m from MailBox jetch join m.account where m.nome=:nome" , nativeQuery = true)
	public List<MailBox> getMailBoxByName(String nome);
	
	
	
	
	@Query(value = "Select m from MailBox m join fetch m.account.email =: email" , nativeQuery = true)
	public List<MailBox> getMailBoxByEmail(String email);
	
	
	
	
	 @Query(value="Select m from MailBox m where m.account.email =: email",
	 countQuery = "Select count(m) from MailBox where m.account.email =: email", nativeQuery = true)
	 public Page<MailBox> getMailBoxByEmail(String email, Pageable pageable); 
	
	
	 
}
