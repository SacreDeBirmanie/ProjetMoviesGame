package pablo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;


public class question_reponses {
	private String id_wiki_film;
	
	private String name_film;
	
	private Date reponse_quand_1;
	private Date reponse_quand_2;
	private Date reponse_quand_3;
	private Date reponse_quand_vrai;
	
	private String reponse_qui_1;
	private String reponse_qui_2;
	private String reponse_qui_3;
	private String reponse_qui_vrai;
	
	private String longitude;
	private String latitude;
	

	public question_reponses(HttpServletResponse resp,int valeur_random_film, int count_directors) {
		try{
			String name_film="";
			Date date_movie;
			String productor_movie="";
			String productor_movie_false_1="";
			String productor_movie_false_2="";
			String longitude_true="";
			String latitude_true="";
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(resp!=null){		
				DatastoreService datastore;
				datastore = DatastoreServiceFactory.getDatastoreService();
				Random r = new Random();


				Key cle_movie;
				cle_movie = KeyFactory.createKey("movies",valeur_random_film);
				Entity movie_trouve = datastore.get(cle_movie);
				
				name_film= (String) movie_trouve.getProperty("name");
				String datetransition = (String) movie_trouve.getProperty("date");
				date_movie = format.parse(datetransition);
				productor_movie= (String) movie_trouve.getProperty("name_director");


				int nb_director=0;
				String id_director = "d"+(String) movie_trouve.getProperty("id_wiki_director");
				Entity director_trouve;	
				Key cle_director;
				int premier_random = -1;
				r = new Random();
				int valeur_random;
				while(nb_director<2){
					valeur_random = 1 + r.nextInt(count_directors - 1);					
					cle_director = KeyFactory.createKey("directors",valeur_random);
					director_trouve = datastore.get(cle_director);

					if((String) director_trouve.getProperty("nom")!=id_director){
						if(premier_random==(-1)||premier_random!=valeur_random){
							premier_random=valeur_random;
							if(nb_director==0){
								productor_movie_false_1=(String) director_trouve.getProperty("name_director");
							}else{
								productor_movie_false_2=(String) director_trouve.getProperty("name_director");
							}
							++nb_director;
						}
					}
				}
				longitude_true = (String) movie_trouve.getProperty("longi");
				latitude_true = (String) movie_trouve.getProperty("lat");
			}else{
				name_film="Blablabla";
				date_movie = format.parse("1989-01-01");
				productor_movie="truc";
				productor_movie_false_1="bidule";
				productor_movie_false_2="chouette";
				longitude_true = "15";
				latitude_true = "-15";
			}
			
			this.name_film = name_film;

		     Calendar myCal = Calendar.getInstance(TimeZone.getDefault());
		     myCal.setTime(date_movie);
			 Random r = new Random();
			 int valeur_random = 1 + r.nextInt(100 - 1);
			 valeur_random = valeur_random%3;
			 
		     GregorianCalendar gc = new GregorianCalendar();
			 this.reponse_quand_vrai=date_movie;
			 int year1, dayOfYear1, year2, dayOfYear2;
			 
			 if(valeur_random==0){
				 valeur_random = 1 + r.nextInt(20);
				 year1= myCal.get(Calendar.YEAR) - r.nextInt(valeur_random)-1;
			     dayOfYear1 = 1 + r.nextInt(gc.getActualMaximum(Calendar.DAY_OF_YEAR));
			     
			     year2 = myCal.get(Calendar.YEAR) - r.nextInt(valeur_random)-1;
			     if(year2==year1){
			    	 year2=year2-valeur_random;
			     }
			     dayOfYear2 = 1 + r.nextInt(gc.getActualMaximum(Calendar.DAY_OF_YEAR));
			    
			 }else if(valeur_random==1){
				 valeur_random = 1 + r.nextInt(20);
			     year1 = myCal.get(Calendar.YEAR) - r.nextInt(valeur_random)- 1;
			     dayOfYear1 = 1 + r.nextInt(gc.getActualMaximum(Calendar.DAY_OF_YEAR));
			     
			     year2 = myCal.get(Calendar.YEAR) + r.nextInt(valeur_random) + 1 ;
			     if(year2==year1){
			    	 year2=year2+valeur_random;
			     }
			     dayOfYear2 = 1 + r.nextInt(gc.getActualMaximum(Calendar.DAY_OF_YEAR));
			     
			 }else{
				 valeur_random = 1 + r.nextInt(20);

			     year1 = myCal.get(Calendar.YEAR) + r.nextInt(valeur_random) + 1 ;
			     dayOfYear1 = 1 + r.nextInt(gc.getActualMaximum(Calendar.DAY_OF_YEAR));
			     
			     year2 = myCal.get(Calendar.YEAR) + r.nextInt(valeur_random) + 1;
			     if(year2==year1){
			    	 year2=year2+valeur_random;
			     }
			     dayOfYear2 = 1 + r.nextInt(gc.getActualMaximum(Calendar.DAY_OF_YEAR));
			    

			 }

		     gc.set(Calendar.YEAR, year1);
	    	 gc.set(Calendar.DAY_OF_YEAR, dayOfYear1);
		     Date date1= format.parse(gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH));

		     gc.set(Calendar.YEAR, year2);
	    	 gc.set(Calendar.DAY_OF_YEAR, dayOfYear2);
		     Date date2= format.parse(gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH));

				
		     if(date1.before(date2)){
		    	 if(date1.before(date_movie)){
			    	 this.reponse_quand_1 = date1;
			    	 if(date2.before(date_movie)){
				    	 this.reponse_quand_2 = date2;
				    	 this.reponse_quand_3 = date_movie;
			    	 }else{
				    	 this.reponse_quand_2 = date_movie;
				    	 this.reponse_quand_3 = date2;
			    	 }
		    	 }else{
			    	this.reponse_quand_1 = date_movie;
				    this.reponse_quand_2 = date1;
				    this.reponse_quand_3 = date2;
		    	 }
		    }else{
		    	 if(date2.before(date_movie)){
			    	 this.reponse_quand_1 = date2;
			    	 if(date1.before(date_movie)){
				    	 this.reponse_quand_2 = date1;
				    	 this.reponse_quand_3 = date_movie;
			    	 }else{
				    	 this.reponse_quand_2 = date_movie;
				    	 this.reponse_quand_3 = date1;
			    	 }
		    	 }else{
			    	this.reponse_quand_1 = date_movie;
				    this.reponse_quand_2 = date2;
				    this.reponse_quand_3 = date1;
		    	 }
		     }
		     			
			valeur_random = 1 + r.nextInt(100 - 1);

			valeur_random = valeur_random%3;
			this.reponse_qui_vrai=productor_movie;
			if(valeur_random == 0){
				this.reponse_qui_1 = productor_movie_false_1;
				this.reponse_qui_2 = productor_movie_false_2;
				this.reponse_qui_3 = productor_movie;
			}else if(valeur_random == 1){
				this.reponse_qui_1 = productor_movie_false_1;
				this.reponse_qui_2 = productor_movie;
				this.reponse_qui_3 = productor_movie_false_2;
			}else{
				this.reponse_qui_1 = productor_movie;
				this.reponse_qui_2 = productor_movie_false_1;
				this.reponse_qui_3 = productor_movie_false_2;				
			}
			
			this.longitude=longitude_true;
			this.latitude=latitude_true;
			
		} catch (Exception e) {
			 if(resp!=null){
				 try{
					resp.getWriter().println("erreur survenur lors de la génération de question/reponse");
					resp.getWriter().println(e.getMessage());

				 } catch(Exception ex){}
			}else{
				System.out.println("erreur survenur lors de la génération de question/reponse");
			}
		 }
	}


	public String getId_wiki_film() {
		return id_wiki_film;
	}


	public void setId_wiki_film(String id_wiki_film) {
		this.id_wiki_film = id_wiki_film;
	}


	public String getName_film() {
		return name_film;
	}


	public void setName_film(String name_film) {
		this.name_film = name_film;
	}


	public Date getReponse_quand_1() {
		return reponse_quand_1;
	}


	public void setReponse_quand_1(Date reponse_quand_1) {
		this.reponse_quand_1 = reponse_quand_1;
	}


	public Date getReponse_quand_2() {
		return reponse_quand_2;
	}


	public void setReponse_quand_2(Date reponse_quand_2) {
		this.reponse_quand_2 = reponse_quand_2;
	}


	public Date getReponse_quand_3() {
		return reponse_quand_3;
	}


	public void setReponse_quand_3(Date reponse_quand_3) {
		this.reponse_quand_3 = reponse_quand_3;
	}


	public Date getReponse_quand_vrai() {
		return reponse_quand_vrai;
	}


	public void setReponse_quand_vrai(Date reponse_quand_vrai) {
		this.reponse_quand_vrai = reponse_quand_vrai;
	}


	public String getReponse_qui_1() {
		return reponse_qui_1;
	}


	public void setReponse_qui_1(String reponse_qui_1) {
		this.reponse_qui_1 = reponse_qui_1;
	}


	public String getReponse_qui_2() {
		return reponse_qui_2;
	}


	public void setReponse_qui_2(String reponse_qui_2) {
		this.reponse_qui_2 = reponse_qui_2;
	}


	public String getReponse_qui_3() {
		return reponse_qui_3;
	}


	public void setReponse_qui_3(String reponse_qui_3) {
		this.reponse_qui_3 = reponse_qui_3;
	}


	public String getReponse_qui_vrai() {
		return reponse_qui_vrai;
	}


	public void setReponse_qui_vrai(String reponse_qui_vrai) {
		this.reponse_qui_vrai = reponse_qui_vrai;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
