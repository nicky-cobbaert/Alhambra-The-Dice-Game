package main;

import domein.DomeinController;
import gui.WelkomTaalScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application {
	@Override
	public void start(Stage primaryStage) {
		DomeinController dc = new DomeinController();
		WelkomTaalScherm wts = new WelkomTaalScherm(dc);
		Scene scene = new Scene(wts);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Alhambra: The Dice Game");
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
