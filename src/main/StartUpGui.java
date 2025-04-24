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
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
		primaryStage.widthProperty().addListener((obs, oud, nieuw) -> {
		    primaryStage.setHeight(nieuw.doubleValue() * 9 / 16);
		});

		primaryStage.heightProperty().addListener((obs, oud, nieuw) -> {
		    primaryStage.setWidth(nieuw.doubleValue() * 16 / 9);
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
