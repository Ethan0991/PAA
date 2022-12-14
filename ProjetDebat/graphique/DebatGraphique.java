package ProjetDebat.graphique;

import ProjetDebat.debat.Debat;
import javafx.application.Application;


public class DebatGraphique{

	
	public static void launchGraphique (String [] args,Debat debat) {
		
		ControllerDebat controller = new ControllerDebat();
		controller.setDataDebat(debat);
		MainStage mainStage = new MainStage();
		mainStage.setController(controller);

		Application.launch(MainStage.class, (java.lang.String[]) null);

		
		}
	
}
