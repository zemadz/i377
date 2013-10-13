import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Unit;
import dao.SetupDao;
import dao.UnitDao;

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		try {
			SetupDao dao = new SetupDao();
			dao.createSchema();
			dao.insertTestData();
		} catch (Exception ex) {

		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doAction = request.getParameter("do");
		String searchString = request.getParameter("searchString");
		List<Unit> units;
		if ("delete".equals(doAction)) {
			String idString = request.getParameter("id");
			try {
				long id = Long.parseLong(idString);
				new UnitDao().deleteUnit(id);
			} catch (NumberFormatException e) {
			}
			units = new UnitDao().getUnits();
		} else if (searchString != null) {
			units = new UnitDao().findUnitsThatNameContains(searchString);
			request.getSession().setAttribute("searchString", searchString);
		} else {
			units = new UnitDao().getUnits();
		}
		request.getSession().setAttribute("units", units);
		request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Unit> units = new UnitDao().getUnits();
		units.clear();
		request.getSession().setAttribute("units", units);
		response.sendRedirect("Search");
	}

}
