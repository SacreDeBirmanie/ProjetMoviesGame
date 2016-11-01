package pablo;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.Date;

public class generate_list_questions {
	
	 ArrayList<question_reponses> list_questions_reponses = new ArrayList<question_reponses>();

	public generate_list_questions(HttpServletResponse resp) {
		try{
		ArrayList<Integer> list_random = new ArrayList<Integer>();
		Random r = new Random();
		DatastoreService datastore;
		datastore = DatastoreServiceFactory.getDatastoreService();
		Key cle_count_movies = KeyFactory.createKey("count_movies", 1);
		Entity count_trouve = datastore.get(cle_count_movies );
		long count_movies_long = (long) count_trouve.getProperty("valeur");
		int count_movies = (int) count_movies_long;	
		
		int valeur_random = -1;
		
		Key cle_count_producteurs = KeyFactory.createKey("count_directors", 1);
		count_trouve = datastore.get(cle_count_producteurs );
		long count_directors_long = (long) count_trouve.getProperty("valeur");
		int count_directors = (int) count_directors_long;
		
		while(list_random.size()<10){
			valeur_random = 0 + r.nextInt(count_movies - 1);
			if(!list_random.contains(valeur_random)){
				list_random.add(valeur_random);
			}
		}

		
		for(int i =0; i<10; ++i){
			this.list_questions_reponses.add(new question_reponses(resp,list_random.get(i),count_directors));
		}
		} catch(Exception e){ 
			try{
				resp.getWriter().println(" Erreur generation questions ! ");
			} catch(Exception ex){}
		}
	}
	
	public void afficher_tout(HttpServletResponse resp){
		try{
			for(int i=0;i<this.list_questions_reponses.size();++i){
				if(resp!=null){
				question_reponses ques_resp = list_questions_reponses.get(i);
				resp.getWriter().println(" Quand est sortit le film "+ques_resp.getName_film()+" ? ");
				resp.getWriter().println(" Réponse 1 : "+ques_resp.getReponse_quand_1());
				resp.getWriter().println(" Réponse 2 : "+ques_resp.getReponse_quand_2());
				resp.getWriter().println(" Réponse 3 "+ques_resp.getReponse_quand_3());

				resp.getWriter().println(" Qui a produit le film "+ques_resp.getName_film()+" ? ");
				resp.getWriter().println(" Réponse 1 : "+ques_resp.getReponse_qui_1());
				resp.getWriter().println(" Réponse 2 : "+ques_resp.getReponse_qui_2());
				resp.getWriter().println(" Réponse 3 "+ques_resp.getReponse_qui_3());
				
				resp.getWriter().println(" Ou a été produit le film "+ques_resp.getName_film()+" ? ");

				resp.getWriter().println(" ------------------------------------------------------------ ");
				resp.getWriter().println(" --------------------------Réponses-------------------------- ");
				resp.getWriter().println(" ------------------------------------------------------------ ");
				resp.getWriter().println(" Le film "+'"'+ques_resp.getName_film()+'"'+" est sortit en "+ques_resp.getReponse_quand_vrai());
				resp.getWriter().println(ques_resp.getReponse_qui_vrai()+" a produit le film "+'"'+ques_resp.getName_film()+'"');
				resp.getWriter().println("Le film "+'"'+ques_resp.getName_film()+'"'+" a été produit en longitude : "+ques_resp.getLongitude() + " et en latitude : "+ques_resp.getLatitude()+".");

				resp.getWriter().println(" ------------------------------------------------------------ ");
				resp.getWriter().println(" ------------------------------------------------------------ ");
				
			}else{
				question_reponses ques_resp = list_questions_reponses.get(i);
				System.out.println(" Quand est sortit le film "+ques_resp.getName_film()+" ? ");
				System.out.println(" Réponse 1 : "+ques_resp.getReponse_quand_1());
				System.out.println(" Réponse 2 : "+ques_resp.getReponse_quand_2());
				System.out.println(" Réponse 3 "+ques_resp.getReponse_quand_3());
	
				System.out.println(" Qui a produit le film "+ques_resp.getName_film()+" ? ");
				System.out.println(" Réponse 1 : "+ques_resp.getReponse_qui_1());
				System.out.println(" Réponse 2 : "+ques_resp.getReponse_qui_2());
				System.out.println(" Réponse 3 "+ques_resp.getReponse_qui_3());
				
				System.out.println(" Ou a été produit le film "+ques_resp.getName_film()+" ? ");
	
				System.out.println(" ------------------------------------------------------------ ");
				System.out.println(" --------------------------Réponses-------------------------- ");
				System.out.println(" ------------------------------------------------------------ ");
				System.out.println(" Le film "+'"'+ques_resp.getName_film()+'"'+" est sortit en "+ques_resp.getReponse_quand_vrai());
				System.out.println(ques_resp.getReponse_qui_vrai()+" a produit le film "+'"'+ques_resp.getName_film()+'"');
				System.out.println("Le film "+'"'+ques_resp.getName_film()+'"'+" a été produit en longitude : "+ques_resp.getLongitude() + " et en latitude : "+ques_resp.getLatitude()+".");
	
				System.out.println(" ------------------------------------------------------------ ");
				System.out.println(" ------------------------------------------------------------ ");
				}
			}
		} catch (Exception e){
			try{
				if(resp!=null){
					resp.getWriter().println("Erreur affichage questions/responses !");
				}else{
					System.out.println("Erreur affichage questions/responses !");
				}
			} catch (Exception ex){}
		}
	}
	
	public int getlongitude(int num_question){
		return  Integer.parseInt(list_questions_reponses.get(num_question).getLongitude());
	}
	public int getlatitude(int num_question){
		return  Integer.parseInt(list_questions_reponses.get(num_question).getLatitude());
	}
	public String getdirector(int num_question){
		return  list_questions_reponses.get(num_question).getReponse_qui_vrai();
	}
	public Date getdate(int num_question){
		return list_questions_reponses.get(num_question).getReponse_quand_vrai();
	}
	
	public ArrayList<String> getreponsesqui(int num_question){
		 ArrayList<String> list = new ArrayList<String>();
		 list.add(list_questions_reponses.get(num_question).getReponse_qui_1());
		 list.add(list_questions_reponses.get(num_question).getReponse_qui_2());
		 list.add(list_questions_reponses.get(num_question).getReponse_qui_3());
		 return list;
	}
	
	public ArrayList<Date> getreponsesquand(int num_question){
		 ArrayList<Date> list = new ArrayList<Date>();
		 list.add(list_questions_reponses.get(num_question).getReponse_quand_1());
		 list.add(list_questions_reponses.get(num_question).getReponse_quand_2());
		 list.add(list_questions_reponses.get(num_question).getReponse_quand_3());
		 return list;
	}
	

}
