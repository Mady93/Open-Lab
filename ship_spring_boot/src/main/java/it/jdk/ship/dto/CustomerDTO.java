package it.jdk.ship.dto;

public class CustomerDTO {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String fiscalCode;
	
	public String getFirstName() {
	    return firstName;
	}
	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}
	public String getLastName() {
	    return lastName;
	}
	public void setLastName(String lastName) {
	    this.lastName = lastName;
	}
	public String getFiscalCode() {
	    return fiscalCode;
	}
	public void setFiscalCode(String fiscalCode) {
	    this.fiscalCode = fiscalCode;
	}
	public Integer getId() {
	    return id;
	}
	public void setId(Integer id) {
	    this.id = id;
	}
}
