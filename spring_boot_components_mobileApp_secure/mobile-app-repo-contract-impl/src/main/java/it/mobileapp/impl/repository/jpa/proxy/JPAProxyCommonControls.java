
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

package it.mobileapp.impl.repository.jpa.proxy;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

/**
 * Common JPA Proxy controls
 */
public abstract class JPAProxyCommonControls {

    /**
     * Check if the Optional is empty.
     *
     * @param optional Optional value
     * @throws NoResultException if the optional is empty
     */
    public void checkEmptyError(Optional optional) throws NoResultException{
        if(optional.isEmpty())
            throw new NoResultException("Element does not exist");
    }

    /**
     * Check if the list is empty.
     *
     * @param list Input list
     * @param <T> Generic Type
     * @throws NoResultException if the list is empty
     */
    public <T> void checkEmptyList(List<T> list) throws NoResultException {
        if(list.isEmpty())
            throw new NoResultException("Empty list");
    }

}
