package administration;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class update_data_json {
	
	private Date date_update;
	private boolean update_movies;
	
	public update_data_json( HttpServletResponse resp, boolean ajout_movies){
		this.update_movies=ajout_movies;
		if(resp!=null){
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Key cle_date;
			if(ajout_movies)
				cle_date = KeyFactory.createKey("date_update_movies",1);
			else
				cle_date = KeyFactory.createKey("date_update_productors",1);
		    try {
		    	Entity dateTrouve = datastore.get(cle_date);
		    	Date valeur_dateTrouve = (Date) dateTrouve.getProperty("valeur");
				this.date_update = valeur_dateTrouve;
		    }catch(Exception e) {
		    	System.out.println("Entity date_update NON TROUVEE !");
		    }
		}else{
			this.date_update=null;
		}
	}
	
	public Date getDate_update() {
		return date_update;
	}

	//ajout des directors limit� � 800
	public int go_update(HttpServletResponse resp){
		int count = 1;
		int cpt_add = 0;
		 int count_movies=0;
		 JSONParser parser = new JSONParser();
		 try {
			 Object obj;
			DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
			Date date_upt;
			if(this.update_movies&&(resp!=null))
				date_upt = this.date_update;
			else
				date_upt = dfm.parse("1989-01-01");

			Date date_initial = date_upt;

			 ArrayList<String> list_id_wiki = new ArrayList<String>();

			 if(this.update_movies)
			 {
				if(resp!=null){
					resp.getWriter().println("Date initial : "+date_initial);
					DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
					Key cle_count_movies = KeyFactory.createKey("count_movies", 1);
					Entity count_trouve = datastore.get(cle_count_movies );
					long count_movies_long = (long) count_trouve.getProperty("valeur");
					count_movies = (int) count_movies_long;
					cpt_add=count_movies;
				}
				 obj = parser.parse(new FileReader("donnees_movies.json"));
//				 obj = parser.parse(new FileReader("donneestest.json"));
				 JSONArray jsonarray;
				
				 JSONObject jsonObject = (JSONObject) obj;
				
				 jsonObject = (JSONObject) jsonObject.get("results");
				
				 jsonarray = (JSONArray) jsonObject.get("bindings");

								 
				 for (Object movie : jsonarray){
					 JSONObject movieobj = (JSONObject) movie;
					 JSONObject movieobjname = (JSONObject) movieobj.get("name");
					 String name = (String) movieobjname.get("value");
					 
					 movieobjname = (JSONObject) movieobj.get("name_director");
					 String name_director = (String) movieobjname.get("value");
					 
					 movieobjname = (JSONObject) movieobj.get("date");
					 String date = (String) movieobjname.get("value");
					 Date date_movie = dfm.parse(date);
					 
					 movieobjname = (JSONObject) movieobj.get("country");
					 String country = (String) movieobjname.get("value");
					 
					 movieobjname = (JSONObject) movieobj.get("lat");
					 String lat = (String) movieobjname.get("value");
					 
					 movieobjname = (JSONObject) movieobj.get("longi");
					 String longi = (String) movieobjname.get("value");
					 
					 movieobjname = (JSONObject) movieobj.get("id_wiki");
					 String id_wiki = (String) movieobjname.get("value");
					 
					 movieobjname = (JSONObject) movieobj.get("id_wiki_director");
					 String id_wiki_director = (String) movieobjname.get("value");
					 

					 
					 boolean bool_check_date = date_movie.after(date_initial);

					 if(bool_check_date){
						 if(!list_id_wiki.contains(id_wiki)){
							 count++;
							 if(count<=500){
								 list_id_wiki.add(id_wiki);
								 boolean bool_date = date_movie.after(date_upt);
								 if(bool_date)
									 date_upt=date_movie;
								 if(resp==null){
									 System.out.println("name : "+name);
									 System.out.println("name_director : "+name_director);
									 System.out.println("date : "+date);
									 System.out.println("country : "+country);
									 System.out.println("lat : "+lat);
									 System.out.println("longi : "+longi);
									 System.out.println("id_wiki : "+id_wiki);
									 System.out.println("id_wiki_director : "+"d"+id_wiki_director);
									 System.out.println("-----------------------------------------------------");
								 }else{
									DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
									resp.getWriter().println("id : "+cpt_add);
									resp.getWriter().println("name : "+name);
									resp.getWriter().println("name_director : "+name_director);
									resp.getWriter().println("date : "+date);
									resp.getWriter().println("country : "+country);
									resp.getWriter().println("lat : "+lat);
									resp.getWriter().println("longi : "+longi);
									resp.getWriter().println("id_wiki : "+id_wiki);
									resp.getWriter().println("id_wiki_director : "+"d"+id_wiki_director);
									Entity e;
									if(lat!="NAN"||longi!="NAN"){
										cpt_add++;
										e = new Entity("movies",cpt_add);
										e.setProperty("wiki_movie",id_wiki);
										e.setProperty("name", name);
										e.setProperty("name_director", name_director);
										e.setProperty("date", date);
										e.setProperty("country", country);
										e.setProperty("lat", lat);
										e.setProperty("longi", longi);
										e.setProperty("id_wiki_director", "d"+id_wiki_director);
				     				    datastore = DatastoreServiceFactory.getDatastoreService();
										datastore.put(e);
										 resp.getWriter().println("date max = "+date_upt);
										resp.getWriter().println("Add Data succesfull");
										resp.getWriter().println("-----------------------------------------------------");
									}
								}
							 }
						 }
					 }
				 }
					System.out.println("date max = "+date_upt);
					 if(cpt_add>count_movies&&this.update_movies&&resp!=null){
						 try{	
							DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
							Entity e;
							e = new Entity("count_movies", 1);
							e.setProperty("valeur",cpt_add);
							datastore.put(e);
						 } catch (Exception e){
							 try{
								 resp.getWriter().println("Probl�me survenue lors de la modification de count movie !");

							 } catch (Exception ex){
							 }
						 }
					 }
			 }else{

				 obj = parser.parse(new FileReader("donnees_directors.json"));

				 JSONArray jsonarray;
				
				 JSONObject jsonObject = (JSONObject) obj;
				
				 jsonObject = (JSONObject) jsonObject.get("results");
				
				 jsonarray = (JSONArray) jsonObject.get("bindings");

								 
				 for (Object director : jsonarray){
					 JSONObject directorobj = (JSONObject) director;
					 JSONObject directorobjname = (JSONObject) directorobj.get("name_director");
					 String name_director = (String) directorobjname.get("value");
					 
					 directorobjname = (JSONObject) directorobj.get("id_wiki");
					 String id_wiki = (String) directorobjname.get("value");
					 
					 count++;
					 if(!list_id_wiki.contains(id_wiki)){
						 list_id_wiki.add(id_wiki);
						 if(cpt_add<=800){
							 cpt_add++;
							 if(resp==null){
								 System.out.println("name_director : "+name_director);
								 System.out.println("-----------------------------------------------------");
							 }else{
								DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
								resp.getWriter().println("name_director : "+name_director);
								Entity e;
								e = new Entity("directors", cpt_add );
								e.setProperty("wiki_director", "d"+id_wiki);
								e.setProperty("name_director", name_director);
		     				    datastore = DatastoreServiceFactory.getDatastoreService();
								datastore.put(e);
								resp.getWriter().println("Add Data succesfull");
								resp.getWriter().println("-----------------------------------------------------");
							 }
						 }
					 }
				 }
			 }

			 if(resp!=null&&this.update_movies){
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				 Entity e;
				 resp.getWriter().println("date max = "+date_upt);
				 e = new Entity("date_update_movies", 1);
				 e.setProperty("valeur", date_upt);
                 datastore = DatastoreServiceFactory.getDatastoreService();
				 datastore.put(e);
			 } 
			 this.date_update=date_upt;

		 } catch (Exception e) {			 
			 if(resp!=null)
				 try{
					 resp.getWriter().println("Probl�me survenue lors de l'ajout de donn�es !");
				 } catch (Exception ex){
					 ex.printStackTrace();
				 }
			 e.printStackTrace();
		 }

		return cpt_add;

	}
	
}
