package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class SpelbordScherm extends BorderPane {

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
	
	public SpelbordScherm(DomeinController dc) {
		this.dc = dc;
		loadFxmlScreen("SpelbordScherm.fxml");
	}
	
	@FXML
    private Button RolKnop;

    @FXML
    private Button SpeelKnop;

    @FXML
    private ImageView dobbelsteenEenDrie;

    @FXML
    private ImageView dobbelsteenEenEen;

    @FXML
    private ImageView dobbelsteenEenNul;

    @FXML
    private ImageView dobbelsteenEenTwee;

    @FXML
    private ImageView dobbelsteenNulDrie;

    @FXML
    private ImageView dobbelsteenNulEen;

    @FXML
    private ImageView dobbelsteenNulNul;

    @FXML
    private ImageView dobbelsteenNulTwee;

    @FXML
    private Label spelerDrie;

    @FXML
    private Label spelerEen;

    @FXML
    private Label spelerTwee;

    @FXML
    private Label spelerVier;

    @FXML
    private Label spelerVijf;

    @FXML
    private Label spelerZes;

    @FXML
    private Label welkeRone;

    @FXML
    void RolKnopKlik(ActionEvent event) {
    	System.out.println("Knop \"Rol\" is ingedrukt");
    }

    @FXML
    void SpeelKnopKlik(ActionEvent event) {
    	System.out.println("Knop \"Speel\" is ingedrukt");
    }

    @FXML
    void dobbelsteenNulNulKlik(MouseEvent event) {
    	System.out.println("1");
    }
    
    @FXML
    void dobbelsteenEenNulKlik(MouseEvent event) {
    	System.out.println("2");
    }
    
    @FXML
    void dobbelsteenNulEenKlik(MouseEvent event) {
    	System.out.println("3");
    }
    
    @FXML
    void dobbelsteenEenEenKlik(MouseEvent event) {
    	System.out.println("4");
    }
    
    @FXML
    void dobbelsteenNulTweeKlik(MouseEvent event) {
    	System.out.println("5");
    }
    
    @FXML
    void dobbelsteenEenTweeKlik(MouseEvent event) {
    	System.out.println("6");
    }
    
    @FXML
    void dobbelsteenNulDrieKlik(MouseEvent event) {
    	System.out.println("7");
    }
    
    @FXML
    void dobbelsteenEenDrieKlik(MouseEvent event) {
    	System.out.println("8");
    }
}
