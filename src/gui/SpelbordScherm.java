package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;


public class SpelbordScherm extends BorderPane {

	private final DomeinController dc;
	
	//Alle statussen van de dobbelstenen => False = niet geselecteerd, true = geselecteerd en dus niet meer mee rollen
	private boolean statusDobbelsteenNulNul = false; 
	private boolean statusDobbelsteenEenNul = false;
	private boolean statusDobbelsteenNulEen = false;
	private boolean statusDobbelsteenEenEen = false;
	private boolean statusDobbelsteenNulTwee = false;
	private boolean statusDobbelsteenEenTwee = false;
	private boolean statusDobbelsteenNulDrie = false;
	private boolean statusDobbelsteenEenDrie = false;
	
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
		
		
		//Code voor de achtergrond
//		Image spelbordPNG = new Image(getClass().getResource("/images/AlhambraStad.png").toExternalForm(), true);
//		
//		BackgroundSize backgroundSize = new BackgroundSize(
//				100, 100, true, true, true, false
//	    );
//
//	    BackgroundImage spelbord = new BackgroundImage(
//	    			spelbordPNG,
//	                BackgroundRepeat.NO_REPEAT,
//	                BackgroundRepeat.NO_REPEAT,
//	                BackgroundPosition.CENTER,
//	                backgroundSize
//	    );
//	        
//	    this.setBackground(new Background(spelbord));
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
    	//Startcode om dobbelsteen te veranderen van image
    	
    	if (statusDobbelsteenNulNul = false) {
    		//Dit moet in een switchcase gestoken worden zodat de juiste dobbelsteen hier terug komt te staan
//    		dobbelsteenNulNul.setImage(new Image(getClass().getResource("Pad naar nieuwe image").toExternalForm()));
    		statusDobbelsteenNulNul=true; //Dobbelsteen wordt geselecteerd
    	} else { 
    		//Dit moet ook in een switchcase gestoken worden zodat de juiste dobbelsteen hier terug komt te staan
//    		dobbelsteenNulNul.setImage(new Image(getClass().getResource("Pad naar nieuwe image").toExternalForm()));
    		statusDobbelsteenNulNul=false; //Dobbelsteen wordt gedeselecteerd
    	}
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
