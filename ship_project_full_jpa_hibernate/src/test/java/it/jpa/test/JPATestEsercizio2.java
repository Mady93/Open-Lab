package it.jpa.test;

import it.backend.entity.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class JPATestEsercizio2 {

    @PersistenceContext
    EntityManager em;

    @Test
    @Rollback(false)
    /**
     *  Test 1
     *  Aggiornamento diretto Entity non relazionata o con relazioni non valorizzate.
     *  Update con la sessione Hibernate. Attenzione rimuove le relazioni esistenti.
     */
    public void test1() {
       Session session = em.unwrap(Session.class);
       Customer customer = new Customer();
       customer.setId(49);
       customer.setFirstName("Test");
       customer.setLastName("Test");
       customer.setFiscalCode("Prova");
       session.update(customer);

    }

    @Test
    @Rollback(false)
    /**
     *  Test 2
     *  Abbiamo query multiple per relazioni Eager:
     *  Soluzione 1: join fetch su address e creditCard
     *  Soluzione 2: Porre le relazioni address e creditCard a Lazy ed attivare il byte code enhancement, osservare la query
     */
    public void test2() {
        //Mettere un zip code corretto e associare l'address al customer
        // Provare con più customer
        // Estrarre tutti i customer con zip code pari a 0100
        TypedQuery<Customer> query1 = em.createQuery(
                "select c from Customer c where c.address.zip='0100'", Customer.class);
        List<Customer> values1 = query1.getResultList();

        //Mettere un zip code corretto
        TypedQuery<Customer> query2 =
        em.createQuery("select c from Customer c join fetch c.address d " +
                "join fetch c.creditCard " +
                "where d.zip='0100'", Customer.class);
        List<Customer> values2 = query2.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     *  Test 3
     */
    public void test3() {
        // Distinct, possiamo avere duplicati per un cliente può avere più reservation
        TypedQuery<Customer> query2 = em.createQuery(
                "select distinct c from Reservation r join r.customers c", Customer.class);
        List<Customer> values2 = query2.getResultList();
    }

    //Operatori:
    // . è il navigation operator
    // +,-,*,/ aritmetici
    // =,>,>=,<,<=,<>,LIKE, BETWEEN, IN, IS NULL, IS EMPTY, MEMBER OF
    // Logici: NOT,AND,OR

    @Test
    @Rollback(false)
    /**
     *  Operatori
     *  Reservations con tax applicata che supera i 300$
     */
    public void test4() {
        // Reservations con tax applicata che supera i 300$
        TypedQuery<Reservation> query3 = em.createQuery("select r from Reservation r " +
                "where (r.amountPaid*0.01) > 300", Reservation.class);
        List<Reservation> values3 = query3.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     *  Operatori
     *  // Le navi di peso in tonnellate in un certo range
     */
    public void test5() {
        // Le navi di peso in tonnellate in un certo range
        TypedQuery<Ship> query1 = em.createQuery("select s from Ship s " +
                "where s.tonnage >= 80000.00 and s.tonnage<=130000.00", Ship.class);
        List<Ship> values1 = query1.getResultList();

        TypedQuery<Ship> query2 = em.createQuery("select s from Ship s " +
                "where s.tonnage between 80000.00 and 130000.00", Ship.class);
        List<Ship> values2 = query2.getResultList();

        TypedQuery<Ship> query3 = em.createQuery("select s from Ship s " +
                "where s.tonnage not between 80000.00 and 130000.00", Ship.class);
        List<Ship> values3 = query3.getResultList();

        TypedQuery<Ship> query4 = em.createQuery("select s from Ship s " +
                "where s.tonnage < 80000.00 or s.tonnage > 130000.00", Ship.class);
        List<Ship> values4 = query4.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     * Filtro con oggetto (valutazione tramite primary key)
     */
    public void test7() {
        //Filtro con oggetto (valutazione tramite primary key)
        TypedQuery<Reservation> query1 = em.createQuery(
                "select r from Reservation r join r.customers c " +
                "where c=:cust", Reservation.class);
        Customer c = new Customer();
        c.setId(95);
        query1.setParameter("cust",c);//Possiamo usare anche date:Date,Calendar..
        List<Reservation> value9 = query1.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     * Test is null
     */
    public void test8() {
        //IS NULL per relazioni ToOne
        TypedQuery<Customer> query1 = em.createQuery("select c from Customer c " +
                "where c.address is null", Customer.class); //oppure is not null
        List<Customer> values1 = query1.getResultList();

        TypedQuery<Customer> query2 = em.createQuery("select c from Customer c " +
                "where :city is not null and :state is not null and " +
                " c.address.state=:state and " +
                " c.address.city=:city", Customer.class);
        query2.setParameter("state", "EN");
        query2.setParameter("city", "Rome");
        List<Customer> values2 = query2.getResultList();
    }
    @Test
    @Rollback(false)
    /**
     * Operatore IN
     */
    public void test9() {
        // Operatore IN
        TypedQuery<Customer> query1 = em.createQuery("select c from Customer c " +
                "where c.address.state in (?1,?2,?3)", Customer.class);
        query1.setParameter(1, "IT");
        query1.setParameter(2, "EN");
        query1.setParameter(3, "UK");
        List<Customer> values1 = query1.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     * Operatore IS EMPTY / IS NOT EMPTY
     */
    public void test10() {
        // Verifica collezioni vuote IS EMPTY
        TypedQuery<Cruise> query13 = em.createQuery("select c from Cruise c " +
                "where c.reservation is empty", Cruise.class);
        List<Cruise> values13 = query13.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     * Instanza membro di una collezione
     */
    public void test11() {
        // Verifica se una istanza è membro di una collezione
        TypedQuery<Customer> query1 = em.createQuery("select c from Customer c " +
                "where :p member of c.phoneNumbers", Customer.class);
        Phone p = new Phone();
        p.setId(68);
        query1.setParameter("p", p);
        List<Customer> values14 = query1.getResultList();

        // la query precedente potrebbe essere formulata come:
        /*TypedQuery<Customer> query15 = em.createQuery("select c from Customer c join c.phoneNumbers n " +
                "where :p = n.id", Customer.class);

        query15.setParameter("p", 68);
        List<Customer> values15 = query15.getResultList();*/
    }

    @Test
    @Rollback(false)
    /**
     * Operatore Like
     */
    public void test12() {
        // LIKE
        TypedQuery<Customer> query1 = em.createQuery("select c from Customer c where c.lastName like '%-%'", Customer.class);
        List<Customer> values1 = query1.getResultList();
        TypedQuery<Customer> query2 = em.createQuery("select c from Customer c where c.lastName like '_ossi'", Customer.class);
        List<Customer> values2 = query2.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     * Funzioni
     */
    public void test13() {
        // Funzioni lower/upper/trim/concat/length/locate/substring/current_date/current_time/current_timestamp ,
        // matematiche abs(),sqrt(),mod(a,b)
        TypedQuery<Customer> query1 = em.createQuery("select c from Customer c where lower(c.lastName)=lower('Rossi')", Customer.class);
        List<Customer> values1 = query1.getResultList();
        TypedQuery<Customer> query2 = em.createQuery("select c from Customer c " +
                "where length(c.lastName)>8 and locate(c.lastName,'Monson') > -1", Customer.class);
        List<Customer> values2 = query2.getResultList();
    }
    @Test
    @Rollback(false)
    /**
     *  Funzioni aggregate
     */
    public void test14() {
        //Funzioni aggregate
        TypedQuery<Long> query1 = em.createQuery("select distinct count(c) from Customer c where c.address.state='IT'", Long.class);
        Long value1 = query1.getSingleResult();

        TypedQuery<Double> query2 = em.createQuery("select max(r.amountPaid) from Reservation r", Double.class);
        Double value2 = query2.getSingleResult();

        TypedQuery<Double> query3 = em.createQuery("select sum(r.amountPaid) from Cruise c " +
                "join c.reservation r where c.id=:id", Double.class);
        query3.setParameter("id",5);
        Double value3 = query3.getSingleResult();

        TypedQuery<Double> query4 = em.createQuery("select avg(r.amountPaid) from Cruise c " +
                "join c.reservation r where c.id=:id", Double.class);
        query4.setParameter("id",5);
        Double value4 = query4.getSingleResult();
    }

    @Test
    @Rollback(false)
    /**
     *  Order By
     */
    public void test15() {
        TypedQuery<Customer> query1 = em.createQuery("select c from Customer c order by c.lastName", Customer.class);
        TypedQuery<Customer> query2 = em.createQuery("select c from Customer c order by c.lastName asc, c.firstName desc", Customer.class);
        List<Customer> result1 = query1.getResultList();
        List<Customer> result2 = query2.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     * Group by having
     */
    public void test17() {
        TypedQuery<Report> query1 = em.createQuery("select new it.backend.entity.Report(c.name,count(r)) from " +
                "Cruise c join c.reservation r group by c.name having count(r)>10", Report.class);
        List<Report> result2 = query1.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     * DTO projection
     */
    public void test18() {
        TypedQuery<PhoneDTO> query1 = em.createQuery("select new it.backend.entity.PhoneDTO(p.number, c.firstName, c.lastName) " +
                "from Phone p join p.customer c where c.id=:id", PhoneDTO.class);
        query1.setParameter("id",95);
        List<PhoneDTO> result1 = query1.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     * Subquery, ALL,ANY,ESISTS
     */
    public void test19() {
        TypedQuery<Long> query1 = em.createQuery("select count(r1) " +
                "from Reservation r1 where r1.amountPaid > (select avg(r2.amountPaid) from Reservation r2)", Long.class);
        Long result1 = query1.getSingleResult();

        //Quando una query ritorna una molteplicità di risultati
        TypedQuery<Cruise> query2 = em.createQuery("select c " +
                "from Cruise c where 0 < all (select r.amountPaid from c.reservation r)", Cruise.class);//oppure any
        List<Cruise> result2 = query2.getResultList();

        TypedQuery<Cruise> query3 = em.createQuery("select c from Cruise c where " +
                "exists (select r from c.reservation r where r.amountPaid=0)", Cruise.class);//oppure any
        List<Cruise> result3 = query3.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     *  Test 2
     *  TypedQuery: Selezione istanze di entity e tipi di Join
     */
    public void test20() {
        int customerId=95;
        Customer c1 = em.find(Customer.class,customerId);
        // Il Customer con id=49
        TypedQuery<Customer> query1 = em.createQuery("select c from Customer c where c.id=:id", Customer.class);
        query1.setParameter("id",customerId);
        Customer c2 = query1.getSingleResult();

        // Il Customer con id=49 con il suo indirizzo
        TypedQuery<Customer> query2 = em.createQuery("select c from Customer c join c.address where c.id=:id", Customer.class);
        query2.setParameter("id",customerId);
        Customer c3 = query2.getSingleResult();

        // Il Customer con id=49 con eventualmente il suo indirizzo
        TypedQuery<Customer> query3 = em.createQuery("select c from Customer c left join c.address where c.id=:id", Customer.class);
        query3.setParameter("id",customerId);
        Customer c4 = query3.getSingleResult();

        // Il Customer con id=49 con  indirizzo la cui città è Roma
        TypedQuery<Customer> query4 = em.createQuery("select c from Customer c  " +
                "join c.address a where c.id=:id and a.city=:city", Customer.class);
        query4.setParameter("id",customerId);
        query4.setParameter("city","Rome");
        Customer c5 = query3.getSingleResult();
    }


    @Test
    @Rollback(false)
    /**
     *  Test 3
     *  Tipi di Join
     */
    public void test21() {
        int id = 95;
        TypedQuery<Customer> query3 = em.createQuery("select c from Customer c left join fetch c.phoneNumbers where c.id=:id", Customer.class);
        query3.setParameter("id", id);
        Customer c3 = query3.getSingleResult();
    }


    @Test
    @Rollback(false)
    /**
     *  Test 4
     *  Estrazioni con JOIN e filtro su collezione
     *  ATTENZIONE:Provare senza altre relazioni su Customer valorizzate se non Phone.Successivamente con address e credit card associate.
     *  Domanda: Riusciamo ad individuare cosa non va?
     */
    public void test22() {
        int id = 95;
        TypedQuery<Customer> query = em.createQuery("select c from Customer c left join " +
                        "fetch c.phoneNumbers n where n.number=:n",
                Customer.class);
        query.setParameter("n", "31243002");
        List<Customer> c3 = query.getResultList();
    }

    @Test
    @Rollback(false)
    /**
     *  Test 23
     *  Estrazioni con JOIN e filtro su collezione
     */
    public void test23() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c left join " +
                        "fetch c.phoneNumbers n left join fetch c.address left join fetch c.creditCard where n.number=:n",
                Customer.class);
        query.setParameter("n", "31243002");
        List<Customer> c3 = query.getResultList();
    }

    @Test
    @Rollback(false)
    public void test24() {
        TypedQuery<Tuple> query =
                em.createQuery("select c.firstName as nome,c.lastName as cognome " +
                                "from Customer c",
                        Tuple.class);
        List<Tuple> elements = query.getResultList();
        for(Tuple t : elements) {
            System.out.println(t.get("nome"));
            System.out.println(t.get("cognome"));
        }

    }

    @Test
    @Rollback(false)
    public void test25() {
        // Selezionare un BusinessUser specificando nome e cognome
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);

        Root user = criteriaQuery.from(Customer.class);
        // select c from Customer c where (c.firstName=:firstName and c.lastName=:lastName)

        Predicate predicato1 =
                criteriaBuilder.equal(user.get("firstName"),
                        criteriaBuilder.parameter(String.class, "firstName"));
        Predicate predicato2 =
                criteriaBuilder.equal(user.get("lastName"),
                        criteriaBuilder.parameter(String.class, "lastName"));

        Predicate predicato3 = criteriaBuilder.and(predicato1,predicato2);

        criteriaQuery.select(user).where(predicato3);

        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        query.setParameter("firstName","Laura");
        query.setParameter("lastName","Giusti");
        Customer customer = query.getSingleResult();
    }

    @Test
    @Rollback(false)
    public void test26() {
        // Selezionare un singolo campo di un Entity
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);

        Root user = criteriaQuery.from(Customer.class);
        criteriaQuery.select(user.get("firstName")).where(criteriaBuilder.equal(user.get("id"), 1));
        TypedQuery<String> query = em.createQuery(criteriaQuery);
        query.getSingleResult();
    }

    @Test
    @Rollback(false)
    public void test27() {
        // Selezionare più campi usando le Tuple
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root user = criteriaQuery.from(Customer.class);
        criteriaQuery.multiselect(
                        user.get("firstName").alias("nome"), user.get("lastName").alias("cognome"))
                .where(criteriaBuilder.equal(user.get("id"), 1));
        Query query = em.createQuery(criteriaQuery);
        List<Tuple> elements = query.getResultList();
        for(Tuple t : elements) {
            System.out.println(t.get("nome"));
            System.out.println(t.get("cognome"));
        }
    }

    @Test
    @Rollback(false)
    public void test28() {
        // Usiamo classi Dto per il recupero di dati
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CustomerDTO> criteriaQuery = criteriaBuilder.createQuery(CustomerDTO.class);

        Root user = criteriaQuery.from(Customer.class);
        criteriaQuery.select(criteriaBuilder.
                construct(CustomerDTO.class, user.get("firstName"), user.get("lastName")));
        Query query = em.createQuery(criteriaQuery);
        List<CustomerDTO> result = query.getResultList();
    }

    @Test
    @Rollback(false)
    public void test30() {
        // Join
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);

        Root user = criteriaQuery.from(Customer.class);
        //Join sim = user.join("sim");
        // select c from BusinessUser c join c.phoneNumbers p where p.number=...

        Join sim = user.join("phoneNumbers", JoinType.INNER);
        criteriaQuery.where(criteriaBuilder.equal(sim.get("number"), "222222222223"));
        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        Customer u = query.getSingleResult();
    }

    @Test
    @Rollback(false)
    public void test31() {
        // Join Fetch
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root user = criteriaQuery.from(Customer.class);

        Fetch<Customer, Phone> simfetch = user.fetch("phoneNumbers", JoinType.INNER);
        Join<Customer, Phone> sim = (Join<Customer, Phone>)simfetch;

        criteriaQuery.where(criteriaBuilder.equal(sim.get("numebr"), "222222222223"));
        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        Customer u = query.getSingleResult();
    }

    @Test
    @Rollback(false)
    public void test32() {
        // Left outer join con condizione di recupero di soli utenti senza numeri di telefono
        // abbiamo un filtro
        // Se presenti duplicati si usa distinct....
        // Rimozione con distinct()...
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root user = criteriaQuery.from(Customer.class);
        //Root user = criteriaQuery.distinct(true).from(BusinessUser.class);

        Fetch<Customer, Phone> phoneFetch = user.fetch("phoneNumbers", JoinType.LEFT);
        //Join<BusinessUser, Sim> sim = (Join<BusinessUser, Sim>) phoneFetch; Non mi serve, non seleziono su Phone

        criteriaQuery.where(criteriaBuilder.isEmpty(user.get("phoneNumbers")));
        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        List<Customer> lista = query.getResultList();
        for(Customer u:lista){
            System.out.println(u.getId());
            System.out.println(u.getPhoneNumbers());
        }
    }
}