package mynote;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class changePassword
 */
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changePassword() {
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
		String 	site = "index.jsp";
		Integer idUser = Integer.parseInt(request.getParameter("idUser"));
		String old_password = request.getParameter("old_password");
		String new_password = request.getParameter("new_password");
		String confirm_password = request.getParameter("confirm_password");
		
		System.out.println("ID DO USER");
		System.out.println(idUser);
		String isErrorHidden = "hidden";
		String isPassCheckHidden = "hidden";
		
		HttpSession session = request.getSession();
		
		boolean isPassEqual = false;
		
		if (new_password.equals(confirm_password)){
			isPassEqual = true;
		}
		
		boolean old_pass_check = dao.checkPass(idUser, old_password);

		if(isPassEqual && old_pass_check) {
			dao.changePass(idUser, new_password);
			isErrorHidden = "hidden";
			isPassCheckHidden = "";
			session.setAttribute("isErrorHidden", isErrorHidden);
			session.setAttribute("isPassCheckHidden", isPassCheckHidden);
		}
		else {
			isErrorHidden = "";
			isPassCheckHidden = "hidden";
			session.setAttribute("isErrorHidden", isErrorHidden);
			session.setAttribute("isPassCheckHidden", isPassCheckHidden);
		}
		
		response.sendRedirect(site);
	}

}
