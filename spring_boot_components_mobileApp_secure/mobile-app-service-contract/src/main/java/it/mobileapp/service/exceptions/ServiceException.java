
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

package it.mobileapp.service.exceptions;

/**
 * Service Exception
 */
public class ServiceException extends Exception {

    private String code = "0000";

    /**
     * No argument constructor
     */
    public ServiceException() {}

    /**
     * Costructor for Error Code
     * @param message Error Message
     * @param code Error Code
     */
    public ServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    /**
     * Message Constructor
     * @param message Error Message
     */
    public ServiceException(String message) {
        super (message);
    }

    /**
     * Throwable Constructor
     * @param cause Cause
     */
    public ServiceException(Throwable cause) {
        super (cause);
    }

    /**
     * Message and Throwable Constructor
     * @param message Error message
     * @param cause Cause
     */
    public ServiceException(String message, Throwable cause) {
        super (message, cause);
    }

    /**
     * Error code
     * @return error code
     */
    public String getCode() {
        return code;
    }
}