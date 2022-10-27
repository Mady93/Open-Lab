package it.jdk.gestione.mappings;

import it.jdk.gestione.dto.MailBoxDTO;
import it.jdk.gestione.entities.MailBox;

public final class MailBoxMapping {

	
	
	
	
	
	//create
	public static MailBox mailBoxMappingMailBoxDTOCreate(MailBoxDTO mailBoxDTO, MailBox mailBoxEntity) {
		
		mailBoxEntity.setAccountMittente(mailBoxDTO.getAccountMittente());
		mailBoxEntity.setMessaggio(mailBoxDTO.getMessaggio());
		
		return mailBoxEntity;
	}
	
	
	
	//update
public static MailBox mailBoxMappingMailBoxDTOUpdate(MailBoxDTO mailBoxDTO, MailBox mailBoxEntity) {
		
		mailBoxEntity.setAccountMittente(mailBoxDTO.getAccountMittente());
		mailBoxEntity.setNome(mailBoxDTO.getNome());
		mailBoxEntity.setMessaggio(mailBoxDTO.getMessaggio());
		
		return mailBoxEntity;
	}
	
	
	
	
	
	//find()
	public static MailBoxDTO mailBoxDTOmappingMailBoxFind(MailBox mailBoxEntity, MailBoxDTO mailBoxDTO) {
		
		mailBoxDTO.setAccountMittente(mailBoxEntity.getAccountMittente());
		mailBoxDTO.setMessaggio(mailBoxEntity.getMessaggio());
		mailBoxDTO.setNome(mailBoxEntity.getNome());
		
		return mailBoxDTO;
	}
}
