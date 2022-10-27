package it.jdk.projectPostman.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.projectPostman.EnumError.EnumError;
import it.jdk.projectPostman.entities.Cruise;
import it.jdk.projectPostman.entities.Ship;
import it.jdk.projectPostman.models.CruiseDTO;
import it.jdk.projectPostman.models.CustomerPageDTO;
import it.jdk.projectPostman.repositories.CruiseRepository;
import it.jdk.projectPostman.repositories.ShipRepository;

@Service
@Transactional(readOnly = true)
public class CruiseService {
    

	private final CruiseRepository cruiseRepository;
	private final ShipRepository shipRepository;

	@Autowired
	public CruiseService(CruiseRepository cruiseRepository, ShipRepository shipRepository) {
		this.cruiseRepository = cruiseRepository;
		this.shipRepository = shipRepository;
	}

	@Transactional
	public CruiseDTO create(CruiseDTO cruiseDTO) throws Exception {

		if (cruiseDTO == null) {
			throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		} else {

			Cruise cruiseEntity = new Cruise();
			cruiseEntity.setName(cruiseDTO.getName());

			if (cruiseDTO.getShip() != null && cruiseDTO.getShip().getId() != null) {
				Optional<Ship> shipEntityOptional = shipRepository.findById(cruiseDTO.getShip().getId());

				Ship shipEntity = shipEntityOptional.get();

				cruiseEntity.setShip(shipEntity);

				cruiseRepository.save(cruiseEntity);
				cruiseDTO.setId(cruiseEntity.getId());
				return cruiseDTO;
			} else {
				throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
			}
		}
	}
	
	@Transactional
	public CruiseDTO update(CruiseDTO cruiseDTO) throws Exception {

		Optional<Cruise> optionalCruiseEntity = cruiseRepository.findById(cruiseDTO.getId());

		if (!optionalCruiseEntity.isEmpty()) {

			Cruise cruiseEntity = optionalCruiseEntity.get();
			cruiseEntity.setName(cruiseDTO.getName());

			if (cruiseDTO.getShip() != null && cruiseDTO.getShip().getId() != null) {

				Optional<Ship> shipEntityOptional = shipRepository.findById(cruiseDTO.getShip().getId());

				Ship shipEntity = shipEntityOptional.get();

				cruiseEntity.setShip(shipEntity);
				return cruiseDTO;
			} else {
				throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
			}
		} else {
			throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}
	
	@Transactional
	public void delete(Integer id) throws Exception {

		Optional<Cruise> optionalCruiseEntity = cruiseRepository.findById(id);
		if (!optionalCruiseEntity.isEmpty()) {

			Cruise cruiseEntity = optionalCruiseEntity.get();

			cruiseRepository.delete(cruiseEntity);
		} else {
			throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}
	
	public CruiseDTO getCruiseById(Integer id) throws Exception {

		Optional<Cruise> optionalCruiseEntity = cruiseRepository.findById(id);
		if (!optionalCruiseEntity.isEmpty()) {

			Cruise cruiseEntity = optionalCruiseEntity.get();

			CruiseDTO cruiseDTO = new CruiseDTO();
			cruiseDTO.setId(cruiseEntity.getId());
			cruiseDTO.setName(cruiseEntity.getName());

			if (cruiseDTO.getShip() != null && cruiseDTO.getShip().getId() != null) {
				
				Optional<Ship> shipEntityOptional = shipRepository.findById(cruiseDTO.getShip().getId());
				
				Ship shipEntity = shipEntityOptional.get();
				
				cruiseEntity.setShip(shipEntity);
				
				return cruiseDTO;
			} else {
				throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
			}
		} else {
			throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}
	
		                                //PERSONALIZZATO
	  public CruiseDTO findByNameAndId(String name,Integer id) throws Exception {
	
		List<Tuple> tuple = cruiseRepository.findByNameAndId(name, id);
		CruiseDTO cruiseDTO;
		if(!tuple.isEmpty()) {
			Cruise cruiseEntity = (Cruise) tuple.get(id);
			cruiseDTO = new CruiseDTO();
			cruiseDTO.setId(cruiseEntity.getId());
			cruiseDTO.setName(cruiseEntity.getName());
			
if (cruiseDTO.getShip() != null && cruiseDTO.getShip().getId() != null) {
				
				Optional<Ship> shipEntityOptional = shipRepository.findById(cruiseDTO.getShip().getId());
				
				Ship shipEntity = shipEntityOptional.get();
				
				cruiseEntity.setShip(shipEntity);
			return cruiseDTO;
			}
		}else {
			throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
		return cruiseDTO;
	}
}
