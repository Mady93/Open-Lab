package it.backend.spring.data;

import it.backend.entity.Address;
import it.backend.entity.CreditCard;
import it.backend.entity.Customer;
import it.backend.entity.Phone;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpec {
    public static Specification<Customer> getUsersByFilter(CustomerFilterDTO filter) {
        return (root, query, criteriBuilder) -> {
            Fetch<Customer, Address> addressFetch = root.fetch("address", JoinType.INNER);
            Fetch<Customer, CreditCard> creditCardFetch = root.fetch("creditCard", JoinType.INNER);
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
