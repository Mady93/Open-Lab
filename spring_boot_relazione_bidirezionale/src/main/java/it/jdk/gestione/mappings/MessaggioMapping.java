package it.jdk.gestione.mappings;

import it.jdk.gestione.dto.MessaggioDTO;
import it.jdk.gestione.entities.Messaggio;

public final class MessaggioMapping {

	
	
	
	//create()
	public static Messaggio messaggioEntityMappingMessaggioDTOCreate(MessaggioDTO messaggioDTO, Messaggio messaggioEntity) {
		
		messaggioEntity.setAccountDestinatario(messaggioDTO.getAccountDestinatario());
		messaggioEntity.setAccountMittente(messaggioDTO.getAccountMittente());
		messaggioEntity.setMailBox(messaggioDTO.getMailBox());
		messaggioEntity.setOggetto(messaggioDTO.getOggetto());
		messaggioEntity.setTesto(messaggioDTO.getTesto());
		
		return messaggioEntity;
	}
	
	
	
	
	
	
	//update()
public static Messaggio messaggioEntityMappingMessaggioDTOUpdate(MessaggioDTO messaggioDTO, Messaggio messaggioEntity) {
		
		messaggioEntity.setAccountDestinatario(messaggioDTO.getAccountDestinatario());
		messaggioEntity.setAccountMittente(messaggioDTO.getAccountMittente());
		messaggioEntity.setMailBox(messaggioDTO.getMailBox());
		messaggioEntity.setOggetto(messaggioDTO.getOggetto());
		messaggioEntity.setTesto(messaggioDTO.getTesto());
		
		return messaggioEntity;
	}
	
	
	
	
	
	
	
	//find()
	public static MessaggioDTO messaggioDTOmappingMessaggioEntityFind(Messaggio messaggioEntity, MessaggioDTO messaggioDTO) {
		
		messaggioDTO.setAccountDestinatario(messaggioEntity.getAccountDestinatario());
		messaggioDTO.setAccountMittente(messaggioEntity.getAccountMittente());
		messaggioDTO.setId(messaggioEntity.getId());
		messaggioDTO.setMailBox(messaggioEntity.getMailBox());
		messaggioDTO.setOggetto(messaggioEntity.getOggetto());
		messaggioDTO.setTesto(messaggioEntity.getTesto());
		
		return messaggioDTO;
	}
}
