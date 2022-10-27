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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional(propagation = Propagation.NOT_SUPPORTED) //Disable transaction on methods test
public class TestSuite4 {

    @Autowired
    private BusinessUserRepository businessUserRepository;
    @Autowired
    private SimRepository simRepository;
    @Autowired
    private ISimEntityRepository simEntityRepository;
    @Autowired
    private ProductRepository productRepository;


    public TestSuite4() {
    }

    /**
     *  Test 1
     *  Sim acquired by an user
     */
    @Test
    public void test1_acquiredByUser() throws RepoException {
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(null, "Mario", "Rossi", "QWEERTY",0);
        Optional<SimDTO> sim = simRepository.findSimById(1);

        assertTrue(!sim.isEmpty());
        BusinessUserDTO user = businessUserRepository.createUser(businessUserDTO);
        SimDTO simDto = simRepository.acquiredByUser(sim.get(), user.getId());
        assertTrue(simDto.getId().intValue() == 1);
        Optional<List<SimDTO>> sim2 = simRepository.findSimByUser(businessUserDTO.getId());
        assertTrue(!sim2.isEmpty());
        assertTrue(sim2.get().size() == 1);
        assertTrue(sim2.get().get(0).getId().intValue() == 1);

        Optional<Sim> simEntity = simEntityRepository.findById(1);
        Sim simEntityValue = simEntity.get();
        simEntityValue.setBusinessUser(null);
        simEntityRepository.save(simEntityValue);

        businessUserRepository.deleteUser(businessUserDTO);
    }

    /**
     *  Test 2
     *  Multiple Sim acquired by an user
     */
    @Test
    public void test2_multipleSimAcquiredByUser() throws RepoException {
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(null, "Mario", "Rossi", "QWEERTY",0);
        Optional<SimDTO> sim1 = simRepository.findSimById(1);
        Optional<SimDTO> sim2 = simRepository.findSimById(2);

        assertTrue(!sim1.isEmpty());
        assertTrue(!sim2.isEmpty());

        BusinessUserDTO user = businessUserRepository.createUser(businessUserDTO);

        SimDTO simDto1 = simRepository.acquiredByUser(sim1.get(), user.getId());
        assertTrue(simDto1.getId().intValue() == 1);

        SimDTO simDto2 = simRepository.acquiredByUser(sim2.get(), user.getId());
        assertTrue(simDto2.getId().intValue() == 2);

        Optional<List<SimDTO>> userSim = simRepository.findSimByUser(businessUserDTO.getId());
        assertTrue(!userSim.isEmpty());
        assertTrue(userSim.get().size() == 2);
        assertTrue(userSim.get().get(0).getId().intValue() == 1);
        assertTrue(userSim.get().get(1).getId().intValue() == 2);

        Optional<Sim> simEntity1 = simEntityRepository.findById(1);
        Optional<Sim> simEntity2 = simEntityRepository.findById(2);

        Sim simEntityValue1 = simEntity1.get();
        simEntityValue1.setBusinessUser(null);
        Sim simEntityValue2 = simEntity2.get();
        simEntityValue2.setBusinessUser(null);

        simEntityRepository.save(simEntityValue1);
        simEntityRepository.save(simEntityValue2);

        businessUserRepository.deleteUser(businessUserDTO);
    }

    /**
     *  Test 3
     *  Concurrent sim acquiring
     */
    @Test
    public void test3_concurrentSimAcquiredByUsers() throws RepoException, InterruptedException {
        final String[] threadExCode = new String[2];
        final Integer[] userId = new Integer[2];

        Thread thread1 = new Thread(() -> {
            BusinessUserDTO businessUserDTO = null;
            try {
                businessUserDTO =
                        new BusinessUserDTO(null, "Mario", "Rossi", "QWEERTY",0);
                businessUserDTO = businessUserRepository.createUser(businessUserDTO);
                Optional<SimDTO> sim = simRepository.findSimById(1);
                SimDTO simDto = simRepository.acquiredByUser(sim.get(), businessUserDTO.getId());
                userId[0] = businessUserDTO.getId();
            } catch (RepoException e) {
                threadExCode[0] = e.getCode();
            }
        });

        Thread thread2 = new Thread(() -> {
            BusinessUserDTO businessUserDTO = null;
            try {
                businessUserDTO =
                        new BusinessUserDTO(null, "Luigi", "Bianchi", "QWEERTY2",0);
                businessUserDTO = businessUserRepository.createUser(businessUserDTO);
                Optional<SimDTO> sim = simRepository.findSimById(1);
                SimDTO simDto = simRepository.acquiredByUser(sim.get(), businessUserDTO.getId());
                userId[1] = businessUserDTO.getId();
            } catch (RepoException e) {
                threadExCode[1] = e.getCode();
            } finally {
                if(businessUserDTO.getId() != null) {
                    try {
                        businessUserRepository.deleteUser(businessUserDTO);
                    } catch (RepoException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertTrue((threadExCode[0] != null && threadExCode[0].equals("R0001")) ||
                (threadExCode[1] != null && threadExCode[1].equals("R0001")));
        assertTrue(!(threadExCode[0] == null) || !(threadExCode[1] == null));

        try {
            if (threadExCode[0] == null && threadExCode[1] != null) {
                Optional<List<SimDTO>> sim = simRepository.findSimByUser(userId[0]);
                assertTrue(!sim.isEmpty());
                assertTrue(sim.get().size() == 1);
                assertTrue(sim.get().get(0).getId().intValue() == 1);
            } else if (threadExCode[1] == null && threadExCode[0] != null) {
                Optional<List<SimDTO>> sim = simRepository.findSimByUser(userId[1]);
                assertTrue(!sim.isEmpty());
                assertTrue(sim.get().size() == 1);
                assertTrue(sim.get().get(0).getId().intValue() == 1);
            } else
                assertTrue(false);
        } finally {
            Optional<Sim> simEntity1 = simEntityRepository.findById(1);

            Sim simEntityValue1 = simEntity1.get();
            simEntityValue1.setBusinessUser(null);

            simEntityRepository.save(simEntityValue1);

            if(userId[0] != null) {
                    businessUserRepository.
                            deleteUser(new BusinessUserDTO(userId[0], "Mario", "Rossi", "QWEERTY",0));
            }
            if(userId[1] != null) {
                    businessUserRepository.
                            deleteUser(new BusinessUserDTO(userId[1], "Luigi", "Bianchi", "QWEERTY2",0));

            }
        }
    }

    /**
     * Test 4
     * Sim already assigned
     * @throws RepoException
     */
    @Test
    public void test4_simAlreadyAssigned() throws RepoException {
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(null, "Mario", "Rossi", "QWEERTY",0);
        Optional<SimDTO> sim = simRepository.findSimById(1);

        assertTrue(!sim.isEmpty());
        BusinessUserDTO user = businessUserRepository.createUser(businessUserDTO);
        SimDTO simDto = simRepository.acquiredByUser(sim.get(), user.getId());
        assertTrue(simDto.getId().intValue() == 1);
        assertTrue(simRepository.simAlreadyAssigned(simDto.getId()));

        Optional<Sim> simEntity = simEntityRepository.findById(1);
        Sim simEntityValue = simEntity.get();
        simEntityValue.setBusinessUser(null);
        simEntityRepository.save(simEntityValue);

        businessUserRepository.deleteUser(businessUserDTO);
    }

    /**
     * Test 5
     * Sim activate/deactivate product and product already activated
     * @throws RepoException
     */
    @Test
    public void test5_simActivateDeactivateProduct() throws RepoException {
        ProductDTO productDTO = new ProductDTO(null, "GIGA MILLE", 0);
        productDTO = productRepository.create(productDTO);
        Optional<SimDTO> sim = simRepository.findSimById(1);
        assertTrue(!sim.isEmpty());
        SimDTO simDTO = simRepository.activateProduct(sim.get().getId(), productDTO.getId());
        List<ProductDTO> products = simDTO.getProducts();
        assertTrue(products.size() == 1);
        assertTrue(products.get(0).getName().equals("GIGA MILLE"));
        assertTrue(simRepository.productAlreadyActivated(sim.get().getId(), productDTO.getId()));

        simDTO = simRepository.deActivateProduct(sim.get().getId(), productDTO.getId());
        assertTrue(simDTO.getProducts() == null || simDTO.getProducts().size() == 0);

        try {
            productRepository.delete(productDTO);
        } catch (RepoException e) {
            assertTrue(false);
        }

        Optional<ProductDTO> product = productRepository.findByName("GIGA MILLE");
        assertTrue(product.isEmpty());
    }


    /**
     * Test 7
     * Sim list
     * @throws RepoException
     */
    @Test
    public void test7_simList() throws RepoException {
     Optional<List<SimDTO>> simList = simRepository.freeSims(10);
     assertTrue(!simList.isEmpty());
    }

}
