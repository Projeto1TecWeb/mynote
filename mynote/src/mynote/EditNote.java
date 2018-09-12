package mynote;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mynote.DAO;
import mynote.Note;

/**
 * Servlet implementation class EditNote
 */
@WebServlet("/EditNote")
public class EditNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditNote() {
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
		out.println("Id: <input type='text' name='idNote'><br>");

		out.println("Text: <input type='text' name='text'><br>");
		out.println("Icon: <input type='text' name='icon'><br>");
		out.println("Color: <input type='text' name='color'><br>");
		out.println("Tag: <input type='text' name='tag'><br>");
		out.println("IdUser: <input type='text' name='idUser'><br>");


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
		
		Note note = new Note();
//		note.setId(Integer.valueOf(request.getParameter("id")));
		note.setNoteText(request.getParameter("text"));
		note.setTag((request.getParameter("tag")));
		note.setColor((request.getParameter("color")));
		note.setIcon((request.getParameter("icon")));
		note.setIdNote(Integer.valueOf(request.getParameter("idNote")));

		dao.altera(note);
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("atualizado" + note.getIdNote());
		out.println("</body></html>");
		dao.close();
	}

}
