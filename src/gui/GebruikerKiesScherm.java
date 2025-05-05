package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import utils.SpelerKleur;

public class GebruikerKiesScherm extends BorderPane {

	private final DomeinController dc;
	private char taal;
	private MainMenuScherm mms;
	private SpelerKleur voorlopigGekozenSpelerKleur;
	private int voorlopigGekozenSpelerNaam;
	private boolean spelerGezocht = false;
	private ResourceBundle bundle;

	@FXML
	private Label overeenkomstigeLettersLabel;

	@FXML
	private Button knopBlauw;

	@FXML
	private Button knopGeel;

	@FXML
	private Button knopGroen;

	@FXML
	private Button knopOranje;

	@FXML
	private Button knopRood;

	@FXML
	private Button knopWit;

	@FXML
	private ListView<String> lijstGekozenSpelers;

	@FXML
	private Button resetKnop;

	@FXML
	private Button verdergaanKnop;

	@FXML
	private ListView<String> lijstSpelers;

	@FXML
	private Button selecteerSpelerKnop;

	@FXML
	private Button terugKnop;

	@FXML
	private TextField zoekBalk;

	@FXML
	private Button zoekKnop;

	@FXML
	private Button verwijderSpelerKnop;

	@FXML
	private Button gekozenKleur0;

	@FXML
	private Button gekozenKleur1;

	@FXML
	private Button gekozenKleur2;

	@FXML
	private Button gekozenKleur3;

	@FXML
	private Button gekozenKleur4;

	@FXML
	private Button gekozenKleur5;

	@FXML
	void knopGeelOnAction(ActionEvent event) {
		if (knopGeel.isDisabled()) {

		} else {
			voorlopigGekozenSpelerKleur = SpelerKleur.GEEL;
			feedbackGekozenSpeler(knopGeel);
		}
	}

	@FXML
	void knopGroenOnAction(ActionEvent event) {
		if (knopGroen.isDisabled()) {
			
		} else {
			voorlopigGekozenSpelerKleur = SpelerKleur.GROEN;
			feedbackGekozenSpeler(knopGroen);
		}
	}

	@FXML
	void knopOranjeOnAction(ActionEvent event) {
		if (knopOranje.isDisabled()) {

		} else {
			voorlopigGekozenSpelerKleur = SpelerKleur.ORANJE;
			feedbackGekozenSpeler(knopOranje);
		}
	}

	@FXML
	void knopRoodOnAction(ActionEvent event) {
		if (knopRood.isDisabled()) {

		} else {
			voorlopigGekozenSpelerKleur = SpelerKleur.ROOD;
			feedbackGekozenSpeler(knopRood);
		}
	}

	@FXML
	void knopWitOnAction(ActionEvent event) {
		if (knopWit.isDisabled()) {

		} else {
			voorlopigGekozenSpelerKleur = SpelerKleur.WIT;
			feedbackGekozenSpeler(knopWit);
		}
	}

	private List<Button> gekozenKleurButtons;
	private List<Button> kleurButtons;

	private void loadFxmlScreen(String name) {
		//FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
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

	public GebruikerKiesScherm(DomeinController dc, char taal, MainMenuScherm mainMenu) {
		this.dc = dc;
		dc.maakNieuwSpel();
		setTaal(taal);
		loadFxmlScreen("GebruikerKiesScherm.fxml");
		
		this.mms = mainMenu;

		gekozenKleurButtons = new ArrayList<Button>(Arrays.asList(gekozenKleur0, gekozenKleur1, gekozenKleur2,
				gekozenKleur3, gekozenKleur4, gekozenKleur5));

		kleurButtons = new ArrayList<Button>(
				Arrays.asList(knopBlauw, knopGeel, knopGroen, knopOranje, knopRood, knopWit));

		resetKnop.fire();

		//Achtergrondfoto!
		Image kiesImage = new Image(getClass().getResource("/images/KiesSpelerSchermZon.png").toExternalForm());

		BackgroundSize size = new BackgroundSize(100, 100, true, true, true, false);

		BackgroundImage achtergrond = new BackgroundImage(kiesImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);

		this.setBackground(new Background(achtergrond));
	}

	private void setTaal(char taal) {
		this.taal = taal;
	}

	@FXML
	void zoekKnopOnAction(ActionEvent event) {
		try {
			if (zoekBalk.getText() == null || zoekBalk.getText().isBlank()) {
				throw new IllegalArgumentException(bundle.getString("error.foutenaam"));
			}

			String gezochteNaam = zoekBalk.getText();
			Map<String, Object> zoekResultaat = dc.zoekDezeSpeler(gezochteNaam);

			@SuppressWarnings("unchecked")
			List<String> goedeNamen = (List<String>) zoekResultaat.get("overeenkomstigeNamen");
			Integer aantalOvereenkomstigeLetter = (Integer) zoekResultaat.get("meesteLetters");

			lijstSpelers.setItems(FXCollections.observableArrayList(goedeNamen));

			overeenkomstigeLettersLabel
					.setText(String.format(bundle.getString("label.overeenkomstigeLetters"), aantalOvereenkomstigeLetter));

			if (goedeNamen.isEmpty()) {
				overeenkomstigeLettersLabel.setText(bundle.getString("label.geenovereenkomst")); 
			} else {
				spelerGezocht = true;
			}

			overeenkomstigeLettersLabel.setVisible(true);

		} catch (IllegalArgumentException e) {
			zoekBalk.setText(e.getMessage());
		}
	}

	@FXML
	void selecteerSpelerKnopOnAction(ActionEvent event) {

		try {
		if (!spelerGezocht) {
			// speler heeft niet gezocht, normale index werkt
			voorlopigGekozenSpelerNaam = lijstSpelers.getSelectionModel().getSelectedIndex();
		} else {
			// normale index zal een verkeerde speler geven, dus ander algoritme

			String gezochteSpeler = lijstSpelers.getSelectionModel().getSelectedItem();

			/*
			 * om de index in de lijstSpelers te vinden gebruik ik de indexOf-methode van
			 * een observablelist
			 */

			lijstAlleSpelersGenereren();
			ObservableList<String> alleSpelers = lijstSpelers.getItems();

			/*
			 * debugger System.out.println(gezochteSpeler);
			 * 
			 * for (String loper : alleSpelers) { System.out.println(loper); }
			 */

			voorlopigGekozenSpelerNaam = alleSpelers.indexOf(gezochteSpeler);
			// System.out.println(voorlopigGekozenSpelerNaam);
		}

		dc.kiesSpelerEnKleur(voorlopigGekozenSpelerNaam, voorlopigGekozenSpelerKleur);

		// de knop op plaats index krijgt een kleur die meegegeven wordt -> hier dus de
		// selecteerknop want die heeft de juiste kleur!
		int index = (dc.geefGekozenSpelers().size() - 1);

		gekozenKleurKnopKleurGever(gekozenKleurButtons.get(index), this.selecteerSpelerKnop);

		// de knop (kleur) die gekozen is zal disabled worden
		// :werkt door te kijken welke knop niet in de gekozenKleuren-lijst zit
		
		
//		for (Button kleurKnop : kleurButtons) {
//			if (!(dc.geefBeschikbareSpelerKleuren().contains(SpelerKleur.valueOf(kleurKnop.getText().toUpperCase())))) {
//				kleurKnop.setDisable(true);
//				kleurKnop.disarm();
//			}
//		}
		
		for (Button kleurKnop : kleurButtons) {
		    if (!(dc.geefBeschikbareSpelerKleuren().contains(geefSpelerKleurVanVertaling(kleurKnop.getText())))) {  // nieuwe code controleert of het engels is en zet om naar nederlands voor enum
		        kleurKnop.setDisable(true);
		        kleurKnop.disarm();
		    }
		}

		
		resetKnop.fire();
		
		}catch (IllegalArgumentException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(bundle.getString("error.fout"));
			alert.setHeaderText(bundle.getString("error.selecteerSpeler"));
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}catch (IndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(bundle.getString("error.fout"));
			alert.setHeaderText(bundle.getString("error.selecteerSpeler"));
			alert.setContentText(bundle.getString("error.geenSpeler"));
			alert.showAndWait();
		}
		
		lijstGekozenSpelers.setStyle("-fx-font-family: 'Algerian'; -fx-font-size: 13px;");
		
	}

	private void gekozenKleurKnopKleurGever(Button gekozenKleurKnop, Button kleurKnop) {
		gekozenKleurKnop.setStyle(kleurKnop.getStyle() + ";-fx-border-radius: 0px;"
				+ "-fx-border-color: transparent; -fx-border-width: 0px;" + "-fx-effect: null;");
		gekozenKleurKnop.setTextFill(kleurKnop.getTextFill());

	}

	@FXML
	void terugKnopOnAction(ActionEvent event) {
		mms.terugNaarMain(taal);
	}

	@FXML
	void knopBlauwOnAction(ActionEvent event) {
		voorlopigGekozenSpelerKleur = SpelerKleur.BLAUW;
		feedbackGekozenSpeler(knopBlauw);

	}

	private void feedbackGekozenSpeler(Button voorlopigeKleur) {

		selecteerSpelerKnop.setStyle(voorlopigeKleur.getStyle()
				+ "; -fx-border-color: TURQUOISE; -fx-border-width: 0.5px; -fx-border-radius: 2px; -fx-effect: dropshadow(gaussian, TURQUOISE, 10, 0.5, 0, 0);");
		selecteerSpelerKnop.setTextFill(voorlopigeKleur.getTextFill());

		//selecteerSpelerKnop.setText("Kies bovenstaande speler!");
		selecteerSpelerKnop.setText(bundle.getString("button.kies"));
		

	}

	@FXML
	void resetKnopOnAction(ActionEvent event) {

		// lijstje maken van alle spelers--------------------------------------

		lijstAlleSpelersGenereren();

		// einde lijstje maken--------------------------------------------------

		// lijstje maken van gekozen spelers-------------------------------------
		AtomicInteger indexGekozenSpelers = new AtomicInteger(1); // Een beforeEach kan geen integer van buitenaf
																	// gebruiken
		List<String> gekozenSpelers = new ArrayList<String>();
		dc.geefGekozenSpelers().forEach(speler -> {
			gekozenSpelers.add(String.format("%d. %s - %d:  %s", indexGekozenSpelers
					.getAndIncrement() /*
										 * ik gebruik hier deze methode om toch de index te kunnen verhogen
										 */
					, speler.gebruikersnaam(), speler.geboortejaar(), speler.kleur().toString().toLowerCase()));
		});

		lijstGekozenSpelers.setItems(FXCollections.observableArrayList(gekozenSpelers));

		// einde lijstje maken----------------------------------------------------

		// de kleur van de selecteerSpelerKnop terug normaal maken----------------
		// -> gebeurd aan de aan de hand van de terugknop zijn originele stijl

		selecteerSpelerKnop.setStyle(terugKnop.getStyle());
		selecteerSpelerKnop.setTextFill(terugKnop.getTextFill());
		selecteerSpelerKnop.setText(bundle.getString("button.kies1"));
		// einde knop stijl terugkeren---------------------------------------------

		// de resetknop disarmed de selecteerSpelerKnop omdat er anders meerdere keren
		// dezelfde kleur kan ingegeven worden
		this.selecteerSpelerKnop.disarm();

		overeenkomstigeLettersLabel.setVisible(false);
		
		voorlopigGekozenSpelerKleur = null;
		voorlopigGekozenSpelerNaam = -1;
		spelerGezocht = false;

	}

	private void lijstAlleSpelersGenereren() {
		// aparte methode omdat zowel de resetKnopOnAction en
		// selecteerSpelerKnopOnAction dezelfde code gebruiken

		AtomicInteger indexAlleSpelers = new AtomicInteger(1);

		List<String> alleSpelers = new ArrayList<String>();
		dc.geefBeschikbareSpelers().forEach(speler -> {
			alleSpelers.add(String.format(("%d. %s - %d"), indexAlleSpelers.getAndIncrement()
			// ik gebruik hier deze methode om
			// toch de index te kunnen verhogen
					, speler.gebruikersnaam(), speler.geboortejaar()));
		});
		
		lijstSpelers.setItems(FXCollections.observableArrayList(alleSpelers));

		lijstSpelers.setStyle("-fx-font-family: 'Algerian'; -fx-font-size: 13px;");
	}

	@FXML
	void verdergaanOnAction(ActionEvent event) {

		try {
			if (dc.geefGekozenSpelers().size() < 3 || dc.geefGekozenSpelers().size() > 6) {
				throw new IllegalArgumentException(bundle.getString("error.teWeinigVeel"));
			}

			this.setBackground(null);
			SpelbordScherm sbs = new SpelbordScherm(dc,taal);
			this.setCenter(sbs);

		} catch (IllegalArgumentException e) {
			Alert verkeerdAantalSpelers = new Alert(AlertType.ERROR);
			verkeerdAantalSpelers.setTitle(bundle.getString("error.start"));
			verkeerdAantalSpelers.setHeaderText(bundle.getString("error.start"));
			verkeerdAantalSpelers.setContentText(e.getMessage());
			verkeerdAantalSpelers.showAndWait();
		}

	}

	@FXML
	void verwijderSpelerOnAction(ActionEvent event) {

		/*
		 * int voorlopigTeVerwijderenSpeler =
		 * lijstSpelers.getSelectionModel().getSelectedIndex();
		 * dc.verwijderSpelerEnKleur(voorlopigTeVerwijderenSpeler,
		 * voorlopigGekozenSpelerKleur);
		 */

		dc.maakNieuwSpel();

		Button gekozenKleurResetter = new Button();
		gekozenKleurResetter.setStyle(";-fx-background-color: #d3d3d3; ;-fx-background-radius: 0;");

		for (int i = 0; i < 6; i++) {
			gekozenKleurKnopKleurGever(gekozenKleurButtons.get(i), gekozenKleurResetter);
		}

		for (Button kleurKnop : kleurButtons) {
			kleurKnop.setDisable(false);
		}

		

		
		resetKnop.fire();
	}
	
	
	// code die er voor zorgt dat de enum geen error geeft wanneer je een engelse naam doorstuurt 
	// Vraag: Mag dit hier blijven staan of moet dit in de domein laag?
	
	private SpelerKleur geefSpelerKleurVanVertaling(String vertaling) {
	    vertaling = vertaling.toLowerCase();
	    switch (vertaling) {
	        case "blauw":
	        case "blue":
	            return SpelerKleur.BLAUW;
	        case "groen":
	        case "green":
	            return SpelerKleur.GROEN;
	        case "wit":
	        case "white":
	            return SpelerKleur.WIT;
	        case "rood":
	        case "red":
	            return SpelerKleur.ROOD;
	        case "geel":
	        case "yellow":
	            return SpelerKleur.GEEL;
	        case "oranje":
	        case "orange":
	            return SpelerKleur.ORANJE;
	        default:
	            throw new IllegalArgumentException("Onbekende kleur: " + vertaling);
	    }
	}

}
