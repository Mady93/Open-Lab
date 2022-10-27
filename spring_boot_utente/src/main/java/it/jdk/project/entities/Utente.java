package it.jdk.project.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="login")
@SequenceGenerator(name="utente_generator_strategy", sequenceName="sequence_utente", initialValue = 1,  allocationSize = 3)
public class Utente {

	private Long id;
	private String cognome;
	private String nome;
	private String via;
	private char cap;
	private String numeroCivico;
	private String telefono;
	private String nazionalita;
	private Date dataNascita;
	
	@Id
	@GeneratedValue(generator = "utente_generator_strategy" , strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="cognome")
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	@Column(name="nome")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="via")
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	@Column(name="cap")
	public char getCap() {
		return cap;
	}
	
	public void setCap(char cap) {
		this.cap = cap;
	}
	
	@Column(name="numero_civico")
	public String getNumeroCivico() {
		return numeroCivico;
	}
	
	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}
	
	@Column(name="telefono")
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Column(name="nazionalita")
	public String getNazionalita() {
		return nazionalita;
	}
	
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	
	@Column(name="data_nascita")
	public Date getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
		}
	}
