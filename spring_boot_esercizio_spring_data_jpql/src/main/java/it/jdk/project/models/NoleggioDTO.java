package it.jdk.project.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoleggioDTO {

	private Long id;
	private Double costo;
	private Date dataInizio;
	private Date dataFine;
	private Integer version;
	private ClienteDTO clienteDTO;
	private AutoDTO autoDTO;

	public NoleggioDTO() {

	}

	public NoleggioDTO(Long id, Double costo, Date dataInizio, Date dataFine, Integer version) {
		super();
		this.id = id;
		this.costo = costo;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public AutoDTO getAutoDTO() {
		return autoDTO;
	}

	public void setAutoDTO(AutoDTO autoDTO) {
		this.autoDTO = autoDTO;
	}

}
