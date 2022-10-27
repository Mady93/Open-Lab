package it.jdk.projectPostman.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ship")
@SequenceGenerator(name = "ship_generator", sequenceName = "ship_sequence",
        initialValue = 1, allocationSize = 3)
public class Ship {

    private Integer id;
    private String name;
    private float tonnage;

    public Ship() {
    	
    }
    
    public Ship(Integer id, String name, float tonnage) {
		super();
		this.id = id;
		this.name = name;
		this.tonnage = tonnage;
	}

	@Override
	public String toString() {
		return "Ship [id=" + id + ", name=" + name + ", tonnage=" + tonnage + "]";
	}



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
