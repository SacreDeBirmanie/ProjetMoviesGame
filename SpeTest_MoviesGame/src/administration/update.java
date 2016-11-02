package administration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class update {

	public update() {
		// TODO Auto-generated constructor stub
	}
	
	//ajout des dates updates
	public void step1(HttpServletResponse resp){
		DatastoreService datastore;
		//////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////AJOUT DE LA date_update initiale/////////////////////////////////			
		//////////////////////////////////////////////////////////////////////////////////////////
		//
		DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
		try{

			Date date_upt = dfm.parse("1989-01-01");
			datastore = DatastoreServiceFactory.getDatastoreService();
			Entity e;
			e = new Entity("date_update_movies", 1);
			e.setProperty("valeur", date_upt);
			datastore.put(e);
			resp.getWriter().println("Datastore Update : date_update_movies");
			
			e = new Entity("date_update_directors", 1);
			e.setProperty("valeur", date_upt);
			datastore.put(e);
			resp.getWriter().println("Datastore Update : date_update_directors");
		} catch (Exception e){
			try{
				resp.getWriter().println("Probl�me step 1");
			} catch (Exception ex){}
		}
	}
	
	public void step1bis(HttpServletResponse resp){
		DatastoreService datastore;
		//////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////AJOUT DE LA date_update initiale/////////////////////////////////			
		//////////////////////////////////////////////////////////////////////////////////////////
		//
		try{
			datastore = DatastoreServiceFactory.getDatastoreService();

			Entity e;
			e = new Entity("count_movies", 1);
			e.setProperty("valeur", 0);
			datastore.put(e);
			e = new Entity("count_directors", 1);
			e.setProperty("valeur", 800);
			datastore.put(e);
			resp.getWriter().println("Datastore Update : count_movies and count_directors");
		} catch (Exception e){
			try{
				resp.getWriter().println("Probl�me step 1bis");
			} catch (Exception ex){}
		}
	}
	
	//verification des dates updates
	public void step2(HttpServletResponse resp){

		DatastoreService datastore;

		//////////////////////////////////////////////////////////////////////////////////////////
		////////////////////Verification que Les date_update initiales sont bien enregistr�es/////
		//////////////////////////////////////////////////////////////////////////////////////////
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		Key cle_date_movies = KeyFactory.createKey("date_update_movies",1);
		Key cle_date_productors = KeyFactory.createKey("date_update_directors",1);
		try {
			Entity dateTrouve = datastore.get(cle_date_movies);
			Date valeur_dateTrouve = (Date) dateTrouve.getProperty("valeur");
			resp.getWriter().println("Entity date_update_movie TROUVEE ! ("+valeur_dateTrouve+")");
			dateTrouve = datastore.get(cle_date_productors);
			valeur_dateTrouve = (Date) dateTrouve.getProperty("valeur");
			resp.getWriter().println("Entity cle_date_productors TROUVEE ! ("+valeur_dateTrouve+")");
		}catch(Exception ex) {
			try{
				resp.getWriter().println("Entity date_update NON TROUVEE !");
		
			} catch (Exception e){}
		}
	}
	
	//ajout des movies ou productors
	//penser a actualiser la page autant de fois que n�cessaire !
	public void step3(HttpServletResponse resp, boolean add_movies){
		//////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////Verification de la cr�ation de update_data///////////////////////
		////////////////////////////////////////et //////////////////////////////////////////////
		/////////////////////////test de l'ajout de donnees et v�rification de date_update////////
		//////////////////////////////////////////////////////////////////////////////////////////
		
		//Test pour l'ajout des movies//
		
		try{
			update_data_json update = new update_data_json(resp,add_movies);
			if(resp!=null){
				if(add_movies){
					resp.getWriter().println("Ajout movies !! ");
					resp.getWriter().println("Voici la date_update pour l'instant : "+update.getDate_update());
				}else{
					resp.getWriter().println("Ajout directors !! ");
				}
			}
			int nb_add_data = update.go_update(resp)-1;
			if(nb_add_data>0){
				resp.getWriter().println("Voici le nombre de donn�es ajout�es : "+(nb_add_data));
				resp.getWriter().println("Datastore Update");
			}else{
				resp.getWriter().println("Vos donn�es sont � jour ! (c'est � dire qu'il n'y pas pas de film plus r�cent que dans le datastore !)");
			}
			if(add_movies){
				resp.getWriter().println("Voici la nouvelle date_update : "+update.getDate_update());
			}
		} catch(Exception e){
			try{
				resp.getWriter().println("Probl�me step 3 !");
			} catch (Exception ex){}
		}
	}
}
