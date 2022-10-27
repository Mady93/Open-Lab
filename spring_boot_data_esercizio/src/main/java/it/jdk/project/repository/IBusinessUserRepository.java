package it.jdk.project.repository;

import java.util.Optional;

import it.jdk.project.models.BusinessUserDTO;
import it.jdk.project.models.BusinessUserFilterDTO;
import it.jdk.project.models.BusinessUserPageDTO;

/**
 *  @author  name
 *  @version 1.0
 *  @since   date
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
}

