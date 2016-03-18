package bg.mobile.code;

import java.sql.SQLException;

public interface IPosterDAO {
	
	public void addPoster(String period, Vehicle vehicle) throws SQLException, ClassNotFoundException;

	public int getLastPosterID() throws ClassNotFoundException, SQLException;
}
