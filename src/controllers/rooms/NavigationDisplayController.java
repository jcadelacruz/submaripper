package controllers.rooms;

import controllers.LocationDisplayController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorInput;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class NavigationDisplayController extends RoomDisplayController implements Initializable {

    @FXML private Button northBtn, westBtn, southBtn, eastBtn;
    ArrayList<Button> buttonList = new ArrayList<>();

    @FXML private void setDirection(Event event) {
        Button b = (Button) event.getSource();
        for(Button i : buttonList){
            i.setEffect(null);
        }
        ColorInput ce = new ColorInput(0, 0, 60, 60, Color.LIGHTBLUE);
        b.setEffect(ce);
        int d = 0;
        if(b==northBtn) d = 0;
        if(b==eastBtn) d = 1;
        if(b==southBtn) d = 2;
        if(b==westBtn) d = 3;
        ldc.setDirection(d);
        
    }
    //location display controller interactions
    @Override
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
            if(e.getCode()==KeyCode.ENTER)ldc.moveShip(e);
        });
    }
    @Override
    public void close(){
        Stage currentStage = (Stage) northBtn.getScene().getWindow();
        currentStage.hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonList.add(northBtn);
        buttonList.add(westBtn);
        buttonList.add(southBtn);
        buttonList.add(eastBtn);
    }
    
}
