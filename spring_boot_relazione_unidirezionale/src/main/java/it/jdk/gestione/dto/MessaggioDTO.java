package it.jdk.gestione.dto;

public class MessaggioDTO {

	private Integer id;
    private String testo;
    private String oggetto;
    private AccountDTO accountMittente;
    private AccountDTO accountDestinatario;
    private MailBoxDTO mailBox;
    
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
	public AccountDTO getAccountMittente() {
		return accountMittente;
	}
	public void setAccountMittente(AccountDTO accountMittente) {
		this.accountMittente = accountMittente;
	}
	public AccountDTO getAccountDestinatario() {
		return accountDestinatario;
	}
	public void setAccountDestinatario(AccountDTO accountDestinatario) {
		this.accountDestinatario = accountDestinatario;
	}
	public MailBoxDTO getMailBox() {
		return mailBox;
	}
	public void setMailBox(MailBoxDTO mailBox) {
		this.mailBox = mailBox;
	}
    
    
}
