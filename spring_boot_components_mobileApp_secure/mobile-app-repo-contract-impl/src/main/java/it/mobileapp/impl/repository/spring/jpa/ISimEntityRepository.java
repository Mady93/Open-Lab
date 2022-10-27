
/******************************************************************************
 * Copyright © Alessandro Zoia                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the “Software”), *
 * to deal in the Software without restriction,including without limitation   *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES                       *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. *
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS                         *
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION  *
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR                    *
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE        *
 * SOFTWARE.                                                                  *
 ******************************************************************************/

package it.mobileapp.impl.repository.spring.jpa;

import it.mobileapp.impl.repository.entities.Product;
import it.mobileapp.impl.repository.entities.Sim;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data Repository
 */
public interface ISimEntityRepository extends JpaRepository<Sim, Integer> {

    /**
     * Finding a sim by id.
     *
     * @param simId Sim id
     * @return A sim with products
     */
    @Query("select c from Sim c left outer join fetch c.products where c.id=:simId")
    Optional<Sim> findSimWithProducts(@Param("simId") Integer simId);

    /**
     * Finding a sim by msisdn and imsi.
     *
     * @param msisdn Sim msisdn
     * @param imsi Sim imsi
     * @return The number of SIMs in match with the criteria
     */
    
    //Verificare se esistono sim che hanno dati invalidi
    @Query("select count(c) from Sim c where c.msisdn=:msisdn or c.imsi=:imsi")
    Long coundByMsisdnAndImsi(@Param("msisdn") String msisdn, @Param("imsi") String imsi);

    /**
     * Finding a sim by id with the specified product activated.
     *
     * @param simId Sim identifier
     * @param product Product data
     * @return A Sim with the input product if exist
     */
    
    //:product member of c.products - l'istanza di product deve essere un membro (ossia presente nella sim) di sim
    @Query("select c from Sim c left outer join fetch c.products p where c.id=:simId and :product member of c.products")
    Optional<Sim> findSimWithProduct(@Param("simId") Integer simId, @Param("product") Product product);

    /**
     * Finding a sim by user id.
     *
     * @param userId User id
     * @return The sim list associated to the User
     */
    @Query("select c from Sim c join fetch c.businessUser u left outer join fetch c.products where u.id=:userId")
    Optional<List<Sim>> findSimByUserId(@Param("userId") Integer userId);

    /**
     * Getting a block of free SIMs.
     *
     * @param page Sim page
     * @return The sim page
     */
    
    //un blocco di sim non associate (null bussinessUser)
    // E'megli metterlo con il page per navigare -- Optional<Page<Sim>> freeSims(Pageable page);
    //Serve creare una classe page
    @Query("select c from Sim c left join c.businessUser b where b is null")
    Optional<List<Sim>> freeSims(Pageable page);
}