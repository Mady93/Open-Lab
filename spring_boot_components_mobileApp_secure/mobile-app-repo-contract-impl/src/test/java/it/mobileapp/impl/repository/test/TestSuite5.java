package it.mobileapp.impl.repository.test;

import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.dmodel.ProductDTO;
import it.mobileapp.dmodel.SimDTO;
import it.mobileapp.dmodel.ValidationDTO;
import it.mobileapp.impl.repository.contract.BusinessUserRepository;
import it.mobileapp.impl.repository.contract.ProductRepository;
import it.mobileapp.impl.repository.contract.SimRepository;
import it.mobileapp.impl.repository.contract.ValidationRepository;
import it.mobileapp.impl.repository.entities.Sim;
import it.mobileapp.impl.repository.entities.Validation;
import it.mobileapp.impl.repository.spring.jpa.ISimEntityRepository;
import it.mobileapp.impl.repository.spring.jpa.IValidationEntityRepository;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional(propagation = Propagation.NOT_SUPPORTED) //Disable transaction on methods test
public class TestSuite5 {

    @Autowired
    private ValidationRepository validationRepository;
    @Autowired
    private BusinessUserRepository businessUserRepository;
    @Autowired
    private IValidationEntityRepository iValidationEntityRepository;

    public TestSuite5() {
    }

    /**
     *  Test 1
     *  Validate User and get validation
     */
    @Test
    public void test1_validateUser() throws RepoException {
        BusinessUserDTO businessUserDTO =
                new BusinessUserDTO(null, "Mario", "Rossi", "QWEERTY",0);
        businessUserDTO = businessUserRepository.createUser(businessUserDTO);
        ValidationDTO validationDTO = new ValidationDTO(null, new Date(), businessUserDTO, 0);
        validationDTO = validationRepository.validateUser(validationDTO);
        Optional<ValidationDTO> validationDTO1 = validationRepository.getValidation(validationDTO.getId());
        assertTrue(!validationDTO1.isEmpty() && validationDTO1.get().getUser().getId().intValue()
                == businessUserDTO.getId().intValue());
        Validation v = iValidationEntityRepository.getById(validationDTO.getId());
        iValidationEntityRepository.delete(v);
        businessUserRepository.deleteUser(businessUserDTO);
    }
}
