package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class WelkomTaalScherm extends GridPane{
	
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
	
	private final DomeinController dc;

	public WelkomTaalScherm(DomeinController dc) {
		this.dc = dc;
		loadFxmlScreen("WelkomTaalScherm.fxml");
	}
	

    @FXML
    private Button KnopENG;

    @FXML
    private Button KnopNL;

    @FXML
    void KnopENGklik(ActionEvent event) {

    }

    @FXML
    void KnopNLklik(ActionEvent event) {

    }
	
}
