package it.backend.spring.data;

import it.backend.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomCustomerRepoImpl implements CustomCustomerRepo {

    @PersistenceContext
    private EntityManager em;

    public void detach(Customer c){
        em.detach(c);
    }

    @Override
    public void refresh(Customer c) {
        em.refresh(c);
    }
}
