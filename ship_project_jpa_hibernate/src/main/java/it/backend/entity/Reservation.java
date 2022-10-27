package it.backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "reservation")
@SequenceGenerator(name = "reservation_generator", sequenceName = "reservation_sequence",
        initialValue = 1, allocationSize = 3)
public class Reservation {

    private Integer id;
    private double amountPaid;
    private Date date;
    private Cruise cruise;
    private Set<Cabin> cabins;
    private Set<Customer> customers;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "reservation_generator", strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "amount_paid")
    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Column(name = "date_reserved")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "cruise_id")
    public Cruise getCruise() {
        return cruise;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }

    @ManyToMany
    @JoinTable(name = "cabin_reservation",joinColumns = {@JoinColumn(name = "reservation_id")},
    inverseJoinColumns = {@JoinColumn(name = "cabin_id")})
    public Set<Cabin> getCabins() {
        return cabins;
    }

    public void setCabins(Set<Cabin> cabins) {
        this.cabins = cabins;
    }

    @ManyToMany
    @OrderBy("lastName ASC")
    @JoinTable(name="reservation_customer", joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
