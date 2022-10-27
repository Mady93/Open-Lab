
/******************************************************************************
 * Copyright © Alessandro Zoia                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the “Software”), *
 * to deal in the Software without restriction,including without limitation   *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES                       *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. *
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS                         *
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION  *
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR                    *
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE        *
 * SOFTWARE.                                                                  *
 ******************************************************************************/

package it.mobileapp.impl.repository.spring.jpa;

import it.mobileapp.dmodel.BusinessUserFilterDTO;
import it.mobileapp.impl.repository.entities.BusinessUser;
import org.springframework.data.jpa.domain.Specification;
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
