package it.jdk.gestione.entities;
import javax.persistence.*;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name = "messaggio")
@SequenceGenerator(name = "messaggio_sequence_generator",sequenceName = "messaggio_sequence",
        initialValue = 1, allocationSize = 3)
public class Messaggio {

    private Integer id;
    private String testo;
    private String oggetto;
    private Account accountMittente;
    private Account accountDestinatario;
    private MailBox mailBox;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "messaggio_sequence_generator" , strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="testo")
	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	@Column(name="oggetto")
	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "account_mittente_fk")
	public Account getAccountMittente() {
		return accountMittente;
	}

	public void setAccountMittente(Account accountMittente) {
		this.accountMittente = accountMittente;
	}

	 @ManyToOne(fetch = FetchType.LAZY)
	    @LazyToOne(LazyToOneOption.NO_PROXY)
	    @JoinColumn(name = "account_destinatario_fk")
	public Account getAccountDestinatario() {
		return accountDestinatario;
	}

	public void setAccountDestinatario(Account accountDestinatario) {
		this.accountDestinatario = accountDestinatario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "mailbox_fk")
	public MailBox getMailBox() {
		return mailBox;
	}

	public void setMailBox(MailBox mailBox) {
		this.mailBox = mailBox;
	}
    
    
}