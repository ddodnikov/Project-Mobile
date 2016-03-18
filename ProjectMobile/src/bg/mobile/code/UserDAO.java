package bg.mobile.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO implements IUserDAO {

	@Override
	public void addUser(String username, String password, String email) throws ClassNotFoundException, SQLException {
		User user = new User(username, password, email);

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");) {

			PreparedStatement addUser = con.prepareStatement(
					"INSERT INTO user (UserID, UserName, Password, EmailAddress)" + " VALUES(?,?,?,?);");

			addUser.setInt(1, user.getId());
			addUser.setString(2, user.getName());
			addUser.setString(3, user.getPassword());
			addUser.setString(4, user.getEmail());

			addUser.executeUpdate();
		}

	}

	@Override
	public int getUserID(String name, String pass) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("Select * from user;");
			while (result.next()) {
				if (result.getString(2).equals(name) && result.getString(3).equals(pass)) {
					return result.getInt(1);
				}
			}
		}
		return -1;
	}

	@Override
	public int getLastUserID() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mobile.bg", "root", "Dimitar910348");) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT MAX(UserID) FROM user;");
			if(result.next())
				return result.getInt(1);
			else
				return 0;
		}
	}
}
