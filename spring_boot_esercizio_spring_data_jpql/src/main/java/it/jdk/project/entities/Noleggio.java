package it.jdk.project.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name = "noleggio")
@SequenceGenerator(name = "noleggio_sequence_generator", sequenceName = "sequence_noleggio", initialValue = 1, allocationSize = 3)
public class Noleggio {

	private Long id;
	private Double costo;
	private Date dataInizio;
	private Date dataFine;
	private int version;
	private Cliente cliente;
	private Auto auto;

	@Id
	@GeneratedValue(generator = "noleggio_sequence_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "idn")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "costo")
	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	@Column(name = "data_inizio")
	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	@Column(name = "data_fine")
	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	
	@Version
	@Column(name = "version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "foreign_key_idclient")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "foreign_key_idauto")
	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	@Override
	public String toString() {
		return "Noleggio [id=" + id + ", costo=" + costo + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine
				+ ", version=" + version + ", cliente=" + cliente + ", auto=" + auto + "]";
	}

}
