package it.jdk.gestione.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.jdk.gestione.dto.AccountDTO;
import it.jdk.gestione.dto.MailBoxDTO;
import it.jdk.gestione.dto.MessaggioDTO;
import it.jdk.gestione.entities.Account;
import it.jdk.gestione.entities.MailBox;
import it.jdk.gestione.entities.Messaggio;
import it.jdk.gestione.repositories.AccountRepository;
import it.jdk.gestione.repositories.MailBoxRepository;
import it.jdk.gestione.repositories.MessaggioRepository;

@Service
@Transactional(readOnly = true)
public class MessaggioService {

	private final MessaggioRepository messaggioRepository;
	private final MailBoxRepository mailBoxRepository;
	private final AccountRepository accountRepository;
	
	@Autowired
	public MessaggioService(MailBoxRepository mailBoxRepository, AccountRepository accountRepository, MessaggioRepository messaggioRepository) {
		this.mailBoxRepository = mailBoxRepository;
		this. accountRepository = accountRepository;
		this.messaggioRepository = messaggioRepository;
	}
	
	@Transactional
	public MessaggioDTO create(MessaggioDTO dto) {
		Messaggio a = new Messaggio();
	    Account aMitt =  accountRepository.getReferenceById(dto.getAccountMittente().getEmail());
	    Account aDest = accountRepository.getReferenceById(dto.getAccountDestinatario().getEmail());
	    MailBox mbox = mailBoxRepository.getReferenceById(dto.getMailBox().getNome());
	    a.setAccountMittente(aMitt);
	    a.setAccountDestinatario(aDest);
	    a.setMailBox(mbox);
	    a.setOggetto(dto.getOggetto());
	    a.setTesto(dto.getTesto());
	    messaggioRepository.save(a);
	    dto.setId(a.getId());	    
	    return dto;
	}
	
	@Transactional
	public MessaggioDTO update(MessaggioDTO dto) {
	    Optional<Messaggio> o = messaggioRepository.findById(dto.getId());
	    if(!o.isEmpty()) {
	    	Messaggio a = o.get();
	        a.setOggetto(dto.getOggetto());
	        a.setTesto(dto.getTesto());
	        Account aMitt =  accountRepository.getReferenceById(dto.getAccountMittente().getEmail());
	        Account aDest = accountRepository.getReferenceById(dto.getAccountDestinatario().getEmail());
	        MailBox mailbox = mailBoxRepository.getReferenceById(dto.getMailBox().getNome());
	        a.setAccountMittente(aMitt);
	        a.setAccountDestinatario(aDest);
	        a.setMailBox(mailbox);
	    }
	    return dto;
	}
	
	
	@Transactional
	public void delete(Integer id) throws Exception {
	    Optional<Messaggio> o = messaggioRepository.findById(id);
	    if(!o.isEmpty()) {
	    	messaggioRepository.delete(o.get());
	}else {
		throw new Exception("Il messaggio non è stata trovato");
		}
	}
	
	
	public MessaggioDTO find(Integer id) throws Exception {
	    Optional<Messaggio> o = messaggioRepository.findById(id);
	    
	    MessaggioDTO dto;
	    
	    if(!o.isEmpty()) {
	    	
	    	dto = new MessaggioDTO();
	    	
	    	Messaggio messaggioEntity = o.get();
	    	
	        Account aMitt =  accountRepository.getReferenceById(dto.getAccountMittente().getEmail());
	        Account aDest = accountRepository.getReferenceById(dto.getAccountDestinatario().getEmail());
	        MailBox mailbox = mailBoxRepository.getReferenceById(dto.getMailBox().getNome());
	        
	        
	        AccountDTO accountMittenteDTO = new AccountDTO();
	        AccountDTO accountDestinatarioDTO = new AccountDTO();
	        MailBoxDTO mailboxDTO = new MailBoxDTO();
	        
	        accountMittenteDTO.setEmail(aMitt.getEmail());
	        accountMittenteDTO.setCognome(aMitt.getCognome());
	        accountMittenteDTO.setNome(aMitt.getNome());
	        
	        accountDestinatarioDTO.setCognome(aDest.getCognome());
	        accountDestinatarioDTO.setEmail(aDest.getEmail());
	        accountDestinatarioDTO.setNome(aDest.getNome());

	        mailboxDTO.setAccountMittente(accountMittenteDTO);
	        mailboxDTO.setNome(mailbox.getNome());
	        
	        dto.setOggetto(messaggioEntity.getOggetto());
	        dto.setTesto(messaggioEntity.getTesto());
	        dto.setId(messaggioEntity.getId());
	        dto.setAccountMittente(accountMittenteDTO);
	        dto.setAccountDestinatario(accountDestinatarioDTO);
	        dto.setMailBox(mailboxDTO);
	     
	        return dto;
	    }else {
	    	throw new Exception("Il messaggio non è stato trovato");
	    }
	   
}
	
}
