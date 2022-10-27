package it.qm.anagrafica.page;

import java.util.List;
import it.qm.anagrafica.models.AnagraficaDTO;

public class PageDTO {

	private long totalPages; // totale pagine
	private long totalElements; // totale record prodotti dalla ricerca
	private int number; // inizio pagina
	private int size; // fine pagina

	private List<AnagraficaDTO> page;

	public PageDTO() {
	}

	public PageDTO(long totalPages, long totalElements, int number, int size, List<AnagraficaDTO> page) {
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.number = number;
		this.size = size;
		this.page = page;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getNumber() {
		return number;
	}

	public int getSize() {
		return size;
	}

	public List<AnagraficaDTO> getPage() {
		return page;
	}
}