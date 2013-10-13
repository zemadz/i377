
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SetupDao;
import dao.UnitDao;

public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doAction = request.getParameter("do");
		if ("clear_data".equals(doAction)) {
			new UnitDao().clearTable();
		} else if ("insert_data".equals(doAction)) {
			new SetupDao().insertTestData();
		}
		response.sendRedirect("Search");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
