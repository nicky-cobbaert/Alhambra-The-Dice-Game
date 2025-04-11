package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GebruikerKiesSchermController extends GridPane {

	private final DomeinController dc;
	private char taal;
	private MainMenuScherm mms;

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

	public GebruikerKiesSchermController(DomeinController dc, char taal, MainMenuScherm mainMenu) {
		this.dc = dc;
		loadFxmlScreen("RegistreerSpelerScherm.fxml");
		setTaal(taal);
		this.mms = mainMenu;

	}

	private void setTaal(char taal) {
		this.taal = taal;
	}

	@FXML
	private ListView<?> lijstSpelers;

	@FXML
	private Button selecteerSpelerKnop;

	@FXML
	private Button terugKnop;

	@FXML
	private TextField zoekBalk;

	@FXML
	private Button zoekKnop;

	@FXML
	void zoekKnopOnAction(ActionEvent event) {
		try  {
			if (zoekBalk.getText() == null || zoekBalk.getText().isBlank() ) {
				throw new IllegalArgumentException("Dit is geen geldige naam!");
			}
			
			String gezochteNaam = zoekBalk.getText();
			
			
			
		}catch (IllegalArgumentException e) {
			zoekBalk.setText(e.getMessage());
		}
	}
	@FXML
	void selecteerSpelerKnopOnAction(ActionEvent event) {

	}

	@FXML
	void terugKnopOnAction(ActionEvent event) {
		mms.terugNaarMain(taal);
	}


}
