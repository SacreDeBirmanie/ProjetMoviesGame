package pablo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

public class generate_list_questions {
	
	 ArrayList<question_reponses> list_questions_reponses = new ArrayList();

	public generate_list_questions(HttpServletResponse resp) {
		for(int i =0; i<10; ++i){
			this.list_questions_reponses.add(new question_reponses(resp));
		}
	}
	
	public void afficher_tout(HttpServletResponse resp){
		try{
			for(int i=0;i<10;++i){
				if(resp!=null){
				question_reponses ques_resp = list_questions_reponses.get(i);
				resp.getWriter().println(" Quand est sortit le film "+ques_resp.getName_film()+" ? ");
				resp.getWriter().println(" R�ponse 1 : "+ques_resp.getReponse_quand_1());
				resp.getWriter().println(" R�ponse 2 : "+ques_resp.getReponse_quand_2());
				resp.getWriter().println(" R�ponse 3 "+ques_resp.getReponse_quand_3());

				resp.getWriter().println(" Qui a produit le film "+ques_resp.getName_film()+" ? ");
				resp.getWriter().println(" R�ponse 1 : "+ques_resp.getReponse_qui_1());
				resp.getWriter().println(" R�ponse 2 : "+ques_resp.getReponse_qui_2());
				resp.getWriter().println(" R�ponse 3 "+ques_resp.getReponse_qui_3());
				
				resp.getWriter().println(" Ou a �t� produit le film "+ques_resp.getName_film()+" ? ");

				resp.getWriter().println(" ------------------------------------------------------------ ");
				resp.getWriter().println(" --------------------------R�ponses-------------------------- ");
				resp.getWriter().println(" ------------------------------------------------------------ ");
				resp.getWriter().println(" Le film "+'"'+ques_resp.getName_film()+'"'+" est sortit en "+ques_resp.getReponse_quand_vrai());
				resp.getWriter().println(ques_resp.getReponse_qui_vrai()+" a produit le film "+'"'+ques_resp.getName_film()+'"');
				resp.getWriter().println("Le film "+'"'+ques_resp.getName_film()+'"'+" a �t� produit en longitude : "+ques_resp.getLongitude() + " et en latitude : "+ques_resp.getLatitude()+".");

				resp.getWriter().println(" ------------------------------------------------------------ ");
				resp.getWriter().println(" ------------------------------------------------------------ ");
				
			}else{
				question_reponses ques_resp = list_questions_reponses.get(i);
				System.out.println(" Quand est sortit le film "+ques_resp.getName_film()+" ? ");
				System.out.println(" R�ponse 1 : "+ques_resp.getReponse_quand_1());
				System.out.println(" R�ponse 2 : "+ques_resp.getReponse_quand_2());
				System.out.println(" R�ponse 3 "+ques_resp.getReponse_quand_3());
	
				System.out.println(" Qui a produit le film "+ques_resp.getName_film()+" ? ");
				System.out.println(" R�ponse 1 : "+ques_resp.getReponse_qui_1());
				System.out.println(" R�ponse 2 : "+ques_resp.getReponse_qui_2());
				System.out.println(" R�ponse 3 "+ques_resp.getReponse_qui_3());
				
				System.out.println(" Ou a �t� produit le film "+ques_resp.getName_film()+" ? ");
	
				System.out.println(" ------------------------------------------------------------ ");
				System.out.println(" --------------------------R�ponses-------------------------- ");
				System.out.println(" ------------------------------------------------------------ ");
				System.out.println(" Le film "+'"'+ques_resp.getName_film()+'"'+" est sortit en "+ques_resp.getReponse_quand_vrai());
				System.out.println(ques_resp.getReponse_qui_vrai()+" a produit le film "+'"'+ques_resp.getName_film()+'"');
				System.out.println("Le film "+'"'+ques_resp.getName_film()+'"'+" a �t� produit en longitude : "+ques_resp.getLongitude() + " et en latitude : "+ques_resp.getLatitude()+".");
	
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

}
