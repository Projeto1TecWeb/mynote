package mynote;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class EditNoteColor
 */
@WebServlet("/EditNoteColor")
public class EditNoteColor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditNoteColor() {
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
		
//		Gson gson = new Gson();
		HttpSession session = request.getSession();
		Integer idUser = (Integer)session.getAttribute("idUser");

	    System.out.println(request.getParameter("note"));
        String jsonObj = request.getParameter("note");
        
        
        Gson gson = new Gson(); 
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = gson.fromJson(jsonObj, type);
//	    System.out.println(myMap.values());
	    
	    
	    System.out.println(myMap.get("idNote"));

		DAO dao = new DAO();
		Note note = dao.getNote(Integer.parseInt(myMap.get("idNote")), idUser);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm MM/dd");
		LocalDateTime now = LocalDateTime.now();
		note.setTime(dtf.format(now));

//		note.setId(Integer.valueOf(request.getParameter("id")));
		note.setColor(myMap.get("color"));

		dao.alteraNota(note);

		dao.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(gson.toJson(note));
		out.print(gson.toJson(note));
		out.flush();
	}

}
