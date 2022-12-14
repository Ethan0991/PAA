package ProjetDebat.graphique;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertError  {

	private Alert alert;
	
	public AlertError(String title, Exception error) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText("Erreur sauvegarde fichier.");
		alert.setContentText(error.getMessage());
	}

	public void showAlert() {
		alert.showAndWait();
	}


}

