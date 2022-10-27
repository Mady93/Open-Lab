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
@Table(name = "phone")
@SequenceGenerator( name ="phone_generator", sequenceName = "phone_sequence",
        initialValue = 1, allocationSize = 3)
public class PhoneEntity {

	private Integer id;
    private String number;
    private Integer type;
    private CustomerEntity customer;
    
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
	    @LazyToOne(LazyToOneOption.NO_PROXY)
		public CustomerEntity getCustomer() {
			return customer;
		}

		public void setCustomer(CustomerEntity customer) {
			this.customer = customer;
		}
	
    
    

}
