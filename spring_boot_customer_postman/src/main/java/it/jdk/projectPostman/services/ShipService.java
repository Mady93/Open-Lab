package it.jdk.projectPostman.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.projectPostman.EnumError.EnumError;
import it.jdk.projectPostman.entities.Ship;
import it.jdk.projectPostman.models.ShipDTO;
import it.jdk.projectPostman.repositories.ShipRepository;

@Service
@Transactional(readOnly = true)
public class ShipService {

	private final ShipRepository shipRepository;
	
	@Autowired
	public ShipService(ShipRepository shipRepository) {
		 this.shipRepository = shipRepository;
	}
	
	@Transactional
	public ShipDTO create(ShipDTO shipDTO) throws Exception {

		if (shipDTO == null) {
			throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		} else {

			Ship shipEntity = new Ship();
			shipEntity.setName(shipDTO.getName());
			shipEntity.setTonnage(shipDTO.getTonnage());
			shipRepository.save(shipEntity);
			shipDTO.setId(shipEntity.getId());
		}
		return shipDTO;
	}

	
	
public ShipDTO update(ShipDTO shipDTO) throws Exception {

	Optional<Ship> optionalShipEntity = shipRepository.findById(shipDTO.getId());

	if (!optionalShipEntity.isEmpty()) {

		Ship shipEntity = optionalShipEntity.get();
		shipEntity.setName(shipDTO.getName());
		shipEntity.setTonnage(shipDTO.getTonnage());
}
	else {
		throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	}
	return shipDTO;
	}


@Transactional
public void delete(Integer id) throws Exception {

	Optional<Ship> optionalShipEntity = shipRepository.findById(id);
	if (!optionalShipEntity.isEmpty()) {

		Ship shipEntity = optionalShipEntity.get();

		shipRepository.delete(shipEntity);
	} else {
		throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}

public ShipDTO getShipById(Integer id) throws Exception {

	Optional<Ship> optionalShipEntity = shipRepository.findById(id);
	ShipDTO shipDTO;
	if (!optionalShipEntity.isEmpty()) {

		Ship shipEntity = optionalShipEntity.get();

		shipDTO = new ShipDTO();
		shipDTO.setId(shipEntity.getId());
		shipDTO.setName(shipEntity.getName());
		shipDTO.setTonnage(shipEntity.getTonnage());	
}else {
	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	return shipDTO;
	}

/*
                                 //PERSNALIZZATO
public ShipDTO getShipByNameAndTonnage (String name , String tonnage) throws Exception {
	Optional<Ship> optionalShipEntity = shipRepository.findShipByNameAndTonnage(name, tonnage);
	ShipDTO shipDTO;
	if (!optionalShipEntity.isEmpty()) {

		Ship shipEntity = optionalShipEntity.get();

		shipDTO = new ShipDTO();
		shipDTO.setId(shipEntity.getId());
		shipDTO.setName(shipEntity.getName());
		shipDTO.setTonnage(shipEntity.getTonnage());
		System.out.println("Ship trovato: "+ shipEntity.toString());
}else {
	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	return shipDTO;
	
	}

                                           //PERSNALIZZATO
/*public PageDTO findShipByName(String name) throws Exception {
	Page<Ship> page = shipRepository.findShipByName(name, PageRequest.of(0, 5));
	PageDTO pageDTO;
	if(!(page.getNumberOfElements()>0)) {
		pageDTO = new PageDTO();
		pageDTO.setnElementi(page.getTotalElements());
		pageDTO.setnPagine(page.getTotalPages());
		pageDTO.setPage(page);
		
		ShipDTO shipDTO = new ShipDTO();
		for (Ship ship : page) {
			ship.
		}

}else {
	throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	return pageDTO;
	
	}*/
}

