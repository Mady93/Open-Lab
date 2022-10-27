package it.jpa.test;

import it.backend.entity.*;
import it.backend.spring.data.CustomerFilterDTO;
import it.backend.spring.data.CustomerRepo;
import it.backend.spring.data.CustomerSpec;
import it.backend.spring.data.ReservationRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class JPATestEsercizio3 {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ReservationRepo reservationRepo;

    @Test
    @Rollback(false)
    /**
     * Test
     * Analisi di findCustomerByFiscalCode (Utilizzare successivamente @Query sul repository)
     */
    public void test() {
        Page<Customer> page = customerRepo.getCustomersByZip("100", PageRequest.of(1,10));
        Customer cutomer = customerRepo.findCustomerByFiscalCode("QWERTY");
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
        customerRepo.save(c);
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
        customerRepo.save(c);
        c = new Customer();
        c.setFirstName("User2");
        c.setLastName("User2");
        c.setFiscalCode("User2");
        customerRepo.save(c);
        c = new Customer();
        c.setFirstName("User3");
        c.setLastName("User3");
        c.setFiscalCode("User3");
        customerRepo.save(c);
    }

    @Test
    @Rollback(false)
    /**
     *  Test 3
     *  Find
     */
    public void test3() {
        Optional<Customer> c = customerRepo.findById(1);
        if(!c.isEmpty()) {
            Customer customer = c.get();
            //.....
        }
    }

    @Test
    @Rollback(false)
    /**
     *  Test 4
     *  getReference
     */
    public void test4() {
        Customer c = customerRepo.getById(1); // <- id esistente / id non esistente
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
        Optional<Customer> c = customerRepo.findById(1);
        if(!c.isEmpty()) {
            Customer customer = c.get();
            customerRepo.detach(customer); //  Non abbiamo la controparte, nel JPARepository, di detach()
            Customer attached = customerRepo.save(customer);
        }
    }

    @Test
    @Rollback(false)
    /**
     *  Test 8
     *  Inserimento Customer con relazioni. I foreign key sono nullable.
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

        customerRepo.save(customer);
    }


    @Test
    @Rollback(false)
    /**
     *  Test 14
     *  Rimozione Entity
     *
     */
    public void test14() {
        Optional<Customer> cOpt = customerRepo.findById(32);
        if(!cOpt.isEmpty()) {
            customerRepo.delete(cOpt.get());
        }
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
        Optional<Customer> cOpt = customerRepo.findById(32);
        if(!cOpt.isEmpty()) {
            Customer c = cOpt.get();
            c.setFirstName("Un nuovo nome");
            //c.setAddress(null);
            //c.setCreditCard(null);
            customerRepo.refresh(c);
        }
    }

    @Test
    @Rollback(false)
    /**
     * Test 20
     *
     * Si il problema prestazionale riguarda il recupero delle prenotazioni (su una nave possiamo avere oltre 5000 prenotazioni).
     * Il recupero a blocchi attraverso paginazione ci permette di contrallare il numero di oggetti
     * correntemente nel persistence context inviando blocchi di statement un pò alla volta.
     * Abbiamo la necessità di affettuare una query pagina per pagina ma risolviamo il problema
     * di un eventuale esaurimento di memoria.
     */
    public void test20() {
        Page<Reservation> reservations = null;
        int index = 0;
        int max = 10;
        do{
            reservations = reservationRepo.getReservations(PageRequest.of(index,max), 5);
            for(Reservation r: reservations)
                reservationRepo.delete(r);
            reservationRepo.flush();
            reservationRepo.clear();
        } while (reservations.getNumberOfElements()>0);
    }

    @Test
    @Rollback(false)
    public void test33() {
        // Criteria test with Spring Data
        CustomerFilterDTO filter = new CustomerFilterDTO("Rossi", null);
        Specification<Customer> spec = CustomerSpec.getUsersByFilter(filter);
        Page<Customer> page = customerRepo.findAll(spec, PageRequest.of(0,10));
    }
}