package pablo;

public class main {

	public static void main(String[] args) {

		update_data_json update_data = new update_data_json(null,true);
		int num_data = update_data.go_update(null);
		System.out.println("ajout de "+(num_data-1)+" données.");
		System.out.println(update_data.getDate_update());
		
	}

}
