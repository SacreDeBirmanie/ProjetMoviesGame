package administration;

import java.io.IOException;

import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Generate10QuestionsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, Admin");
		try{
			generate_list_questions gene = new generate_list_questions(resp);
			gene.afficher_tout(resp);
		} catch (Exception e) {
	    	resp.getWriter().println("UNE ERREUR S'EST PRODUITE !!!!!");
	    	resp.getWriter().println( e.toString());
		}

	}
}

