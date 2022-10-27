package it.jdk.project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "auto")
@SequenceGenerator(name = "auto_sequence_generator", sequenceName = "sequence_auto", initialValue = 1, allocationSize = 3)
public class Auto {

	private Long id;
	private String targa;
	private String modello;
	private int version;

	@Id
	@GeneratedValue(generator = "auto_sequence_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "ida")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "targa")
	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	@Column(name = "modello")
	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	@Version
	@Column(name = "version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Auto [id=" + id + ", targa=" + targa + ", modello=" + modello + ", version=" + version + "]";
	}

}
