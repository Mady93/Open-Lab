package it.jdk.project.services;

import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.jdk.project.dto.BusinessUserDTO;
import it.jdk.project.dto.ValidationDTO;
import it.jdk.project.entities.BusinessUser;
import it.jdk.project.entities.Validation;
import it.jdk.project.repositories.IBusinessUserRepository;
import it.jdk.project.repositories.IValidationRepository;

@Service
public class ValidationService {
	
private final IValidationRepository repository;
private final IBusinessUserRepository userRepository;

@Autowired
public ValidationService(IValidationRepository repository, IBusinessUserRepository userRepository) {
	this.repository = repository;
	this.userRepository = userRepository;
}








@Transactional(readOnly = false)
public ValidationDTO validateUser(ValidationDTO validationDTO) {
	
    Validation validation = new Validation();
    
    validation.setDate(validationDTO.getDate());
    
    BusinessUser businessUser = userRepository.getById(validationDTO.getUser().getId());
    
    if(businessUser.getVersion() != validationDTO.getUser().getVersion()) {
    	
        throw new OptimisticLockingFailureException("Entity ID and Dto ID mismatch");
    }
    validation.setBusinessUser(businessUser);
    
    repository.save(validation);
    
    BusinessUserDTO businessUserDTO = new BusinessUserDTO(businessUser.getId(),
    		
            businessUser.getFirstName(), businessUser.getLastName(),
            businessUser.getFiscalCode(), businessUser.getVersion());
    		return new ValidationDTO(validation.getId(), validation.getDate(),
            businessUserDTO, validation.getVersion());
}











public Optional<ValidationDTO> getValidationById(Integer id) {
    try {
        Optional<Validation> validation =repository.findById(id);
        
        BusinessUser user = validation.get().getBusinessUser();
        
        BusinessUserDTO userDTO = new BusinessUserDTO(user.getId(), user.getFirstName(),
        		
                user.getLastName(), user.getFiscalCode(),user.getVersion());
        
        return Optional.of(new ValidationDTO(validation.get().getId(), validation.get().getDate(),
                userDTO, validation.get().getVersion()));
        
    } catch (NoResultException e){
        return Optional.empty();
    }
}









public Optional<ValidationDTO> getValidationByIdUser(Integer userId) {
	 try {
           Optional<Validation> validation = repository.findByUserId(userId);
           BusinessUser user = validation.get().getBusinessUser();
           BusinessUserDTO userDTO = new BusinessUserDTO(user.getId(), user.getFirstName(),
                   user.getLastName(), user.getFiscalCode(),user.getVersion());
           return Optional.of(new ValidationDTO(validation.get().getId(), validation.get().getDate(),
                   userDTO, validation.get().getVersion()));
       } catch (NoResultException e){
           return Optional.empty();
       	}
	}
}
