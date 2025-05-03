package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomeinController;
import dto.DobbelsteenDTO;
import dto.FicheDTO;
import dto.GebouwsteenDTO;
import dto.SpelerDTO;
import dto.ZetsteenDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.DobbelsteenKleur;
import utils.SpelerKleur;


public class SpelbordScherm extends BorderPane {

	private final DomeinController dc;
	private List<ImageView> dobbelImages;
	private List<ImageView> ficheImages;
	private List<GridPane> zetsteenGebieden;
	private ResourceBundle bundle;
	
	private char taal;
	
	
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
	
	public SpelbordScherm(DomeinController dc, char taal) {
		this.dc = dc;
		this.taal = taal;
		dc.startSpel();
		loadFxmlScreen("SpelbordScherm.fxml");
		zetSpelersBegin();
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
        for(ImageView i:ficheImages) {
        	i.setId("KEEP");
        }
        
        plaatsFiches();
        zetsteenGebieden = new ArrayList<GridPane>();
        zetsteenGebieden.add(BlauwResultaatGebied);
        zetsteenGebieden.add(RoodResultaatGebied);
        zetsteenGebieden.add(BruinResultaatGebied);
        zetsteenGebieden.add(GrijsResultaatGebied);
        zetsteenGebieden.add(GroenResultaatGebied);
        zetsteenGebieden.add(PaarsResultaatGebied);
        welkeRonde.setText(String.format(bundle.getString("label.ronde"), dc.getRonde()));

        plaatsImagesGebouwen();
		
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
		Image spelbordPNG = new Image(getClass().getResource("/images/Spelbord.png").toExternalForm(), true);
		
		BackgroundSize backgroundSize = new BackgroundSize(
				100, 100, true, true, true, false
	    );

	    BackgroundImage spelbord = new BackgroundImage(
	    			spelbordPNG,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundPosition.CENTER,
	                backgroundSize
	    );
	    
	    this.setBackground(new Background(spelbord));
	}

	private void plaatsImagesGebouwen() {
		for(int index = 0;index <=5;index ++) {
			plaatsNeer(zetsteenGebieden.get(index), DobbelsteenKleur.values()[index]);
		}
		
	}
	
	private void plaatsNeer(GridPane gp,DobbelsteenKleur kleur) {
		ImageView gebouw = new ImageView(getClass().getResource("/images/Dobbelsteen"+kleur+".png").toExternalForm());
		gebouw.fitHeightProperty().set(34);
		gebouw.fitWidthProperty().set(30);
		gebouw.setId("KEEP");
		switch(kleur) {
		case ROOD,BLAUW,BRUIN -> gp.add(gebouw, 0, 0);
		case GRIJS,PAARS,GROEN -> gp.add(gebouw,0,8);
		}
	}

	@FXML
	private GridPane BlauwResultaatGebied;
	
	@FXML
	private GridPane BruinResultaatGebied;

	@FXML
	private GridPane GrijsResultaatGebied;
	
	@FXML
	private GridPane GroenResultaatGebied;

	@FXML
	private GridPane PaarsResultaatGebied;
	
	@FXML
    private GridPane RoodResultaatGebied;
	
	@FXML
    private Button RolKnop;

    @FXML
    private Button SpeelKnop;
    
    @FXML
    private GridPane gebouwsteenGebied;

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
	private VBox spelerBox;
    
    private List<Label> labelsSpelers;
    
    private void veranderHuidigeSpeler() {
    	int index = dc.geefGekozenSpelers().indexOf(dc.getHuidigeSpelerDTO());
    	
    	for (int i = 0;i<dc.geefGekozenSpelers().size();i++) {
    		labelsSpelers.get(i).setUnderline((i == index ? true : false));
    		labelsSpelers.get(i).setFont(Font.font("Algerian",(i == index ? 18 : 14)));
    	}
    }
    
    private void updateAantalZetstenen(SpelerDTO spelerVanVorigeRonde) {
    	int aantalZetstenen = (int) spelerVanVorigeRonde.zetstenen().stream().filter(e -> e.positie()==0).count();
    	labelsSpelers.get(dc.geefGekozenSpelers().indexOf(spelerVanVorigeRonde)).setText(String.format("%s - %d punten - %d %s",spelerVanVorigeRonde.gebruikersnaam(),spelerVanVorigeRonde.punten(),
    			aantalZetstenen, (aantalZetstenen == 1)? "zetsteen":"zetstenen"));;
    }
    
    private void herzetSpelersNaRonde() {
    	for (int i = 0; i<dc.geefGekozenSpelers().size();i++) {
    		SpelerDTO speler = dc.geefGekozenSpelers().get(i);
    		labelsSpelers.get(i).setText(String.format("%s - %d punten - %d zetstenen", speler.gebruikersnaam(),speler.punten(),dc.geefAantalZetstenen()));
    	}
    }
    
    private void zetSpelersBegin() {
    	
    	labelsSpelers = new ArrayList<>();
    	List<SpelerDTO> gekozenSpelers = dc.geefGekozenSpelers();
    	
    	int aantalSpelers = gekozenSpelers.size();
    	int aantalZetstenen = (int) gekozenSpelers.get(0).zetstenen().stream().filter(e -> e.positie()==0).count();
    	Label spelerEen = new Label(String.format(bundle.getString("label.spelerStatus"),gekozenSpelers.get(0).gebruikersnaam(),gekozenSpelers.get(0).punten(),
    			aantalZetstenen, bundle.getString((aantalZetstenen == 1) ? "label.zetsteen.enkel" : "label.zetsteen.meervoud")));
//    	Label spelerEen = new Label(String.format("%s - %d punten - %d %s",gekozenSpelers.get(0).gebruikersnaam(),gekozenSpelers.get(0).punten(),
//    			aantalZetstenen, (aantalZetstenen == 1)? "zetsteen":"zetstenen"));
    	spelerBox.getChildren().add(spelerEen);
    	geefLabelJuisteOpmaak(spelerEen,gekozenSpelers.get(0).kleur());
    	labelsSpelers.add(spelerEen);
    	
    	aantalZetstenen = (int) gekozenSpelers.get(1
    			).zetstenen().stream().filter(e -> e.positie()==0).count();
    	Label spelerTwee = new Label(String.format(bundle.getString("label.spelerStatus"),gekozenSpelers.get(1).gebruikersnaam(),gekozenSpelers.get(1).punten(),
    			aantalZetstenen, bundle.getString((aantalZetstenen == 1) ? "label.zetsteen.enkel" : "label.zetsteen.meervoud")));
//    	Label spelerTwee = new Label(String.format("%s - %d punten - %d %s",gekozenSpelers.get(1).gebruikersnaam(),gekozenSpelers.get(1).punten(),
//    			aantalZetstenen, (aantalZetstenen == 1)? "zetsteen":"zetstenen"));
    	spelerBox.getChildren().add(spelerTwee);
    	geefLabelJuisteOpmaak(spelerTwee,gekozenSpelers.get(1).kleur());
    	labelsSpelers.add(spelerTwee);
    	
    	aantalZetstenen = (int) gekozenSpelers.get(2).zetstenen().stream().filter(e -> e.positie()==0).count();
    	Label spelerDrie = new Label(String.format(bundle.getString("label.spelerStatus"),gekozenSpelers.get(2).gebruikersnaam(),gekozenSpelers.get(2).punten(),
    			aantalZetstenen, bundle.getString((aantalZetstenen == 1) ? "label.zetsteen.enkel" : "label.zetsteen.meervoud")));
    	spelerBox.getChildren().add(spelerDrie);
    	geefLabelJuisteOpmaak(spelerDrie,gekozenSpelers.get(2).kleur());
    	labelsSpelers.add(spelerDrie);
    	
    	if (aantalSpelers > 3) {
    		aantalZetstenen = (int) gekozenSpelers.get(3).zetstenen().stream().filter(e -> e.positie()==0).count();
    		Label spelerVier = new Label(String.format(bundle.getString("label.spelerStatus"),gekozenSpelers.get(3).gebruikersnaam(),gekozenSpelers.get(3).punten(),
    				aantalZetstenen, bundle.getString((aantalZetstenen == 1) ? "label.zetsteen.enkel" : "label.zetsteen.meervoud")));
        	spelerBox.getChildren().add(spelerVier);
        	geefLabelJuisteOpmaak(spelerVier,gekozenSpelers.get(3).kleur());
        	labelsSpelers.add(spelerVier);
    	}
    	if (aantalSpelers > 4) {
    		aantalZetstenen = (int) gekozenSpelers.get(4).zetstenen().stream().filter(e -> e.positie()==0).count();
    		Label spelerVijf = new Label(String.format(bundle.getString("label.spelerStatus"),gekozenSpelers.get(4).gebruikersnaam(),gekozenSpelers.get(4).punten(),
    				aantalZetstenen, bundle.getString((aantalZetstenen == 1) ? "label.zetsteen.enkel" : "label.zetsteen.meervoud")));
        	geefLabelJuisteOpmaak(spelerVijf,gekozenSpelers.get(4).kleur());
        	spelerBox.getChildren().add(spelerVijf);
        	labelsSpelers.add(spelerVijf);
    	}
    	if (aantalSpelers > 5) {
    		aantalZetstenen = (int) gekozenSpelers.get(5).zetstenen().stream().filter(e -> e.positie()==0).count();
    		Label spelerZes = new Label(String.format(bundle.getString("label.spelerStatus"),gekozenSpelers.get(5).gebruikersnaam(),gekozenSpelers.get(5).punten(),
    				aantalZetstenen, bundle.getString((aantalZetstenen == 1) ? "label.zetsteen.enkel" : "label.zetsteen.meervoud")));
        	spelerBox.getChildren().add(spelerZes);
        	geefLabelJuisteOpmaak(spelerZes,gekozenSpelers.get(5).kleur());
        	labelsSpelers.add(spelerZes);
    	}
    	veranderHuidigeSpeler();
    }
    
    private void geefLabelJuisteOpmaak(Label lbl, SpelerKleur kleur) {
        int prefH = 358/dc.geefGekozenSpelers().size();

        lbl.setPrefHeight(prefH);
        lbl.setFont(Font.font("Algerian", 15));
        lbl.setAlignment(Pos.CENTER);
        lbl.setTextAlignment(TextAlignment.CENTER);
        lbl.setWrapText(true);

        // Stel een DropShadow effect in voor een rand rond de tekst
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);  // De rand (schaduw) moet zwart zijn
        dropShadow.setOffsetX(1);         // Zet de offset van de schaduw
        dropShadow.setOffsetY(1);         // Zet de offset van de schaduw
        dropShadow.setRadius(0.5);          // Hoe ver de schaduw zich uitstrekt (kleine waarde voor een dunne rand)

        lbl.setEffect(dropShadow);

        switch (kleur) {
        case SpelerKleur.BLAUW -> lbl.setTextFill(Color.BLUE);
        case SpelerKleur.GROEN -> lbl.setTextFill(Color.LIMEGREEN);
        case SpelerKleur.WIT -> lbl.setTextFill(Color.WHITESMOKE);
        case SpelerKleur.GEEL -> lbl.setTextFill(Color.YELLOW);
        case SpelerKleur.ORANJE -> lbl.setTextFill(Color.color(1, 0.35, 0));
        case SpelerKleur.ROOD -> lbl.setTextFill(Color.RED);
        }
    }
    
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
    	for(FicheDTO fDTO: ficheDTOs) {
    		ficheImages.get(fDTO.positie() - 1).setImage(new Image(getClass().getResource(welkeFiche(fDTO.waarde())).toExternalForm()));

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
    		RolKnop.disarm(); //zonder disarm kan je enter duwen om te blijven rollen
        if (!gerold) {
            // Disable de rolknop na 3 worpen
            RolKnop.setDisable(true);
            RolKnop.disarm(); 
            System.out.println("Je hebt al 3 keer gegooid! De knop is uitgeschakeld.");
            disableDobbelstenen(true);
        }
        SpeelKnop.setDisable(false);
            // Eerste worp? Dan dobbelstenen aanklikbaar maken.
            disableDobbelstenen(false);
        zetAlleDobbelstenenNaarHunHuidigeImage();
    }
    
    private void zetAlleDobbelstenenNaarHunHuidigeImage() {
    	List<DobbelsteenDTO> dobbelDTOs = dc.getDobbelstenenDTOs();
        for(int i = 0; i < 8; i ++) {
        	veranderNaarCorrecteDobbelsteen(dobbelDTOs.get(i),dobbelImages.get(i));
        }
    }
    
    private void veranderNaarCorrecteDobbelsteen(DobbelsteenDTO dobbelsteenDTO, ImageView imageView) {
    	if(dobbelsteenDTO.kleur() == null) {
    		imageView.setImage(null);
    		return;
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
    
    public void beïndigBeurt(DobbelsteenKleur kleur) {
    	//hier wordt het neergeplaatst
        SpelerDTO huidigeSpelerDTO = dc.beïndigBeurt(kleur);
        RolKnop.setDisable(false);
        SpeelKnop.setDisable(true);
        // Reset afbeeldingen van dobbelstenen
        zetAlleDobbelstenenNaarHunHuidigeImage();
        
        
        ZetsteenDTO nieuweZetsteen = huidigeSpelerDTO.zetstenen().stream().filter(e -> e.positie() != 0).toList().getLast();
        plaatsZetsteen(nieuweZetsteen.positie(), huidigeSpelerDTO.kleur());
        // Dobbelstenen weer actief maken
        disableDobbelstenen(false);
        if(dc.isEindeVanDeRonde()) {
        	beïndigDeRonde();
        	if(dc.getIsEindeSpel()) {
            	//hier komt het overwinningscherm dan
        		WinnaarScherm ws = new WinnaarScherm(dc, taal);
            	this.setCenter(ws);
            	
        		return;
            }
        	beginRonde();
        }
        try {
        	updateAantalZetstenen(huidigeSpelerDTO);
        }catch(Exception e) {
        	final String huidigeSpelerGebruikersnaam = huidigeSpelerDTO.gebruikersnaam();
        	huidigeSpelerDTO = dc.geefGekozenSpelers().stream()
        			.filter(s -> s.gebruikersnaam() == huidigeSpelerGebruikersnaam)
        			.findFirst().get();
        	updateAantalZetstenen(huidigeSpelerDTO);
        }
        
        veranderHuidigeSpeler();
    }
    
    private void beginRonde() {
		dc.startRonde();
		herzetSpelersNaRonde();
		plaatsFiches();
	}
    private void maakHetGebiedLeeg(GridPane gp) {
    	for(javafx.scene.Node n:gp.getChildren()) {
    		if(n.getId() != "KEEP") {
    			n.setId("REMOVE");
    		}
    	}
    	gp.getChildren().removeIf(e -> e.getId() == "REMOVE");
    }

	private void beïndigDeRonde() {
    	System.out.println("Ronde is klaar");
    	dc.beïndigRonde();
    	for(GridPane gp:zetsteenGebieden) {
    		maakHetGebiedLeeg(gp);;
    	}
    	if(gebouwsteenGebied.getChildren().size() != 0) {
    		gebouwsteenGebied.getChildren().clear();
    	}
    	dc.geefGekozenSpelers().
    	stream().
    	forEach(e -> plaatsGebouwstenen(e.gebouwtsenen(), e.kleur()));
    	welkeRonde.setText(String.format(bundle.getString("label.ronde"), dc.getRonde() + 1));

    	
	}

	private void plaatsZetsteen(int positie, SpelerKleur kleurZetsteen) { //kolom = in hoeveel keer, rij = hoeveel je gegooid hebt
    	int kleurBord = positie/100,rij = (positie/10)%10,kolom = positie%10; 
    	ImageView zetsteen = new ImageView(new Image(getClass().getResource("/images/Zetsteen"+kleurZetsteen+".png").toExternalForm(), true));
    	zetsteen.setFitHeight(20);
    	zetsteen.setFitWidth(20);
    	if (kleurBord<=3) { //Bovenste helft!
    		int kolomRelatief = kolom;
    		switch (kolom) {
    		case 1 -> kolomRelatief = 3; // In de bovenste helft is de eerste kolom het slechtste (in 3 keer gegooid!
    		case 3 -> kolomRelatief = 1;
    		}
    		switch (kolom) {
        	case 1 -> zetsteen.setTranslateY(5); //Staat in de eerste rij -> moet 5 pixels omhoog om mooi te passen
        	case 3 -> zetsteen.setTranslateY(-5); //Staat in de laatste rij -> moet 5 pixels naar beneden om mooi te passen
        	}
    		switch (kleurBord) {
    		case 1 -> BlauwResultaatGebied.add(zetsteen, kolomRelatief-1, rij);
    		case 2 -> RoodResultaatGebied.add(zetsteen, kolomRelatief-1, rij);
    		case 3 -> BruinResultaatGebied.add(zetsteen, kolomRelatief-1, rij);
    		}
    	} else { //Onderste helft
    		switch (kolom) {
        	case 1 -> zetsteen.setTranslateY(-5); //Staat in de eerste rij -> moet 5 pixels omhoog om mooi te passen
        	case 3 -> zetsteen.setTranslateY(5); //Staat in de laatste rij -> moet 5 pixels naar beneden om mooi te passen
        	}
    		switch (kleurBord) {
    		case 4 -> GrijsResultaatGebied.add(zetsteen, kolom-1, 8-rij);
    		case 5 -> GroenResultaatGebied.add(zetsteen, kolom-1, 8-rij);
    		case 6 -> PaarsResultaatGebied.add(zetsteen, kolom-1, 8-rij);
    		}
    	}
    	
    }
    
    @FXML
    void SpeelKnopKlik(ActionEvent event) {
    	
    	disableDobbelstenen(true);
    	this.setDisable(true);
    	kiesScherm();
    }
    
    private void kiesScherm() {
    	Stage stage = new Stage();
    	KiesDobbelsteenKleurScherm kdks = new KiesDobbelsteenKleurScherm(dc, taal, this);
		Scene scene = new Scene(kdks);
		stage.setScene(scene);
		stage.setTitle("Kies jouw kleur!");
		stage.show();
    }
    
    private void plaatsGebouwstenen(List<GebouwsteenDTO> stenen, SpelerKleur kleur) {
    	for(GebouwsteenDTO steen : stenen) {
    		if(steen.positie()%100 != 0) {
    		ImageView gebouwsteen = new ImageView(new Image(getClass().getResource("/images/"+kleur+"gebouwsteen.png").toExternalForm(), true));
        	gebouwsteen.setFitHeight(30);
        	gebouwsteen.setFitWidth(30);
        	
        	gebouwsteen.setViewOrder(steen.volgorde());
        	
        	int offset = (steen.volgorde()-1)*5;
        	
        	gebouwsteen.setTranslateX(offset);
        	gebouwsteen.setTranslateY(offset);
        	
        	gebouwsteenGebied.add(gebouwsteen, (steen.positie()/100)-1, 10-steen.positie()%10);
    		}
    	}
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
