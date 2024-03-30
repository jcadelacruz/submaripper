package controllers.rooms;

import controllers.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class NavigationDisplayController extends RoomDisplayController implements Initializable {

    @FXML private Button northBtn, westBtn, southBtn, eastBtn;

    @FXML private void setDirection(ActionEvent event) {
        Button b = (Button) event.getSource();
        int d = 0;
        if(b==northBtn) d = 0;
        if(b==eastBtn) d = 1;
        if(b==southBtn) d = 2;
        if(b==westBtn) d = 3;
        ldc.setDirection(d);
        
    }
    //location display controller interactions
    public void setCloseFunction(){
        Stage currentStage = (Stage) northBtn.getScene().getWindow();
        //setting on close event
        currentStage.setOnCloseRequest(event -> {
            System.out.println("Close ndc");
            sdc.setRoomOpened(false);
        });
        //setting onkeypress
        setOnKeyPressedMoveLocation(ldc);
    }
    public void setOnKeyPressedMoveLocation(LocationDisplayController a){
        northBtn.getScene().setOnKeyPressed(e -> {
            if(e.getCode()==KeyCode.W)ldc.moveShip(e);
        });
    }
    public void close(){
        Stage currentStage = (Stage) northBtn.getScene().getWindow();
        currentStage.hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addToActiveRooms(this);
    }
    
}
