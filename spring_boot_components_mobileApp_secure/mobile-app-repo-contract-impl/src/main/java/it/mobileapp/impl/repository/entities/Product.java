
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

package it.mobileapp.impl.repository.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    private Integer id;
    private String name;
    private Set<Sim> sims;
    private int version;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy="products")
    public Set<Sim> getSims() {
        return sims;
    }

    public void setSims(Set<Sim> sims) {
        this.sims = sims;
    }

    @Override
    public boolean equals(Object product){
        if(product==null){
            return false;
        }
        return id != null && id.equals(((Product)product).id);
    }

    /**
     * Synchronization method
     * @param sim to be added to the set
     */
    public void addSim(Sim sim){
        if(sim==null)
            throw new IllegalArgumentException("Sim null!");
        if(this.sims==null)
            this.sims = new LinkedHashSet<>();
        this.sims.add(sim);
        if(sim.getProducts()==null)
            sim.setProducts(new LinkedHashSet<>());
        sim.getProducts().add(this);
    }

    /**
     * Synchronization method
     * @param sim to be removed from the list
     */
    public void removeProduct(Sim sim){
        if(sim==null)
            throw new IllegalArgumentException("Sim null!");
        if(this.sims==null)
            throw new IllegalArgumentException("Sim empty list!");
        if(sim.getProducts()==null)
            throw new IllegalArgumentException("Products empty list!");
        this.sims.remove(sim);
        sim.getProducts().remove(this);
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
