package it.qm.anagrafica.criteria.api;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import it.qm.anagrafica.entities.Anagrafica;
import it.qm.anagrafica.filter.FilterAnagraficaDTO;

public class Spec {

	// CRITERIA API

	public static Specification<Anagrafica> getAnagraficaByFilter(FilterAnagraficaDTO filter) {
		return (root, query, criteriBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (filter.getCodiceFiscale() != null) {
				Predicate targaPredicate = criteriBuilder.equal(root.get("codiceFiscale"), filter.getCodiceFiscale());
				predicates.add(targaPredicate);
			}

			if (filter.getCodiceFiscaleAzienda() != null) {
				Predicate modelloPredicate = criteriBuilder.equal(root.get("codiceFiscaleAzienda"),filter.getCodiceFiscaleAzienda());
				predicates.add(modelloPredicate);
			}

			if (filter.getEmail() != null) {
				Predicate modelloPredicate = criteriBuilder.equal(root.get("email"), filter.getEmail());
				predicates.add(modelloPredicate);
			}

			if (filter.getPartitaIva() != null) {
				Predicate modelloPredicate = criteriBuilder.equal(root.get("partitaIva"), filter.getPartitaIva());
				predicates.add(modelloPredicate);
			}

			if (filter.getTipoCliente() != null) {
				Predicate modelloPredicate = criteriBuilder.equal(root.get("tipoCliente"), filter.getTipoCliente());
				predicates.add(modelloPredicate);
			}

			Predicate[] arrayPredicate = new Predicate[predicates.size()];

			for (int i = 0; i < arrayPredicate.length; i++) {
				arrayPredicate[i] = predicates.get(i);
			}

			return criteriBuilder.and(arrayPredicate);
		};
	}

}