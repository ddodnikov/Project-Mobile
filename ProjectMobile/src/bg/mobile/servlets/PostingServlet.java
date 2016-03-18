package bg.mobile.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.mobile.code.PosterDAO;
import bg.mobile.code.VehicleDAO;

@WebServlet("/PostingServlet")
public class PostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String price = request.getParameter("price");
		String power = request.getParameter("power");
		String color = request.getParameter("color");
		String mileage = request.getParameter("mileage");
		String engine = request.getParameter("engine");
		String gearbox = request.getParameter("gearbox");
		String type = request.getParameter("type");
		String period = request.getParameter("period");
		
		try {
			new PosterDAO().addPoster(period, new VehicleDAO().addVehicle(brand, model, year, price, power, color, mileage, engine, gearbox, type));
			
			PrintWriter writer = response.getWriter();
			writer.println("<html> <body> ");
			writer.println("<h1> You poster was successfully added in site's datebase </h1>");
			writer.println("</body> </html> ");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
