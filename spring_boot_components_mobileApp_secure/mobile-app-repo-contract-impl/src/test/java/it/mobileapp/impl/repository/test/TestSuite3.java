package it.mobileapp.impl.repository.test;

import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.dmodel.ProductDTO;
import it.mobileapp.dmodel.SimDTO;
import it.mobileapp.impl.repository.contract.BusinessUserRepository;
import it.mobileapp.impl.repository.contract.ProductRepository;
import it.mobileapp.impl.repository.contract.SimRepository;
import it.mobileapp.impl.repository.entities.Sim;
import it.mobileapp.impl.repository.spring.jpa.ISimEntityRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional(propagation = Propagation.NOT_SUPPORTED) //Disable transaction on methods test
public class TestSuite3 {

    @Autowired
    private SimRepository simRepository;
    @Autowired
    private ProductRepository productRepository;

    public TestSuite3() {
    }

    /**
     *  Test 1
     *  Create Product
     */
    @Test
    public void test1_createProduct() throws RepoException {
        ProductDTO productDTO = new ProductDTO(null, "GIGA 1000",0);
        productDTO = productRepository.create(productDTO);
        assertTrue(productDTO.getId() != null);
        Optional<ProductDTO> product = productRepository.findByName("GIGA 1000");
        assertTrue(!product.isEmpty() && product.get().getName().equals("GIGA 1000"));
        productRepository.delete(product.get());
    }

    /**
     *  Test 2
     *  Update Product
     */
    @Test
    public void test2_updateProduct() throws RepoException {
        ProductDTO productDTO = new ProductDTO(null, "GIGA 1000",0);
        productDTO = productRepository.create(productDTO);
        assertTrue(productDTO.getId() != null);
        Optional<ProductDTO> product = productRepository.findByName("GIGA 1000");
        assertTrue(!product.isEmpty() && product.get().getName().equals("GIGA 1000"));
        ProductDTO productDTO1 = new ProductDTO(product.get().getId(), "GIGA 2000", product.get().getVersion());
        productDTO1 = productRepository.update(productDTO1);
        product = productRepository.findByName("GIGA 2000");
        assertTrue(!product.isEmpty() && product.get().getName().equals("GIGA 2000"));
        productRepository.delete(productDTO1);
    }

    /**
     *  Test 3
     *  Exists a Sim with this product activated
     */
    @Test
    public void test3_existsWithThisProductActivated() throws RepoException {
        ProductDTO productDTO = new ProductDTO(null, "GIGA 1000",0);
        productDTO = productRepository.create(productDTO);
        assertTrue(productDTO.getId() != null);
        Optional<ProductDTO> product = productRepository.findByName("GIGA 1000");
        assertTrue(!product.isEmpty() && product.get().getName().equals("GIGA 1000"));
        Optional<SimDTO> simDTO = simRepository.findSimById(1);
        assertTrue(!simDTO.isEmpty() && simDTO.get().getId().intValue()==1);

        SimDTO sim = simRepository.activateProduct(simDTO.get().getId(),productDTO.getId());
        assertTrue(productRepository.existASimWithThisProductActivated(productDTO.getId()));
        simRepository.deActivateProduct(sim.getId(), productDTO.getId());
        productRepository.delete(productDTO);
    }

    /**
     *  Test 4
     *  Find by id and list
     */
    @Test
    public void test4_findByIdAndList() throws RepoException {
        ProductDTO productDTO1 = new ProductDTO(null, "GIGA 1000",0);
        ProductDTO productDTO2 = new ProductDTO(null, "GIGA 2000",0);
        productDTO1 = productRepository.create(productDTO1);
        productDTO2 = productRepository.create(productDTO2);
        Optional<ProductDTO> product = productRepository.findById(productDTO1.getId());
        assertTrue(!product.isEmpty());

        Optional<List<ProductDTO>> list = productRepository.list();
        assertTrue(!list.isEmpty() && list.get().size()==2);

        productRepository.delete(productDTO1);
        productRepository.delete(productDTO2);
    }
}
