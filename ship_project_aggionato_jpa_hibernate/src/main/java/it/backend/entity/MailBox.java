package it.backend.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="mailbox")
public class MailBox {
	
	private String nome;
	private Set<Messaggio> messaggi;
	private Account accountt;
	
	@Id
	@Column(name="nome")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.LAZY, mappedBy = "mailBoxx")
	public Set<Messaggio> getMessaggi() {
		return messaggi;
	}
	
	public void setMessaggi(Set<Messaggio> messaggi) {
		this.messaggi = messaggi;
	}

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email_acc_mittente_foreign_key")
	public Account getAccountt() {
		return accountt;
	}

	public void setAccountt(Account accountt) {
		this.accountt = accountt;
	}
}

