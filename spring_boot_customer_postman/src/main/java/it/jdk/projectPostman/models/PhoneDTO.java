package it.jdk.projectPostman.models;

public class PhoneDTO {

	private Integer id;
    private String number;
    private Integer type;
    private CustomerDTO customerDTO;
    
    public PhoneDTO() {
    	
    }

	

	public PhoneDTO(Integer id, String number, Integer type, CustomerDTO customerDTO) {
		super();
		this.id = id;
		this.number = number;
		this.type = type;
		this.customerDTO = customerDTO;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}



	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}



	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	
}
