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
 * Servlet implementation class RemoveNote
 */
@WebServlet("/RemoveNote")
public class RemoveNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveNote() {
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
		
		
	
		 
		 
//			Gson gson = new Gson();

		    System.out.println(request.getParameter("note"));
	        String jsonObj = request.getParameter("note");
	        
	        
	        Gson gson = new Gson(); 
	        Type type = new TypeToken<Map<String, String>>(){}.getType();
	        Map<String, String> myMap = gson.fromJson(jsonObj, type);
//		    System.out.println(myMap.values());
		    
		    
		    System.out.println(myMap.get("idNote"));

			DAO dao = new DAO();

			dao.removeNota(Integer.parseInt(myMap.get("idNote")));


			dao.close();
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			System.out.println(gson.toJson(myMap));
			out.print(gson.toJson(myMap));
			out.flush();
	}

}
