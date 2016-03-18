package bg.mobile.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.mobile.code.VehicleDAO;

/**
 * Servlet implementation class GetAllServlet
 */
@WebServlet("/GetAll")
public class GetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		List<String> vehicles = new VehicleDAO().getAllVehicles();
		
		pw.println("<html> <body>");
		
		for (String car : vehicles) {			
			pw.println("<p>" + car + "</p>");
		}
		pw.println("<a href = /ProjectMobile/>Back to home page </a>");
		pw.println("</body> </html>");
	}


}
