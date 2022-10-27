package it.jpa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import it.backend.entity.Account;
import it.backend.entity.Dipartimento;
import it.backend.entity.Impiegato;
import it.backend.entity.MailBox;
import it.backend.entity.Messaggio;

import java.util.HashSet;

import javax.persistence.*;
import javax.swing.JTextArea;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class JPATestEsercizio1 {

    @PersistenceContext
    EntityManager em;

    
    /*
    @Test
    @Rollback(false)
    public void inserimentoMailBox() {
    	MailBox mailBox = new MailBox(); 
   	 	 mailBox.setAccountt(new Account());
   	 	 mailBox.setMessaggi(new HashSet<>());
   	 	 mailBox.setNome("mailBox1");
   	 	em.persist(mailBox); 
    }
    
    @Test
    @Rollback(false)
    public void inserimentoMessaggio() {
    	Messaggio messaggio = new Messaggio(); 
   	 	messaggio.setAccountDestinatario(new Account() );
   	 	messaggio.setAccountMittente(new Account());
   	 	messaggio.setMailBoxx(new MailBox());
   	 	messaggio.setOggetto("Rifornimento");
   	 	messaggio.setText(new JTextArea ("Aspetto i rifornimenti"));
   	 	messaggio.setCodice(1);
   	 	em.persist(messaggio); 
    }
    
    @Test
    @Rollback(false)
    public void inserimentoAccount() {
    	Account account = new Account();
    	account.setCognomeAccount("Fantozzi");
    	account.setEmail("Fantozzi.Ugo@gmail.com");
    	account.setMailBox(new MailBox());
    	account.setMessaggio(new HashSet<>());
    	account.setNomeAccount("Fantozzi");
    	em.persist(account);
    }
    @Test
    @Rollback(false)
    public void ricercaMessaggio() {
    	Messaggio messaggio = em.find(Messaggio.class, 1);
        System.out.print(messaggio.getText());
    }
    
    @Test
    @Rollback(false)
    public void updateMessaggio() {
    	Messaggio messaggio = new Messaggio(); 
   	 	messaggio.setAccountDestinatario(new Account());
   	 	messaggio.setAccountMittente(new Account());
   	 	messaggio.setMailBoxx(new MailBox());
   	 	messaggio.setOggetto("Riellaborazione");
   	 	messaggio.setText(new JTextArea ("Ciao come va"));
   	 	messaggio.setCodice(1);
   	 	messaggio= em.merge(messaggio);
    }*/
    /*
    @Test
    @Rollback(false)
    /*
     * persistenza messaggio con associazione mailbox
     */
    /*
    public void inserimentoMessaggioNellaMailBox() {
    	MailBox mailBox = em.find(MailBox.class,"mailBox1");
    	if(mailBox != null) {
    		Messaggio messaggio = new Messaggio();
    		messaggio.setAccountDestinatario(new Account());
    		messaggio.setAccountMittente(new Account());
    		messaggio.setMailBoxx(mailBox);
    		messaggio.setOggetto("richiesta passaporto");
    		messaggio.setText(new JTextArea("Buongiorno..."));
    	}
    	
    	
    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
                                  // 30.09.
    

    @Test
    @Rollback(false)
    /*
     * Laborarotio1
     * Persistenza dipartimento
     */
    public void persistenzaDipartimento() {
    	Dipartimento dipartimento = new Dipartimento();
        dipartimento.setNome("Dip1");
        dipartimento.setCitta("Milano");
        dipartimento.setIndirizzo("Via Roma 1");
        em.persist(dipartimento);
    }
    
    @Test
    @Rollback(false)
    /*
     * Laborarotio2
     *  Merge merge dati impiegato
     */
    public void mergeMergeDatiImpiegato() {
    	  Dipartimento dipartimento = new Dipartimento();
          dipartimento.setNome("Dip1");
          dipartimento.setCitta("Roma");
          dipartimento.setIndirizzo("Via Roma 1");
          dipartimento.setImpiegati(new HashSet<>());
          em.merge(dipartimento);
    }
    
    @Test
    @Rollback(false)
    /*
     * Laborarotio3
     *  Find recupero un impiegato
     */
    public void findReccuperoUnImpiegato() {
    	Dipartimento dipartimento = em.find(Dipartimento.class,"Dip1");
        System.out.println(dipartimento);
    }
    
    @Test
    @Rollback(false)
    /*
     * Laborarotio4
     *   Remove rimuovo un impiegato
     */
    public void removeRimuovoUnImpiegato() {
    	 Dipartimento dipartimento = em.find(Dipartimento.class,"Dip1");
         em.remove(dipartimento);
    }
    
    @Test
    @Rollback(false)
    /*
     * Laborarotio5
     *    Persistenza impiegato con associazione dipartimento
     */
    public void  persistenzaImpiegatoConAssociazioneDipartimento() {
    	  Dipartimento dipartimento = em.find(Dipartimento.class,"Amministrazione");
          if(dipartimento != null) {
              Impiegato impiegato = new Impiegato();
              impiegato.setDipartimento(dipartimento);
              impiegato.setNome("Carlo");
              impiegato.setCognome("Magno");
              impiegato.setCodiceFiscale("123459087");
              impiegato.setUfficio(10);
              impiegato.setCittaResidenza("Roma");
              impiegato.setStipendio(20000.0);
              em.persist(impiegato);
          } else {
              System.out.println("Dipartimento non trovato");
          }
    }
    
    @Test
    @Rollback(false)
    /*
     * Laborarotio6
     *     Aggiornamento impiegato
     */
    public void aggoirnamentoImpiegato() {
    	   Impiegato impiegato = em.find(Impiegato.class,"123459087");
           if(impiegato != null)
               impiegato.setStipendio(30000.0);
           else
               System.out.println("Impiegato non trovato"); 
    }
   
    @Test
    @Rollback(false)
    /*
     * Laborarotio7
     *     Aggiornamento relazione impiegato
     */
    public void aggiornamentoRelazioneImpiegato() {
    	 Impiegato impiegato = em.find(Impiegato.class,"123459087");
         Dipartimento dipartimento = em.find(Dipartimento.class,"Produzione");

         if(dipartimento == null) {
             System.out.println("Dipartimento non trovato");
             return;
         }
         if(impiegato == null) {
             System.out.println("Impiegato non trovato");
             return;
         }
         
         impiegato.setDipartimento(dipartimento);
         dipartimento.getImpiegati().add(impiegato);
    }
    
    @Test
    @Rollback(false)
    /*
     * Laborarotio8
     *     Disassociazione impiegato, verifica orphanRemoval true
        //Attenzione al problema di memoria su getImpiegati ....
     */ 
    public void disassociazioneImpiegato() {
    	Impiegato impiegato = em.find(Impiegato.class,"123459087");
        impiegato.getDipartimento().getImpiegati().remove(impiegato);
        impiegato.setDipartimento(null);
    }
    
    @Test
    @Rollback(false)
    /*
     * Laborarotio9
     *   Stesso risultato
     */ 
    public void disassociazioneImpiegatoStessoRislutato() {
    	Impiegato impiegato = em.find(Impiegato.class,"ABC1");
        em.remove(impiegato);
    }

}