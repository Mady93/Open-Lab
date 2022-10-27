package it.jdk.project.criteria.api;

import org.springframework.data.jpa.domain.Specification;

import it.jdk.project.dto.BusinessUserFilterDTO;
import it.jdk.project.entities.BusinessUser;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Specification for Criteria Query
 */
public class BusinessUserSpec {
	
	                           //CRITERIA API
    /**
     * Get Specification Object
     * @param filter Input filter
     * @return Specification object for Criteria Query
     */
    public static Specification<BusinessUser> getUsersByFilter(BusinessUserFilterDTO filter) {
        return (root, query, criteriBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getLastName() != null) {
                Predicate lastNamePredicate = criteriBuilder.equal(root.get("lastName"), filter.getLastName());
                predicates.add(lastNamePredicate);
            }

            if (filter.getFiscalCode() != null) {
                Predicate fiscalCodePredicate = criteriBuilder.equal(root.get("fiscalCode"), filter.getFiscalCode());
                predicates.add(fiscalCodePredicate);
            }

            Predicate[] arrayPredicate = new Predicate[predicates.size()];

            for (int i = 0; i < arrayPredicate.length; i++) {
                arrayPredicate[i] = predicates.get(i);
            }

            return criteriBuilder.and(arrayPredicate);
        };
    }
}

