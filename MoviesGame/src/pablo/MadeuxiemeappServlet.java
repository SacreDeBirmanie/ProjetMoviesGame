package pablo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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
//////////////////////////////////////////////////////////////////////////////////////////
//			upd.step2(resp);
//////////////////////////////////////////////////////////////////////////////////////////
// mettre à faux pour ajouter des directors mais attention pour l'ajout des directors il ne faut pas actualiser la page 
//			sinon ils sont ajoutés en double !!!
			upd.step3(resp,true);


		} catch (Exception e) {
	    	resp.getWriter().println("UNE ERREUR S'EST PRODUIT !!!!!");
		}

	}
}
