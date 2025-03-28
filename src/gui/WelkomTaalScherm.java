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
	}
	
}
