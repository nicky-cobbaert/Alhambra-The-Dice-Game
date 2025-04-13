package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import utils.SpelerKleur;

public class GebruikerKiesSchermController extends BorderPane {

	private final DomeinController dc;
	private char taal;
	private MainMenuScherm mms;
	private SpelerKleur voorlopigGekozenSpelerKleur;
	private int voorlopigGekozenSpelerNaam;

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
	private ListView<?> lijstGekozenSpelers;

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
	void zoekKnopOnAction(ActionEvent event) {
		try {
			if (zoekBalk.getText() == null || zoekBalk.getText().isBlank()) {
				throw new IllegalArgumentException("Dit is geen geldige naam!");
			}

			String gezochteNaam = zoekBalk.getText();
			List<String> goedeNamen = dc.zoekDezeSpeler(gezochteNaam);

			lijstSpelers.setItems((ObservableList<String>) goedeNamen);

		} catch (IllegalArgumentException e) {
			zoekBalk.setText(e.getMessage());
		}
	}

	@FXML
	void selecteerSpelerKnopOnAction(ActionEvent event) {

		voorlopigGekozenSpelerNaam = lijstSpelers.getSelectionModel().getSelectedIndex();
		dc.kiesSpelerEnKleur(voorlopigGekozenSpelerNaam, voorlopigGekozenSpelerKleur);

		resetKnop.fire();
	}

	@FXML
	void terugKnopOnAction(ActionEvent event) {
		mms.terugNaarMain(taal);
	}

	@FXML
	void knopBlauwOnAction(ActionEvent event) {
		voorlopigGekozenSpelerKleur = SpelerKleur.BLAUW;

	}

	@FXML
	void knopGeelOnAction(ActionEvent event) {
		voorlopigGekozenSpelerKleur = SpelerKleur.GEEL;
	}

	@FXML
	void knopGroenOnAction(ActionEvent event) {
		voorlopigGekozenSpelerKleur = SpelerKleur.GROEN;
	}

	@FXML
	void knopOranjeOnAction(ActionEvent event) {
		voorlopigGekozenSpelerKleur = SpelerKleur.ORANJE;
	}

	@FXML
	void knopRoodOnAction(ActionEvent event) {
		voorlopigGekozenSpelerKleur = SpelerKleur.ROOD;
	}

	@FXML
	void knopWitOnAction(ActionEvent event) {
		voorlopigGekozenSpelerKleur = SpelerKleur.WIT;
	}

	@FXML
	void resetKnopOnAction(ActionEvent event) {
		List<String> alleSpelers = new ArrayList<String>();

		dc.geefBeschikbareSpelers().forEach(speler -> {
			alleSpelers.add(speler.gebruikersnaam());
		});

		lijstSpelers.setItems((ObservableList<String>) alleSpelers);

	}

	@FXML
	void verdergaanOnAction(ActionEvent event) {

		try {
			if (dc.geefGekozenSpelers().size() < 3 || dc.geefGekozenSpelers().size() > 6) {
				throw new IllegalArgumentException("Er moeten minstens 3 spelers , maximum 6 spelers deelnemen!");
			}
			this.setBackground(null);
			SpelbordScherm sbs = new SpelbordScherm(dc);
			this.setCenter(sbs);

		} catch (IllegalArgumentException e) {
			Alert verkeerdAantalSpelers = new Alert(AlertType.ERROR);
			verkeerdAantalSpelers.setTitle("Het Spel kan niet worden gestart");
			verkeerdAantalSpelers.setHeaderText("Het Spel kan niet worden gestart.");
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

		lijstGekozenSpelers.setItems(FXCollections.emptyObservableList());

		resetKnop.fire();
	}

}
