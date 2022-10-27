package it.jdk.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.jdk.project.criteria_api_auto.AutoSpec;
import it.jdk.project.entities.Auto;
import it.jdk.project.filter_auto.FilterAutoDTO;
import it.jdk.project.models.AutoDTO;
import it.jdk.project.page_auto.PageDTO;
import it.jdk.project.repositories.AutoRepository;

@Service
@Transactional(readOnly = true)
public class AutoService {

	private final AutoRepository repository;

	@Autowired
	public AutoService(AutoRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public AutoDTO create(AutoDTO dto) throws Exception {

		if (dto != null) {
			Auto auto = new Auto();
			auto.setTarga(dto.getTarga());
			auto.setModello(dto.getModello());
			repository.save(auto);
			dto.setId(auto.getId());
			dto.setVersion(0);
			return dto;
		} else {
			throw new Exception("Error! model == null");
		}
	}

	
	
	
	
	// find by id con la dto projection
	public Optional<AutoDTO> findById(Long id) {
		return repository.findAutoById(id);
	}

	
	
	
	
	
	
	
	
	
	public Optional<AutoDTO> findAutoByTarga(String targa) throws Exception {
		Optional<Auto> entityOptional = repository.findAutoByTarga(targa);
		if (entityOptional != null) {
			try {
				return Optional.of(new AutoDTO(entityOptional.get().getId(), entityOptional.get().getTarga(),
						entityOptional.get().getModello(), entityOptional.get().getVersion()));
			} catch (NoResultException e) {
				return Optional.empty();
			}
		} else {
			throw new Exception("Auto serach by model not found!");
		}
	}

	
	
	
	
	
	
	
	
	public Optional<PageDTO> findPage(int number, int size) throws Exception {
		if (number >= 1) {
			Page<Auto> pageAuto = repository.findAll(PageRequest.of(number - 1, size));
			return makeDTO(pageAuto, number, size);
		} else {
			throw new Exception("Pagina non trovata! La pagina deve essere >=1");
		}
	}

	
	 
	  
	public Optional<PageDTO> findByFilter(int number, int size, FilterAutoDTO filter) throws Exception {
		Specification<Auto> spec = AutoSpec.getAutoByFilter(filter);
		if (number >= 1) {
			Page<Auto> pageAuto = repository.findAll(spec, PageRequest.of(number - 1, size));
			return makeDTO(pageAuto, number, size);
		} else {
			throw new Exception("Pagina non trovata! La pagina deve essere >=1");
		}
	}

	private Optional<PageDTO> makeDTO(Page<Auto> pageAuto, int number, int size) {
		List<AutoDTO> dtoList = new ArrayList<>();
		for (Auto entity : pageAuto.getContent()) {
			dtoList.add(new AutoDTO(entity.getId(), entity.getTarga(), entity.getModello(), entity.getVersion()));
		}
		try {
			if (dtoList.isEmpty())
				throw new NoResultException("Empty list");
			return Optional.of(new PageDTO(pageAuto.getTotalPages(), pageAuto.getTotalElements(),
					pageAuto.getNumber()+1, pageAuto.getSize(), dtoList));
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	  
	
	
	
	
	
	
	
	
	
	  public List<AutoDTO> findList() throws Exception{
	  
	  List<Auto> listEntity = this.repository.findAll(); List<AutoDTO> listDTO =
	  new ArrayList<>();
	  
	  for (Auto entity : listEntity) { AutoDTO dto = new AutoDTO();
	  
	  if(listEntity != null) {
	  
	  dto.setId(entity.getId()); 
	  dto.setTarga(entity.getTarga());
	  dto.setModello(entity.getModello()); 
	  dto.setVersion(entity.getVersion());
	  listDTO.add(dto); 
	  }else { 
		  throw new Exception("Users not found"); 
	  		} 
	  } 
	  return listDTO;
	  
	  }
	  
	
	
	
	
	
	
	
	
	
	

	
		  
	
	  @Transactional(readOnly = false) public AutoDTO update(AutoDTO autoDTO)
	  throws Exception { Optional<Auto> entityOptional = repository.findById(autoDTO.getId());
	  
	  if(!entityOptional.isEmpty()) {
		  Auto entity = entityOptional.get();
	  
	  if(entity.getVersion() != autoDTO.getVersion()) { 
		  throw new Exception("Entity Version and Dto Version mismatch");
	  
	  }else { 
		  entity.setTarga(autoDTO.getTarga());
		  entity.setModello(autoDTO.getModello());
		  autoDTO.setVersion(autoDTO.getVersion()+1); 
		  return autoDTO; }
	  
	  }else { 
		  throw new Exception("User not found!"); 
		  }
	  }
	  
	  
	  
	  
	  
	  
//##################################### Il primo metodo conta ed è collegato al secondo metodo ####################################
	
	  
	  
	  
	  
	  
	  public Long seEsisteAutoDiverseCheHannoLaStessaTarga(String targa, Long id) {
	  return repository.seEsisteAutoDiverseCheHannoLaStessaTarga(targa, id); 
	  }
	  
	  
	  
	  
	  
	  
	  
	   	  
	  @Transactional(readOnly = false) 
	  public AutoDTO updateSeTargaDifferente(AutoDTO autoDTO, String targa, Long id) throws Exception{ 
		  Optional<Auto> entityOptional = repository.findById(autoDTO.getId());
		  Long errorTargaGiaAssociata = seEsisteAutoDiverseCheHannoLaStessaTarga(targa, id);
	  
	  if(!entityOptional.isEmpty()) {
		  Auto entity = entityOptional.get();
	  
	  if(entity.getVersion() != autoDTO.getVersion()) { 
		  throw new Exception("Entity Version and Dto Version mismatch");
	  }
	  if(errorTargaGiaAssociata > 0) { 
		  throw new Exception("Error cause targa is alredy associated"); 
		  }else {
	  entity.setTarga(autoDTO.getTarga()); 
	  entity.setModello(autoDTO.getModello());
	  autoDTO.setVersion(autoDTO.getVersion()+1); 
	  //return autoDTO;  
		  }
	  }else{ 
		  throw new Exception("User not found!"); 
		  		}
	  return autoDTO; 
	  		}
	  
	  
	  
	  
	  
	  
	  @Transactional(readOnly = false) public void delete(AutoDTO autoDTO) throws Exception { 
		  Optional<Auto> entityOptional = repository.findById(autoDTO.getId()); 
		  if(!entityOptional.isEmpty()) {
		  Auto entity = entityOptional.get(); 
		  if(entity.getVersion() != autoDTO.getVersion()) { 
			  throw new Exception("Entity Version and Dto Version mismatch"); 
	  }else {
	  repository.delete(entity); 
	  		} 
		  }else { 
			  throw new Exception("User not found!");
		  }
	  }
	  

}