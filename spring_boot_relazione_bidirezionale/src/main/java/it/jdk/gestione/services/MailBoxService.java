package it.jdk.gestione.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.gestione.dto.MailBoxDTO;
import it.jdk.gestione.entities.Account;
import it.jdk.gestione.entities.MailBox;
import it.jdk.gestione.enums.EnumError;
import it.jdk.gestione.mappings.MailBoxMapping;
import it.jdk.gestione.repositories.MailBoxRepository;

@Service
@Transactional(readOnly = true)
public class MailBoxService {

	private final MailBoxRepository mailBoxRepository;
	
	
	@Autowired
	public MailBoxService(MailBoxRepository mailBoxRepository) {
		this.mailBoxRepository = mailBoxRepository;
		
	}
	
	
	
	
	
	
	@Transactional
	public MailBoxDTO create(MailBoxDTO mailBoxDTO) throws Exception {
		
		Optional<MailBox> mailBoxOptional = mailBoxRepository.findById(mailBoxDTO.getNome());
		
		if(mailBoxOptional.isEmpty()) {
			
			MailBox mailBoxEntity = new MailBox();
			
			mailBoxEntity = MailBoxMapping.mailBoxMappingMailBoxDTOCreate(mailBoxDTO, mailBoxEntity);
			
			mailBoxRepository.save(mailBoxEntity);
			
			mailBoxDTO.setNome(mailBoxEntity.getNome());
			
			 return mailBoxDTO;
		}
		else {
			throw new Exception(EnumError.RESOURCE_ALREADY_RESENT.toString());
		}
	}

	
	
	
	
	
	
	
	@Transactional
	public MailBoxDTO update(MailBoxDTO mailBoxDTO) throws Exception{
		
	    Optional<MailBox> mailBoxOptional = mailBoxRepository.findById(mailBoxDTO.getNome());
	    
	    if(!mailBoxOptional.isEmpty()) {
	    	
	    	MailBox mailBoxEntity = mailBoxOptional.get();
	    	
	    	mailBoxEntity = MailBoxMapping.mailBoxMappingMailBoxDTOUpdate(mailBoxDTO,mailBoxEntity);
	    	
	    	return mailBoxDTO;
	    	
	    }else {
	    	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	    }
	}
	
	
	
	
	
	
	
	
	@Transactional
	public void delete(String nome) throws Exception {
		
	    Optional<MailBox> mailBoxOptional = mailBoxRepository.findById(nome);
	    
	    if(!mailBoxOptional.isEmpty()) {
	    	
	    	MailBox mailBoxEntity = mailBoxOptional.get();
	    	
	    	mailBoxRepository.delete(mailBoxEntity);
	    	
	}else {
		throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	public MailBoxDTO find(String nome)throws Exception {
		
	    Optional<MailBox> mailBoxOptional = mailBoxRepository.findById(nome);
	    
	    MailBoxDTO mailBoxDTO = new MailBoxDTO();
	    
	    if(!mailBoxOptional.isEmpty()) {
	    	
	    	MailBox mailBoxEntity = mailBoxOptional.get();
	    	
	    	mailBoxDTO = MailBoxMapping.mailBoxDTOmappingMailBoxFind(mailBoxEntity,mailBoxDTO);
	    	
	        return mailBoxDTO;
	        
	    }else {
	    	throw new Exception (EnumError.RESOURCE_NOT_FOUND.toString());
	    }
	}
}
