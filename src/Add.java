

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Unit;
import dao.UnitDao;

public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Add() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Unit newUnit = new Unit();
		newUnit.setName(request.getParameter("name"));
		newUnit.setCode(request.getParameter("code"));
		new UnitDao().addUnit(newUnit);
		response.sendRedirect("Search");
	}

}
