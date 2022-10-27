package it.backend.spring.data;

import it.backend.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer,Integer>, CustomCustomerRepo {

    @Query(value="select c from Customer c where c.address.zip=:zip",
            countQuery = "select count(c) from Customer c where c.address.zip=:zip")
    public Page<Customer> getCustomersByZip(String zip, Pageable pageable);

    //@Query(value="select c from Customer c join fetch c.address join fetch c.creditCard where c.fiscalCode=:fiscalCode")
    public Customer findCustomerByFiscalCode(String fiscalCode);

    @Query(value="select c from Customer c",
            countQuery = "select count(c) from Customer c")
    public Page<Customer> getCustomers(Pageable pageable);

    public Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);

}
