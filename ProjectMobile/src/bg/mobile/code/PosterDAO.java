package bg.mobile.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PosterDAO implements IPosterDAO {

	@Override
	public void addPoster(String period, Vehicle vehicle) throws SQLException, ClassNotFoundException {

		Poster poster = new Poster(period, vehicle);

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");) {

			PreparedStatement addPoster = con.prepareStatement(
					"INSERT INTO poster(PosterID, Period, VIP, SellerID, VehicleID)" + " VALUES(?,?,null,null,?);");

			addPoster.setInt(1, poster.getId());
			addPoster.setString(2, poster.getPeriod());
			addPoster.setInt(3, poster.getVehicle().getId());

			addPoster.executeUpdate();
		}
	}

	public List<String> findVehiclesByUserID(int userID) throws ClassNotFoundException, SQLException {

		List<String> vehicles = new ArrayList<String>();

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");) {

			Statement statement = con.createStatement();

			ResultSet result = statement.executeQuery("SELECT SellerID, VehicleID FROM poster;");

			while (result.next()) {
				if (result.getInt(1) == userID) {
					int vehicleID = result.getInt(2);
					Statement statement2 = con.createStatement();
					ResultSet resultV = statement2
							.executeQuery("SELECT * FROM vehicle WHERE VehicleID = " + vehicleID + ";");
					while (resultV.next()) {
						String car = "CarID = " + resultV.getInt(1) + "; Brand = " + resultV.getString(2) + "; Model = "
								+ resultV.getString(3) + "; Year = " + resultV.getString(4) + "; Price = "
								+ resultV.getString(5);
						vehicles.add(car);
					}
				}
			}
		}
		return vehicles;
	}

	@Override
	public int getLastPosterID() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT MAX(PosterID) FROM poster;");
			if(result.next())
				return result.getInt(1);
			else
				return 0;
		}
	}

}
