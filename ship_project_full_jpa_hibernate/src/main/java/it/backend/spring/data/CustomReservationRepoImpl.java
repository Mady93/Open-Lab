package it.backend.spring.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomReservationRepoImpl implements CustomReservationRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void clear() {
        em.clear();
    }
}
