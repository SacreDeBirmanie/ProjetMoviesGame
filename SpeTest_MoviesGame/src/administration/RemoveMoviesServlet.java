package administration;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class RemoveMoviesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, Admin");
			try{
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				
				Query maQuery = new Query("movies");
				
				List<Entity> results =
					    datastore.prepare(maQuery).asList(FetchOptions.Builder.withDefaults());
				
			for(int i = 0; i<results.size();++i){
				resp.getWriter().println("un tour de roue");
				resp.getWriter().println(results.get(i).getKey());
				results.get(i).getKey();
				datastore.delete(results.get(i).getKey());
			}
				
			
				

			} catch (Exception e) {
		    	resp.getWriter().println("UNE ERREUR S'EST PRODUIT !!!!!");
		    	resp.getWriter().println(e.toString());
			}

		}
	}