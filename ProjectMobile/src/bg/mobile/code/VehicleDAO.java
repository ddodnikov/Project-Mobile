package bg.mobile.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO implements IVehicleDAO{

	@Override
	public Vehicle addVehicle(String brand, String model,String year, String price,
			String power, String color, String mileage, String engine, String gearbox, String type) throws SQLException, ClassNotFoundException {
		
		Vehicle vehicle = new Vehicle(brand, model, year, price);
		
		vehicle.addProperty("power", power);
		
		vehicle.addProperty("color", color);
		
		vehicle.addProperty("mileage", mileage);
		
		vehicle.addProperty("engine", engine);
		
		vehicle.addProperty("gearbox", gearbox);
		
		vehicle.addProperty("type", type);
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");) {
			
			PreparedStatement addVehicle = con.prepareStatement("INSERT INTO vehicle(VehicleID, Brand, Model, Year, Price)"
					+ " VALUES(?,?,?,?,?);");
			
			addVehicle.setInt(1, vehicle.getId());
			addVehicle.setString(2, vehicle.getBrand());
			addVehicle.setString(3, vehicle.getModel());
			addVehicle.setString(4, vehicle.getYear());
			addVehicle.setString(5, vehicle.getPrice());
			
			addVehicle.executeUpdate();
			
			PreparedStatement addVehicleProperty = con.prepareStatement("INSERT INTO properties(VehicleID, Property, Value)"
					+ " VALUES(?,?,?)");
		
			for(String string : vehicle.getproperties().keySet()) {
				addVehicleProperty.setInt(1, vehicle.getId());
				addVehicleProperty.setString(2, string);
				addVehicleProperty.setString(3, vehicle.getproperties().get(string));
				
				addVehicleProperty.executeUpdate();
			}
			
			return vehicle;
		}
	}
	@Override
	public List<String> searchVehicle(String brand) {
		
		Connection con = null;
		try {
			List<String> vehicles = new ArrayList<>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");
			ResultSet result = con.createStatement().executeQuery("Select * from vehicle where Brand like \'"+ brand +"\';");

			while (result.next()) {
				String car = "CarID = " + result.getInt(1) + "; Brand = " + result.getString(2) + "; Model = "
						+ result.getString(3) + "; Year = " + result.getString(4) + "; Price = "
						+ result.getString(5);
				vehicles.add(car);
			}
			return vehicles;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public List<String> getAllVehicles() {
		Connection con = null;
		try {
			List<String> vehicles = new ArrayList<>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");
			ResultSet result = con.createStatement().executeQuery("Select * from vehicle ;");

			while (result.next()) {
				String car = "CarID = " + result.getInt(1) + "; Brand = " + result.getString(2) + "; Model = "
						+ result.getString(3) + "; Year = " + result.getString(4) + "; Price = "
						+ result.getString(5);
				vehicles.add(car);
			}
			return vehicles;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public int getLastVehicleID() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT MAX(VehicleID) FROM vehicle;");
			if(result.next())
				return result.getInt(1);
			else
				return 0;
		}
	}
	
	

}
