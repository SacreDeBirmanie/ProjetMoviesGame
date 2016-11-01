package pablo;

import java.io.IOException;

import javax.servlet.http.*;


@SuppressWarnings("serial")
public class MadeuxiemeappServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, Admin");
		try{
			update upd = new update();
//////////////////////////////////////////////////////////////////////////////////////////
//			upd.step1(resp);
//			upd.step1bis(resp);
//////////////////////////////////////////////////////////////////////////////////////////
//			upd.step2(resp);
//////////////////////////////////////////////////////////////////////////////////////////
// mettre à faux pour ajouter des directors mais attention pour l'ajout des directors il ne faut pas actualiser la page 
//			sinon ils sont ajoutés en double !!!
//			upd.step3(resp,true);

			generate_list_questions gene = new generate_list_questions(resp);
			gene.afficher_tout(resp);
		} catch (Exception e) {
	    	resp.getWriter().println("UNE ERREUR S'EST PRODUITE !!!!!");
	    	resp.getWriter().println( e.toString());
		}

	}
}
