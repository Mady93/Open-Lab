package it.jdk.gestione.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.gestione.dto.AccountDTO;
import it.jdk.gestione.dto.MailBoxDTO;
import it.jdk.gestione.entities.Account;
import it.jdk.gestione.entities.MailBox;
import it.jdk.gestione.repositories.AccountRepository;
import it.jdk.gestione.repositories.MailBoxRepository;

@Service
@Transactional(readOnly = true)
public class MailBoxService {

	private final MailBoxRepository mailBoxRepository;
	private final AccountRepository accountRepository;
	
	@Autowired
	public MailBoxService(MailBoxRepository mailBoxRepository, AccountRepository accountRepository) {
		this.mailBoxRepository = mailBoxRepository;
		this.accountRepository = accountRepository;
	}
	
	
	
	
	
	@Transactional
	public MailBoxDTO create(MailBoxDTO dto) {
		
		MailBox mailBox = new MailBox();
		
	    Account account =  accountRepository.getReferenceById(dto.getAccountMittente().getEmail());
	    
	    mailBox.setAccountMittente(account);
	    
	    mailBoxRepository.save(mailBox);
	    
	    dto.setNome(mailBox.getNome());
	    
	    return dto;
	}
	
	@Transactional
	public MailBoxDTO update(MailBoxDTO dto) {
	    Optional<MailBox> o = mailBoxRepository.findById(dto.getNome());
	    if(!o.isEmpty()) {
	    	MailBox a = o.get();
	        a.setNome(dto.getNome());
	        Account account =  accountRepository.getReferenceById(dto.getAccountMittente().getEmail());
	        a.setAccountMittente(account);
	    }
	    return dto;
	}
	
	@Transactional
	public void delete(String nome) throws Exception {
	    Optional<MailBox> o = mailBoxRepository.findById(nome);
	    if(!o.isEmpty()) {
	    	mailBoxRepository.delete(o.get());
	}else {
		throw new Exception("La mail box non è stata trovata");
	}
	}
	
	public MailBoxDTO find(String nome)throws Exception {
	    Optional<MailBox> o = mailBoxRepository.findById(nome);
	    MailBoxDTO dto;
	    if(!o.isEmpty()) {
	    	
	    	dto = new MailBoxDTO();
	    	MailBox mailBox = o.get();
	        dto.setNome(mailBox.getNome());

	        Account aMitt = accountRepository.getReferenceById(dto.getAccountMittente().getEmail());
	        
	        AccountDTO accountDTO = new AccountDTO();
	        
	        accountDTO.setEmail(aMitt.getEmail());
	        accountDTO.setCognome(aMitt.getCognome());
	        accountDTO.setNome(aMitt.getNome());
	        dto.setAccountMittente(accountDTO);
	        return dto;
	    }else {
	    	throw new Exception ("La mail box non è stata trovata");
	    }
}
}
