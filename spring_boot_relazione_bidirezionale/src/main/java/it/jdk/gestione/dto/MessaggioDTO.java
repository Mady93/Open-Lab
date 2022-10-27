package it.jdk.gestione.dto;

import it.jdk.gestione.entities.Account;
import it.jdk.gestione.entities.MailBox;

public class MessaggioDTO {

	 private Integer id;
	    private String testo;
	    private String oggetto;
	    private Account accountMittente;
	    private Account accountDestinatario;
	    private MailBox mailBox;
	    
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTesto() {
			return testo;
		}
		public void setTesto(String testo) {
			this.testo = testo;
		}
		public String getOggetto() {
			return oggetto;
		}
		public void setOggetto(String oggetto) {
			this.oggetto = oggetto;
		}
		public Account getAccountMittente() {
			return accountMittente;
		}
		public void setAccountMittente(Account accountMittente) {
			this.accountMittente = accountMittente;
		}
		public Account getAccountDestinatario() {
			return accountDestinatario;
		}
		public void setAccountDestinatario(Account accountDestinatario) {
			this.accountDestinatario = accountDestinatario;
		}
		public MailBox getMailBox() {
			return mailBox;
		}
		public void setMailBox(MailBox mailBox) {
			this.mailBox = mailBox;
		}
	    
	    
}
