package it.jdk.gestione.dto;

import java.util.List;

import it.jdk.gestione.entities.MailBox;
import it.jdk.gestione.entities.Messaggio;

public class AccountDTO {

	private String email;
    private String nome;
    private String cognome;
    private List<MailBox> mailbox;
    private List<Messaggio> messaggioMittente;
    private List<Messaggio> messaggioDestinatario;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public List<MailBox> getMailbox() {
		return mailbox;
	}
	public void setMailbox(List<MailBox> mailbox) {
		this.mailbox = mailbox;
	}
	public List<Messaggio> getMessaggioMittente() {
		return messaggioMittente;
	}
	public void setMessaggioMittente(List<Messaggio> messaggioMittente) {
		this.messaggioMittente = messaggioMittente;
	}
	public List<Messaggio> getMessaggioDestinatario() {
		return messaggioDestinatario;
	}
	public void setMessaggioDestinatario(List<Messaggio> messaggioDestinatario) {
		this.messaggioDestinatario = messaggioDestinatario;
	}
	
	 
}
