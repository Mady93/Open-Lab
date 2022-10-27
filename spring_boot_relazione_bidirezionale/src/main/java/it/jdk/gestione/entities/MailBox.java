package it.jdk.gestione.entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "mailbox")
public class MailBox {

    private String nome;
    private Account accountMittente;
    private List<Messaggio> messaggio;

    @Id
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_fk")
	public Account getAccountMittente() {
		return accountMittente;
	}

	public void setAccountMittente(Account accountMittente) {
		this.accountMittente = accountMittente;
	}
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "mailbox")
	public List<Messaggio> getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(List<Messaggio> messaggio) {
		this.messaggio = messaggio;
	}
	
	
	}
