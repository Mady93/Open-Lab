package it.qm.anagrafica.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.qm.anagrafica.entities.Anagrafica;
import it.qm.anagrafica.models.AnagraficaDTO;

public interface AnagraficaRepository extends JpaRepository<Anagrafica, Integer>, JpaSpecificationExecutor<Anagrafica> {

	@Query("Select count(a) from Anagrafica a where a.codiceFiscale=:codiceFiscale and a.id<>:id")
	Long codiceFiscaleGiaAssociato(@Param("codiceFiscale") String codiceFiscale, @Param("id") Long id);

	Optional<Anagrafica> findById(Long id);

	Optional<Anagrafica> findAnagraficaByCodiceFiscale(String codiceFiscale);

	Page<Anagrafica> findAll(Specification<Anagrafica> spec, Pageable pageable);

	@Query("Select a from Anagrafica a where a.tipoCliente=:tipoCliente")
	List<Anagrafica> findList(@Param("tipoCliente") String tipoCliente);

	@Query("select new it.qm.anagrafica.models.AnagraficaDTO(a.id, a.cap, a.codiceFiscale, a.codiceFiscaleAzienda , a.nome, a.cognome"
			+ ", a.comune, a.provincia, a.email, a.indirizzo, a.nazionalita, a.nomeProfessione, a.partitaIva,"
			+ "a.ragioneFiscale, a.telefono, a.tipoCliente, a.dataCreazione, a.dataLastUpdate) from Anagrafica a where a.id=:id")
	Optional<AnagraficaDTO> findAnagraficaById(@Param("id") Long id);
}
