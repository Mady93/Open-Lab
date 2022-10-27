
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

package it.mobileapp.service.contract;

import it.mobileapp.dmodel.SimDTO;
import it.mobileapp.service.exceptions.ServiceException;
import java.util.List;
import java.util.Optional;

/**
 * The sim service contract. This
 * contract specifies which operations, about a sim,
 * must be defined by a service.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
public interface ISimService {

    /**
     * Create a new sim.
     *
     * @param simDTO Sim info
     * @return DTO Sim info
     * @throws ServiceException Cannot create the sim
     * a) if required sim info does not provided (invalid data) CODE 0018
     */
    SimDTO createSim(SimDTO simDTO) throws ServiceException;

    /**
     * Assigning a sim to the user.
     *
     * @param simDTO DTO Sim info
     * @param userId User identifier
     * @return DTO Sim info
     *
     * @throws ServiceException Cannot assign a sim to the user
     * a) if required sim end user info does not provided (invalid data) CODE 0018
     * b) Sim already assigned CODE 0001
     * c) Sim does not exist CODE 0002
     * d) User does not exist CODE 0003
     * e) General error CODE 0000
     */
    SimDTO acquiredByUser(SimDTO simDTO, Integer userId) throws ServiceException;


    /**
     * Activating a product on the sim.
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return DTO Sim info
     *
     * @throws ServiceException Cannot activate the product on the SIM
     * a) if required sim and product info does not provided (invalid data) CODE 0018
     * b) Product already activated CODE 0005
     * c) Sim does not exist CODE 0002
     * e) Product does not exist CODE 0004
     * e) General error CODE 0000
     */
    
    //manca la validazione dell'utente
    
    SimDTO activateProduct(Integer simId, Integer productId) throws ServiceException;

    /**
     * Deactivating a product on the sim
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return DTO Sim info
     *
     * @throws ServiceException Cannot deactivate the product on the SIM
     * a) if required sim and product info does not provided (invalid data) CODE 0018
     * b) Product not activated CODE 0007
     * c) Sim does not exist CODE 0002
     * d) Product does not exist CODE 0004
     * e) General error CODE 0000
     */
    SimDTO deActivateProduct(Integer simId, Integer productId) throws ServiceException;

    /**
     * Finding user's SIMs
     *
     * @param userId User identifier
     * @return Optional object with SIMs if the user has SIMs
     * @throws ServiceException Cannot find SIMS:
     * a) if required user info does not provided (invalid data) CODE 0018
     * b) User does not exist CODE 0003
     * c) General error CODE 0000
     */
    Optional<List<SimDTO>> findSimByUser(Integer userId) throws ServiceException;
}

