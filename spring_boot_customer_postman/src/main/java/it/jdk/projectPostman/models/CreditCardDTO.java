package it.jdk.projectPostman.models;

import java.util.Date;

import it.jdk.projectPostman.entities.CustomerEntity;

public class CreditCardDTO {

	private Integer id;
    private Date expDate;
    private String number;
    private String cvv;
    private String organization;
    private CustomerDTO customerDTO;
    
    public CreditCardDTO() {
    	
    }

	

	public CreditCardDTO(Integer id, Date expDate, String number, String cvv, String organization,
			CustomerDTO customerDTO) {
		super();
		this.id = id;
		this.expDate = expDate;
		this.number = number;
		this.cvv = cvv;
		this.organization = organization;
		this.customerDTO = customerDTO;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}



	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}



	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	
	
}
