package it.backend.spring.data;

public class CustomerFilterDTO {

    private String lastName;
    private String fiscalCode;

    public CustomerFilterDTO(String lastName, String fiscalCode) {
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
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
}
