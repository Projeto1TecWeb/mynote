package mynote;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class verifyUser
 */
@WebServlet("/verifyUser")
public class verifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verifyUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		String site = "signInError.jsp";
		boolean verifyUser = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String isErrorHidden = "hidden";
		String isPassCheckHidden = "hidden";
		
		HttpSession session = request.getSession();
		session.setAttribute("isPassCheckHidden", isPassCheckHidden);
		session.setAttribute("isErrorHidden", isErrorHidden);
		
		verifyUser = dao.verifyUser(username, password);
			
		
		if(verifyUser) {
			site = "index.jsp";
			Integer idUser = dao.getIdFromUsername(username);
			session.setAttribute("idUser", idUser);
			response.sendRedirect("index.jsp");

		}
		
		request.getRequestDispatcher(site).include(request, response);
	}

}
