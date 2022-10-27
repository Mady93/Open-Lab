package it.backend.entity;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "credit_card")
@SequenceGenerator( name ="credit_card_generator", sequenceName = "credit_card_sequence",
        initialValue = 1, allocationSize = 3)
public class CreditCard {

    private Integer id;
    private Date expDate;
    private String number;
    private String cvv;
    private String organization;
    private Customer customer;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "credit_card_generator", strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exp_date")
    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "cvv")
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Column(name = "organization")
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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
