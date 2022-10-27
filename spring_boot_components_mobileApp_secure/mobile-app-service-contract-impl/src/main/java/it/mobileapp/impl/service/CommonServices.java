
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

package it.mobileapp.impl.service;

import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.dmodel.ProductDTO;
import it.mobileapp.dmodel.SimDTO;
import it.mobileapp.repository.contract.IBusinessUserRepository;
import it.mobileapp.repository.contract.IProductRepository;
import it.mobileapp.repository.contract.ISimRepository;
import it.mobileapp.repository.contract.IValidationRepository;
import it.mobileapp.repository.contract.exceptions.RepoException;
import it.mobileapp.service.exceptions.ServiceException;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;

/**
 * Common service controls
 */
public abstract class CommonServices {
	
	
	// Classe astratta dove si mette anche il codice comune a tutti i servizi di qst modulo
	 //controlli per i servizi

    public static final String GENERIC_ERROR_MESSAGE = "Service error";
    protected final IBusinessUserRepository iBusinessUserRepository;
    protected final ISimRepository iSimRepository;
    protected final IProductRepository iProductRepository;
    protected final IValidationRepository iValidationRepository;

    /**
     * Constructor
     * @param iBusinessUserRepository Business user repository contract
     * @param iSimRepository Sim repository contract
     * @param iProductRepository Product repository contract
     * @param iValidationRepository Validation repository contract
     */
    
   
    public CommonServices(IBusinessUserRepository iBusinessUserRepository, ISimRepository iSimRepository,
                          IProductRepository iProductRepository, IValidationRepository iValidationRepository){
        this.iBusinessUserRepository = iBusinessUserRepository;
        this.iSimRepository = iSimRepository;
        this.iProductRepository = iProductRepository;
        this.iValidationRepository = iValidationRepository;
    }

    /**
     * Check if the users exist.
     *
     * @param userId User id
     * @throws ServiceException if the user does not exist
     */
    protected void userExistControl(Integer userId) throws ServiceException{
        if(userId==null)
            throw new ServiceException("Service Error - User id cannot be null","0000");
        Optional<BusinessUserDTO> userData = null;
        try {
            userData = iBusinessUserRepository.findById(userId);
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - "+ e.getMessage(), e.getCode());
        }
        if(userData.isEmpty())
            throw new ServiceException("Service Error - User not found","0003");
    }

    /**
     * Check if the user has SIMs.
     *
     * @param userId User id
     * @throws ServiceException if the user has SIMs
     */
    protected void userWithSimControl(Integer userId) throws ServiceException{
        Optional<List<SimDTO>> sims = null;
        try {
            sims = iSimRepository.findSimByUser(userId);
            if(!sims.isEmpty() && sims.get().size()>0)
                throw new ServiceException("Business Logic Error - User with associated sim", "0009");
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - " + e.getMessage(), e.getCode());
        }
    }

    /**
     * Check if the sim exist.
     *
     * @param simId Sim identifier
     * @throws ServiceException
     *  a) Sim does not exist CODE 0002
     *  b) General error CODE 0000
     */
    protected void simExistControl(Integer simId) throws ServiceException {
        try {
            Optional<SimDTO> sim = iSimRepository.findSimById(simId);
            if(sim.isEmpty())
                throw new ServiceException("Service Error - Sim does not exist" , "0002");
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - " + e.getMessage(), e.getCode());
        }
    }

    /**
     * Check if the sim is already assigned.
     *
     * @param simId Sim identifier
     * @throws ServiceException
     *  a) Sim already assigned CODE 0001
     *  b) General error CODE 0000
     */
    protected void simAlreadyAssignedControl(Integer simId) throws ServiceException {
        try {
            boolean assigned = iSimRepository.simAlreadyAssigned(simId);
            if(assigned)
                throw new ServiceException("Service Error - Sim already assigned" , "0001");
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - " + e.getMessage(), e.getCode());
        }
    }

    /**
     * Check if the product exist.
     *
     * @param productId Product id
     * @throws ServiceException
     *  a) Pro@Override
	duct does not exist CODE 0004
     *  b) General error CODE 0000
     */
    protected void productExistControl(Integer productId) throws ServiceException{
        try {
            Optional<ProductDTO> product = iProductRepository.findById(productId);
            if(product.isEmpty())
                throw new ServiceException("Service Error - Product does not exist" , "0004");
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - " + e.getMessage(), e.getCode());
        }
    }

    /**
     * Check if the product exist.
     *
     * @param productName Product name
     * @throws ServiceException
     *  a) A Product with the same name already exist CODE 0010
     *  b) General error CODE 0000
     */
    protected void productExistControl(String productName) throws ServiceException{
        try {
            Optional<ProductDTO> product = iProductRepository.findByName(productName);
            if(!product.isEmpty())
                throw new ServiceException("Service Error -  A Product with the same name already exist" , "0010");
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - " + e.getMessage(), e.getCode());
        }
    }

    /**
     * Check if the product is already activated.
     *
     * @param simId Sim Identifier
     * @param productId Product Identifier
     * @throws ServiceException
     *  a) Product already activated CODE 0005
     *  b) General error CODE 0000
     */
    protected void productAlreadyActivatedControl(Integer simId, Integer productId) throws ServiceException{
        try {
            boolean activated = iSimRepository.productAlreadyActivated(simId, productId);
            if(activated)
                throw new ServiceException("Service Error - Product already activated" , "0005");
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - " + e.getMessage(), e.getCode());
        }
    }

    /**
     * Check if the product is activated.
     *
     * @param simId Sim Identifier
     * @param productId Product Identifier
     * @throws ServiceException
     *  a) Product not activated CODE 0007
     *  b) General error CODE 0000
     */
    protected void productNotActivatedControl(Integer simId, Integer productId) throws ServiceException{
        try {
            boolean activated = iSimRepository.productAlreadyActivated(simId, productId);
            if(!activated)
                throw new ServiceException("Service Error - Product not activated" , "0007");
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - " + e.getMessage(), e.getCode());
        }
    }

    /**
     * Check if we have a sim with this product.
     *
     * @param productId Product Identifier
     * @throws ServiceException
     *  a) The Product has at least one sim on which is activated CODE 0012
     *  b) General error CODE 0000
     */
    protected void existSimWithThisProduct(Integer productId) throws ServiceException {
        try {
            boolean exist = iProductRepository.existASimWithThisProductActivated(productId);
            if(exist)
                throw new ServiceException("Service Error - The Product has at least one sim on which is activated" , "0012");
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - " + e.getMessage(), e.getCode());
        }
    }

    /**
     * Check if a user exist with the input fiscal code.
     *
     * @param user User identifier
     * @throws ServiceException if an User with the same fiscal code already exist CODE 0008
     */
    protected void existUserWithFiscalCodeControl(BusinessUserDTO user) throws ServiceException {
        try {
            if (iBusinessUserRepository.existsUserWithFiscalCode(user.getFiscalCode())) {
                throw new ServiceException("Business Logic Error - User with the same fiscal code already exist", "0008");
            }
        } catch (RepoException e) {
            throw new ServiceException("Repository Error - " + e.getMessage(), e.getCode());
        }
    }

    /**
     * Null value check.
     *
     * @param values Input parameters
     * @param alias  Input parameters alias
     * @throws ServiceException if same value is null
     */
    protected void isNullControl(Object[] values, String[] alias) throws ServiceException {
        JSONObject messages = new JSONObject(); // stringa json per mandare nel messaggio
        for(int i=0; i<values.length; i++){
            if(values[i] == null) {
                messages.put(alias[i], alias[i] + " is mandatory");
            }
        }
        if(!messages.isEmpty()) {
            throw new ServiceException(messages.toString(), "0018");
        }
    }


}
