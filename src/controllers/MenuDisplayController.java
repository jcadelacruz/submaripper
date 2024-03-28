package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import submaripper.Submaripper;

public class MenuDisplayController implements Initializable {

    @FXML private Button startBtn;
    @FXML private TextField searchField;
    @FXML private Button submitBtn;

    @FXML private void openLocation(ActionEvent event) throws IOException {
        FXMLLoader l = Submaripper.openFXML("Location", event, this.getClass());
    }
    @FXML private void submitSeed(ActionEvent event) {
    }
    @FXML private void openCredits(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
