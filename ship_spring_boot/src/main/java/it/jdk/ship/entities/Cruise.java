package it.jdk.ship.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cruise")
@SequenceGenerator(name = "cruise_generator", sequenceName = "cruise_sequence",
        initialValue = 1, allocationSize = 3)
public class Cruise {

    private Integer id;
    private String name;
    private Ship ship;
    private Set<Reservation> reservation;
    private Set<Customer> customers;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "cruise_generator", strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ship_id")
    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "cruise", orphanRemoval = true)
    public Set<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(Set<Reservation> reservation) {
        this.reservation = reservation;
    }

    @ManyToMany
    @JoinTable(name = "reservation_customer",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")} )
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
