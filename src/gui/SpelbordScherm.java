package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import dto.DobbelsteenDTO;
import dto.FicheDTO;
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
	private List<ImageView> dobbelImages;
	private List<ImageView> ficheImages;
	
	
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
		dc.startSpel();
		dc.startRonde();
		loadFxmlScreen("SpelbordScherm.fxml");
		//plaatsFiches();
		dobbelImages = new ArrayList<ImageView>();
        dobbelImages.add(dobbelsteenNulNul);
        dobbelImages.add(dobbelsteenEenNul);
        dobbelImages.add(dobbelsteenNulEen);
        dobbelImages.add(dobbelsteenEenEen);
        dobbelImages.add(dobbelsteenNulTwee);
        dobbelImages.add(dobbelsteenEenTwee);
        dobbelImages.add(dobbelsteenNulDrie);
        dobbelImages.add(dobbelsteenEenDrie);
        clearImages(dobbelImages);
        SpeelKnop.setDisable(true);
        ficheImages = new ArrayList<ImageView>();
        ficheImages.add(fiche0);
        ficheImages.add(fiche1);
        ficheImages.add(fiche2);
        ficheImages.add(fiche3);
        ficheImages.add(fiche4);
        ficheImages.add(fiche5);
        plaatsFiches();
		
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
//		Image spelbordPNG = new Image(getClass().getResource("/images/bordTest.png").toExternalForm(), true);
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
    	List<FicheDTO> ficheDTOs = dc.getGeplaatsteFicheDTOs();
    	for(int i = 1;i <= 6;i ++) {
    		int indexVanFiche = 0;
    		int teller = 0;
    		for(FicheDTO f:ficheDTOs) {
    			if(f.positie() == i) {
    				indexVanFiche = teller;
    				break;
    			}
    			teller ++;
    		}
    		ficheImages.get(i-1).setImage(new Image(getClass().getResource(welkeFiche(ficheDTOs.get(indexVanFiche).waarde())).toExternalForm()));
    		
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
    	boolean gerold= dc.rolDobbelstenen();
    	if (dc.getAantalKeerGerold() >= 3)
    		RolKnop.setDisable(true);
        if (!gerold) {
            // Disable de rolknop na 3 worpen
            RolKnop.setDisable(true);
            System.out.println("Je hebt al 3 keer gegooid! De knop is uitgeschakeld.");
            disableDobbelstenen(true);
        }
        SpeelKnop.setDisable(false);
            // Eerste worp? Dan dobbelstenen aanklikbaar maken.
            disableDobbelstenen(false);
        List<DobbelsteenDTO> dobbelDTOs = dc.getDobbelstenenDTOs();
        for(int i = 0; i < 8; i ++) {
        	veranderNaarCorrecteDobbelsteen(dobbelDTOs.get(i),dobbelImages.get(i));
        }
        
//    	for(int i = 1; i<=8; i++) {
//    		switch (i) {
//    		case 1 : if (statusDobbelsteenNulNul == false) {
//    			DobbelsteenKleur dk = dc.rol(i);
//    			kleurNulNul = dk;
//    			dobbelsteenNulNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
//    		}
//    		case 2 : if (statusDobbelsteenEenNul == false) {
//    			DobbelsteenKleur dk = dc.rol(i);
//    			kleurEenNul = dk;
//    			dobbelsteenEenNul.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
//    		}
//    		case 3 : if (statusDobbelsteenNulEen == false) {
//    			DobbelsteenKleur dk = dc.rol(i);
//    			kleurNulEen = dk;
//    			dobbelsteenNulEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
//    		}
//    		case 4 : if (statusDobbelsteenEenEen == false) {
//    			DobbelsteenKleur dk = dc.rol(i);
//    			kleurEenEen = dk;
//    			dobbelsteenEenEen.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
//    		}
//    		case 5 : if (statusDobbelsteenNulTwee == false) {
//    			DobbelsteenKleur dk = dc.rol(i);
//    			kleurNulTwee = dk;
//    			dobbelsteenNulTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
//    		}
//    		case 6 : if (statusDobbelsteenEenTwee == false) {
//    			DobbelsteenKleur dk = dc.rol(i);
//    			kleurEenTwee = dk;
//    			dobbelsteenEenTwee.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
//    		}
//    		case 7 : if (statusDobbelsteenNulDrie == false) {
//    			DobbelsteenKleur dk = dc.rol(i);
//    			kleurNulDrie = dk;
//    			dobbelsteenNulDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
//    		}
//    		case 8 : if (statusDobbelsteenEenDrie == false) {
//    			DobbelsteenKleur dk = dc.rol(i);
//    			kleurEenDrie = dk;
//    			dobbelsteenEenDrie.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dk+".png").toExternalForm()));
//    		}
//    		}
//    	}
    }
    
    private void veranderNaarCorrecteDobbelsteen(DobbelsteenDTO dobbelsteenDTO, ImageView imageView) {
    	if(dobbelsteenDTO.kleur() == null) {
    		imageView.setImage(null);
    	}
    	if(dobbelsteenDTO.nogRollen()) {
    		imageView.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dobbelsteenDTO.kleur()+".png").toExternalForm()));
    	}else {
        	imageView.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dobbelsteenDTO.kleur()+"select.png").toExternalForm()));

    	}
    }
    
    private void veranderVanNogRollenDobbelsteen(ImageView i,int index) {
    	System.out.println(index);
    	DobbelsteenDTO dobbelDTO = dc.getDobbelstenenDTOs().get(index);
    	if (dc.veranderStatusNogRollenDobbelsteen(index) == false) {//kijkt of de dobbelsteen nog mag rollen na status verandering
    		i.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dobbelDTO.kleur()+"select.png").toExternalForm()));
    	 //Dobbelsteen wordt gedeselecteerd
    	} else {
    		i.setImage(new Image(getClass().getResource("/images/Dobbelsteen"+dobbelDTO.kleur()+".png").toExternalForm()));
    	 //Dobbelsteen wordt geselecteerd
    	}
    }
    private void disableDobbelstenen(boolean disable) {
    	for(ImageView i:dobbelImages) {
    		i.setDisable(disable);
    	}
    }
    private void clearImages(List<ImageView> images) {
    	for(ImageView i:images) {
    		i.setImage(null);
    	}
    }
    private void resetVoorVolgendeSpeler() {
        dc.resetVoorVolgendeSpeler();
        RolKnop.setDisable(false);
        SpeelKnop.setDisable(true);
        // Reset dobbelsteenstatussen
        clearImages(dobbelImages);

        // Reset afbeeldingen van dobbelstenen
        
        // Dobbelstenen weer actief maken
        disableDobbelstenen(false);
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
        if (dobbelsteenNulNul.getImage() == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	veranderVanNogRollenDobbelsteen(dobbelsteenNulNul, 0);
    	
    }
    
    @FXML
    void dobbelsteenEenNulKlik(MouseEvent event) {
        if (dobbelsteenEenNul.getImage() == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	veranderVanNogRollenDobbelsteen(dobbelsteenEenNul, 1);
    }
    
    @FXML
    void dobbelsteenNulEenKlik(MouseEvent event) {
        if (dobbelsteenNulEen.getImage() == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	veranderVanNogRollenDobbelsteen(dobbelsteenNulEen, 2);
    }
    
    @FXML
    void dobbelsteenEenEenKlik(MouseEvent event) {
        if (dobbelsteenEenEen.getImage() == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
    	veranderVanNogRollenDobbelsteen(dobbelsteenEenEen, 3);
    }
    
    @FXML
    void dobbelsteenNulTweeKlik(MouseEvent event) {
        if (dobbelsteenNulTwee.getImage() == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
        veranderVanNogRollenDobbelsteen(dobbelsteenNulTwee, 4);
    }
    
    @FXML
    void dobbelsteenEenTweeKlik(MouseEvent event) {
        if (dobbelsteenEenTwee.getImage() == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
        veranderVanNogRollenDobbelsteen(dobbelsteenEenTwee, 5);
    }
    
    @FXML
    void dobbelsteenNulDrieKlik(MouseEvent event) {
        if (dobbelsteenNulDrie.getImage() == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
        veranderVanNogRollenDobbelsteen(dobbelsteenNulDrie, 6);
    }
    
    @FXML
    void dobbelsteenEenDrieKlik(MouseEvent event) {
        if (dobbelsteenEenDrie.getImage() == null) {
            // Nog niet gegooid, dus stoppen
            return;
        }
        veranderVanNogRollenDobbelsteen(dobbelsteenEenDrie, 7);
    }
}
