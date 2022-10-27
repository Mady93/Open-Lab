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

package it.mobileapp.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class models a Response that must be sent
 * to the client.
 *
 * @param <T> Model type
 * @param <E> Error type
 * @param <C> Error code type
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RestResponseEntity<T,E,C> {

    public abstract T getData();
    public abstract boolean isOk();
    public abstract E getError();
    public abstract C getErrorCode();

    private RestResponseEntity(){}

    /**
     * Build a Response using a model object
     *
     * @param data Model object
     * @param <T> Model type
     * @return a Response encapsulating a Model object
     */
    public static <T> RestResponseEntity buildData(T data){
            return new RestResponseEntity<T, Object, Object>() {
                @Override
                public T getData() {
                    return data;
                }
                @Override
                public boolean isOk() {
                    return true;
                }
                @Override
                public Object getError() {
                    return null;
                }
                @Override
                public Object getErrorCode() {
                    return null;
                }
            };
    }

    /**
     * Build a Response using a message with an error code
     *
     * @param errorMessage Error message
     * @param erroCode Error code
     * @param <E> Error message type
     * @param <C> Error code type
     * @return a Response encapsulating an Error object
     */
    public static <E,C> RestResponseEntity buildError(E errorMessage, C erroCode){
        return new RestResponseEntity<Object, E, C>() {
            @Override
            public Object getData() {
                return null;
            }
            @Override
            public boolean isOk() {
                return false;
            }
            @Override
            public E getError() {
                return errorMessage;
            }
            @Override
            public C getErrorCode() {
                return erroCode;
            }
        };
    }
}
