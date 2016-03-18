package bg.mobile.code;

import java.sql.SQLException;

public class User {

	private int id;
	private String name;
	private String password;
	private String email;

	public User(String name, String password, String email) {
		try {
			this.id = (new UserDAO().getLastUserID() + 1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 0)
			this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null)
			this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null)
			this.email = email;
	}

}
