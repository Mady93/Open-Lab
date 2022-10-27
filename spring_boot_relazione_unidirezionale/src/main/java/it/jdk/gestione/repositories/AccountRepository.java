package it.jdk.gestione.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jdk.gestione.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,String>{

	/*
	 * Il recuperare una mailbox con il suo account utilizzando il nome della mailbox
	 * 
       Il recuperare tutte le mailbox di uno specifico account tramite la mail dell'account
       
    Recuperare la lista di mailbox , con almeno un messaggio,  di uno specifico account tramite la mail dell'account
	 */
	
	
}
