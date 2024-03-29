package controllers.rooms;

import controllers.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class NavigationDisplayController implements Initializable {

    @FXML private Button northBtn, westBtn, southBtn, eastBtn;
    SubmarineDisplayController sdc;
    LocationDisplayController ldc;

    @FXML private void setDirection(ActionEvent event) {
    }
    //LocationDisplayController interactions
    private void setCloseFunction(){
        Stage currentStage = (Stage) northBtn.getScene().getWindow();
        //setting on close event
        currentStage.setOnCloseRequest(event -> {
            System.out.println("Close ndc");
            sdc.setRoomOpened(false);
        });
    }
    public void setSubmarineDisplayController(SubmarineDisplayController a) {
        sdc = a;
        setCloseFunction();
    }
    public void setLocationDisplayController(LocationDisplayController a) {
        ldc = a;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
