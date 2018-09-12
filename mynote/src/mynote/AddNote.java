package mynote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNote
 */
@WebServlet("/AddNote")
public class AddNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNote() {
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
		out.println("Text: <input type='text' name='text'><br>");
		out.println("Icon: <input type='text' name='icon'><br>");
		out.println("Color: <input type='text' name='color'><br>");
		out.println("Tag: <input type='text' name='tag'><br>");
		out.println("Tag: <input type='text' name='idUser'><br>");


		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		 Note note = new Note();
		 note.setNoteText(request.getParameter("text"));
		 note.setIcon((request.getParameter("icon")));
		 note.setColor(request.getParameter("color"));
		 note.setTag(request.getParameter("tag"));
		 note.setIdUser(Integer.parseInt(request.getParameter("idUser")));


		 dao.adicionaNota(note);
		 PrintWriter out = response.getWriter();
		 out.println("<html><body>");
		 out.println("adicionado" + note.getIdNote());
		 out.println("</body></html>");
		 dao.close();
	}

}
