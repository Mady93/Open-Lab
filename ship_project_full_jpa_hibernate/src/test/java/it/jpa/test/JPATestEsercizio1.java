package it.jpa.test;

import it.backend.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.*;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class JPATestEsercizio1 {

    @PersistenceContext
    EntityManager em;


    @Test
    @Rollback(false)
    /**
     *  Lab 1
     */
    public void lab1() {
        Dipartimento dipartimento = new Dipartimento();
        dipartimento.setNome("DipTest1");
        dipartimento.setCitta("Roma");
        dipartimento.setIndirizzo("Via Roma 1");
        em.persist(dipartimento);
    }

    @Test
    @Rollback(false)
    /**
     *  Lab 2
     *  Eseguire una prima volta e successivamente una seconda
     *  osservando l'output prodotto SQL di Hibernate
     */
    public void lab2() {
        // Merge Dipartimento
        Dipartimento dipartimento = new Dipartimento();
        dipartimento.setNome("DipTest1");
        dipartimento.setCitta("Roma");
        dipartimento.setIndirizzo("Via Roma 2");
        dipartimento.setImpiegati(new ArrayList<>()); // issue
        dipartimento = em.merge(dipartimento);
    }

    @Test
    @Rollback(false)
    /**
     *  Lab 3
     *  Eseguire con Lazy ed Eager su relazione con Impiegato
     */
    public void lab3() {
        // find Dipartimento
        Dipartimento dipartimento = em.find(Dipartimento.class,"DipTest1");//Managed
        System.out.println(dipartimento);
    }

    @Test
    @Rollback(false)
    /**
     *  Lab 4
     */
    public void lab4() {
        // remove Dipartimento
        Dipartimento dipartimento = em.find(Dipartimento.class,"DipTest1");//Managed
        em.remove(dipartimento);
    }

    @Test
    @Rollback(false)
    /**
     *  Lab 5
     */
    public void lab5() {
        Dipartimento dipartimento = em.find(Dipartimento.class, "Amministrazione"); //Managed
        // dipartimento
        if( dipartimento != null) {
            Impiegato impiegato = new Impiegato(); //New
            impiegato.setNome("Carlo");
            impiegato.setCognome("Magno");
            impiegato.setCodiceFiscale("123459087");
            impiegato.setUfficio(10);
            impiegato.setCittaResidenza("Roma");
            impiegato.setStipendio(20000);
            impiegato.setDipartimento(dipartimento);
            em.persist(impiegato); //impiegato
        } else {
            System.out.println("Errore, dipartimento non trovato");
        }
    }

    @Test
    @Rollback(false)
    /**
     *  Lab 6
     */
    public void lab6() {
        // Aggiornamento impiegato
        Impiegato impiegato = em.find(Impiegato.class,"123459087"); // Managed
        if(impiegato != null ) {
            impiegato.setStipendio(30000); // Schedulata un update
        } else {
            System.out.println("Errore, impiegato non trovato");
        }
    }

    @Test
    @Rollback(false)
    /**
     *  Lab 7
     */
    public void lab7() {
        Impiegato impiegato = em.find(Impiegato.class,"123459087");
        if(impiegato == null) {
            System.out.println("Impiegato non trovato");
            return;
        }
        Dipartimento dipartimento = em.find(Dipartimento.class, "Produzione");
        if(dipartimento == null) {
            System.out.println("Dipartimento non trovato");
            return;
        }
        impiegato.setDipartimento(dipartimento);
    }

    @Test
    @Rollback(false)
    /**
     *  Lab 9
     */
    public void lab8() {
        // Disassociazione  impiegato
        Impiegato impiegato = em.find(Impiegato.class,"123459087");
        em.remove(impiegato); // delete schedulata

        /*Dipartimento dipartimento = new Dipartimento();
        dipartimento.setNome("X");
        dipartimento.setCitta("Pippo4");
        dipartimento.setImpiegati(new ArrayList<>());
        Impiegato imp = new Impiegato();
        imp.setCodiceFiscale("X");
        imp.setDipartimento(dipartimento);
        dipartimento.getImpiegati().add(imp);
        em.merge(dipartimento);*/
    }

    @Test
    @Rollback(false)
    /**
     *  Test 1
     *  Persistenza singola entity
     */
    public void test1() {
        Customer c = new Customer();
        c.setFirstName("Mario");
        c.setLastName("Rossi");
        c.setFiscalCode("AQWERT");
        em.persist(c);
    }

    @Test
    @Rollback(false)
    /**
     *  Test 2
     *  Persistenza entity multiple
     *  Valutazione Sequence Generator
     *
     * Decommento spring.jpa.properties.hibernate.id.new_generator_mappings=false -> Hi/Lo algorithm abilitato
     * Commento spring.jpa.properties.hibernate.id.optimizer.pooled.preferred=pooled-lo
     *
     * Commento spring.jpa.properties.hibernate.id.new_generator_mappings=false -> Hi/Lo algorithm abilitato
     * Decommento spring.jpa.properties.hibernate.id.optimizer.pooled.preferred=pooled-lo --> pooled-lo
     *
     * Rimozione dei record precedenti e:
     * Commento spring.jpa.properties.hibernate.id.new_generator_mappings=false -> Hi/Lo algorithm abilitato
     * Commento spring.jpa.properties.hibernate.id.optimizer.pooled.preferred=pooled-lo --> pooled
     */
    public void test2() {
        Customer c = new Customer();
        c.setFirstName("User1");
        c.setLastName("User1");
        c.setFiscalCode("User1");
        em.persist(c);
        c = new Customer();
        c.setFirstName("User2");
        c.setLastName("User2");
        c.setFiscalCode("User2");
        em.persist(c);
        c = new Customer();
        c.setFirstName("User3");
        c.setLastName("User3");
        c.setFiscalCode("User3");
        em.persist(c);
    }

    @Test
    @Rollback(false)
    /**
     *  Test 3
     *  Find
     */
    public void test3() {
        Customer c = em.find(Customer.class, 1); // <- id esistente / id non esistente
    }

    @Test
    @Rollback(false)
    /**
     *  Test 4
     *  getReference
     */
    public void test4() {
        Customer c = em.getReference(Customer.class, 1); // <- id esistente / id non esistente
    }

    /**
     *  Test 5
     *  Merge detached entity
     *  Osserviamo cosa accade in output.
     *
     *  Domande:
     *  Perchè abbiamo una query aggiuntiva?
     *  Perchè nel join del merge compare la relazione lazy?
     *  Cosa succede se rimuovo il cascade Merge dalla relazione Lazy?
     *  Cosa succede se lo rimuovo dalle relazioni Address e CreditCard?
     *
     */
    @Test
    @Rollback(false)
    public void test5() {
        Customer detached = em.find(Customer.class, 1);
        em.detach(detached);
        Customer attached = em.merge(detached);
    }

    @Test
    @Rollback(false)
    /**
     *  Test 8
     *  Inserimento Customer con relazioni. I foreign key sono nullable.
     *
     *  Nessun errore ma relazioni non valorizzate. ################
     *
     *  Domande:
     *  Perchè le relazioni non sono valorizzate?
     *  Come correggiamo il codice affinchè il tutto funzioni?
     */
    public void test8() {
        Customer customer = new Customer();
        customer.setFirstName("Mario");
        customer.setLastName("Rossi");
        customer.setFiscalCode("QWERTY");

        Set<Phone> phones = new HashSet<Phone>();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setNumber("31243002");
        phone1.setType(0);
        //X

        phone2.setNumber("33923120");
        phone2.setType(0);
        //X

        phones.add(phone1);
        phones.add(phone2);

        customer.setPhoneNumbers(phones);

        Address address = new Address();
        address.setCity("Rome");
        address.setState("IT");
        address.setZip("01001");
        address.setStreet("Via Roma");
        customer.setAddress(address);
        //X

        CreditCard card = new CreditCard();
        card.setNumber("0001392841033");
        card.setCvv("123");
        card.setExpDate(new Date());
        card.setOrganization("VISA");
        customer.setCreditCard(card);
        //X

        em.persist(customer);
    }

    @Test
    @Rollback(false)
    /**
     *  Test 9
     *  Dal test precedente, rimuoviamo tutte le relazioni
     */
    public void test9() {
        Customer detached = em.find(Customer.class, 11);
        em.detach(detached);

        detached.setPhoneNumbers(new HashSet<>());//https://hibernate.atlassian.net/browse/HHH-9940 Issue
        detached.setAddress(null);
        detached.setCreditCard(null);
        detached.setReservations(null);

        Customer cManaged = em.merge(detached);
        cManaged.setFirstName("Test9");
    }

    @Test
    @Rollback(false)
    /**
     *  Test 10
     *  Aggiornamento Customer completo di relazioni.
     *
     *  Aggiornare Customer vuoto fornendo indirizzo e carta di credito.
     *  Notiamo come gli oggetti in stato new vengano resi automaticamente
     *  persistenti durante la fase di sincronizzazzione dell'istanza Customer.
     */
    public void test10() {
        Customer customer = em.find(Customer.class, 1);

        Address address = new Address();
        CreditCard card = new CreditCard();

        address.setStreet("Via Roma 10");
        address.setZip("04030");
        address.setState("IT");
        address.setCity("Roma");
        address.setCustomer(customer);

        card.setOrganization("VISA");
        card.setExpDate(new Date());
        card.setCvv("123");
        card.setNumber("00218129821");
        card.setCustomer(customer);

        customer.setAddress(address);
        customer.setCreditCard(card);
    }

    @Test
    @Rollback(false)
    /**
     *  Test 11
     *  Continuiamo l'aggiornamento fornendo due numeri di telefono al Customer
     *  esistente.
     *
     *  Domande:
     *  Perchè abbiamo una extra query per Phone?
     *  Con le conoscenze in nostro possesso come possiamo evitarla?
     */
    public void test11() {
        Customer customer = em.find(Customer.class, 14);

        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setNumber("3328221123");
        phone1.setType(1);
        phone2.setNumber("289323222");
        phone2.setType(2);

        phone1.setCustomer(customer);
        phone2.setCustomer(customer);

        customer.getPhoneNumbers().add(phone1);
        customer.getPhoneNumbers().add(phone2);
    }

    @Test
    @Rollback(false)
    /**
     *  Test 14
     *  Rimozione Entity
     *
     */
    public void test14() {
        Customer c = em.find(Customer.class, 17);
        em.remove(c);
    }

    @Test
    @Rollback(false)
    /**
     *  Test 16
     *  Refresh Entity
     *  Si recupera lo stato dell'entity da database.
     *
     *  Domanda:
     *  Perchè non sono caricate le entities relazionate?
     *  Come posso caricarle?
     */
    public void test16() {
        Customer c = em.find(Customer.class, 17);
        c.setFirstName("Un nuovo nome");
        //c.setAddress(null);
        //c.setCreditCard(null);
        em.refresh(c);
    }

    @Test
    @Rollback(false)
    /**
     * Test 17a
     * Inserimento Cruise-Cabin
     */
    public void test17a() {
        Cruise cruise = new Cruise();

        Cabin cabin = new Cabin();
        cruise.setName("Viaggio Roma-Barcellona");
        cabin.setName("Premium");
        cabin.setBackCount(1);
        cabin.setDeckLevel(1);

        em.persist(cruise);
        em.persist(cabin);
    }

    @Test
    @Rollback(false)
    /**
     * Test 17b
     * Prenotazione per un viaggio
     */
    public void test17b() {
        Cruise cruise = em.find(Cruise.class, 8);
        Cabin cabin = em.getReference(Cabin.class, 8);
        Customer customer = em.getReference(Customer.class, 17);

        Set<Cabin> cabins = new HashSet<>();
        Set<Customer> customers = new HashSet<>();

        cabins.add(cabin);
        customers.add(customer);

        Reservation reservation = new Reservation();
        reservation.setCabins(cabins);
        reservation.setCustomers(customers);

        reservation.setCruise(cruise);            // Sincronizzazione relazione bidirezionale
        cruise.getReservation().add(reservation); // Sincronizzazione relazione bidirezionale
    }


    @Test
    @Rollback(false)
    /**
     * Test 18
     * Prenotazione per un viaggio
     */
    public void test18() {
        Cruise cruise = em.getReference(Cruise.class,8);
        Cabin cabin = em.getReference(Cabin.class, 8);
        Customer customer = em.getReference(Customer.class, 17);

        Set<Cabin> cabins = new HashSet<>();
        Set<Customer> customers = new HashSet<>();

        cabins.add(cabin);
        customers.add(customer);

        Reservation reservation = new Reservation();
        reservation.setCruise(cruise);
        reservation.setCabins(cabins);
        reservation.setCustomers(customers);

        em.persist(reservation);
    }

    @Test
    @Rollback(false)
    /**
     * Test 19
     * Rimozione massiva: cancellazione di un viaggio con tutte le prenotazioni
     */
    public void test19() {
        Cruise cruise = em.find(Cruise.class,5);
        if(cruise.getReservation() != null) {
            cruise.getReservation().size();
            cruise.getReservation().clear();
        }
    }


    @Test
    @Rollback(false)
    /**
     *  Test 20
     *
     *  Rimozione Entity
     */
    public void test20() {
        List<Reservation> reservations = null;
        int index = 0;
        int max = 10;
        org.hibernate.engine.spi.SessionImplementor session = em.unwrap(org.hibernate.engine.spi.SessionImplementor.class);
        org.hibernate.engine.spi.PersistenceContext pc = session.getPersistenceContext();
        do {
            //index+=1;
            reservations = getReservations(max, index, 5);
            for(Reservation r : reservations)
                em.remove(r);
            em.flush();
            em.clear();
            Logger.getLogger("TEST20").warning("Number of managed entities:" + pc.getNumberOfManagedEntities());
        } while(reservations.size()>0);
    }

    private List<Reservation> getReservations(int max, int index, int cruiseId){
        TypedQuery<Reservation> query =
                em.createQuery("select d from Reservation d join " +
                        " fetch d.cruise where d.cruise.id=:id", Reservation.class);
        query.setParameter("id", cruiseId);
        return query.setMaxResults(max).setFirstResult(index).getResultList();
    }
    
    /*
     *                           Scrivere codice JPA per inserire:
     * 
L'account con email a.rossi@jdk.it, nome Armando, cognome Rossi

L'account con email pippo@pluto.it, nome Lucio, cognome Verdi

La mailbox di nome a.rossi@jdk.it_OUT associata all'account a.rossi@jdk.it

La mailbox di nome pippo@pluto.it_IN associata all'account  pippo@pluto.it

Un messaggio da a.rossi@jdk.it a pippo@pluto.it nella mailbox a.rossi@jdk.it_OUT con testo ed oggetto a piacere

Il messaggio precedente  nella mailbox  pippo@pluto.it_IN

- Scrivere la seguenti interrogazioni JPQL:

Estrarre le mailbox dell'account a.rossi@jdk.it (*)

Estrarre tutti i messaggi della mailbox  a.rossi@jdk.it_OUT dell'account mittente  a.rossi@jdk.it

(*) Almeno questa query
     */

    @Test
    @Rollback(false)
    public void PersistenzaAccountRossiEpippo() {
    	Account account1 = new Account();
    	account1.setCoognome("Rossi");
    	account1.setNome("Armando");
    	account1.setEmail("a.rossi@jdk.it");
    	account1.setMailboxes(new ArrayList<MailBox>());
    	em.persist(account1);
    	
    	Account account2 = new Account();
    	account2.setCoognome("Verdi");
    	account2.setNome("Lucio");
    	account2.setEmail("pippo@pluto.it");
    	account2.setMailboxes(new ArrayList<MailBox>());
    	em.persist(account2);
}
    
    @Test
    @Rollback(false)
    public void InserimentoMailBoxRossiOutNellAccount1EpippiINnellAccount2() {
    	Account account1 = em.find(Account.class, "a.rossi@jdk.it");
    	Account account2 = em.find(Account.class, "pippo@pluto.it");
         
         if(account1 != null) {
           MailBox mailbox1 = new MailBox();
           mailbox1.setNome("a.rossi@jdk.it_OUT");
           mailbox1.setAccount(account1);
             em.persist(mailbox1); 
         } else {
             System.out.println("Account rossi non trovato");
         }
         
         
         if(account2 != null) {
           MailBox mailbox2 = new MailBox();
           mailbox2.setNome("pippo@pluto.it_IN");
           mailbox2.setAccount(account2);
             em.persist(mailbox2); 
         } else {
             System.out.println("Account pippo non trovato");
         }
         
    }
    
    @Test
    @Rollback(false)
    public void InserimentoMessaggioRossiNellAccountMittenteEmailBoxOUTeMessaggioPippoNellAccountDestinatarioEmailBoxIN() {
    	Account accountMittente = em.find(Account.class, "a.rossi@jdk.it");
    	Account accountDestinatario = em.find(Account.class, "pippo@pluto.it");
  
    	MailBox mailboxOUT = em.find(MailBox.class, "a.rossi@jdk.it_OUT");
        MailBox mailboxIN = em.find(MailBox.class, "pippo@pluto.it_IN");
        	 Messaggio messaggioInviato = new Messaggio();
        	 messaggioInviato.setMittente(accountMittente);
        	 messaggioInviato.setDestinatario(accountDestinatario);
        	 messaggioInviato.setMailBox(mailboxOUT);
        	 messaggioInviato.setOggetto("oggetto da rossi@jdk");
        	 messaggioInviato.setTesto("Ciao sono a.rossi@jdk.it");
             em.persist(messaggioInviato); 
        
        	 Messaggio messaggioRicevuto = new Messaggio();
        	 messaggioRicevuto.setMittente(messaggioRicevuto.getMittente());
        	 messaggioRicevuto.setDestinatario(messaggioRicevuto.getDestinatario());
           messaggioRicevuto.setMailBox(mailboxIN);
           messaggioRicevuto.setOggetto(messaggioRicevuto.getOggetto());
           messaggioRicevuto.setTesto(messaggioRicevuto.getTesto());
             em.persist(messaggioRicevuto); 
    }
    
    @Test
    @Rollback(false)
    public void EstrazioneMailboxDellAccount() {
    TypedQuery<MailBox> query =
            em.createQuery("select c from MailBox c join fetch c.account d " +
                    "where d.email='a.rossi@jdk.it_OUT'",MailBox.class);
            List<MailBox> values = query.getResultList();
            
            for(MailBox m : values) {
            	System.out.println(m);
            }
    }
    
}
    