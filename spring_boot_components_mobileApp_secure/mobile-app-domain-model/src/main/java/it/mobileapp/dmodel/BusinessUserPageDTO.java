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

package it.mobileapp.dmodel;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * Business User Page Model
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessUserPageDTO implements Serializable{

    private long total; //totale record prodotti dalla ricerca
    private int start; // inizio pagina
    private int max;   // fine pagina

    private List<BusinessUserDTO> page;

    public BusinessUserPageDTO() {}

    public BusinessUserPageDTO(long total, int start, int max, List<BusinessUserDTO> page) {
        this.total = total;
        this.start = start;
        this.max = max;
        this.page = page;
    }

    public List<BusinessUserDTO> getPage() {
        return page;
    }

    public long getTotal() {
        return total;
    }

    public int getStart() {
        return start;
    }

    public int getMax() {
        return max;
    }
}
