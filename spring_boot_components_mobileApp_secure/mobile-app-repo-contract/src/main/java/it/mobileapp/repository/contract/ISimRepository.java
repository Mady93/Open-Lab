
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

package it.mobileapp.repository.contract;

import it.mobileapp.dmodel.SimDTO;
import it.mobileapp.repository.contract.exceptions.*;
import java.util.List;
import java.util.Optional;

/**
 * The Sim repository contract. This
 * contract specifies which operations, about a sim,
 * must be defined by a repository.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
public interface ISimRepository {


    /**
     * Create a new Sim using DTO info.
     * Warning, product info will be ignored,
     * this method only create an empty Sim.
     *
     * @param simDTO Sim DTO info
     * @return DTO sim data
     * @throws RepoException Cannot create the sim:
     * a) General error CODE R0000
     */
    SimDTO createSim(SimDTO simDTO) throws RepoException;

    /**
     * Assigning a sim to the user.
     *
     * @param simDTO DTO sim data
     * @param userId User identifier
     * @return DTO sim data
     *
     * @throws RepoException Cannot assign a sim to the user:
     * a) General error CODE R0000
     * b) Concurrent modification CODE R0001
     */
    SimDTO acquiredByUser(SimDTO simDTO, Integer userId) throws RepoException;

    /**
     * Check if the sim is already assigned to a user.
     *
     * @param simId Sim identifier
     * @return true if the sim is assigned, false otherwise
     * @throws RepoException Cannot check the Sim:
     * a) General error CODE R0000
     */
    boolean simAlreadyAssigned(Integer simId) throws RepoException;
    
    //restituisce un booleano

    /**
     * Check if the product is activated on the input sim.
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return true if the product is activated false otherwise
     * @throws RepoException
     * a) General error CODE R0000
     */
    boolean productAlreadyActivated(Integer simId, Integer productId) throws RepoException;

    /**
     * Activating a product on the sim.
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return DOT Sim data
     *
     * @throws RepoException Cannot activate the product on the sim:
     * a) General error CODE R0000
     */
    SimDTO activateProduct(Integer simId, Integer productId) throws RepoException;

    /**
     * Deactivating a product on the sim.
     *
     * @param simId Sim identifier
     * @param productId Product identifier
     * @return Sim data
     *
     * @throws RepoException Cannot deactivate the product on the sim:
     * a) General error CODE R0000
     */
    SimDTO deActivateProduct(Integer simId, Integer productId) throws RepoException;

    /**
     * Find users's sim.
     *
     * @param userId User identifier
     * @return Optional object with users's sim if a user has SIMs
     * @throws RepoException Cannot find SIMs:
     * a) General error CODE R0000
     */
    Optional<List<SimDTO>> findSimByUser(Integer userId) throws RepoException;

    /**
     * Find a specific sim by id.
     *
     * @param simId Sim identifier
     * @return Optional object with sim data if exist
     * @throws RepoException Cannot find SIM:
     * a) General error CODE R0000
     */
    Optional<SimDTO> findSimById(Integer simId) throws RepoException;

    //caricare sim ed i suoi prodotti
    
    /**
     * SIMs count using msisdn and imsi.
     *
     * @param msisdn Sim msisdn
     * @param imsi Sim imsi
     * @return Number of SIMs
     * @throws RepoException
     * a) General error CODE R0000
     */
    Long coundByMsisdnAndImsi(String msisdn, String imsi) throws RepoException;

    /**
     * Get a pack of free SIMs.
     *
     * @param blockSize Pack block size
     * @return SIMs list
     * @throws RepoException Cannot find SIMs:
     * a) General error CODE R0000
     */
    Optional<List<SimDTO>> freeSims(Integer blockSize) throws RepoException;
    //reccuperare un pacchetto di sim libere pari a blockSize
}
