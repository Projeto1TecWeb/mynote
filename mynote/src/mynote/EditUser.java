package mynote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mynote.DAO;
import mynote.User;

/**
 * Servlet implementation class EditNote
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
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
		out.println("Id: <input type='text' name='idUser'><br>");

		out.println("Name: <input type='text' name='name'><br>");
		out.println("Username: <input type='text' name='username'><br>");
		out.println("Password: <input type='text' name='password'><br>");

		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");	
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		DAO dao = new DAO();
		
		User user = new User();
//		note.setId(Integer.valueOf(request.getParameter("id")));
		user.setName(request.getParameter("name"));
		user.setUsername((request.getParameter("username")));
		user.setPassword((request.getParameter("password")));
		user.setIdUser(Integer.valueOf(request.getParameter("idUser")));

		dao.alteraUser(user);
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("atualizado" + user.getIdUser());
		out.println("</body></html>");
		dao.close();
	}

}
