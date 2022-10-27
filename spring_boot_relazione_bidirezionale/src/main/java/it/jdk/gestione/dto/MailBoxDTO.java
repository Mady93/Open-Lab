package it.jdk.gestione.dto;

import java.util.List;

import it.jdk.gestione.entities.Account;
import it.jdk.gestione.entities.Messaggio;

public class MailBoxDTO {

	private String nome;
    private Account accountMittente;
    private List<Messaggio> messaggio;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Account getAccountMittente() {
		return accountMittente;
	}
	public void setAccountMittente(Account accountMittente) {
		this.accountMittente = accountMittente;
	}
	public List<Messaggio> getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(List<Messaggio> messaggio) {
		this.messaggio = messaggio;
	}

    
}
