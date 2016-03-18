package bg.mobile.code;

import java.sql.SQLException;
import java.util.List;

public interface IVehicleDAO {
	
	public Vehicle addVehicle(String brand, String model,String year, String price,
			String power, String color, String mileage, String engine, String gearbox, String type) throws SQLException, ClassNotFoundException;
	
	public List<String> searchVehicle(String brand);
	
	public List<String> getAllVehicles();

	public int getLastVehicleID() throws ClassNotFoundException, SQLException;
}
