
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

package it.mobileapp.impl.repository.contract;

import it.mobileapp.repository.contract.exceptions.RepoException;

/**
 * Common controls
 */
public abstract class CommonControls {

    /**
     * Check if some value is null
     * @param values input data
     * @throws RepoException if some value is null
     */
    public void checkNull(Object ... values) throws RepoException{
       for(int i=0; i<values.length; i++){
           if(values[i] == null)
               throw new RepoException("Parameter "+ i + " is null");
       }
    }

    /**
     * Check if some value is not null
     * @param values input data
     * @throws RepoException if some value is not null
     */
    public void checkNotNull(Object ... values) throws RepoException {
        for(int i=0; i<values.length; i++){
            if(values[i] != null)
                throw new RepoException("Parameter "+ i + " is not null");
        }
    }
}
