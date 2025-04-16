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
import javafx.scene.layout.GridPane;
import utils.DobbelsteenKleur;
import utils.SpelerKleur;


public class SpelbordScherm extends BorderPane {

	private final DomeinController dc;
	
	//Dit is nodig zodat we de images kunnen veranderen
	private DobbelsteenKleur kleurNulNul;
	private DobbelsteenKleur kleurEenNul;
	private DobbelsteenKleur kleurNulEen;
	private DobbelsteenKleur kleurEenEen;
	private DobbelsteenKleur kleurNulTwee;
	private DobbelsteenKleur kleurEenTwee;
	private DobbelsteenKleur kleurNulDrie;
	private DobbelsteenKleur kleurEenDrie;
	
	//Alle statussen van de dobbelstenen => False = niet geselecteerd, true = geselecteerd en dus niet meer mee rollen
	private boolean statusDobbelsteenNulNul = false; 
	private boolean statusDobbelsteenEenNul = false;
	private boolean statusDobbelsteenNulEen = false;
	private boolean statusDobbelsteenEenEen = false;
	private boolean statusDobbelsteenNulTwee = false;
	private boolean statusDobbelsteenEenTwee = false;
	private boolean statusDobbelsteenNulDrie = false;
	private boolean statusDobbelsteenEenDrie = false;
	
	private int aantalWorpen = 0;
	
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
		
		//SpelTest
//		dc.maakNieuwSpel();
//		dc.kiesSpelerEnKleur(1, SpelerKleur.BLAUW);
//	
//		dc.kiesSpelerEnKleur(2, SpelerKleur.GEEL);
//		dc.kiesSpelerEnKleur(3, SpelerKleur.GROEN);
//		dc.startSpel();
//		dc.speelRonde();
//		
//		RolKnopKlik(null);
//		plaatsFiches();
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
    private Label welkeRonde;
    
    @FXML
    private ImageView fiche0;

    @FXML
    private ImageView fiche1;

    @FXML
    private ImageView fiche2;

    @FXML
    private ImageView fiche3;

    @FXML
    private ImageView fiche4;

    @FXML
    private ImageView fiche5;


    private void plaatsFiches() {
    	for(int i=1;i<7;i++) {
    		int waarde = dc.geefWaardeVanPositie(i);
    		
    		switch (i) { //Welke fiche je moet aanpassen
    		case 1 : fiche0.setImage(new Image(getClass().getResource(welkeFiche(waarde)).toExternalForm()));
    		case 2 : fiche1.setImage(new Image(getClass().getResource(welkeFiche(waarde)).toExternalForm()));
    		case 3 : fiche2.setImage(new Image(getClass().getResource(welkeFiche(waarde)).toExternalForm()));
    		case 4 : fiche3.setImage(new Image(getClass().getResource(welkeFiche(waarde)).toExternalForm()));
    		case 5 : fiche4.setImage(new Image(getClass().getResource(welkeFiche(waarde)).toExternalForm()));
    		case 6 : fiche5.setImage(new Image(getClass().getResource(welkeFiche(waarde)).toExternalForm()));
    		}
    	}
    }
    
    private String welkeFiche(int waarde) {
    	switch (waarde) { //Welke waarde bij de fiche
		case 0 : return "/images/StartspelerFiche.png";
		case 1 : return "/images/FicheEen.png";
		case 2 : return "/images/FicheTwee.png";
		case 3 : return "/images/FicheDrie.png";
		default : return "Fout";
		}
    }
    
    @FXML
    void RolKnopKlik(ActionEvent event) {
        if (aantalWorpen >= 3) {
            // Disable de rolknop na 3 worpen
            RolKnop.setDisable(true);
            System.out.println("Je hebt al 3 keer gegooid! De knop is uitgeschakeld.");
            disableDobbelstenen(true);
            return; 
        }
        if (aantalWorpen == 0) {
            // Eerste worp? Dan dobbelstenen aanklikbaar maken.
            disableDobbelstenen(false);
        }
        aantalWorpen++;
        
    	for(int i = 1; i<=8; i++) {
    		switch (i) {
    		case 1 : if (statusDobbelsteenNulNul == false) {
    			DobbelsteenKleur dk = dc.rol(i);
    			kleurNulNul = dk;
    			dobbelsteenNulNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
    		}
    		case 2 : if (statusDobbelsteenEenNul == false) {
    			DobbelsteenKleur dk = dc.rol(i);
    			kleurEenNul = dk;
    			dobbelsteenEenNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
    		}
    		case 3 : if (statusDobbelsteenNulEen == false) {
    			DobbelsteenKleur dk = dc.rol(i);
    			kleurNulEen = dk;
    			dobbelsteenNulEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
    		}
    		case 4 : if (statusDobbelsteenEenEen == false) {
    			DobbelsteenKleur dk = dc.rol(i);
    			kleurEenEen = dk;
    			dobbelsteenEenEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
    		}
    		case 5 : if (statusDobbelsteenNulTwee == false) {
    			DobbelsteenKleur dk = dc.rol(i);
    			kleurNulTwee = dk;
    			dobbelsteenNulTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
    		}
    		case 6 : if (statusDobbelsteenEenTwee == false) {
    			DobbelsteenKleur dk = dc.rol(i);
    			kleurEenTwee = dk;
    			dobbelsteenEenTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
    		}
    		case 7 : if (statusDobbelsteenNulDrie == false) {
    			DobbelsteenKleur dk = dc.rol(i);
    			kleurNulDrie = dk;
    			dobbelsteenNulDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
    		}
    		case 8 : if (statusDobbelsteenEenDrie == false) {
    			DobbelsteenKleur dk = dc.rol(i);
    			kleurEenDrie = dk;
    			dobbelsteenEenDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
    		}
    		}
    	}
    }
    private void disableDobbelstenen(boolean disable) {
        dobbelsteenNulNul.setDisable(disable);
        dobbelsteenEenNul.setDisable(disable);
        dobbelsteenNulEen.setDisable(disable);
        dobbelsteenEenEen.setDisable(disable);
        dobbelsteenNulTwee.setDisable(disable);
        dobbelsteenEenTwee.setDisable(disable);
        dobbelsteenNulDrie.setDisable(disable);
        dobbelsteenEenDrie.setDisable(disable);
    }
    private void resetVoorVolgendeSpeler() {
        aantalWorpen = 0;
        RolKnop.setDisable(false);
        
        // Reset dobbelsteenstatussen
        statusDobbelsteenNulNul = false;
        statusDobbelsteenEenNul = false;
        statusDobbelsteenNulEen = false;
        statusDobbelsteenEenEen = false;
        statusDobbelsteenNulTwee = false;
        statusDobbelsteenEenTwee = false;
        statusDobbelsteenNulDrie = false;
        statusDobbelsteenEenDrie = false;

        // Reset afbeeldingen van dobbelstenen
        if (kleurNulNul != null)
            dobbelsteenNulNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen" + kleurNulNul + ".png").toExternalForm()));
        if (kleurEenNul != null)
            dobbelsteenEenNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen" + kleurEenNul + ".png").toExternalForm()));
        if (kleurNulEen != null)
            dobbelsteenNulEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen" + kleurNulEen + ".png").toExternalForm()));
        if (kleurEenEen != null)
            dobbelsteenEenEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen" + kleurEenEen + ".png").toExternalForm()));
        if (kleurNulTwee != null)
            dobbelsteenNulTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen" + kleurNulTwee + ".png").toExternalForm()));
        if (kleurEenTwee != null)
            dobbelsteenEenTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen" + kleurEenTwee + ".png").toExternalForm()));
        if (kleurNulDrie != null)
            dobbelsteenNulDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen" + kleurNulDrie + ".png").toExternalForm()));
        if (kleurEenDrie != null)
            dobbelsteenEenDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen" + kleurEenDrie + ".png").toExternalForm()));

        // Dobbelstenen weer actief maken
     //   disableDobbelstenen(false);
    }
    
    @FXML
    void SpeelKnopKlik(ActionEvent event) {
    	System.out.println("Knop \"Speel\" is ingedrukt");
    	disableDobbelstenen(true);
    	resetVoorVolgendeSpeler();
//    	System.out.println(kleurNulNul);
//    	System.out.println(kleurEenNul);
//    	System.out.println(kleurNulEen);
//    	System.out.println(kleurEenEen);
//    	System.out.println(kleurNulTwee);
//    	System.out.println(kleurEenTwee);
//    	System.out.println(kleurNulDrie);
//    	System.out.println(kleurEenDrie);
    }

    @FXML
    void dobbelsteenNulNulKlik(MouseEvent event) {
        if (kleurNulNul == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	System.out.println("1");
    	if (statusDobbelsteenNulNul == false) {
    		dobbelsteenNulNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurNulNul+"select.png").toExternalForm()));
    		statusDobbelsteenNulNul=true; //Dobbelsteen wordt geselecteerd
    	} else {
    		dobbelsteenNulNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurNulNul+".png").toExternalForm()));
    		statusDobbelsteenNulNul=false; //Dobbelsteen wordt gedeselecteerd
    	}
    	
    }
    
    @FXML
    void dobbelsteenEenNulKlik(MouseEvent event) {
        if (kleurEenNul == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	System.out.println("2");
    	
    	if (statusDobbelsteenEenNul == false) {
    		dobbelsteenEenNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurEenNul+"select.png").toExternalForm()));
    		statusDobbelsteenEenNul=true; //Dobbelsteen wordt geselecteerd
    	} else {
    		dobbelsteenEenNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurEenNul+".png").toExternalForm()));
    		statusDobbelsteenEenNul=false; //Dobbelsteen wordt gedeselecteerd
    	}
    }
    
    @FXML
    void dobbelsteenNulEenKlik(MouseEvent event) {
        if (kleurNulEen == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	System.out.println("3");
    	
    	if (statusDobbelsteenNulEen == false) {
    		dobbelsteenNulEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurNulEen+"select.png").toExternalForm()));
    		statusDobbelsteenNulEen=true; //Dobbelsteen wordt geselecteerd
    	} else {
    		dobbelsteenNulEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurNulEen+".png").toExternalForm()));
    		statusDobbelsteenNulEen=false; //Dobbelsteen wordt gedeselecteerd
    	}
    }
    
    @FXML
    void dobbelsteenEenEenKlik(MouseEvent event) {
        if (kleurEenEen == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	System.out.println("4");
    	
    	if (statusDobbelsteenEenEen == false) {
    		dobbelsteenEenEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurEenEen+"select.png").toExternalForm()));
    		statusDobbelsteenEenEen=true; //Dobbelsteen wordt geselecteerd
    	} else {
    		dobbelsteenEenEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurEenEen+".png").toExternalForm()));
    		statusDobbelsteenEenEen=false; //Dobbelsteen wordt gedeselecteerd
    	}
    }
    
    @FXML
    void dobbelsteenNulTweeKlik(MouseEvent event) {
        if (kleurNulTwee == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	System.out.println("5");
    	
    	if (statusDobbelsteenNulTwee == false) {
    		dobbelsteenNulTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurNulTwee+"select.png").toExternalForm()));
    		statusDobbelsteenNulTwee=true; //Dobbelsteen wordt geselecteerd
    	} else {
    		dobbelsteenNulTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurNulTwee+".png").toExternalForm()));
    		statusDobbelsteenNulTwee=false; //Dobbelsteen wordt gedeselecteerd
    	}
    }
    
    @FXML
    void dobbelsteenEenTweeKlik(MouseEvent event) {
        if (kleurEenTwee == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	System.out.println("6");
    	
    	if (statusDobbelsteenEenTwee == false) {
    		dobbelsteenEenTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurEenTwee+"select.png").toExternalForm()));
    		statusDobbelsteenEenTwee=true; //Dobbelsteen wordt geselecteerd
    	} else {
    		dobbelsteenEenTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurEenTwee+".png").toExternalForm()));
    		statusDobbelsteenEenTwee=false; //Dobbelsteen wordt gedeselecteerd
    	}
    }
    
    @FXML
    void dobbelsteenNulDrieKlik(MouseEvent event) {
        if (kleurNulDrie == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	System.out.println("7");
    	
    	if (statusDobbelsteenNulDrie == false) {
    		dobbelsteenNulDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurNulDrie+"select.png").toExternalForm()));
    		statusDobbelsteenNulDrie=true; //Dobbelsteen wordt geselecteerd
    	} else {
    		dobbelsteenNulDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurNulDrie+".png").toExternalForm()));
    		statusDobbelsteenNulDrie=false; //Dobbelsteen wordt gedeselecteerd
    	}
    }
    
    @FXML
    void dobbelsteenEenDrieKlik(MouseEvent event) {
        if (kleurEenDrie == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	System.out.println("8");
    	
    	if (statusDobbelsteenEenDrie == false) {
    		dobbelsteenEenDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurEenDrie+"select.png").toExternalForm()));
    		statusDobbelsteenEenDrie=true; //Dobbelsteen wordt geselecteerd
    	} else {
    		dobbelsteenEenDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+kleurEenDrie+".png").toExternalForm()));
    		statusDobbelsteenEenDrie=false; //Dobbelsteen wordt gedeselecteerd
    	}
    }
}
