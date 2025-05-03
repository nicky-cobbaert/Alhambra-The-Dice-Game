package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.DobbelsteenKleur;

public class KiesDobbelsteenKleurScherm extends GridPane {

	private final DomeinController dc;
	private SpelbordScherm ss;

	@FXML
	private Button blauweKleurKnop;

	@FXML
	private Button bruineKleurKnop;

	@FXML
	private Button grijzeKleurKnop;

	@FXML
	private Button groeneKleurKnop;

	@FXML
	private Button paarseKleurKnop;

	@FXML
	private Button rodeKleurKnop;

	public KiesDobbelsteenKleurScherm(DomeinController dc, char taal, SpelbordScherm ss) {
		this.dc = dc;
		this.ss = ss;
		loadFxmlScreen("KiesDobbelsteenKleurScherm.fxml");
		zetKnopCorrect(blauweKleurKnop, DobbelsteenKleur.BLAUW);
		zetKnopCorrect(bruineKleurKnop, DobbelsteenKleur.BRUIN);
		zetKnopCorrect(grijzeKleurKnop, DobbelsteenKleur.GRIJS);
		zetKnopCorrect(groeneKleurKnop, DobbelsteenKleur.GROEN);
		zetKnopCorrect(paarseKleurKnop, DobbelsteenKleur.PAARS);
		zetKnopCorrect(rodeKleurKnop, DobbelsteenKleur.ROOD);
	}

	
	private void zetKnopCorrect(Button knop, DobbelsteenKleur kleur) {
		long aantal = dc.getDobbelstenenDTOs().stream().
				filter(e -> e.kleur().equals(kleur))
				.count();
		knop.setText(String.format("%s %d",
				knop.getText(),aantal));
		knop.setDisable(aantal == 0);
		
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

	@FXML
    void kiesBlauweKleur(ActionEvent event) {
		gekozen(DobbelsteenKleur.BLAUW);
    }

    @FXML
    void kiesBruineKleur(ActionEvent event) {
    	gekozen(DobbelsteenKleur.BRUIN);
    }

    @FXML
    void kiesGrijzeKleur(ActionEvent event) {
    	gekozen(DobbelsteenKleur.GRIJS);
    }

    @FXML
    void kiesGroeneKleur(ActionEvent event) {
    	gekozen(DobbelsteenKleur.GROEN);
    }

    @FXML
    void kiesPaarseKleur(ActionEvent event) {
    	gekozen(DobbelsteenKleur.PAARS);
    }

    @FXML
    void kiesRodeKleur(ActionEvent event) {
    	gekozen(DobbelsteenKleur.ROOD);
    }
    
    private void gekozen(DobbelsteenKleur kleur) {
    	Stage stage = (Stage) groeneKleurKnop.getScene().getWindow();
    	stage.close();
    	ss.setDisable(false);
    	ss.beïndigBeurt(kleur);
    	
    	
    }


}
