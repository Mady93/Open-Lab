package it.jdk.projectPostman.entities;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@SequenceGenerator(name = "customer_generator",
        sequenceName = "customer_sequence", initialValue = 1, allocationSize = 3)

public class CustomerEntity {

	    private Integer id;
	    private String lastName;
	    private String firstName;
	    private String fiscalCode;
	    private AddressEntity address;
	    private List<PhoneEntity> phoneNumbers;
	    private CreditCardEntity creditCard;

	    @Id
	    @GeneratedValue(generator = "customer_generator",strategy = GenerationType.SEQUENCE)
	    @Column(name = "id")
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    @Column(name = "last_name")
	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    @Column(name = "first_name")
	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    @Column(name = "fiscal_code")
	    public String getFiscalCode() {
	        return fiscalCode;
	    }

	    public void setFiscalCode(String fiscalCode) {
	        this.fiscalCode = fiscalCode;
	    }

	    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
	            fetch = FetchType.EAGER,
	            mappedBy = "customer", orphanRemoval = true)
	    //@LazyToOne(LazyToOneOption.NO_PROXY)
	    public AddressEntity getAddress() {
	        return address;
	    }

	    public void setAddressEntity(AddressEntity address) {
	        this.address = address;
	    }

	    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
	            fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
	    public List<PhoneEntity> getPhoneNumbers() {
			return phoneNumbers;
		}

		public void setPhoneNumbers(List<PhoneEntity> phoneNumbers) {
			this.phoneNumbers = phoneNumbers;
		}

		@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
	            orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "customer")
	    //@LazyToOne(LazyToOneOption.NO_PROXY)
	    public CreditCardEntity getCreditCard() {
	        return creditCard;
	    }

	    public void setCreditCard(CreditCardEntity creditCard) {
	        this.creditCard = creditCard;
	    }
	}

