package it.jdk.ship.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.jdk.ship.dto.CustomerDTO;
import it.jdk.ship.entities.Customer;
import it.jdk.ship.repository.CustomerRepoPx;

@Service
@Transactional(readOnly=true)
public class CustomerService {

	private final CustomerRepoPx customerRepoPx;
	
	@Autowired
	public CustomerService(CustomerRepoPx customerRepoPx) {
		this.customerRepoPx = customerRepoPx;
	}
	
	@Transactional
	public CustomerDTO create(CustomerDTO customerDTO) {
	    Customer c = new Customer();
	    c.setFirstName(customerDTO.getFirstName());
	    c.setLastName(customerDTO.getLastName());
	    c.setFiscalCode(customerDTO.getFiscalCode());
	    customerRepoPx.save(c);
	    customerDTO.setId(c.getId());
	    return customerDTO;
	}
	
	@Transactional
	public CustomerDTO update(CustomerDTO customerDTO) {
	    Optional<Customer> cOpt = customerRepoPx.findById(customerDTO.getId());
	    if(!cOpt.isEmpty()) {
	        Customer c = cOpt.get();
	        c.setFirstName(customerDTO.getFirstName());
	        c.setLastName(customerDTO.getLastName());
	        c.setFiscalCode(customerDTO.getFiscalCode());
	    }
	    return customerDTO;
	}
	
	@Transactional
	public void delete(Integer id) {
	    Optional<Customer> cOpt = customerRepoPx.findById(id);
	    if(!cOpt.isEmpty())
	        customerRepoPx.delete(cOpt.get());
	}
	
	public CustomerDTO find(Integer id) {
	    Optional<Customer> cOpt = customerRepoPx.findById(id);
	    if(!cOpt.isEmpty()) {
	        CustomerDTO dto = new CustomerDTO();
	        Customer c = cOpt.get();
	        dto.setId(c.getId());
	        dto.setFirstName(c.getFirstName());
	        dto.setLastName(c.getLastName());
	        dto.setFiscalCode(c.getFiscalCode());
	        return dto;
	    }
	    return new CustomerDTO();
}
}
