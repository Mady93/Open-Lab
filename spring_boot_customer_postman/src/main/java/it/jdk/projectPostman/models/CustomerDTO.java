package it.jdk.projectPostman.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO implements Serializable {

	private Integer id;
	private String lastName;
	private String firstName;
	private String fiscalCode;
	private AddressDTO addressDTO;
	private CreditCardDTO creditCardDTO;
	private List<PhoneDTO> listPhoneDTO;

	public CustomerDTO() {

	}

	public CustomerDTO(Integer id, String lastName, String firstName, String fiscalCode) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.fiscalCode = fiscalCode;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public CreditCardDTO getCreditCardDTO() {
		return creditCardDTO;
	}

	public void setCreditCardDTO(CreditCardDTO creditCardDTO) {
		this.creditCardDTO = creditCardDTO;
	}

	public List<PhoneDTO> getListPhoneDTO() {
		return listPhoneDTO;
	}

	public void setListPhoneDTO(List<PhoneDTO> listPhoneDTO) {
		this.listPhoneDTO = listPhoneDTO;
	}
}
