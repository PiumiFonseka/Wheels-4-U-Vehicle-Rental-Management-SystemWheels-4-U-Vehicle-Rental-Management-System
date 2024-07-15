
package dashboard;

public class cars {
    private int id;
    private String brand;
    private String fuelType;
    private String color;
    private int passengers;
    private String gearbox;
    private double price;
    
	public cars(int id, String brand, String fuelType, String color, int passengers, String gearbox, double price) {
		super();
		this.id = id;
		this.brand = brand;
		this.fuelType = fuelType;
		this.color = color;
		this.passengers = passengers;
		this.gearbox = gearbox;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	public String getGearbox() {
		return gearbox;
	}
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}