package bg.mobile.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.mobile.code.PosterDAO;
import bg.mobile.code.UserDAO;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =  request.getParameter("user");
		String password = request.getParameter("pass");
		
	try {
		
		int userId = new UserDAO().getUserID(username, password);
		
		if(userId>0){
			
			List<String> vehicles = new PosterDAO().findVehiclesByUserID(userId);
			
			PrintWriter writer = response.getWriter();
			
			writer.println("<html> <body> ");
			writer.println("<p> These are your vehicles you are selling : </p>");
			for(String car : vehicles) {
				writer.println("<p> " + car + " </p>");
			}
			writer.println("<a href = /ProjectMobile/>Back to home page </a>");
			writer.println("</body> </html> ");
			
		}
		else{
			response.sendRedirect("./login.html");
		}
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
	
	
}
