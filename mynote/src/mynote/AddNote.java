package mynote;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 
		 
		 String jsonObj = request.getParameter("note");
	        
	        
	        Gson gson = new Gson(); 
	        Type type = new TypeToken<Map<String, String>>(){}.getType();
	        Map<String, String> myMap = gson.fromJson(jsonObj, type);
//		    System.out.println(myMap.values());
		    
		    
		    System.out.println(myMap.get("noteText"));

			DAO dao = new DAO();
			Note note = new Note();
			 note.setNoteText(myMap.get("noteText"));
			 note.setIcon((("icon")));
			 note.setColor(("#00d900"));
			 note.setTag(("tag"));
			 note.setIdUser(1);
//			note.setId(Integer.valueOf(request.getParameter("id")));

			dao.adicionaNota(note);
			note.setIdNote(dao.getLastInsertedId());
			dao.close();
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			System.out.println(gson.toJson(note));
			out.print(gson.toJson(note));
			out.flush();
	}

}
