package it.jdk.projectPostman.models;


public class CruiseDTO {
		
		private Integer id;
		private String name;
		private ShipDTO ship;

	public CruiseDTO() {
			
	}
		
	public CruiseDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "CruiseDTO [id=" + id + ", name=" + name + ", ship=" + ship + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ShipDTO getShip() {
		return ship;
	}

	public void setShip(ShipDTO ship) {
		this.ship = ship;
	}
}
