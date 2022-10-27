package it.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    private String email;
    private String nome;
    private String coognome;
    private List<MailBox> mailboxes;

    @Id
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "cognome")
    public String getCoognome() {
        return coognome;
    }

    public void setCoognome(String coognome) {
        this.coognome = coognome;
    }

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "account")
    public List<MailBox> getMailboxes() {
        return mailboxes;
    }

    public void setMailboxes(List<MailBox> mailboxes) {
        this.mailboxes = mailboxes;
    }
}
