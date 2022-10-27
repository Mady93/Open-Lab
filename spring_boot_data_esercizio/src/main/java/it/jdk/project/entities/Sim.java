package it.jdk.project.entities;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sim")
@NamedEntityGraph(name="sim-business_user",
        attributeNodes = {@NamedAttributeNode("businessUser")}
)
public class Sim {

    private Integer id;
    private String msisdn;
    private String imsi;
    private BusinessUser businessUser;
    private Set<Product> products;
    private int version;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "msisdn", nullable = false)
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Column(name = "imsi", nullable = false)
    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_user_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    public BusinessUser getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(BusinessUser businessUser) {
        this.businessUser = businessUser;
    }

    
    //SERVIVA ESSERE UNIDIREZIONALE LATO SIM cosi ogni sim
    //aveva i suoi prodotto perche le sim sono moltissime mentre i prodotti(offerte) sono pochi.
    //Il lato prodotti (product) scomparre e resta solo il lato sim poi nella query JPQL si usa la tupla.
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sim_product",
            joinColumns = {@JoinColumn(name = "sim_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object sim){
        if(sim==null){
            return false;
        }
        return id != null && id.equals(((Sim)sim).id);
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public String toString() {
        return "Sim{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                ", imsi='" + imsi + '\'' +
                '}';
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Version
    @Column(name="version")
    public int getVersion() {
        return version;
    }
}

