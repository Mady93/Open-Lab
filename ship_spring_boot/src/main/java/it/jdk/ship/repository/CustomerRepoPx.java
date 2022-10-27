package it.jdk.ship.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.jdk.ship.entities.Customer;

@Repository
@Transactional(readOnly = true)
public class CustomerRepoPx {

	@PersistenceContext
	private EntityManager em;
	
	public Customer getReferenceById(Integer id) {
		Customer customer = new Customer();
		customer.setId(id);
		return em.getReference(Customer.class, customer);
		}
	
	@Transactional
	public Customer save(Customer customer) {
		if(customer.getId() == null) {
			em.persist(customer);
			return customer;
		}else {
			return em.merge(customer);
		}
	}
	
	public Optional<Customer> findById(Integer id){
		Customer customer = em.find(Customer.class, id);
		if(customer != null) {
			return Optional.of(customer);
		}else {
			return Optional.empty();
		}
	}
	
	@Transactional
	public void delete(Customer customer) {
		Customer managed = em.find(Customer.class, customer.getId());
	em.remove(managed);
	}
	
	}


