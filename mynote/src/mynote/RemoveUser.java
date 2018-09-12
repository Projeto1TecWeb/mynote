package mynote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveNote
 */
@WebServlet("/RemoveUser")
public class RemoveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
		 out.println("<html><body>");
		 out.println("<form method='post'>");
		 out.println("Remover ID: <input type='number' name='idUser'><br>");
		 out.println("<input type='submit' value='Submit'>");
		 out.println("</form>");
		 out.println("<body><html>");	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 DAO dao = new DAO();
		 dao.removeUser(Integer.valueOf(request.getParameter("idUser")));
		  PrintWriter out = response.getWriter();
		  out.println("<html><body>");
		  out.println("removido");
		  out.println("</body></html>");
		  dao.close();
	}

}
