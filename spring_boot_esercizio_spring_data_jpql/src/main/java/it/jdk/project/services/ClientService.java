package it.jdk.project.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.jdk.project.aop.isnull.ToLog;
import it.jdk.project.aop.isnull.isNull;
import it.jdk.project.entities.Cliente;
import it.jdk.project.models.ClienteDTO;
import it.jdk.project.repositories.ClientRepository;
import it.jdk.project.rest.ServiceException;

@Service
@Transactional(readOnly = true)
public class ClientService{

	private final ClientRepository repository;
	private String nome;
	
	@Autowired
	public ClientService( ClientRepository repository) {
		this.repository = repository;
	}

	//     List<TupleElement<?>> getElements();
	
	
	/*
	 * this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.version = version;
	 */
	
	
	    @ToLog(level = "warning") //OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL
	    @isNull
	@Transactional
	public ClienteDTO create(ClienteDTO dto) throws ServiceException {

		if (dto != null) {
			Cliente cliente = new Cliente();
			cliente.setNome(dto.getNome());
			cliente.setCognome(dto.getCognome());
			cliente.setCodiceFiscale(dto.getCodiceFiscale());
			repository.save(cliente);
			dto.setId(cliente.getId());
			dto.setVersion(0);
			return dto;
		} else {
			throw new ServiceException("Error! model == null");
		}
	}
	
	
	
	
	
	
	public List<ClienteDTO> findfindByName(String nome) throws Exception {
		
		List<Cliente> listEntity = this.repository.findByName(nome); 
		List<ClienteDTO> listDTO = new ArrayList<>();
				  
				  for (Cliente entity : listEntity) { 
					  
					 ClienteDTO dto = new ClienteDTO();
				  
				  if(listEntity != null) {
				  
				  dto.setId((entity).getId()); 
				  dto.setNome((entity).getNome()); 
				  dto.setCognome((entity).getCognome());
				  dto.setCodiceFiscale((entity).getCodiceFiscale());
				  dto.setVersion((entity).getVersion());
				  listDTO.add(dto); 
				  }else { 
					  throw new Exception("Client not found"); 
				  		} 
				  } 
				  return listDTO;
				  
				  }
}
