package it.jdk.projectPostman.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@SequenceGenerator( name ="address_generator", sequenceName = "address_sequence",
                    initialValue = 1, allocationSize = 3)
public class AddressEntity {

	 	private Integer id;
	    private String street;
	    private String city;
	    private String state;
	    private String zip;
	    private CustomerEntity customer;
	    
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
		public CustomerEntity getCustomer() {
			return customer;
		}

		public void setCustomer(CustomerEntity customer) {
			this.customer = customer;
		}

}
