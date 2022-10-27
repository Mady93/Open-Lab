package it.jdk.ship.entities;

import javax.persistence.*;

@Entity
@Table(name = "ship")
@SequenceGenerator(name = "ship_generator", sequenceName = "ship_sequence",
        initialValue = 1, allocationSize = 3)
public class Ship {

    private Integer id;
    private String name;
    private float tonnage;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "ship_generator", strategy = GenerationType.SEQUENCE)
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

    @Column(name = "tonnage")
    public float getTonnage() {
        return tonnage;
    }

    public void setTonnage(float tonnage) {
        this.tonnage = tonnage;
    }
}
