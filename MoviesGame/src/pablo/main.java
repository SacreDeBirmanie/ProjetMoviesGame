package pablo;

import java.util.GregorianCalendar;
import java.util.Random;

public class main {

	public static void main(String[] args) {

//		update_data_json update_data = new update_data_json(null,true);
//		int num_data = update_data.go_update(null);
//		System.out.println("ajout de "+(num_data-1)+" donn�es.");
//		System.out.println(update_data.getDate_update());

//		question_reponses ques_rep = new question_reponses(null);
		generate_list_questions list_questions_reponses = new generate_list_questions(null);
		list_questions_reponses.afficher_tout(null);

	}

}
