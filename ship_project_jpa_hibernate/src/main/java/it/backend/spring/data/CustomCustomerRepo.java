package it.backend.spring.data;

import it.backend.entity.Customer;

public interface CustomCustomerRepo {
    public void detach(Customer c);
    public void refresh(Customer c);
}
