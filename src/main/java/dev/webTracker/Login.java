package dev.webTracker;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAODB.DAODBuser;
import Interface.UserInterface;
import facade.userFacade;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ss = request.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		userFacade uf = userFacade.login(userName, password);
		if(uf == null){
			response.setContentType("text/html");
			request.setAttribute("message", "bad login");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			ss.setAttribute("facade", uf);
		}
	}

}
