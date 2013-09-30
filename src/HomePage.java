import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;

public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomePage() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String sessionParameter = (String)request.getParameter("param");
	    if(sessionParameter!=null) {
	    	session.setAttribute("param", sessionParameter);
	    }
	    response.getWriter().println("Hello");
	    response.getWriter().println(String.format("Your session id is %s", session.getId()));
	    response.getWriter().println(String.format("Session attribute is %s", session.getAttribute("param")));
	    
	    
	}
	
}
