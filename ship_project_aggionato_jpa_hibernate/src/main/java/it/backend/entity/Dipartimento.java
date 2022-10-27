package it.backend.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dipartimento")


public class Dipartimento {
private String nome;
private String indirizzo;
private String citta;
private Set<Impiegato> impiegati; // List<Impiegato>impiegati

@Id
@Column(name="nome")
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
@Column(name="indirizzo")
public String getIndirizzo() {
	return indirizzo;
}
public void setIndirizzo(String indirizzo) {
	this.indirizzo = indirizzo;
}
@Column(name="citta")
public String getCitta() {
	return citta;
}
public void setCitta(String citta) {
	this.citta = citta;
}
@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "dipartimento")
public Set<Impiegato> getImpiegati() {
	return impiegati;
}
public void setImpiegati(Set<Impiegato> impiegati) {
	this.impiegati = impiegati;
}
}
