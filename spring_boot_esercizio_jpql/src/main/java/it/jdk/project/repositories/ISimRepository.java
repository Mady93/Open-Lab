package it.jdk.project.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.jdk.project.entities.Product;
import it.jdk.project.entities.Sim;

//User orphanRemoval = false

@Repository
public interface ISimRepository extends JpaRepository<Sim, Integer>{

	
	      //LA SIMdto HA RIFERIMENTO A PRODUCTdto, MA PRODUCTdto A SIMdto NO perche productdto (ha poche oferte)
			//LA SIMdto HA RIFERIMNTO A USERdto ,MA USERdto NON HA RIFERIMENTO a SIMdto
	
	
    // (:product member of c.products) - L'istanza di product deve essere presente nella sim
@Query("select c from Sim c left outer join fetch c.products p where c.id=:simId and :product member of c.products")
Optional<Sim> findSimWithProduct(@Param("simId") Integer simId, @Param("product") Product product);

	


	                              //Selezionare le sim con i prodotti  
	@Query("select c from Sim c left outer join fetch c.products where c.id=:simId")
    Optional<Sim> findSimWithProducts(@Param("simId") Integer simId);
	
	
	
	
	
    //Lista delle sim con prodotti associate agli utenti
 @Query("select c from Sim c join fetch c.businessUser u left outer join fetch c.products where u.id=:userId")
Optional<List<Sim>> findSimByUserId(@Param("userId") Integer userId);

	
	
	                      //Verificare se esistono sim che hanno dati invalidi
    @Query("select count(c) from Sim c where c.msisdn=:msisdn or c.imsi=:imsi")
    Long countByMsisdnAndImsi(@Param("msisdn") String msisdn, @Param("imsi") String imsi);
    
    
    
 
    
                               //un blocco di sim non associate agli user
    @Query("select c from Sim c left join c.businessUser b where b is null")
    Optional<List<Sim>> freeSims(Pageable page);
    //Optional<Page<Sim>> freeSims(Pageable page);

}

