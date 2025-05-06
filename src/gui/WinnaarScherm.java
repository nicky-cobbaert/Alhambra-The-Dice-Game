package gui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import domein.DomeinController;
import dto.SpelerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import utils.SpelerKleur;

public class WinnaarScherm extends BorderPane {

	private char taal;
	private final DomeinController dc;
	private ResourceBundle bundle;
	
	private void loadFxmlScreen(String name) {
		Locale locale = (taal == 'E') ? Locale.ENGLISH : new Locale("");
		//ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		this.bundle = ResourceBundle.getBundle("messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(name), bundle);
		
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public WinnaarScherm(DomeinController dc, char taal) {
		this.dc = dc;
		setTaal(taal);
		loadFxmlScreen("WinnaarScherm.fxml");

		Image spelbordPNG = new Image(getClass().getResource("/images/WinnaarScherm.png").toExternalForm(), true);
		
		BackgroundSize backgroundSize = new BackgroundSize(
				100, 100, true, true, true, false
	    );

	    BackgroundImage spelbord = new BackgroundImage(
	    			spelbordPNG,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundPosition.CENTER,
	                backgroundSize
	    );
	    
	    this.setBackground(new Background(spelbord));
		
		// labels aanvullen volgens punten-----------------------
	    
	    List<SpelerDTO> gesorteerd = dc.geefGekozenSpelers().stream().sorted(Comparator.comparing(SpelerDTO::punten)).collect(Collectors.toList()).reversed();

		// overzichtSpelersKleur is de plaats van de speler in de speler zijn bijhorende
		// kleur
//		Label[] overzichtSpelersKleur = { overzichtSpelerKleur1, overzichtSpelerKleur2, overzichtSpelerKleur3,
//				overzichtSpelerKleur4, overzichtSpelerKleur5, overzichtSpelerKleur6 };

		// speler label zijn tekstje aanmaken------------------------------

		String winnaars = "";

		
		//if statement kijkt of er meerdere winaars zijn
		if (dc.geefWinnaars().size() == 1) {
			winnaars = dc.geefWinnaars().get(0).gebruikersnaam();

		} else {

			//als er meerdere zijn itereert de iterator over alle winnaars
			Iterator<SpelerDTO> it = new ArrayList<SpelerDTO>().iterator();

			while (it.hasNext()) {
				winnaars += String.format("%s en ", it.next().gebruikersnaam());
			}

			winnaars.trim(); // haalt de laatste spatie weg

		}

		// einde speler label zijn tekstje aanmaken------------------------------

		gefeliciteerdLabel.setText(String.format("Gefeliciteerd, %s!", winnaars));
		
		// stream om te overlopen, te sorteren op basis van punten en elke speler zijn
		// plaatsLabel aanpast (zie hieronder)
		
			//Speler1
			positie1.setText("1.");
			geb1.setText(gesorteerd.get(0).gebruikersnaam());
			punt1.setText(String.format("%d", gesorteerd.get(0).punten()));
			gew1.setText(String.format("%d", (dc.geefWinnaars().contains(gesorteerd.get(0)) ? gesorteerd.get(0).aantalGewonnen()+1:gesorteerd.get(0).aantalGewonnen())));
			ges1.setText(String.format("%d", gesorteerd.get(0).aantalGespeeld()));
			//Speler2
			positie2.setText("2.");
			geb2.setText(gesorteerd.get(1).gebruikersnaam());
			punt2.setText(String.format("%d", gesorteerd.get(1).punten()));
			gew2.setText(String.format("%d", (dc.geefWinnaars().contains(gesorteerd.get(1)) ? gesorteerd.get(1).aantalGewonnen()+1:gesorteerd.get(1).aantalGewonnen())));
			ges2.setText(String.format("%d", gesorteerd.get(1).aantalGespeeld()));
			//Speler3
			positie3.setText("3.");
			geb3.setText(gesorteerd.get(2).gebruikersnaam());
			punt3.setText(String.format("%d", gesorteerd.get(2).punten()));
			gew3.setText(String.format("%d", (dc.geefWinnaars().contains(gesorteerd.get(2)) ? gesorteerd.get(2).aantalGewonnen()+1:gesorteerd.get(2).aantalGewonnen())));
			ges3.setText(String.format("%d", gesorteerd.get(2).aantalGespeeld()));
			
		if (dc.geefGekozenSpelers().size()>3) {
			//Speler4
			positie4.setText("4.");
			geb4.setText(gesorteerd.get(3).gebruikersnaam());
			punt4.setText(String.format("%d", gesorteerd.get(3).punten()));
			gew4.setText(String.format("%d", (dc.geefWinnaars().contains(gesorteerd.get(3)) ? gesorteerd.get(3).aantalGewonnen()+1:gesorteerd.get(3).aantalGewonnen())));
			ges4.setText(String.format("%d", gesorteerd.get(3).aantalGespeeld()));
		}
	if (dc.geefGekozenSpelers().size()>4) {
			//Speler5
			positie5.setText("5.");
			geb5.setText(gesorteerd.get(4).gebruikersnaam());
			punt5.setText(String.format("%d", gesorteerd.get(4).punten()));
			gew5.setText(String.format("%d", (dc.geefWinnaars().contains(gesorteerd.get(4)) ? gesorteerd.get(4).aantalGewonnen()+1:gesorteerd.get(4).aantalGewonnen())));
			ges5.setText(String.format("%d", gesorteerd.get(4).aantalGespeeld()));
		}
	if (dc.geefGekozenSpelers().size()>5) {
			//Speler6
			positie6.setText("6.");
			geb6.setText(gesorteerd.get(5).gebruikersnaam());
			punt6.setText(String.format("%d", gesorteerd.get(5).punten()));
			gew6.setText(String.format("%d", (dc.geefWinnaars().contains(gesorteerd.get(5)) ? gesorteerd.get(5).aantalGewonnen()+1:gesorteerd.get(5).aantalGewonnen())));
			ges6.setText(String.format("%d", gesorteerd.get(5).aantalGespeeld()));
		}
	}

	private String kleurKiezer(SpelerKleur kleur) {
		switch (kleur) {
		case BLAUW:
			return "-fx-background-color: #2980B9;	-fx-background-color: linear-gradient(to bottom, #FFFFFF, #6DD5FA, #2980B9);";
		case GEEL:
			return "-fx-background-color: #fffc00; -fx-background-color: linear-gradient(to bottom, #ffffff, #fffc00);";
		case GROEN:
			return "-fx-background-color: #11998e;  -fx-background-color: linear-gradient(to bottom, #38ef7d, #11998e);";
		case ORANJE:
			return "-fx-background-color: #f12711; -fx-background-color: linear-gradient(to bottom, #f5af19, #f12711);";
		case ROOD:
			return "-fx-background-color: #D31027; -fx-background-color: linear-gradient(to bottom, #EA384D, #D31027);";
		case WIT:
			return "-fx-background-color: #8e9eab; -fx-background-color: linear-gradient(to bottom, #eef2f3, #8e9eab);";
		}
		return "black";

	}

	private void setTaal(char taal) {
		this.taal=taal;
	}

	@FXML
	private Button afsluitButton;
		
	@FXML
	private Button bedanktKnop;
	
	 	@FXML
	    private Label geb1;

	    @FXML
	    private Label geb2;

	    @FXML
	    private Label geb3;

	    @FXML
	    private Label geb4;

	    @FXML
	    private Label geb5;

	    @FXML
	    private Label geb6;

	    @FXML
	    private Label gefeliciteerdLabel;

	    @FXML
	    private Label ges1;

	    @FXML
	    private Label ges2;

	    @FXML
	    private Label ges3;

	    @FXML
	    private Label ges4;

	    @FXML
	    private Label ges5;

	    @FXML
	    private Label ges6;

	    @FXML
	    private Label gew1;

	    @FXML
	    private Label gew2;

	    @FXML
	    private Label gew3;

	    @FXML
	    private Label gew4;

	    @FXML
	    private Label gew5;

	    @FXML
	    private Label gew6;

	    @FXML
	    private Label positie1;

	    @FXML
	    private Label positie2;

	    @FXML
	    private Label positie3;

	    @FXML
	    private Label positie4;

	    @FXML
	    private Label positie5;

	    @FXML
	    private Label positie6;

	    @FXML
	    private Label punt1;

	    @FXML
	    private Label punt2;

	    @FXML
	    private Label punt3;

	    @FXML
	    private Label punt4;

	    @FXML
	    private Label punt5;

	    @FXML
	    private Label punt6;

	@FXML
	void afsluitKlik(ActionEvent event) {
		// platform.exit vraagt om de javafx programmas rustig af te sluiten
		// system.exit forceert alle jvm processen om te stoppen
//		Platform.exit();
//		System.exit(0);
		
		MainMenuScherm mms = new MainMenuScherm(dc, taal);
		this.setCenter(mms);
	}

	@FXML
	 void bedanktKlik(ActionEvent event) {
		Alert bedanktAlert = new Alert(AlertType.INFORMATION);
		bedanktAlert.setTitle(bundle.getString("bedankt.title"));
		bedanktAlert.setHeaderText(bundle.getString("bedankt.header"));
		bedanktAlert.setContentText(bundle.getString("bedankt.content"));
		bedanktAlert.showAndWait();
	}


}
