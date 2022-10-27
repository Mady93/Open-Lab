package it.jdk.gestione.entities;


import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    private String email;
    private String nome;
    private String cognome;
    private List<MailBox> mailbox;
    private List<Messaggio> messaggioMittente;
    private List<Messaggio> messaggioDestinatario;

    @Id
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "accountMittente")
	public List<MailBox> getMailbox() {
		return mailbox;
	}

	public void setMailbox(List<MailBox> mailbox) {
		this.mailbox = mailbox;
	}

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "accountMittente")
	public List<Messaggio> getMessaggioMittente() {
		return messaggioMittente;
	}

	public void setMessaggioMittente(List<Messaggio> messaggioMittente) {
		this.messaggioMittente = messaggioMittente;
	}

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "accountDestinatario")
	public List<Messaggio> getMessaggioDestinatario() {
		return messaggioDestinatario;
	}

	public void setMessaggioDestinatario(List<Messaggio> messaggioDestinatario) {
		this.messaggioDestinatario = messaggioDestinatario;
	}
	
	
    
    
}
