package it.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Impiegato")

public class Impiegato {
	

	private String nome;
	private String cognome;
	private Integer ufficio;
	private Double stipendio;
	private String codiceFiscale;
	private String cittaResidenza;
	private Dipartimento dipartimento;
	
	@Column(name="nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(name="cognome")
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	@Column(name="ufficio")
	public Integer getUfficio() {
		return ufficio;
	}
	public void setUfficio(Integer ufficio) {
		this.ufficio = ufficio;
	}
	@Column(name="stipendio")
	public Double getStipendio() {
		return stipendio;
	}
	public void setStipendio(Double stipendio) {
		this.stipendio = stipendio;
	}
	@Id
	@Column(name="codice_fiscale")
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	@Column(name="citta_residenza")
	public String getCittaResidenza() {
		return cittaResidenza;
	}
	public void setCittaResidenza(String cittaResidenza) {
		this.cittaResidenza = cittaResidenza;
	}
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dipartimeno_fk")             
	public Dipartimento getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(Dipartimento dipartimento) {
		this.dipartimento = dipartimento;
	}
}



