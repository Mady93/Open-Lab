package it.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "impiegato")
public class Impiegato {

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String cittaResidenza;

    private Integer ufficio;
    private Integer stipendio;
    private Dipartimento dipartimento;

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

    @Column(name = "ufficio")
    public Integer getUfficio() {
        return ufficio;
    }

    public void setUfficio(Integer ufficio) {
        this.ufficio = ufficio;
    }

    @Column(name = "stipendio")
    public Integer getStipendio() {
        return stipendio;
    }

    public void setStipendio(Integer stipendio) {
        this.stipendio = stipendio;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dipartimento_fk")
    public Dipartimento getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(Dipartimento dipartimento) {
        this.dipartimento = dipartimento;
    }

}
