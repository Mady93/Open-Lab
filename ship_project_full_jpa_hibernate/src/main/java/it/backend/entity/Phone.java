package it.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@SequenceGenerator( name ="phone_generator", sequenceName = "phone_sequence",
        initialValue = 1, allocationSize = 3)
public class Phone {

    private Integer id;
    private String number;
    private Integer type;
    private Customer customer;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "phone_generator", strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
