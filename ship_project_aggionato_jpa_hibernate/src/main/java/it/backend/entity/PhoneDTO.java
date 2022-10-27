package it.backend.entity;

public class PhoneDTO {
    private String number;
    private CustomerDTO customer;

    public PhoneDTO(String number, String nome, String cognome) {
        this.number = number;
        this.customer = new CustomerDTO(nome, cognome);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
