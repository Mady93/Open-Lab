package it.jdk.project.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO implements Serializable{

    private String name;
    private Integer id;
    private int version; // per la gestione dell'anomalia lost update(transazioni)

    public ProductDTO() {}

    public ProductDTO(Integer id, String name, int version){
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
