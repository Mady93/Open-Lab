package it.jdk.project.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.jdk.project.EnumError;
import it.jdk.project.dto.UtenteDTO;
import it.jdk.project.entities.Utente;
import it.jdk.project.repositories.UtenteRepository;

@Service
@Transactional(readOnly=true)
public class UtenteService {
	private UtenteRepository utenteRepository; // Serve solo per la persistenza persist() = save()
	
	@Autowired
	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}
	
	
	
	
	@Transactional
	public UtenteDTO create(UtenteDTO utenteDTO) {
		
	   Utente utenteEntity = new Utente();
	   
	    utenteEntity.setCap(utenteDTO.getCap());
	    utenteEntity.setCognome(utenteDTO.getCognome());
	    utenteEntity.setDataNascita(utenteDTO.getDataNascita());
	    utenteEntity.setNazionalita(utenteDTO.getNazionalita());
	    utenteEntity.setNome(utenteDTO.getNome());
	    utenteEntity.setNumeroCivico(utenteDTO.getNumeroCivico());
	    utenteEntity.setTelefono(utenteDTO.getTelefono());
	    utenteEntity.setVia(utenteDTO.getVia());
	    
	    utenteRepository.save(utenteEntity);
	    
	 // dopo la persitenza ho l'id è l'assoccio
	    utenteDTO.setId(utenteEntity.getId());  
	    
	    return utenteDTO;
	}
	
	
	
	
	
	@Transactional
	public UtenteDTO update(UtenteDTO utenteDTO) throws Exception {
		
	    Optional<Utente> utenteOptional = utenteRepository.findById(utenteDTO.getId());
	    
	    if(!utenteOptional.isEmpty()) {
	    	
	        Utente utenteEntity = utenteOptional.get();
	        
	        utenteEntity.setCap(utenteDTO.getCap());
		    utenteEntity.setCognome(utenteDTO.getCognome());
		    utenteEntity.setDataNascita(utenteDTO.getDataNascita());
		    utenteEntity.setNazionalita(utenteDTO.getNazionalita());
		    utenteEntity.setNome(utenteDTO.getNome());
		    utenteEntity.setNumeroCivico(utenteDTO.getNumeroCivico());
		    utenteEntity.setTelefono(utenteDTO.getTelefono());
		    utenteEntity.setVia(utenteDTO.getVia());
		    
		    return utenteDTO;
		    
	    }else {
	    	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	    }
	    
	}
	
	
	
	
	
	@Transactional
	public void delete(Long id) throws Exception {
		
	    Optional<Utente> utenteOptional = utenteRepository.findById(id);
	    
	    if(!utenteOptional.isEmpty()) {
	    	
	    	utenteRepository.delete(utenteOptional.get());
	    	
	}else {
		throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	}
}
	
	
	
	
	
	public UtenteDTO find(Long id) throws Exception {
		
	    Optional<Utente> utenteOptional = utenteRepository.findById(id);
	    
	    if(!utenteOptional.isEmpty()) {
	    	
	    	UtenteDTO utenteDTO = new UtenteDTO();
	    	
	        Utente utenteEntity =utenteOptional.get();
	        
	        utenteDTO.setId(utenteEntity.getId());
	        utenteDTO.setCap(utenteEntity.getCap());
	        utenteDTO.setCognome(utenteEntity.getCognome());
	        utenteDTO.setDataNascita(utenteEntity.getDataNascita());
	        utenteDTO.setNazionalita(utenteEntity.getNazionalita());
	        utenteDTO.setNome(utenteEntity.getNome());
	        utenteDTO.setNumeroCivico(utenteEntity.getNumeroCivico());
	        utenteDTO.setTelefono(utenteEntity.getTelefono());
	        utenteDTO.setVia(utenteEntity.getVia());
	        
	        return utenteDTO;
	        
	    }else {
	    	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	    	//throw new RuntimeException();
	    } 
	}
}
