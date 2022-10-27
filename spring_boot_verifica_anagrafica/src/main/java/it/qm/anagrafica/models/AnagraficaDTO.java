package it.qm.anagrafica.models;

import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnagraficaDTO {

	private Long id;
	private String cap;
	private String codiceFiscale;
	private String codiceFiscaleAzienda;
	private String nome;
	private String cognome;
	private String comune;
	private String provincia;
	private String email;
	private String indirizzo;
	private String nazionalita;
	private String nomeProfessione;
	private String partitaIva;
	private String ragioneFiscale;
	private String telefono;
	private String tipoCliente;
	private LocalDate dataCreazione;
	private LocalDate dataLastUpdate;

	public AnagraficaDTO() {

	}

	public AnagraficaDTO(Long id, String cap, String codiceFiscale, String codiceFiscaleAzienda, String nome,
			String cognome, String comune, String provincia, String email, String indirizzo, String nazionalita,
			String nomeProfessione, String partitaIva, String ragioneFiscale, String telefono, String tipoCliente,
			LocalDate dataCreazione, LocalDate dataLastUpdate) {
		this.id = id;
		this.cap = cap;
		this.codiceFiscale = codiceFiscale;
		this.codiceFiscaleAzienda = codiceFiscaleAzienda;
		this.nome = nome;
		this.cognome = cognome;
		this.comune = comune;
		this.provincia = provincia;
		this.email = email;
		this.indirizzo = indirizzo;
		this.nazionalita = nazionalita;
		this.nomeProfessione = nomeProfessione;
		this.partitaIva = partitaIva;
		this.ragioneFiscale = ragioneFiscale;
		this.telefono = telefono;
		this.tipoCliente = tipoCliente;
		this.dataCreazione = dataCreazione;
		this.dataLastUpdate = dataLastUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceFiscaleAzienda() {
		return codiceFiscaleAzienda;
	}

	public void setCodiceFiscaleAzienda(String codiceFiscaleAzienda) {
		this.codiceFiscaleAzienda = codiceFiscaleAzienda;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public String getNomeProfessione() {
		return nomeProfessione;
	}

	public void setNomeProfessione(String nomeProfessione) {
		this.nomeProfessione = nomeProfessione;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getRagioneFiscale() {
		return ragioneFiscale;
	}

	public void setRagioneFiscale(String ragioneFiscale) {
		this.ragioneFiscale = ragioneFiscale;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public LocalDate getDataLastUpdate() {
		return dataLastUpdate;
	}

	public void setDataLastUpdate(LocalDate dataLastUpdate) {
		this.dataLastUpdate = dataLastUpdate;
	}
}
