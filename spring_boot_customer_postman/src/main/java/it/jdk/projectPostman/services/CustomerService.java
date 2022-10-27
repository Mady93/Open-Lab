package it.jdk.projectPostman.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.jdk.projectPostman.EnumError.EnumError;
import it.jdk.projectPostman.entities.AddressEntity;
import it.jdk.projectPostman.entities.CreditCardEntity;
import it.jdk.projectPostman.entities.CustomerEntity;
import it.jdk.projectPostman.entities.PhoneEntity;
import it.jdk.projectPostman.entities.Ship;
import it.jdk.projectPostman.models.AddressDTO;
import it.jdk.projectPostman.models.CreditCardDTO;
import it.jdk.projectPostman.models.CustomerDTO;
import it.jdk.projectPostman.models.CustomerPageDTO;
import it.jdk.projectPostman.models.FilterDTO;
import it.jdk.projectPostman.models.PhoneDTO;
import it.jdk.projectPostman.repositories.AddressRepository;
import it.jdk.projectPostman.repositories.CreditCardRepository;
import it.jdk.projectPostman.repositories.CustomerRepository;
import it.jdk.projectPostman.repositories.CustomerSpec;
import it.jdk.projectPostman.repositories.PhoneRepository;

@Service
@Transactional(readOnly = true)
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		
	}

	@Transactional
	public CustomerDTO create(CustomerDTO customerDTO) throws Exception {

		if (customerDTO == null) {
			throw new Exception(EnumError.RESOURCE_ALREADY_RESENT.toString());
		} else {

			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setFirstName(customerDTO.getFirstName());
			customerEntity.setFiscalCode(customerDTO.getFiscalCode());
			customerEntity.setLastName(customerDTO.getLastName());

			if (customerDTO.getAddressDTO() != null && customerDTO.getAddressDTO().getId() != null) {
				AddressEntity addressEntity = new AddressEntity();
				AddressDTO addressDTO = customerDTO.getAddressDTO();
				addressEntity.setCity(addressDTO.getCity());
				addressEntity.setState(addressDTO.getState());
				addressEntity.setStreet(addressDTO.getStreet());
				addressEntity.setZip(addressDTO.getZip());
				customerEntity.setAddressEntity(addressEntity);

			}

			else if (customerDTO.getCreditCardDTO() != null && customerDTO.getCreditCardDTO().getId() != null) {
				
				CreditCardEntity creditCardEntity = new CreditCardEntity();
				CreditCardDTO creditCardDTO = customerDTO.getCreditCardDTO();
				creditCardEntity.setCvv(creditCardDTO.getCvv());
				creditCardEntity.setExpDate(creditCardDTO.getExpDate());
				creditCardEntity.setNumber(creditCardDTO.getNumber());
				creditCardEntity.setOrganization(creditCardDTO.getOrganization());
				customerEntity.setCreditCard(creditCardEntity);

			} else if (customerDTO.getListPhoneDTO() != null) {

				List<PhoneEntity> listPhoneEntity = new ArrayList<>();
				List<PhoneDTO> listPhoneDTO = customerDTO.getListPhoneDTO();

				for (PhoneDTO phoneDTO : listPhoneDTO) {

					PhoneEntity phoneEntity = new PhoneEntity();
					phoneEntity.setCustomer(customerEntity);
					phoneEntity.setNumber(phoneDTO.getNumber());
					phoneEntity.setType(phoneDTO.getType());
					listPhoneEntity.add(phoneEntity);
					customerEntity.setPhoneNumbers(listPhoneEntity);
				}

			} else {
				throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
			}

			customerRepository.save(customerEntity);
			customerDTO.setId(customerEntity.getId());
		}
		return customerDTO;
	}
	
	@Transactional
	public CustomerDTO update(CustomerDTO customerDTO) throws Exception {

		Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerDTO.getId());

		if (!optionalCustomerEntity.isEmpty()) {

			CustomerEntity customerEntity = optionalCustomerEntity.get();

			customerEntity.setFirstName(customerDTO.getFirstName());
			customerEntity.setFiscalCode(customerDTO.getFiscalCode());
			customerEntity.setLastName(customerDTO.getLastName());

			AddressEntity addressEntity = new AddressEntity();
			AddressDTO addressDTO = customerDTO.getAddressDTO();
			addressEntity.setCity(addressDTO.getCity());
			addressEntity.setState(addressDTO.getState());
			addressEntity.setStreet(addressDTO.getStreet());
			addressEntity.setZip(addressDTO.getZip());

			CreditCardEntity creditCardEntity = new CreditCardEntity();
			CreditCardDTO creditCardDTO = customerDTO.getCreditCardDTO();
			creditCardEntity.setCvv(creditCardDTO.getCvv());
			creditCardEntity.setExpDate(creditCardDTO.getExpDate());
			creditCardEntity.setNumber(creditCardDTO.getNumber());
			creditCardEntity.setOrganization(creditCardDTO.getOrganization());

			List<PhoneEntity> listPhoneEntity = new ArrayList<>();
			List<PhoneDTO> listPhoneDTO = customerDTO.getListPhoneDTO();

			for (PhoneDTO phoneDTO : listPhoneDTO) {

				PhoneEntity phoneEntity = new PhoneEntity();
				phoneEntity.setCustomer(customerEntity);
				phoneEntity.setNumber(phoneDTO.getNumber());
				phoneEntity.setType(phoneDTO.getType());
				listPhoneEntity.add(phoneEntity);

			}
			return customerDTO;

		} else {
			throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}
	

	@Transactional
	public void delete(Integer id) throws Exception {

		Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(id);
		if (!optionalCustomerEntity.isEmpty()) {

			CustomerEntity customerEntity = optionalCustomerEntity.get();

			customerRepository.delete(customerEntity);
		} else {
			throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
		}
	}

	
	public CustomerDTO findById(Integer id) throws Exception {

	 Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
	 
	 CustomerDTO customerDTO = new CustomerDTO();
	 
	  if(!customerEntityOptional.isEmpty()) {
	  CustomerEntity customerEntity = customerEntityOptional.get();
	 
	  customerDTO.setFirstName(customerEntity.getFirstName());
	  customerDTO.setFiscalCode(customerEntity.getFiscalCode());
	  customerDTO.setId(customerEntity.getId());
	  customerDTO.setLastName(customerEntity.getLastName());
	 
	  AddressEntity addressEntity = customerEntity.getAddress(); 
	  CreditCardEntity creditCardEntity = customerEntity.getCreditCard();
	  List<PhoneEntity> listPhoneEntity = customerEntity.getPhoneNumbers();
		
	  if(customerDTO.getAddressDTO() != null && customerDTO.getAddressDTO().getId() != null) {
		
	  AddressDTO addressDTO = new AddressDTO();
	  addressDTO.setCity(addressEntity.getCity());
	  addressDTO.setId(addressEntity.getId());
	  addressDTO.setState(addressEntity.getState());
	  addressDTO.setStreet(addressEntity.getStreet());
	  addressDTO.setZip(addressEntity.getZip());
	  
	  customerDTO.setAddressDTO(addressDTO); 
	  
	  } else if(customerDTO.getCreditCardDTO() !=null && customerDTO.getCreditCardDTO().getId() != null) {
		  
	  CreditCardDTO creditCardDTO = new CreditCardDTO();
	  creditCardDTO.setCvv(creditCardEntity.getCvv());
	  creditCardDTO.setExpDate(creditCardEntity.getExpDate());
	  creditCardDTO.setId(creditCardEntity.getId());
	  creditCardDTO.setNumber(creditCardEntity.getNumber());
	  creditCardDTO.setOrganization(creditCardEntity.getOrganization());
	  creditCardDTO.setCustomerDTO(customerDTO);
	  
	  customerDTO.setCreditCardDTO(creditCardDTO);

	  }
	  
	  List<PhoneDTO> listPhoneDTO = new ArrayList<PhoneDTO>(); 
	  
	  for (PhoneEntity phoneEntity: listPhoneEntity) {
	  
		  if(phoneEntity != null) {
			  
			  PhoneDTO phoneDTO = new PhoneDTO();
				
			  AddressDTO appoAddressDTO = new AddressDTO();
			  appoAddressDTO.setCity(phoneEntity.getCustomer().getAddress().getCity());
			  appoAddressDTO.setId(phoneEntity.getCustomer().getAddress().getId());
			  appoAddressDTO.setState(phoneEntity.getCustomer().getAddress().getState());
			  appoAddressDTO.setStreet(phoneEntity.getCustomer().getAddress().getStreet());
			  appoAddressDTO.setZip(phoneEntity.getCustomer().getAddress().getZip());
			  
			  customerDTO.setAddressDTO(appoAddressDTO);
			  
			  phoneDTO.setCustomerDTO(customerDTO);
			  phoneDTO.setId(phoneEntity.getId());
			  phoneDTO.setNumber(phoneEntity.getNumber());
			  phoneDTO.setType(phoneEntity.getType());
			  
			  listPhoneDTO.add(phoneDTO);
			  customerDTO.setListPhoneDTO(listPhoneDTO);   
		  }
	  } 
	 }else { 
		 throw new Exception(EnumError.RESOURCE_NOT_FOUND.toString());
	 
	 } return customerDTO;
	  
 }
			  
	  

	public Optional<CustomerPageDTO> findCustomers(int page, int pageSize) {
		Page<CustomerEntity> pageCustomer = customerRepository.findAll(PageRequest.of(page, pageSize));
		return makeDTO(pageCustomer, page, pageSize);
	}
/*
	public Optional<CustomerPageDTO> findCustomerByFilter(int page, int pageSize, FilterDTO customerFilterDTO) {
		Specification<CustomerEntity> spec = CustomerSpec.getCustomerByFilter(customerFilterDTO);
		Page<CustomerEntity> pageCustomer = customerRepository.findAll(spec, PageRequest.of(page, pageSize));
		return makeDTO(pageCustomer, page, pageSize);
	}
*/
	private Optional<CustomerPageDTO> makeDTO(Page<CustomerEntity> pageCustomer, int page, int pageSize) {
		List<CustomerDTO> dtoList = new ArrayList<>();
		for (CustomerEntity customerEntity : pageCustomer.getContent()) {
			dtoList.add(new CustomerDTO(customerEntity.getId(), customerEntity.getLastName(),
					customerEntity.getFirstName(), customerEntity.getFiscalCode()));
		}
		try {
			if (dtoList.isEmpty())
				throw new NoResultException("Empty list");
			return Optional.of(new CustomerPageDTO(pageCustomer.getTotalElements(), page, pageSize, dtoList));
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
}
