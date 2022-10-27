package it.jdk.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationDTO implements Serializable{

    private Integer id;
    private BusinessUserDTO user;
    private Date date;
    private int version;

    public ValidationDTO() {}

    public ValidationDTO(Integer id, Date date, BusinessUserDTO user, int version){
        this.id = id;
        this.date = date;
        this.user = user;
        this.version = version;
    }

    public BusinessUserDTO getUser() {
        return user;
    }

    public void setUser(BusinessUserDTO user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
