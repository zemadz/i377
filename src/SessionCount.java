
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import listeners.SessionCountListener;

public class SessionCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int sessionsActive;

	public SessionCount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println(String.format("count: %s", SessionCountListener.getActiveSessionsCount()));
	}

}
