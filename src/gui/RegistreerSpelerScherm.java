package gui;

import java.io.IOException;

import domein.DomeinController;
import exceptions.GebruikersnaamInGebruikException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class RegistreerSpelerScherm extends BorderPane{
	
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
	
	public RegistreerSpelerScherm(DomeinController dc, char taal, MainMenuScherm mainMenu) {
		this.dc = dc;
		loadFxmlScreen("RegistreerSpelerScherm.fxml");
		setTaal(taal);
		this.mms = mainMenu;
		
	}
	
	private void setTaal(char taal) {
		this.taal = taal;
	}
	
	@FXML
    private Button gaTerugKnop;

    @FXML
    private TextField geboortejaarIngeven;

    @FXML
    private TextField gebruikersnaamIngeven;

    @FXML
    private Button registreerKnop;

    @FXML
    void gaTerugKnopKlik(ActionEvent event) {
    	mms.terugNaarMain(taal);
    }

    @FXML
    void registreerKnopKlik(ActionEvent event) {
    	try {
    		
    		String gebruikersnaam = gebruikersnaamIngeven.getText();
    		int geboortejaar = Integer.parseInt(geboortejaarIngeven.getText());
    		
    		dc.registreerSpeler(gebruikersnaam, geboortejaar);
    		
    		Alert alertGelukt = new Alert(AlertType.INFORMATION);
    		alertGelukt.setHeaderText("Gebruiker succesvol geregistreerd!");
    		alertGelukt.setContentText(String.format("%s met geboortejaar %d is succesvol toegevoegd!", gebruikersnaam, geboortejaar));
    		alertGelukt.showAndWait();
    		
    	} catch (NumberFormatException e) {
			toonFoutMelding("Geef een geldig geboortejaar in!");
		} catch (IllegalArgumentException e) {
			toonFoutMelding(e.getMessage());
		} catch (GebruikersnaamInGebruikException e) {
			toonFoutMelding(e.getMessage());
		}
    }
    
    private void toonFoutMelding(String melding) {
    	Alert alertFout = new Alert(AlertType.ERROR);
    	alertFout.setHeaderText("Gebruiker kan niet geregistreerd worden!");
		alertFout.setContentText(melding);
		alertFout.showAndWait();
    }
	
}
