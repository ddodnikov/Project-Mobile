package bg.mobile.code;

import java.sql.SQLException;

public class Poster {
	
	private int id;
	private boolean isVIP;
	private String period;
	
	private Vehicle vehicle;
	
	public Poster(String period, Vehicle vehicle) {
		this.setPeriod(period);
		this.setVehicle(vehicle);
		try {
			this.id = (new PosterDAO().getLastPosterID() + 1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isVIP() {
		return this.isVIP;
	}
	
	public String getPeriod() {
		return this.period;
	}
	
	public void setPeriod(String period) {
		if(period != null)
			this.period = period;
	}
	
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		if(vehicle != null)
		this.vehicle = vehicle;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	
}
