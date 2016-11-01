package pablo;

public class main {

	public static void main(String[] args) {

//		update_data_json update_data = new update_data_json(null,true);
//		int num_data = update_data.go_update(null);
//		System.out.println("ajout de "+(num_data-1)+" données.");
//		System.out.println(update_data.getDate_update());

		update upd = new update();
	//////////////////////////////////////////////////////////////////////////////////////////
	//upd.step1(resp);
	//upd.step1bis(resp);
	//////////////////////////////////////////////////////////////////////////////////////////
	//upd.step2(resp);
	//////////////////////////////////////////////////////////////////////////////////////////
	//mettre à faux pour ajouter des directors mais attention pour l'ajout des directors il ne faut pas actualiser la page 
	//sinon ils sont ajoutés en double !!!
		upd.step3(null,false);

	}

}
