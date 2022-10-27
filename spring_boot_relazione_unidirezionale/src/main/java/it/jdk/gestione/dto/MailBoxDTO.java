package it.jdk.gestione.dto;

public class MailBoxDTO {

	private String nome;
    private AccountDTO accountMittente;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public AccountDTO getAccountMittente() {
		return accountMittente;
	}

	public void setAccountMittente(AccountDTO accountMittente) {
		this.accountMittente = accountMittente;
	}
}
