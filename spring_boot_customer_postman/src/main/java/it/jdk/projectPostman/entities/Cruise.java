package it.jdk.projectPostman.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name = "cruise")
@SequenceGenerator(name = "cruise_generator", sequenceName = "cruise_sequence",
        initialValue = 1, allocationSize = 3)
public class Cruise {

    private Integer id;
    private String name;
    private Ship ship;

    
    public Cruise() {
    	
    }
    
    
    
    public Cruise(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

    


	@Override
	public String toString() {
		return "Cruise [id=" + id + ", name=" + name + ", ship=" + ship + "]";
	}



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
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name="ship_id")
    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

}

