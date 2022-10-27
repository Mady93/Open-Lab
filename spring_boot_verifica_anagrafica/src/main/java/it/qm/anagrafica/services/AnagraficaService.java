package it.qm.anagrafica.services;

import java.time.LocalDate;
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
import it.qm.anagrafica.criteria.api.Spec;
import it.qm.anagrafica.entities.Anagrafica;
import it.qm.anagrafica.filter.FilterAnagraficaDTO;
import it.qm.anagrafica.models.AnagraficaDTO;
import it.qm.anagrafica.page.PageDTO;
import it.qm.anagrafica.repositories.AnagraficaRepository;
import it.qm.anagrafica.rest.ServiceException;

@Service
@Transactional(readOnly = true)
public class AnagraficaService {

	private final AnagraficaRepository repository;

	@Autowired
	public AnagraficaService(AnagraficaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public AnagraficaDTO create(AnagraficaDTO dto) throws ServiceException {

		if (dto != null) {
			Anagrafica anagrafica = new Anagrafica();
			anagrafica.setCap(dto.getCap());
			anagrafica.setCodiceFiscale(dto.getCodiceFiscale());
			anagrafica.setCodiceFiscaleAzienda(dto.getCodiceFiscaleAzienda());
			anagrafica.setNome(dto.getNome());
			anagrafica.setCognome(dto.getCognome());
			anagrafica.setComune(dto.getComune());
			anagrafica.setProvincia(dto.getProvincia());
			anagrafica.setEmail(dto.getEmail());
			anagrafica.setIndirizzo(dto.getIndirizzo());
			anagrafica.setNazionalita(dto.getNazionalita());
			anagrafica.setNomeProfessione(dto.getNomeProfessione());
			anagrafica.setPartitaIva(dto.getPartitaIva());
			anagrafica.setRagioneFiscale(dto.getRagioneFiscale());
			anagrafica.setTelefono(dto.getTelefono());
			anagrafica.setTipoCliente(dto.getTipoCliente());
			anagrafica.setDataCreazione(dto.getDataCreazione());
			// anagrafica.setDataLastUpdate(dto.getDataLastUpdate());
			repository.save(anagrafica);
			dto.setId(anagrafica.getId());
			return dto;
		} else {
			throw new ServiceException("Error! MoodelDTO == null!");
		}
	}

	public Long codiceFiscaleGiaAssociato(String codiceFiscale, Long id) {
		return repository.codiceFiscaleGiaAssociato(codiceFiscale, id);
	}

	@Transactional(readOnly = false)
	public AnagraficaDTO update(AnagraficaDTO dto, String codiceFiscale, Long id) throws ServiceException {
		Optional<Anagrafica> entityOptional = repository.findById(dto.getId());
		Long errorCodiceFiscaleGiaAssociato = codiceFiscaleGiaAssociato(codiceFiscale, id);

		if (!entityOptional.isEmpty()) {
			Anagrafica anagrafica = entityOptional.get();

			if (errorCodiceFiscaleGiaAssociato > 0) {
				throw new ServiceException("Error cause fiscalCode is alredy associated");
			} else {

				anagrafica.setCap(dto.getCap());
				anagrafica.setCodiceFiscale(dto.getCodiceFiscale());
				anagrafica.setCodiceFiscaleAzienda(dto.getCodiceFiscaleAzienda());
				anagrafica.setNome(dto.getNome());
				anagrafica.setCognome(dto.getCognome());
				anagrafica.setComune(dto.getComune());
				anagrafica.setProvincia(dto.getProvincia());
				anagrafica.setEmail(dto.getEmail());
				anagrafica.setIndirizzo(dto.getIndirizzo());
				anagrafica.setNazionalita(dto.getNazionalita());
				anagrafica.setNomeProfessione(dto.getNomeProfessione());
				anagrafica.setPartitaIva(dto.getPartitaIva());
				anagrafica.setRagioneFiscale(dto.getRagioneFiscale());
				anagrafica.setTelefono(dto.getTelefono());
				anagrafica.setTipoCliente(dto.getTipoCliente());
				anagrafica.setDataCreazione(dto.getDataCreazione());
				anagrafica.setDataLastUpdate(LocalDate.now());
			}

		} else {
			throw new ServiceException("Client not found!");
		}
		return dto;
	}

	@Transactional(readOnly = false)
	public void delete(AnagraficaDTO autoDTO) throws ServiceException {
		Optional<Anagrafica> entityOptional = repository.findById(autoDTO.getId());
		if (!entityOptional.isEmpty()) {
			Anagrafica entity = entityOptional.get();
			repository.delete(entity);
		} else {
			throw new ServiceException("Client not found!");
		}
	}

	public Optional<AnagraficaDTO> findAnagraficaByCodiceFiscale(String codiceFiscale) throws ServiceException {
		Optional<Anagrafica> entityOptional = repository.findAnagraficaByCodiceFiscale(codiceFiscale);
		if (entityOptional != null) {
			try {

				return Optional.of(new AnagraficaDTO(entityOptional.get().getId(), entityOptional.get().getCap(),
						entityOptional.get().getCodiceFiscale(), entityOptional.get().getCodiceFiscaleAzienda(),
						entityOptional.get().getNome(), entityOptional.get().getCognome(),
						entityOptional.get().getComune(), entityOptional.get().getProvincia(),
						entityOptional.get().getEmail(), entityOptional.get().getIndirizzo(),
						entityOptional.get().getNazionalita(), entityOptional.get().getNomeProfessione(),
						entityOptional.get().getPartitaIva(), entityOptional.get().getRagioneFiscale(),
						entityOptional.get().getTelefono(), entityOptional.get().getTipoCliente(),
						entityOptional.get().getDataCreazione(), entityOptional.get().getDataLastUpdate()));
			} catch (NoResultException e) {
				return Optional.empty();
			}
		} else {
			throw new ServiceException("Auto serach by model not found!");
		}
	}

	public Optional<PageDTO> findPage(int number, int size) throws ServiceException {
		if (number >= 1) {
			Page<Anagrafica> pageA = repository.findAll(PageRequest.of(number - 1, size));
			return makeDTO(pageA, number, size);
		} else {
			throw new ServiceException("Pagina non trovata! La pagina deve essere >=1");
		}
	}

	public Optional<PageDTO> findByFilter(int number, int size, FilterAnagraficaDTO filter) throws Exception {
		Specification<Anagrafica> spec = Spec.getAnagraficaByFilter(filter);
		if (number >= 1) {
			Page<Anagrafica> pageA = repository.findAll(spec, PageRequest.of(number - 1, size));
			return makeDTO(pageA, number, size);
		} else {
			throw new Exception("Pagina non trovata! La pagina deve essere >=1");
		}
	}

	private Optional<PageDTO> makeDTO(Page<Anagrafica> pageA, int number, int size) {
		List<AnagraficaDTO> dtoList = new ArrayList<>();
		for (Anagrafica entity : pageA.getContent()) {
			dtoList.add(new AnagraficaDTO(entity.getId(), entity.getCap(), entity.getCodiceFiscale(),
					entity.getCodiceFiscaleAzienda(), entity.getNome(), entity.getCognome(), entity.getCognome(),
					entity.getProvincia(), entity.getEmail(), entity.getIndirizzo(), entity.getNazionalita(),
					entity.getNomeProfessione(), entity.getPartitaIva(), entity.getRagioneFiscale(),
					entity.getTelefono(), entity.getTipoCliente(), entity.getDataCreazione(),
					entity.getDataLastUpdate()));
		}
		try {
			if (dtoList.isEmpty())
				throw new NoResultException("Empty list");
			return Optional.of(new PageDTO(pageA.getTotalPages(), pageA.getTotalElements(), pageA.getNumber() + 1,
					pageA.getSize(), dtoList));
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	public List<AnagraficaDTO> findList(String tipoCliente) throws Exception {

		List<Anagrafica> listEntity = this.repository.findList(tipoCliente);
		List<AnagraficaDTO> listDTO = new ArrayList<>();

		for (Anagrafica entity : listEntity) {
			AnagraficaDTO dto = new AnagraficaDTO();

			if (listEntity != null) {

				dto.setId(entity.getId());
				dto.setCap(entity.getCap());
				dto.setCodiceFiscale(entity.getCodiceFiscale());
				dto.setNome(entity.getNome());
				dto.setCognome(entity.getCognome());
				dto.setProvincia(entity.getProvincia());
				dto.setEmail(entity.getEmail());
				dto.setIndirizzo(dto.getIndirizzo());
				dto.setNazionalita(entity.getNazionalita());
				dto.setNomeProfessione(entity.getNomeProfessione());
				dto.setPartitaIva(entity.getPartitaIva());
				dto.setRagioneFiscale(entity.getRagioneFiscale());
				dto.setTelefono(entity.getTelefono());
				dto.setTipoCliente(entity.getTipoCliente());
				dto.setDataCreazione(entity.getDataCreazione());
				dto.setDataLastUpdate(entity.getDataLastUpdate());
				listDTO.add(dto);
			} else {
				throw new Exception("Users not found");
			}
		}
		return listDTO;

	}

	public Optional<AnagraficaDTO> findById(Long id) {
		return repository.findAnagraficaById(id);
	}
}