package bg.mobile.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.mobile.code.UserDAO;


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("firstname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		try {
			new UserDAO().addUser(name, password, email);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PrintWriter writer = response.getWriter();
		
		writer.println("<html> <body> ");
		writer.println("<p> YOU WERE SUCCEFULLY REGISTERED </p>");
		writer.println("<a href = /ProjectMobile/>Back to home page </a>");
		writer.println("</body> </html> ");;
	}

}
