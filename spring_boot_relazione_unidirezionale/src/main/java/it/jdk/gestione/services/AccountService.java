package it.jdk.gestione.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.gestione.dto.AccountDTO;
import it.jdk.gestione.entities.Account;
import it.jdk.gestione.error.EnumError;
import it.jdk.gestione.repositories.AccountRepository;

@Service
@Transactional(readOnly=true)
public class AccountService {

	private final AccountRepository accountRepository;
	
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	
	
	
	
	@Transactional
	public AccountDTO create(AccountDTO accountDTO) {
		
	    Account accountEntity = new Account();
	    
	    accountEntity.setNome(accountDTO.getNome());
	    
	    accountEntity.setCognome(accountDTO.getCognome());
	    
	    accountEntity.setEmail(accountDTO.getEmail());
	    
	    accountRepository.save(accountEntity);
	    
	    accountDTO.setEmail(accountEntity.getEmail());
	    
	    return accountDTO;
	}
	
	
	
	
	
	
	
	@Transactional
	public AccountDTO update(AccountDTO accountDTO) throws Exception{
		
	    Optional<Account> accountOptional = accountRepository.findById(accountDTO.getEmail());
	    
	    if(!accountOptional.isEmpty()) {
	    	
	        Account accountEntity = accountOptional.get();
	        
	        accountEntity.setNome(accountDTO.getNome());
	        
	        accountEntity.setCognome(accountDTO.getCognome());
	        
	        accountDTO.setEmail(accountEntity.getEmail());
	        
	        return accountDTO;
	        
	    }else {
	    	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	    }
	    
	}
	
	
	
	
	
	
	@Transactional
	public void delete(String email) throws Exception {
		
	    Optional<Account> accountOptional = accountRepository.findById(email);
	    
	    if(!accountOptional.isEmpty()) {
	    	
	    	accountRepository.delete(accountOptional.get());
	}else {
		throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}
	
	
	
	
	
	
	public AccountDTO find(String email) throws Exception{
		
	    Optional<Account> accountOptional = accountRepository.findById(email);
	    
	    if(!accountOptional.isEmpty()) {
	    	
	    	AccountDTO accountDTO = new AccountDTO();
	    	
	        Account a = accountOptional.get();
	        
	        accountDTO.setEmail(a.getEmail());
	        
	        accountDTO.setNome(a.getNome());
	        
	        accountDTO.setCognome(a.getCognome());
	        
	        return accountDTO;
	    }else {
	    	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	    }
}
}
