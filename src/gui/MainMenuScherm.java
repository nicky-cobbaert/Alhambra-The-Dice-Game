package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
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

	public MainMenuScherm(DomeinController dc, char taal) {
		this.dc = dc;
		loadFxmlScreen("MainMenuScherm.fxml");
//		MenuBar menubar = buildMenuBar();
//		setTop(menubar);
		setTaal(taal);
		setAchtergrondMarkt();
	}

	public void terugNaarMain(char taal) {
		loadFxmlScreen("MainMenuScherm.fxml");
		setTaal(taal);
		setAchtergrondMarkt();
	}

	private void setTaal(char taal) {
		this.taal = taal;

//		if (this.taal == 'N') {
//			nl.setDisable(true);
////			nl.setText("NL gekozen"); //Dit is altijd een mogelijkheid, om duidelijkheid te scheppen voor de gebruiker!
//			eng.setDisable(false);
//		}
//		if (this.taal == 'E') {
//			eng.setDisable(true);
////			eng.setText("ENG gekozen"); //Dit is altijd een mogelijkheid, om duidelijkheid te scheppen voor de gebruiker!
//			nl.setDisable(false);
//		}
	}

	private MenuBar buildMenuBar() {
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		MenuItem een = new MenuItem("Een");
		MenuItem twee = new MenuItem("Twee");
		MenuItem exit = new MenuItem("Verlaat");
		exit.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));

		een.setOnAction(e -> System.out.println("Een is gedrukt")); // De sysout is staat er gewoon zodat er geen
																	// compileerfouten zijn -> is te vervangen
		twee.setOnAction(e -> System.out.println("Twee is gedrukt")); // De sysout is staat er gewoon zodat er geen
																		// compileerfouten zijn -> is te vervangen
		exit.setOnAction(e -> Platform.exit());

		fileMenu.getItems().addAll(een, twee, new SeparatorMenuItem(), exit);
		menuBar.getMenus().add(fileMenu);

		Menu taalMenu = new Menu("Taal");
		nl = new MenuItem("NL");
		eng = new MenuItem("ENG");

		nl.setOnAction(e -> setTaal('N'));
		eng.setOnAction(e -> setTaal('E'));

		taalMenu.getItems().addAll(nl, eng);
		menuBar.getMenus().add(taalMenu);

		return menuBar;
	}

	private void setAchtergrondMarkt() {
		Image marktImage = new Image(getClass().getResource("/images/AchtergrondMarkt.png").toExternalForm());

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
	}

	@FXML
	void speelSpelKnopKlik(ActionEvent event) {
		this.setBackground(null);
		GebruikerKiesScherm gks = new GebruikerKiesScherm(dc, taal, this);
		this.setCenter(gks);
	}

	@FXML
	void verlaatKnopKlik(ActionEvent event) {
		verlaatKnop.setOnAction(e -> Platform.exit());
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

}