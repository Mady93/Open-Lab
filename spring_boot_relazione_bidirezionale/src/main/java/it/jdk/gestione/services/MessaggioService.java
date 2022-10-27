package it.jdk.gestione.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.gestione.dto.MessaggioDTO;
import it.jdk.gestione.entities.Messaggio;
import it.jdk.gestione.enums.EnumError;
import it.jdk.gestione.mappings.MessaggioMapping;
import it.jdk.gestione.repositories.MessaggioRepository;

@Service
@Transactional(readOnly = true)
public class MessaggioService {

	private final MessaggioRepository messaggioRepository;
	
	@Autowired
	public MessaggioService(MessaggioRepository messaggioRepository) {
		this.messaggioRepository = messaggioRepository;
	}
	
	
	
	
	
	
	
	@Transactional
	public MessaggioDTO create(MessaggioDTO messaggioDTO) throws Exception {
		
		Optional<Messaggio> messaggioOptional = messaggioRepository.findById(messaggioDTO.getId());
		
		if(messaggioOptional.isEmpty()) {
		
		Messaggio messaggioEntity = new Messaggio();
			
		messaggioEntity = MessaggioMapping.messaggioEntityMappingMessaggioDTOCreate(messaggioDTO, messaggioEntity);
		
	    messaggioRepository.save(messaggioEntity);
	    
	    messaggioDTO.setId(messaggioEntity.getId());	
	    
	    return messaggioDTO;
	    
		}else {
			throw new Exception(EnumError.RESOURCE_ALREADY_RESENT.toString());
		}
	}
	
	
	
	
	
	
	
	@Transactional
	public MessaggioDTO update(MessaggioDTO messaggioDTO) throws Exception{
		
	    Optional<Messaggio> messaggioOptional = messaggioRepository.findById(messaggioDTO.getId());
	    
	    if(!messaggioOptional.isEmpty()) {
	    	
	    	Messaggio messaggioEntity = messaggioOptional.get();
	    	
	    	messaggioEntity = MessaggioMapping.messaggioEntityMappingMessaggioDTOUpdate(messaggioDTO, messaggioEntity);
	    	
	        return messaggioDTO;
	        
	    }else {
	    	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	    }
	   
	}


	
	
	
	
	
	@Transactional
	public void delete(Integer id) throws Exception {
		
	    Optional<Messaggio> messaggioOptional = messaggioRepository.findById(id);
	    
	    if(!messaggioOptional.isEmpty()) {
	    	
	    	Messaggio messaggioEntity = messaggioOptional.get();
	    	
	    	messaggioRepository.delete(messaggioEntity);
	    	
	}else {
		throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}
	
	
	
	
	
	
	
	
	public MessaggioDTO find(Integer id) throws Exception {
		
	    Optional<Messaggio> messaggioOptional = messaggioRepository.findById(id);
	    
	    MessaggioDTO messaggioDTO = new MessaggioDTO();;
	    
	    if(!messaggioOptional.isEmpty()) {
	    	
	    	Messaggio messaggioEntity = messaggioOptional.get();
	    	
	    	messaggioDTO = MessaggioMapping.messaggioDTOmappingMessaggioEntityFind(messaggioEntity, messaggioDTO);
	    	
	        return messaggioDTO;
	        
	    }else {
	    	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	    }
	}	
}
