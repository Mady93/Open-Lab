package it.mobileapp.impl.repository.test;

import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.impl.repository.contract.BusinessUserRepository;
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

import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional(propagation = Propagation.NOT_SUPPORTED) //Disable transaction on method test
public class TestSuite1 {

    @Autowired
    private  BusinessUserRepository businessUserRepository;
    private static Integer userId;

    public TestSuite1() {
    }

    /**
     *  Test 1
     *  Create new user correctly compiled
     */
    @Test
    public void test1_createNewUser() throws RepoException{
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(null, "Mario", "Rossi", "QWEERTY",0);
        businessUserDTO = businessUserRepository.createUser(businessUserDTO);
        userId = businessUserDTO.getId();
        assertTrue(businessUserDTO.getId()!=null);
        Optional<BusinessUserDTO> businessUserDTO2= businessUserRepository.findById(userId);
        assertTrue(!businessUserDTO2.isEmpty());
        BusinessUserDTO businessUserDTO3 = businessUserDTO2.get();
        assertTrue(businessUserDTO3.getFirstName().equals(businessUserDTO.getFirstName()) &&
                businessUserDTO3.getLastName().equals(businessUserDTO.getLastName()) &&
                businessUserDTO3.getFiscalCode().equals(businessUserDTO.getFiscalCode()));

    }

    /**
     *  Test 2
     *  Create new user incorrectly compiled
     *  Table BusinessUser all columns not null
     */
    @Test
    public void test2_createNewUserWithError() {
        try {
            BusinessUserDTO businessUserDTO =
                    new BusinessUserDTO(null, null, "Rossi", "QWEERTY2",0);
            businessUserDTO = businessUserRepository.createUser(businessUserDTO);
        } catch (RepoException e) {
            assertTrue(e.getMessage().contains("org.hibernate.PropertyValueException: not-null property references a null or transient value"));
        }
    }

    /**
     *  Test 3
     *  Create new user with a fiscalCode that already exist
     *  Table BusinessUser unique constraint on Fiscal Code column
     */
    @Test
    public void test3_createNewUserWithError() throws RepoException{
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(null, "Luigi", "Rossi", "QWEERTY", 0);
        try {
            businessUserDTO = businessUserRepository.createUser(businessUserDTO);
        } catch (RepoException e) {
            assertTrue(e.getMessage().contains("org.hibernate.exception.ConstraintViolationException"));
        }
    }

    /**
     *  Test 4
     *  Update an user
     */
    @Test
    public void test4_updateUser() throws RepoException {
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(userId, "Mario2", "Rossi2", "QWEERTY2", 0);
        businessUserRepository.updateUser(businessUserDTO);
        Optional<BusinessUserDTO> businessUserDTO2 = businessUserRepository.findById(userId);
        assertTrue(!businessUserDTO2.isEmpty());
        BusinessUserDTO businessUserDTO3 = businessUserDTO2.get();
        assertTrue(businessUserDTO3.getId().intValue() == businessUserDTO.getId().intValue() &&
                businessUserDTO3.getFirstName().equals(businessUserDTO.getFirstName()) &&
                businessUserDTO3.getLastName().equals(businessUserDTO.getLastName()) &&
                businessUserDTO3.getFiscalCode().equals(businessUserDTO.getFiscalCode()));
    }

    /**
     *  Test 4
     *  Concurrent Modification OptimisticLockException
     */
    @Test
    public void test5_updateUser()  {
        try {
            BusinessUserDTO businessUserDTO =
                    new BusinessUserDTO(userId, "Mario2", "Rossi2", "QWEERTY2", 0);
            businessUserRepository.updateUser(businessUserDTO);
            assertTrue(false);
        } catch (RepoException e) {
            assertTrue(e.getCode().equals("R0001"));
        }
    }

    /**
     *  Test 5
     *  Concurrent Transaction Modification OptimisticLockException
     */
    @Test
    public void test7_updateConcurrentUser() throws InterruptedException {
        final String[] threadExCode = new String[2];

        Thread thread1 = new Thread(() -> {
            try {
                Optional<BusinessUserDTO> businessUserDTO2 = businessUserRepository.findById(userId);
                businessUserDTO2.get().setFirstName("Thread1");
                businessUserRepository.updateUser(businessUserDTO2.get());
            } catch (RepoException e) {
                threadExCode[0] = e.getCode();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Optional<BusinessUserDTO> businessUserDTO2 = businessUserRepository.findById(userId);
                businessUserDTO2.get().setFirstName("Thread2");
                businessUserRepository.updateUser(businessUserDTO2.get());
            } catch (RepoException e) {
                threadExCode[1] = e.getCode();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertTrue((threadExCode[0] != null && threadExCode[0].equals("R0001")) ||
                (threadExCode[1] != null && threadExCode[1].equals("R0001")));
        assertTrue(!(threadExCode[0] == null) || !(threadExCode[1] == null));
        Optional<BusinessUserDTO> businessUserDTO = null;
        try {
            businessUserDTO = businessUserRepository.findById(userId);
            assertTrue((threadExCode[0] == null && businessUserDTO.get().getFirstName().equals("Thread1")) ||
                    (threadExCode[1] == null && businessUserDTO.get().getFirstName().equals("Thread2")));
        } catch (RepoException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
    /**
     *  Test 7
     *  Delete an user with a bad version
     */
    @Test
    public void test8_deleteBadVersion() {
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(userId, "Mario2", "Rossi2", "QWEERTY2", 0);
        try {
            businessUserRepository.deleteUser(businessUserDTO);
            assertTrue(false);
        } catch (RepoException e) {
            assertTrue(e.getCode().equals("R0001"));
        }
    }

    /**
     *  Test 8
     *  Delete an user with correct version
     */
    @Test
    public void test9_deleteUserOk() throws RepoException {
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(userId, "Mario2", "Rossi2", "QWEERTY2", 2);
        businessUserRepository.deleteUser(businessUserDTO);
        Optional<BusinessUserDTO> businessUserDTO2 = businessUserRepository.findById(userId);
        assertTrue(businessUserDTO2.isEmpty());
    }
}
