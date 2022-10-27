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
@Table(name="account")
public class Account {
	
	private String nomeAccount;
	private String cognomeAccount;
	private String email;
	private MailBox mailBox;
	private Set<Messaggio> messaggio;
	
	@Column(name="nome_account")
	public String getNomeAccount() {
		return nomeAccount;
	}
	
	public void setNomeAccount(String nomeAccount) {
		this.nomeAccount = nomeAccount;
	}
	
	@Column(name="cognome_account")
	public String getCognomeAccount() {
		return cognomeAccount;
	}
	
	public void setCognomeAccount(String cognomeAccount) {
		this.cognomeAccount = cognomeAccount;
	}
	@Id
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER,
            mappedBy = "accountt", orphanRemoval = true)
	public MailBox getMailBox() {
		return mailBox;
	}

	public void setMailBox(MailBox mailBox) {
		this.mailBox = mailBox;
	}

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "account")
	public Set<Messaggio> getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(Set<Messaggio> messaggio) {
		this.messaggio = messaggio;
	}
	
	
 }

