package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class KiesDobbelsteenKleurScherm extends GridPane {

	private final DomeinController dc;

	@FXML
	private Button blauweKleurKnop;

	@FXML
	private Button bruineKleurKnop;

	@FXML
	private Button grijzeKleurKnop;

	@FXML
	private Button groeneKleurKnop;

	@FXML
	private Button paarseKleurKnop;

	@FXML
	private Button rodeKleurKnop;

	private KiesDobbelsteenKleurScherm(DomeinController dc, char taal) {
		this.dc = dc;
		loadFxmlScreen("KiesDobbelsteenKleurScherm.fxml");
	}

	private void loadFxmlScreen(String name) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@FXML
    void kiesBlauweKleur(ActionEvent event) {

		
		
    }

    @FXML
    void kiesBruineKleur(ActionEvent event) {

    }

    @FXML
    void kiesGrijzeKleur(ActionEvent event) {

    }

    @FXML
    void kiesGroeneKleur(ActionEvent event) {
    	
    }

    @FXML
    void kiesPaarseKleur(ActionEvent event) {

    }

    @FXML
    void kiesRodeKleur(ActionEvent event) {

    }


}
