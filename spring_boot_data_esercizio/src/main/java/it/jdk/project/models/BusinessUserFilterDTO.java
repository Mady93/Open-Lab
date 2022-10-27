package it.jdk.project.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessUserFilterDTO implements Serializable{
	
	//rappresentazione dei campi del filtro

    private String fiscalCode;
    private String lastName;

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

