package it.jdk.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.project.criteria.api.BusinessUserSpec;
import it.jdk.project.dto.BusinessUserDTO;
import it.jdk.project.dto.BusinessUserFilterDTO;
import it.jdk.project.dto.BusinessUserPageDTO;
import it.jdk.project.entities.BusinessUser;
import it.jdk.project.entities.Product;
import it.jdk.project.repositories.IBusinessUserRepository;

@Service
@Transactional(readOnly = true)
public class BusinessUserService {

	private final IBusinessUserRepository repository;

	@Autowired
	public BusinessUserService(IBusinessUserRepository repository) {
		this.repository = repository;
	
	}
	
	 @Transactional(readOnly = false)
	public BusinessUserDTO create(BusinessUserDTO dto) throws Exception{
		
				if(dto != null) {
			
				BusinessUser entity = new BusinessUser();
				entity.setFirstName(dto.getFirstName());
				entity.setFiscalCode(dto.getFiscalCode());
				entity.setLastName(dto.getLastName());
				repository.save(entity);
				dto.setId(entity.getId());
				dto.setVersion(0);
				return dto;
		}else {
			throw new Exception("Error");
		}
	}
	

	 public Optional<BusinessUserDTO> findUserByFiscalCode(String fiscalCode) {
		 Optional<BusinessUser> entityOptional = repository.findByFiscalCode(fiscalCode);
		 try {
				return Optional.of(new BusinessUserDTO(entityOptional.get().getId(),entityOptional.get().getFirstName(),entityOptional.get().getLastName(),entityOptional.get().getFiscalCode(),entityOptional.get().getVersion()));
 
		 }catch(NoResultException e) {
			return Optional.empty();
		 }
		 
	 }
	 
	
	
	
	
	
	
	public Optional<BusinessUserPageDTO> findUsers(int page, int pageSize) {
		Page<BusinessUser> pageUser = repository.findAll(PageRequest.of(page, pageSize));
		return makeDTO(pageUser, page, pageSize);
	}

	
	
	
	
	
	public Optional<BusinessUserPageDTO> findUserByFilter(int start, int max, BusinessUserFilterDTO filter) {
		Specification<BusinessUser> spec = BusinessUserSpec.getUsersByFilter(filter);
		Page<BusinessUser> pageUser =repository.findAll(spec, PageRequest.of(start, max));
		return makeDTO(pageUser, start, max);
	}

	
	
	
	
	private Optional<BusinessUserPageDTO> makeDTO(Page<BusinessUser> pageUser, int page, int pageSize) {
		List<BusinessUserDTO> dtoList = new ArrayList<>();
		for (BusinessUser entity : pageUser.getContent()) {
			dtoList.add(new BusinessUserDTO(entity.getId(), entity.getLastName(),
					entity.getFirstName(), entity.getFiscalCode(), entity.getVersion()));
		}
		try {
			if (dtoList.isEmpty())
				throw new NoResultException("Empty list");
			return Optional.of(new BusinessUserPageDTO(pageUser.getTotalElements(), page, pageSize, dtoList));
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	
	
	
	
	
	public List<BusinessUserDTO> findUsersList() throws Exception{
		
		List<BusinessUser> listEntity = this.repository.findAll();
		List<BusinessUserDTO> listDTO = new ArrayList<>();
	
		for (BusinessUser entity : listEntity) {
			BusinessUserDTO dto = new BusinessUserDTO();
			
			if(listEntity != null) {
				
				dto.setFirstName(entity.getFirstName());
				dto.setFiscalCode(entity.getFiscalCode());
				dto.setLastName(entity.getLastName());
				dto.setVersion(entity.getVersion());
				dto.setId(entity.getId());
				listDTO.add(dto);
			}else {
				throw new Exception("Users not found");
			}
		}
		return listDTO;
	
	}


	
	
	
	
	@Transactional(readOnly = false)
    public BusinessUserDTO updateUser(BusinessUserDTO businessUserDTO) throws Exception{
        Optional<BusinessUser> entityOptional = repository.findById(businessUserDTO.getId());
        
        if(!entityOptional.isEmpty()) {
        	BusinessUser entity = entityOptional.get(); 
        	
        if(entity.getVersion() != businessUserDTO.getVersion()) {
            throw new OptimisticLockingFailureException("Entity Version and Dto Version mismatch");
            
        }else {
        	entity.setFirstName(businessUserDTO.getFirstName());
            entity.setLastName(businessUserDTO.getLastName());
            entity.setFiscalCode(businessUserDTO.getFiscalCode());
            businessUserDTO.setVersion(businessUserDTO.getVersion()+1);
            return businessUserDTO;
        }
        
    }else {
    	throw new Exception("User not found!");
    	}
		
	}
	

	
	
	
	@Transactional(readOnly = false)
    public void deleteUser(BusinessUserDTO businessUserDTO) throws Exception {
        Optional<BusinessUser> entityOptional = repository.findById(businessUserDTO.getId());
        if(!entityOptional.isEmpty()) {
        	BusinessUser entity = entityOptional.get(); 
        	if(entity.getVersion() != businessUserDTO.getVersion()) {
                throw new OptimisticLockingFailureException("Entity Version and Dto Version mismatch");
            }else {
            	 repository.delete(entity);
            }
        }else {
        	throw new Exception("User not found!");
        	}
    	}
	
	
	
	//############################################ Il primo metodo conta ed è collegato al secondo metodo ####################################
	
	public Long seEsisteUtentiDiversiCheHannoLoStessoFiscalCode(Integer id, String fiscalCode) {
        return repository.seEsisteUtentiDiversiCheHannoLoStessoFiscalCode(id, fiscalCode);
    }
	
	
	
	/*
	@Transactional(readOnly = false)
    public BusinessUserDTO updateUserIfFiscalCodeIsDifferent(BusinessUserDTO businessUserDTO,Integer id, String fiscalCode) throws Exception{
        Optional<BusinessUser> entityOptional = repository.findById(businessUserDTO.getId());
        Long errorExistUtenteGiaAssociatoAlFiscalCode = seEsisteUtentiDiversiCheHannoLoStessoFiscalCode(id, fiscalCode);

        if(!entityOptional.isEmpty()) {
        	BusinessUser entity = entityOptional.get(); 
        	
        if(entity.getVersion() != businessUserDTO.getVersion()) {
            throw new OptimisticLockingFailureException("Entity Version and Dto Version mismatch");
            
        }if(errorExistUtenteGiaAssociatoAlFiscalCode > 0) {
        	throw new Exception("Error cause fiscalCode is alredy associated to another user");
        }else {
        	entity.setFirstName(businessUserDTO.getFirstName());
            entity.setLastName(businessUserDTO.getLastName());
            entity.setFiscalCode(businessUserDTO.getFiscalCode());
            businessUserDTO.setVersion(businessUserDTO.getVersion()+1);
            //return businessUserDTO;
        	}
        }else {
        	throw new Exception("User not found!");
        	}
		return businessUserDTO;   
    	}*/
	}
	
	

