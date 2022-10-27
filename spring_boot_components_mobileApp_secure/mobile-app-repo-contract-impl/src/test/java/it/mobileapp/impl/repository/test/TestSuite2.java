package it.mobileapp.impl.repository.test;

import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.dmodel.BusinessUserFilterDTO;
import it.mobileapp.dmodel.BusinessUserPageDTO;
import it.mobileapp.impl.repository.contract.BusinessUserRepository;
import it.mobileapp.impl.repository.entities.BusinessUser;
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

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional(propagation = Propagation.NOT_SUPPORTED) //Disable transaction on method test
public class TestSuite2 {

    @Autowired
    private  BusinessUserRepository businessUserRepository;
    private static Integer userId;

    public TestSuite2() {
    }

    /**
     *  Test 1
     *  Check if an user with this fiscal code exists
     */
    @Test
    public void test1_existUserWithFiscalCode() throws RepoException {
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(null, "Mario", "Rossi", "QWEERTY",0);
        businessUserDTO = businessUserRepository.createUser(businessUserDTO);
        System.out.println(businessUserRepository.existsUserWithFiscalCode(businessUserDTO.getFiscalCode()));
        assertTrue(businessUserRepository.existsUserWithFiscalCode(businessUserDTO.getFiscalCode()));
        businessUserDTO.setVersion(0);
        businessUserRepository.deleteUser(businessUserDTO);
        assertTrue(!businessUserRepository.existsUserWithFiscalCode(businessUserDTO.getFiscalCode()));
    }

    /**
     *  Test 2
     *  Find an user by id
     */
    @Test
    public void test2_findUserById() throws RepoException {
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(null, "Mario", "Rossi", "QWEERTY",0);
        businessUserDTO = businessUserRepository.createUser(businessUserDTO);
        Optional<BusinessUserDTO> businessUserDTO2 = businessUserRepository.findById(businessUserDTO.getId());
        assertTrue(!businessUserDTO2.isEmpty());
        businessUserRepository.deleteUser(businessUserDTO2.get());
        businessUserDTO2 = businessUserRepository.findById(businessUserDTO2.get().getId());
        assertTrue(businessUserDTO2.isEmpty());
        try {
            businessUserDTO2 = businessUserRepository.findById(null);
        } catch (RepoException e){
            assertTrue(e.getMessage().contains("Parameter"));
        }
    }

    /**
     *  Test 3
     *  Find users using pagination
     */
    @Test
    public void test3_findUsers() throws RepoException {
        BusinessUserDTO businessUserDTO1 =
                new BusinessUserDTO(null, "User1", "User1", "User1",0);
        BusinessUserDTO businessUserDTO2 =
                new BusinessUserDTO(null, "User2", "User2", "User2",0);
        BusinessUserDTO businessUserDTO3 =
                new BusinessUserDTO(null, "User3", "User3", "User3",0);
        BusinessUserDTO businessUserDTO4 =
                new BusinessUserDTO(null, "User4", "User4", "User4",0);
        BusinessUserDTO businessUserDTO5 =
                new BusinessUserDTO(null, "User5", "User5", "User5",0);
        BusinessUserDTO businessUserDTO6 =
                new BusinessUserDTO(null, "User6", "User6", "User6",0);
        BusinessUserDTO businessUserDTO7 =
                new BusinessUserDTO(null, "User7", "User7", "User7",0);
        BusinessUserDTO businessUserDTO8 =
                new BusinessUserDTO(null, "User8", "User8", "User8",0);

        businessUserDTO1 = businessUserRepository.createUser(businessUserDTO1);
        businessUserDTO2 = businessUserRepository.createUser(businessUserDTO2);
        businessUserDTO3 = businessUserRepository.createUser(businessUserDTO3);
        businessUserDTO4 = businessUserRepository.createUser(businessUserDTO4);
        businessUserDTO5 = businessUserRepository.createUser(businessUserDTO5);
        businessUserDTO6 = businessUserRepository.createUser(businessUserDTO6);
        businessUserDTO7 = businessUserRepository.createUser(businessUserDTO7);
        businessUserDTO8 = businessUserRepository.createUser(businessUserDTO8);

        Optional<BusinessUserPageDTO> page1 = businessUserRepository.findUsers(0, 4);
        Optional<BusinessUserPageDTO> page2 = businessUserRepository.findUsers(1, 4);

        assertTrue(!page1.isEmpty());
        assertTrue(!page2.isEmpty());

        BusinessUserPageDTO page1Dto = page1.get();
        BusinessUserPageDTO page2Dto = page2.get();

        assertTrue(page1Dto.getStart()==0);
        assertTrue(page2Dto.getStart()==1);
        assertTrue(page1Dto.getMax()==4);
        assertTrue(page2Dto.getMax()==4);
        assertTrue(page1Dto.getTotal()==8);
        assertTrue(page2Dto.getTotal()==8);

        int i=businessUserDTO1.getId();
        for(BusinessUserDTO user: page1Dto.getPage()){
            assertTrue(user.getId().intValue()==i);
            i++;
        }

        i=businessUserDTO5.getId();
        for(BusinessUserDTO user: page2Dto.getPage()){
            assertTrue(user.getId().intValue()==i);
            i++;
        }

        businessUserRepository.deleteUser(businessUserDTO1);
        businessUserRepository.deleteUser(businessUserDTO2);
        businessUserRepository.deleteUser(businessUserDTO3);
        businessUserRepository.deleteUser(businessUserDTO4);
        businessUserRepository.deleteUser(businessUserDTO5);
        businessUserRepository.deleteUser(businessUserDTO6);
        businessUserRepository.deleteUser(businessUserDTO7);
        businessUserRepository.deleteUser(businessUserDTO8);
    }

    /**
     *  Test 4
     *  Find users using pagination with different page size
     */
    @Test
    public void test4_findUsersDifferentSize() throws RepoException {
        BusinessUserDTO businessUserDTO1 =
                new BusinessUserDTO(null, "User1", "User1", "User1",0);
        BusinessUserDTO businessUserDTO2 =
                new BusinessUserDTO(null, "User2", "User2", "User2",0);
        BusinessUserDTO businessUserDTO3 =
                new BusinessUserDTO(null, "User3", "User3", "User3",0);
        BusinessUserDTO businessUserDTO4 =
                new BusinessUserDTO(null, "User4", "User4", "User4",0);
        BusinessUserDTO businessUserDTO5 =
                new BusinessUserDTO(null, "User5", "User5", "User5",0);
        BusinessUserDTO businessUserDTO6 =
                new BusinessUserDTO(null, "User6", "User6", "User6",0);
        BusinessUserDTO businessUserDTO7 =
                new BusinessUserDTO(null, "User7", "User7", "User7",0);

        businessUserDTO1 = businessUserRepository.createUser(businessUserDTO1);
        businessUserDTO2 = businessUserRepository.createUser(businessUserDTO2);
        businessUserDTO3 = businessUserRepository.createUser(businessUserDTO3);
        businessUserDTO4 = businessUserRepository.createUser(businessUserDTO4);
        businessUserDTO5 = businessUserRepository.createUser(businessUserDTO5);
        businessUserDTO6 = businessUserRepository.createUser(businessUserDTO6);
        businessUserDTO7 = businessUserRepository.createUser(businessUserDTO7);

        Optional<BusinessUserPageDTO> page1 = businessUserRepository.findUsers(0, 4);
        Optional<BusinessUserPageDTO> page2 = businessUserRepository.findUsers(1, 4);

        assertTrue(!page1.isEmpty());
        assertTrue(!page2.isEmpty());

        BusinessUserPageDTO page1Dto = page1.get();
        BusinessUserPageDTO page2Dto = page2.get();

        assertTrue(page1Dto.getStart()==0);
        assertTrue(page2Dto.getStart()==1);
        assertTrue(page1Dto.getMax()==4);
        assertTrue(page2Dto.getMax()==4);
        assertTrue(page1Dto.getTotal()==7);
        assertTrue(page2Dto.getTotal()==7);
        assertTrue(page1Dto.getPage().size() == 4);
        assertTrue(page2Dto.getPage().size() == 3);

        int i=businessUserDTO1.getId();
        for(BusinessUserDTO user: page1Dto.getPage()){
            assertTrue(user.getId().intValue()==i);
            i++;
        }

        i=businessUserDTO5.getId();
        for(BusinessUserDTO user: page2Dto.getPage()){
            assertTrue(user.getId().intValue()==i);
            i++;
        }

        businessUserRepository.deleteUser(businessUserDTO1);
        businessUserRepository.deleteUser(businessUserDTO2);
        businessUserRepository.deleteUser(businessUserDTO3);
        businessUserRepository.deleteUser(businessUserDTO4);
        businessUserRepository.deleteUser(businessUserDTO5);
        businessUserRepository.deleteUser(businessUserDTO6);
        businessUserRepository.deleteUser(businessUserDTO7);
    }

    /**
     *  Test 5
     *  Find users using pagination and filter
     */
    @Test
    public void test5_findUsersByFilter() throws RepoException {
        BusinessUserDTO businessUserDTO1 =
                new BusinessUserDTO(null, "A", "A", "F",0);
        BusinessUserDTO businessUserDTO2 =
                new BusinessUserDTO(null, "B", "A", "G",0);
        BusinessUserDTO businessUserDTO3 =
                new BusinessUserDTO(null, "C", "B", "H",0);
        BusinessUserDTO businessUserDTO4 =
                new BusinessUserDTO(null, "D", "A", "I",0);
        BusinessUserDTO businessUserDTO5 =
                new BusinessUserDTO(null, "E", "K", "L",0);


        businessUserDTO1 = businessUserRepository.createUser(businessUserDTO1);
        businessUserDTO2 = businessUserRepository.createUser(businessUserDTO2);
        businessUserDTO3 = businessUserRepository.createUser(businessUserDTO3);
        businessUserDTO4 = businessUserRepository.createUser(businessUserDTO4);
        businessUserDTO5 = businessUserRepository.createUser(businessUserDTO5);

        BusinessUserFilterDTO businessUserFilterDTO = new BusinessUserFilterDTO();
        businessUserFilterDTO.setLastName("A");
        Optional<BusinessUserPageDTO> page1 = businessUserRepository.findUsersByFilter(0, 2, businessUserFilterDTO);
        Optional<BusinessUserPageDTO> page2 = businessUserRepository.findUsersByFilter(1, 2, businessUserFilterDTO);

        assertTrue(!page1.isEmpty());
        assertTrue(!page2.isEmpty());

        BusinessUserPageDTO page1Dto = page1.get();
        BusinessUserPageDTO page2Dto = page2.get();

        assertTrue(page1Dto.getStart()==0);
        assertTrue(page2Dto.getStart()==1);
        assertTrue(page1Dto.getMax()==2);
        assertTrue(page2Dto.getMax()==2);
        assertTrue(page1Dto.getTotal()==3);
        assertTrue(page2Dto.getTotal()==3);
        assertTrue(page1Dto.getPage().size() == 2);
        assertTrue(page2Dto.getPage().size() == 1);

        int i=0;
        BusinessUserDTO[] output1 = {businessUserDTO1, businessUserDTO2};
        for(BusinessUserDTO user: page1Dto.getPage()){
            assertTrue(user.getId().intValue()==output1[i].getId().intValue());
            i++;
        }

        i=0;
        BusinessUserDTO[] output2 = {businessUserDTO4};
        for(BusinessUserDTO user: page2Dto.getPage()){
            assertTrue(user.getId().intValue()==output2[i].getId().intValue());
            i++;
        }

        businessUserRepository.deleteUser(businessUserDTO1);
        businessUserRepository.deleteUser(businessUserDTO2);
        businessUserRepository.deleteUser(businessUserDTO3);
        businessUserRepository.deleteUser(businessUserDTO4);
        businessUserRepository.deleteUser(businessUserDTO5);
    }
}
