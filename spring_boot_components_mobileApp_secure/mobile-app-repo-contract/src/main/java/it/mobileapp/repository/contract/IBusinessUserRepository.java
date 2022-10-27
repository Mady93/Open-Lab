

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

import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.dmodel.BusinessUserFilterDTO;
import it.mobileapp.dmodel.BusinessUserPageDTO;
import it.mobileapp.repository.contract.exceptions.RepoException;
import java.util.Optional;

/**
 * The BusinessUser repository contract. This
 * contract specifies which operations, about a user,
 * must be defined by a repository.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
public interface IBusinessUserRepository {

    /**
     * Create a new User using DTO info.
     *
     * @param user DTO user data
     * @return DTO user data
     * @throws RepoException Cannot create the user
     * a) General error CODE R0000
     */
    BusinessUserDTO createUser(BusinessUserDTO user) throws RepoException;

    /**
     * Check if there is a user with the input fiscal code.
     *
     * @param fiscalCode User's fiscal code
     * @return true if the user exist false otherwise
     * @throws RepoException Cannot check the user
     * a) General error CODE R0000
     */
    boolean existsUserWithFiscalCode(String fiscalCode) throws RepoException;

    /**
     * Update a User using the input DTO info.
     *
     * @param user DTO user data
     * @return Updated DTO user data
     * @throws RepoException Cannot update the user
     * a) Concurrent modification CODE R0001
     * b) General error CODE R0000
     */
    BusinessUserDTO updateUser(BusinessUserDTO user) throws RepoException;

    /**
     * Delete the user specified by the DTO info.
     *
     * @param user DTO user data
     * @throws RepoException Cannot delete the user
     * a) General error CODE R0000
     * b) Concurrent modification CODE R0001
     */
    void deleteUser(BusinessUserDTO user) throws RepoException;

    /**
     * Find a user by id.
     *
     * @param id User identifier
     * @return User data or empty value
     * @throws RepoException Cannot get User's data:
     *  a) General error CODE R0000
     */
    Optional<BusinessUserDTO> findById(Integer id) throws RepoException;

    /**
     * Find users and getting the specified page (start) with size (max).
     *
     * @param start The specified page
     * @param max Page size
     * @return Users page
     * @throws RepoException Cannot get user's data:
     *   a) General error CODE R0000
     */
    Optional<BusinessUserPageDTO> findUsers(int start, int max) throws RepoException;

    /**
     * Find users and getting the specified page, page (start) with size (max), using a filter.
     *
     * @param start Page we want
     * @param max Page size
     * @param filter Filter criteria
     * @return Users page
     * @throws RepoException Cannot get user's data:
     *   a) General error CODE R0000
     */
    Optional<BusinessUserPageDTO> findUsersByFilter(int start, int max, BusinessUserFilterDTO filter) throws RepoException;

//##############################################################################################################
      }

