package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import domein.DomeinController;
import dto.SpelerDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import utils.SpelerKleur;

public class WinnaarScherm extends BorderPane {

	private final DomeinController dc;
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

	public WinnaarScherm(DomeinController dc, char taal, MainMenuScherm mainMenu) {
		this.dc = dc;
		setTaal(taal);
		loadFxmlScreen("WinnaarScherm.fxml");

		// labels aanvullen volgens punten-----------------------

		Label[] overzichtSpelers = { overzichtSpeler1, overzichtSpeler2, overzichtSpeler3, overzichtSpeler4,
				overzichtSpeler5, overzichtSpeler6 };

		// overzichtSpelersKleur is de plaats van de speler in de speler zijn bijhorende
		// kleur
		Label[] overzichtSpelersKleur = { overzichtSpelerKleur1, overzichtSpelerKleur2, overzichtSpelerKleur3,
				overzichtSpelerKleur4, overzichtSpelerKleur5, overzichtSpelerKleur6 };

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

		List<SpelerDTO> spelers = dc.geefGekozenSpelers();

		// stream om te overlopen, te sorteren op basis van punten en elke speler zijn
		// plaatsLabel aanpast (zie hieronder)
		spelers.stream().sorted(Comparator.comparing(SpelerDTO::punten)).forEach((e) -> {

			// innerklasse die de het label op index (op basis van punten) aanpast:
			overzichtSpelers[spelers.indexOf(e)].setText(String.format("%10s %10d %10d %10d", e.gebruikersnaam(),
					e.punten(), e.aantalGewonnen(), e.aantalGespeeld()));

			// verder geeft de innerklasse ook een rang met behorende spelerkleur:
			overzichtSpelersKleur[spelers.indexOf(e)].setText(String.format("%d", spelers.indexOf(e) + 1));
			overzichtSpelersKleur[spelers.indexOf(e)].setStyle(kleurKiezer(e.kleur()));

		});

		// afsluitknop een kleurtje geven-------------------------------

		afsluitButton.setStyle(kleurKiezer(dc.geefWinnaars().get(0).kleur()));

		// afsluitknop kleuren einde-----------------------------------

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
	}

	@FXML
	private Button afsluitButton;

	@FXML
	private Label gefeliciteerdLabel;

	@FXML
	private Button bedanktKnop;

	@FXML
	private Label overzichtSpeler1;

	@FXML
	private Label overzichtSpeler2;

	@FXML
	private Label overzichtSpeler3;

	@FXML
	private Label overzichtSpeler4;

	@FXML
	private Label overzichtSpeler5;

	@FXML
	private Label overzichtSpeler6;

	@FXML
	private Label overzichtSpelerKleur1;

	@FXML
	private Label overzichtSpelerKleur2;

	@FXML
	private Label overzichtSpelerKleur3;

	@FXML
	private Label overzichtSpelerKleur4;

	@FXML
	private Label overzichtSpelerKleur5;

	@FXML
	private Label overzichtSpelerKleur6;

	@FXML
	void afsluitButtonOnAction(ActionEvent event) {
		// platform.exit vraagt om de javafx programmas rustig af te sluiten
		// system.exit forceert alle jvm processen om te stoppen
		Platform.exit();
		System.exit(0);
	}

	@FXML
	void bedanktKnopOnAction(ActionEvent event) {

		Alert bedanktAlert = new Alert(AlertType.INFORMATION);
		bedanktAlert.setTitle("Bedankt om te spelen!");
		bedanktAlert.setHeaderText("Credits:");
		bedanktAlert.setContentText("Cobbaert Nicky\nDe Wever Lars\nGheysels Wout\nLippens Sverre\nVan Horen Jelle");
		bedanktAlert.showAndWait();
	}

}
