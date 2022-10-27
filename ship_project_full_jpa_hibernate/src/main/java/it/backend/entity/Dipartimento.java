package it.backend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dipartimento")
public class Dipartimento {

    private String nome;
    private String citta;
    private String indirizzo;
    private List<Impiegato> impiegati;

    @Id
    @Column(name="nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "citta")
    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Column(name = "indirizzo")
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.MERGE}, orphanRemoval = true,
            mappedBy = "dipartimento")
    public List<Impiegato> getImpiegati() {
        return impiegati;
    }

    public void setImpiegati(List<Impiegato> impiegati) {
        this.impiegati = impiegati;
    }

    @Override
    public String toString() {
        return "Dipartimento{" +
                "nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
