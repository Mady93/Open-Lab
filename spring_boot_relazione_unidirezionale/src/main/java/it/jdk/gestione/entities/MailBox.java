package it.jdk.gestione.entities;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "mailbox")
public class MailBox {

    private String nome;
    private Account accountMittente;

    @Id
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "account_fk")
	public Account getAccountMittente() {
		return accountMittente;
	}

	public void setAccountMittente(Account accountMittente) {
		this.accountMittente = accountMittente;
	}
	}
