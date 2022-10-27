package it.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.JTextArea;

@Entity
@Table(name="messaggio")
public class Messaggio {

	private Integer codice;
	private JTextArea text;
	private String oggetto;
	private MailBox mailBoxx;
	private Account accountMittente;
	private Account accountDestinatario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codice")
	public Integer getCodice() {
		return codice;
	}
	
	public void setCodice(Integer codice) {
		this.codice = codice;
	}
	
	@Column(name="text")
	public JTextArea getText() {
		return text;
	}
	
	public void setText(JTextArea text) {
		this.text = text;
	}
	
	@Column(name="oggetto")
	public String getOggetto() {
		return oggetto;
	}
	
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nome_mailbox_fk")
	public MailBox getMailBoxx() {
		return mailBoxx;
	}
	
	public void setMailBoxx(MailBox mailBoxx) {
		this.mailBoxx = mailBoxx;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_acount_mittente_foreign_key")
	public Account getAccountMittente() {
		return accountMittente;
	}

	public void setAccountMittente(Account accountMittente) {
		this.accountMittente = accountMittente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email_account_destinatario_foreign_key") 
	public Account getAccountDestinatario() {
		return accountDestinatario;
	}

	public void setAccountDestinatario(Account accountDestinatario) {
		this.accountDestinatario = accountDestinatario;
	}
	
	
}
