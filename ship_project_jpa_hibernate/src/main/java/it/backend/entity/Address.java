package it.backend.entity;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "address")
@SequenceGenerator( name ="address_generator", sequenceName = "address_sequence",
                    initialValue = 1, allocationSize = 3)
public class Address {

    private Integer id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private Customer customer;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "address_generator", strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    //@LazyToOne(LazyToOneOption.NO_PROXY)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
