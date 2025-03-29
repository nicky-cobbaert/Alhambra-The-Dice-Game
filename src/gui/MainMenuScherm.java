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
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;

public class MainMenuScherm extends BorderPane{

	private char taal;
	private final DomeinController dc;
	
	public MainMenuScherm(DomeinController dc, char taal) {
		this.dc=dc;
		loadFxmlScreen("MainMenuScherm.fxml");
		setTaal(taal);
		MenuBar menubar = buildMenuBar();
		setTop(menubar);
	}
	
	private void setTaal(char taal) {
		this.taal = taal;
	}
	
	private MenuBar buildMenuBar() {
		MenuBar menuBar = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem een = new MenuItem("Een");
		MenuItem twee = new MenuItem("Twee");
		MenuItem exit = new MenuItem("Verlaat");
		exit.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));
		
		een.setOnAction(e -> System.out.println("Een is gedrukt")); //De sysout is staat er gewoon zodat er geen compileerfouten zijn -> is te vervangen
		twee.setOnAction(e -> System.out.println("Twee is gedrukt")); //De sysout is staat er gewoon zodat er geen compileerfouten zijn -> is te vervangen
		exit.setOnAction(e -> Platform.exit());
		
		file.getItems().addAll(een,twee,new SeparatorMenuItem(),exit);
		menuBar.getMenus().add(file);
		
		
		
		return menuBar;
	}

    @FXML
    private Button registreerKnop;

    @FXML
    private Button speelSpelKnop;

    @FXML
    void registreerKnopKlik(ActionEvent event) {
    	
    }

    @FXML
    void speelSpelKnopKlik(ActionEvent event) {

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
