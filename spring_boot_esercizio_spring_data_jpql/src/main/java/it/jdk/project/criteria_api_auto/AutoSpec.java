package it.jdk.project.criteria_api_auto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import it.jdk.project.entities.Auto;
import it.jdk.project.filter_auto.FilterAutoDTO;
import it.jdk.project.models.AutoDTO;

public class AutoSpec {

                                              //CRITERIA API

public static Specification<Auto> getAutoByFilter(FilterAutoDTO filter) {
return (root, query, criteriBuilder) -> {
List<Predicate> predicates = new ArrayList<>();

if (filter.getTarga() != null) {
Predicate targaPredicate = criteriBuilder.equal(root.get("targa"), filter.getTarga());
predicates.add(targaPredicate);
}

if (filter.getModello() != null) {
Predicate modelloPredicate = criteriBuilder.equal(root.get("modello"), filter.getModello());
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
