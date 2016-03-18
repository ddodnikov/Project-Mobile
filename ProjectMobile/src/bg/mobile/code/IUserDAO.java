package bg.mobile.code;

import java.sql.SQLException;

public interface IUserDAO {

	public void addUser(String username, String password, String email) throws ClassNotFoundException, SQLException;

	public int getUserID(String name, String pass) throws ClassNotFoundException, SQLException;

	public int getLastUserID() throws ClassNotFoundException, SQLException;
	
}
