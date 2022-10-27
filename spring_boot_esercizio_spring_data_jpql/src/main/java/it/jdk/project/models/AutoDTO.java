package it.jdk.project.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutoDTO {

	private Long id;
	private String targa;
	private String modello;
	private Integer version;

	public AutoDTO() {

	}

	public AutoDTO(Long id, String targa, String modello, Integer version) {
		this.id = id;
		this.targa = targa;
		this.modello = modello;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
