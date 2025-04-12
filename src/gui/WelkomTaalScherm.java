package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class WelkomTaalScherm extends BorderPane {
    
    private MainMenuScherm mainMenu;

    private final DomeinController dc;

    public WelkomTaalScherm(DomeinController dc) {
        this.dc = dc;
        loadFxmlScreen("WelkomTaalScherm.fxml");
        setBackgroundImage();
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

    private void setBackgroundImage() {
        Image backgroundImage = new Image(
            getClass().getResource("/images/AlhambraStad.png").toExternalForm(),
            0, 0, true, true  // preserve ratio, auto scale
        );

        BackgroundSize backgroundSize = new BackgroundSize(
            100, 100, true, true, true, false // gebruik percentages
        );

        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            backgroundSize
        );

        this.setBackground(new Background(background));
    }

    @FXML
    private Button KnopENG;

    @FXML
    private Button KnopNL;

    @FXML
    void KnopENGklik(ActionEvent event) {
        // Geeft engels mee als taal!
    }

    @FXML
    void KnopNLklik(ActionEvent event) {
        mainMenu = new MainMenuScherm(dc, 'N');
        this.setCenter(mainMenu);
        

    }
}


