package it.jdk.gestione.mappings;

import it.jdk.gestione.dto.AccountDTO;
import it.jdk.gestione.entities.Account;

public final class AccountMapping {

	
	
	
	
	//create
	public static Account accountMappingAccountDTOCreate(AccountDTO accountDTO, Account accountEntity) {
		
		accountEntity.setCognome(accountDTO.getCognome());
		accountEntity.setMailbox(accountDTO.getMailbox());
		accountEntity.setMessaggioDestinatario(accountDTO.getMessaggioDestinatario());
		accountEntity.setMessaggioMittente(accountDTO.getMessaggioMittente());
		accountEntity.setNome(accountDTO.getNome());
		
		return accountEntity;
	}
	
	
	
	
	
	
	//update
public static Account accountMappingAccountDTOUpdate(AccountDTO accountDTO,Account accountEntity) {
		
		
		accountEntity.setCognome(accountDTO.getCognome());
		accountEntity.setEmail(accountDTO.getEmail());
		accountEntity.setMailbox(accountDTO.getMailbox());
		accountEntity.setMessaggioDestinatario(accountDTO.getMessaggioDestinatario());
		accountEntity.setMessaggioMittente(accountDTO.getMessaggioMittente());
		accountEntity.setNome(accountDTO.getNome());
		
		return accountEntity;
	}
	
	
	
	

	
	//find()
	public static AccountDTO accountDTOmappingAccountEntityFind(Account accountEntity, AccountDTO accountDTO) {
		
		accountDTO.setCognome(accountEntity.getCognome());
		accountDTO.setEmail(accountEntity.getEmail());
		accountDTO.setMailbox(accountEntity.getMailbox());
		accountDTO.setMessaggioDestinatario(accountEntity.getMessaggioDestinatario());
		accountDTO.setMessaggioMittente(accountEntity.getMessaggioMittente());
		accountDTO.setNome(accountEntity.getNome());
		
		return accountDTO;
	}
}
