package it.jdk.projectPostman.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerPageDTO implements Serializable{
	
	 private long total; //totale record prodotti dalla ricerca
	    private int start; // inizio pagina
	    private int max;   // fine pagina

	    private List<CustomerDTO> page;

	    public CustomerPageDTO() {}

	    public CustomerPageDTO(long total, int start, int max, List<CustomerDTO> page) {
	        this.total = total;
	        this.start = start;
	        this.max = max;
	        this.page = page;
	    }

	    public List<CustomerDTO> getPage() {
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