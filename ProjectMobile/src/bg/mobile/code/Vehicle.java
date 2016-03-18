package bg.mobile.code;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Vehicle {

	private int id;
	private String brand;
	private String model;
	private String year;
	private String price;

	private Map<String, String> properties = new HashMap<String, String>();

	public Vehicle(String brand, String model,String year, String price) {
		this.setBrand(brand);
		this.setModel(model);
		this.setYear(year);
		this.setPrice(price);
		try {
			this.id = (new VehicleDAO().getLastVehicleID() + 1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addProperty(String property, String value) {
		if(property != null && value != null)
			this.properties.put(property, value);
	}
	
	public int getId() {
		return this.id;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		if (brand != null)
			this.brand = brand;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		if (model != null)
			this.model = model;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		if (year != null)
			this.year = year;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		if (price!= null)
			this.price = price;
	}
	
	public Map<String, String> getproperties() {
		return this.properties;
	}

}
