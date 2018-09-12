package mynote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mynote.DAO;
import mynote.User;

/**
 * Servlet implementation class ListaUser
 */
@WebServlet("/ListaUser")
public class ListaUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		List<User> users = dao.getListaUser();
		PrintWriter out = response.getWriter();
		out.println("<html><body><table border='1'>");
		out.println("<tr><td>ID</td><td>Name</td>" + "<td>Username</td><td>Password</td></tr>");
		for (User user : users) {
			out.println("<tr><td>" + user.getIdUser() + "</td>");
			out.println("<td>" + user.getName() + "</td>");
			out.println("<td>" + user.getUsername() + "</td>");
			out.println("<td>" + user.getPassword() + "</td></tr>");

		}
		out.println("</table></body></html>");

		dao.close();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}