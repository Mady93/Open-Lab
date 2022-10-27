package it.jdk.projectPostman.entities;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "credit_card")
@SequenceGenerator( name ="credit_card_generator", sequenceName = "credit_card_sequence",
        initialValue = 1, allocationSize = 3)
public class CreditCardEntity {

	 	private Integer id;
	    private Date expDate;
	    private String number;
	    private String cvv;
	    private String organization;
	    private CustomerEntity customer;
	    
	    @Id
	    @Column(name = "id")
	    @GeneratedValue(generator = "credit_card_generator", strategy = GenerationType.SEQUENCE)
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    @Temporal(TemporalType.TIMESTAMP) // per il tipo della data
	    //@Temporal(TemporalType.DATE)
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
		public CustomerEntity getCustomer() {
			return customer;
		}

		public void setCustomer(CustomerEntity customer) {
			this.customer = customer;
		}
	    
	    
}
