package it.qm.anagrafica.entities;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "anagrafica")
@SequenceGenerator(name = "anagrafica_sequence_generator", sequenceName = "sequence_anagrafica", initialValue = 1, allocationSize = 3)
public class Anagrafica {

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

	@Id
	@GeneratedValue(generator = "anagrafica_sequence_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cap")
	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	@Column(name = "codice_fiscale")
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	@Column(name = "codice_fiscale_azienda")
	public String getCodiceFiscaleAzienda() {
		return codiceFiscaleAzienda;
	}

	public void setCodiceFiscaleAzienda(String codiceFiscaleAzienda) {
		this.codiceFiscaleAzienda = codiceFiscaleAzienda;
	}

	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cognome")
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Column(name = "comune")
	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	@Column(name = "provincia")
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "indirizzo")
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Column(name = "nazionalita")
	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	@Column(name = "nome_professione")
	public String getNomeProfessione() {
		return nomeProfessione;
	}

	public void setNomeProfessione(String nomeProfessione) {
		this.nomeProfessione = nomeProfessione;
	}

	@Column(name = "partita_iva")
	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	@Column(name = "ragione_fiscale")
	public String getRagioneFiscale() {
		return ragioneFiscale;
	}

	public void setRagioneFiscale(String ragioneFiscale) {
		this.ragioneFiscale = ragioneFiscale;
	}

	@Column(name = "telefono")
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "tipo_cliente")
	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	@Column(name = "data_creazione")
	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	@Column(name = "data_last_update")
	public LocalDate getDataLastUpdate() {
		return dataLastUpdate;
	}

	public void setDataLastUpdate(LocalDate dataLastUpdate) {
		this.dataLastUpdate = dataLastUpdate;
	}

	@Override
	public String toString() {
		return "Anagrafica [id=" + id + ", cap=" + cap + ", codiceFiscale=" + codiceFiscale + ", codiceFiscaleAzienda="
				+ codiceFiscaleAzienda + ", nome=" + nome + ", cognome=" + cognome + ", comune=" + comune
				+ ", provincia=" + provincia + ", email=" + email + ", indirizzo=" + indirizzo + ", nazionalita="
				+ nazionalita + ", nomeProfessione=" + nomeProfessione + ", partitaIva=" + partitaIva
				+ ", ragioneFiscale=" + ragioneFiscale + ", telefono=" + telefono + ", tipoCliente=" + tipoCliente
				+ ", dataCreazione=" + dataCreazione + ", dataLastUpdate=" + dataLastUpdate + "]";
	}
}