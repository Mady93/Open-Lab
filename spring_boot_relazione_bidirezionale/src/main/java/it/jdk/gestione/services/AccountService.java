package it.jdk.gestione.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.gestione.dto.AccountDTO;
import it.jdk.gestione.entities.Account;
import it.jdk.gestione.enums.EnumError;
import it.jdk.gestione.mappings.AccountMapping;
import it.jdk.gestione.repositories.AccountRepository;
import it.jdk.gestione.repositories.MailBoxRepository;
import it.jdk.gestione.repositories.MessaggioRepository;

@Service
@Transactional(readOnly=true)
public class AccountService {

	private final AccountRepository accountRepository;

	
	@Autowired
	public AccountService(AccountRepository accountRepository,MailBoxRepository mailboxRepository,MessaggioRepository messaggioRepository) {
		this.accountRepository = accountRepository;
	}
	
	
	
	
	
	@Transactional
	public AccountDTO create(AccountDTO accountDTO) {
		
		Account accountEntity = new Account();
		
		accountEntity = AccountMapping.accountMappingAccountDTOCreate(accountDTO, accountEntity);
		
	    accountRepository.save(accountEntity);
	    
		accountDTO.setEmail(accountEntity.getEmail());

	    
	    return accountDTO;
	}
	
	
	
	
	@Transactional
	public AccountDTO update(AccountDTO accountDTO) throws Exception {
		
	    Optional<Account> optionalAccountEntity = accountRepository.findById(accountDTO.getEmail());
	    
	    if(!optionalAccountEntity.isEmpty()) {
	    	
	    	Account accountEntity = optionalAccountEntity.get();
	    	
	    	accountEntity = AccountMapping.accountMappingAccountDTOUpdate(accountDTO,accountEntity);
	        
	        return accountDTO;
	        
	    }else {
	    	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	    }
	}
	
	
	
	
	@Transactional
	public void delete(String email) throws Exception {
		
	    Optional<Account> optionalAccountEntity = accountRepository.findById(email);
	    if(!optionalAccountEntity.isEmpty()) {
	    	
	    	Account accountEntity = optionalAccountEntity.get();
	    	
	    	accountRepository.delete(accountEntity);
	}else {
		throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}
	
	
	
	
	
	
	
	public AccountDTO find(String email) throws Exception{
		
	    Optional<Account> optionalAccountEntity = accountRepository.findById(email);
	    
	    AccountDTO accountDTO = new AccountDTO();
	    
	    if(!optionalAccountEntity.isEmpty()) {
	    	
	    	Account accountEntity = optionalAccountEntity.get();
	    	
	    	accountDTO = AccountMapping.accountDTOmappingAccountEntityFind(accountEntity, accountDTO);

	    	return accountDTO;
	        
	    }else {
	    	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	    }
	}
}
