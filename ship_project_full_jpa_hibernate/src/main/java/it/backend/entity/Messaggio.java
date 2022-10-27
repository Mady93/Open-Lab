package it.backend.entity;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "messaggio")
@SequenceGenerator(name = "messaggio_sequence_generator",sequenceName = "messaggio_sequence",
        initialValue = 1, allocationSize = 3)
public class Messaggio {

    private Integer id;
    private String testo;
    private String oggetto;

    private Account mittente;
    private Account destinatario;
    private MailBox mailBox;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "messaggio_sequence_generator" ,
            strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "testo")
    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    @Column(name = "oggetto")
    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    @ManyToOne
    @JoinColumn(name = "account_mittente")
    public Account getMittente() {
        return mittente;
    }

    public void setMittente(Account mittente) {
        this.mittente = mittente;
    }

    @ManyToOne
    @JoinColumn(name = "account_destinatario")
    public Account getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Account destinatario) {
        this.destinatario = destinatario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mailbox")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    public MailBox getMailBox() {
        return mailBox;
    }

    public void setMailBox(MailBox mailBox) {
        this.mailBox = mailBox;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                "id=" + id +
                ", testo='" + testo + '\'' +
                ", oggetto='" + oggetto + '\'' +
                '}';
    }
}
