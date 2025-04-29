package gui;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import domein.DomeinController;
import dto.SpelerDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

public class MainMenuScherm extends BorderPane {

	private char taal;
	private final DomeinController dc;
	private RegistreerSpelerScherm rss;
	private BorderPane mainMenu;

	private MenuItem nl;
	private MenuItem eng;

	private boolean isOfflineButtonKlikbaar = true;

	public MainMenuScherm(DomeinController dc, char taal) {
		this.dc = dc;
		setTaal(taal);
		loadFxmlScreen("MainMenuScherm.fxml");
		setAchtergrondMarkt();
		zetLeaderboard();		
	}

	public void terugNaarMain(char taal) {
		setTaal(taal);
		loadFxmlScreen("MainMenuScherm.fxml");
		setAchtergrondMarkt();
		zetOfflineButtonUit();
		zetLeaderboard();
	}

	private void setTaal(char taal) {
		this.taal = taal;
	}

	private void setAchtergrondMarkt() {
		Image marktImage = new Image(getClass().getResource("/images/MainMenuScherm.png").toExternalForm());

		BackgroundSize size = new BackgroundSize(100, 100, true, true, true, false);

		BackgroundImage achtergrond = new BackgroundImage(marktImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);

		this.setBackground(new Background(achtergrond));
	}

	@FXML
	private Button registreerKnop;

	@FXML
	private Button speelSpelKnop;

	@FXML
	private Button verlaatKnop;

	@FXML
	void registreerKnopKlik(ActionEvent event) {
		this.setBackground(null);
		rss = new RegistreerSpelerScherm(dc, taal, this);
		this.setCenter(rss);

		isOfflineButtonKlikbaar = false;
	}

	@FXML
	void speelSpelKnopKlik(ActionEvent event) {
		this.setBackground(null);
		GebruikerKiesScherm gks = new GebruikerKiesScherm(dc, taal, this);
		this.setCenter(gks);

		isOfflineButtonKlikbaar = false;
	}

	@FXML
	void verlaatKnopKlik(ActionEvent event) {
		verlaatKnop.setOnAction(e -> Platform.exit());
	}

	private void loadFxmlScreen(String name) {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));

		// voor resource bundeles:
		// Locale locale = (taal == 'E') ? Locale.ENGLISH : new Locale("nl");
		// ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		// FXMLLoader loader = new FXMLLoader(getClass().getResource(name), bundle);

//		loader.setRoot(this);
//		loader.setController(this);
//		try {
//			loader.load();
//		} catch (IOException ex) {
//			throw new RuntimeException(ex);
//		}

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

	@FXML
	private Button offlineButton;

	@FXML
	void offlineButtonOnAction(ActionEvent event) {

		Alert offlineModusAlert = new Alert(AlertType.CONFIRMATION);
		offlineModusAlert.setTitle("Offline-Modus Activatiescherm");
		offlineModusAlert.setHeaderText("Wilt u de Offline Modus inschakelen?");
		offlineModusAlert.setContentText(
				"Alhambra maakt normaal gezien gebruik van de VICHogent Servers. Mocht er een probleem zijn met verbinding, dan zou het een ramp zijn.\nMaar vrees niet! Door Offline-Modus aan te zetten kunt u blijven genieten van Alhambra, zelfs bij onvoorziene omstandigheden!\n\n\nEens de Offline-Modus aanstaan kan er niet meer gewisseld worden naar de VICHogent servers tot na de volgende herstart.");

		Optional<ButtonType> resultaat = offlineModusAlert.showAndWait();

		if (resultaat.isPresent() && resultaat.get() == ButtonType.OK) {
			isOfflineButtonKlikbaar = false;
			zetOfflineButtonUit();

			try {
				dc.startOfflineModus();
			} catch (RuntimeException e) {

				Alert h2ModuleError = new Alert(AlertType.ERROR);// ik zet hier een catch, omdat anders het spel crasht,
																	// als de H2 module er niet is (om data naar van het
																	// speler bestand op te slaan)
				h2ModuleError.setTitle("Geen H2 module driver jar gevonden");
				h2ModuleError.setHeaderText("Offline-Modus kan niet gestart worden!");
				h2ModuleError.setContentText(
						"De offline-modus maakt gebruik van de h2 module jar om informatie in te laden. Het spel kan dus niet gespeeld worden in offline modus zonder dit bestand.\n\n\nH2 Database Engine Download: https://github.com/h2database/h2database/releases/");
				h2ModuleError.showAndWait();
			}
		}

	}

	private void zetOfflineButtonUit() {
		if (!isOfflineButtonKlikbaar) {
			offlineButton.disarm();
			offlineButton.setDisable(true);
		}

	}

	  @FXML
	    private Label leaderboardDrie;

	    @FXML
	    private Label leaderboardEen;

	    @FXML
	    private Label leaderboardTwee;

	    @FXML
	    private Label leaderboardVier;

	    @FXML
	    private Label leaderboardVijf;
	    
	    @FXML
	    private Label leaderboard;
	    
	 private void zetLeaderboard() {
		 List<SpelerDTO> lb = dc.geefLeaderboard();
		 
		 leaderboardEen.setWrapText(true);
		 leaderboardTwee.setWrapText(true);
		 leaderboardDrie.setWrapText(true);
		 leaderboardVier.setWrapText(true);
		 leaderboardVijf.setWrapText(true);
		 
		 //%n na het streepje is voor de # keer gewonnen eronder te zetten, wordt besproken of we dit doen of niet!
		 leaderboardEen.setText(String.format("1. %s - %n%d keer gewonnen", lb.get(0).gebruikersnaam(),lb.get(0).aantalGewonnen()));
		 leaderboardTwee.setText(String.format("2. %s - %n%d keer gewonnen", lb.get(1).gebruikersnaam(),lb.get(1).aantalGewonnen()));
		 leaderboardDrie.setText(String.format("3. %s - %d keer gewonnen", lb.get(2).gebruikersnaam(),lb.get(2).aantalGewonnen()));
		 leaderboardVier.setText(String.format("4. %s - %d keer gewonnen", lb.get(3).gebruikersnaam(),lb.get(3).aantalGewonnen()));
		 leaderboardVijf.setText(String.format("5. %s - %d keer gewonnen", lb.get(4).gebruikersnaam(),lb.get(4).aantalGewonnen()));
	 }

}