package it.jdk.projectPostman.models;

public class ShipDTO {

	private Integer id;
    private String name;
    private float tonnage;
    
    public ShipDTO() {
    	
    }

	public ShipDTO(Integer id, String name, float tonnage) {
		super();
		this.id = id;
		this.name = name;
		this.tonnage = tonnage;
	}

	@Override
	public String toString() {
		return "ShipDTO [id=" + id + ", name=" + name + ", tonnage=" + tonnage + "]";
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

	public float getTonnage() {
		return tonnage;
	}

	public void setTonnage(float tonnage) {
		this.tonnage = tonnage;
	}
}
