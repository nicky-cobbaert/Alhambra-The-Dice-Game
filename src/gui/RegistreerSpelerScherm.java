package gui;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomeinController;
import exceptions.GebruikersnaamInGebruikException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

public class RegistreerSpelerScherm extends BorderPane{
	
	private final DomeinController dc;
	private char taal;
	private MainMenuScherm mms;

	private void loadFxmlScreen(String name) {
		//FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
		Locale locale = (taal == 'E') ? Locale.ENGLISH : new Locale("nl");
	    ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
	    FXMLLoader loader = new FXMLLoader(getClass().getResource(name), bundle);
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
		setTaal(taal);
		loadFxmlScreen("RegistreerSpelerScherm.fxml");
		
		this.mms = mainMenu;
		setZwembad();
		
	}
	
	private void setTaal(char taal) {
		this.taal = taal;
	}
	
	
	
	private void setZwembad() {
		Image kiesImage = new Image(getClass().getResource("/images/Registreer.png").toExternalForm());

		BackgroundSize size = new BackgroundSize(100, 100, true, true, true, false);

		BackgroundImage achtergrond = new BackgroundImage(kiesImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);

		this.setBackground(new Background(achtergrond));
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
    	this.setBackground(null);
    	mms.terugNaarMain(taal);
    }

    @FXML
    void registreerKnopKlik(ActionEvent event) {
    	try {
    		
    		String gebruikersnaam = gebruikersnaamIngeven.getText();
    		int geboortejaar = Integer.parseInt(geboortejaarIngeven.getText());
    		
    		dc.registreerSpeler(gebruikersnaam, geboortejaar);
    		
    		Alert alertGelukt = new Alert(AlertType.INFORMATION);
    		alertGelukt.setTitle("Registratie gelukt!");
    		alertGelukt.setHeaderText("Gebruiker succesvol geregistreerd!");
    		alertGelukt.setContentText(String.format("%s met geboortejaar %d is succesvol toegevoegd!", gebruikersnaam, geboortejaar));
    		alertGelukt.showAndWait();
    		
    		mms.terugNaarMain(taal);
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
    	alertFout.setTitle("Registratie mislukt!");
    	alertFout.setHeaderText("Gebruiker kan niet geregistreerd worden!");
		alertFout.setContentText(melding);
		alertFout.showAndWait();
    }
	
}
