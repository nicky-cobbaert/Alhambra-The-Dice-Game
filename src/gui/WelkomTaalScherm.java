package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WelkomTaalScherm extends VBox{
	
	private final DomeinController dc;

	public WelkomTaalScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}
	
	private void buildGui() {
		this.setPadding(new Insets(20));

		setWidth(100);
		setAlignment(Pos.CENTER);
		
		Label label = new Label("Welkom");
		Label labelOnder = new Label("onder");
		
		Button knopNL = new Button("Speel");
		Button knopENG = new Button("Play");
		
		label.setText("Welkom bij/Welcome by");
		labelOnder.setText("Alhambra: The Dice Game!");
		knopNL.setText("Speel het spel!");
		knopENG.setText("Play the game!");
		
		VBox vbox = new VBox(20,label,labelOnder);
		HBox hbox = new HBox(50,knopNL,knopENG);
		
		this.getChildren().add(vbox);
		this.getChildren().add(hbox);
	}
	
}
