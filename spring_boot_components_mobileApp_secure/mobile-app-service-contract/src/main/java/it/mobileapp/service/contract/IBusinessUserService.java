
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
import it.mobileapp.dmodel.BusinessUserDTO;
import it.mobileapp.dmodel.BusinessUserFilterDTO;
import it.mobileapp.dmodel.BusinessUserPageDTO;
import it.mobileapp.service.exceptions.ServiceException;

import java.awt.print.Pageable;
import java.util.Optional;


/**
 * The business user service contract. This
 * contract specifies which operations, about a business user,
 * must be defined by a service.
 *
 * @author  Alessandro Zoia
 * @version 1.0
 * @since   13/08/2022
 */
public interface IBusinessUserService {

     /**
      * Create a new user.
      *
      * @param user DTO user data
      * @return  DTO user data
      * @throws ServiceException Cannot create the user
      * a) if required user info does not provided (invalid data) CODE 0018
      * b) if a user with the same fiscal code already exist CODE 0008
      * c) General error CODE 0000
      */
     BusinessUserDTO createUser(BusinessUserDTO user) throws ServiceException;

     /**
      * Update user.
      *
      * @param user  DTO user data
      * @return  DTO user data
      * @throws ServiceException Cannot update the User
      * a) if required user info does not provided (invalid data) CODE 0018
      * b) User does not exist CODE 0003
      * b) General error CODE 0000
      */
     BusinessUserDTO updateUser(BusinessUserDTO user) throws ServiceException;

     /**
      * Delete a user.
      *
      * @param user DTO user data
      * @throws ServiceException Cannot delete the user
      * a) if required user info does not provided (invalid data) CODE 0018
      * b) User does not exist CODE 0003
      * c) User exist with the specified sim CODE 0009
      * d) General error CODE 0000
      */
     void deleteUser(BusinessUserDTO user) throws ServiceException;

     /**
      * Find user by id.
      *
      * @param id User identifier
      * @return User data or empty value
      * @throws ServiceException Cannot get user's data:
      * a) if required user info does not provided (invalid data) CODE 0018
      * b) General error CODE 0000
      */
     Optional<BusinessUserDTO> findById(Integer id) throws ServiceException;

     /**
      * Finding users obtaining the page (start) with size (max).
      *
      * @param start Page we want
      * @param max Page size
      * @return Users page
      * @throws ServiceException Cannot get user's data:
      *   a) General error CODE 0000
      */
     Optional<BusinessUserPageDTO> findUsers(int start, int max) throws ServiceException;

     /**
      * Finding users obtaining the page (start) with size (max) using a filter.
      *
      * @param start Page we want
      * @param max Page size
      * @param filter Filter criteria
      * @return Users page
      * @throws ServiceException Cannot get User's data:
      *   a) General error CODE 0000
      */
     Optional<BusinessUserPageDTO> findUsersByFilter(int start, int max, BusinessUserFilterDTO filter) throws ServiceException;


     //##########################################################################################
     
 	Long coundByIdAndFiscalCode(Integer id, String fiscalCode);

}