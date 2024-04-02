/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.rooms;

import controllers.LocationDisplayController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import submaripper.Room;
import submaripper.Spatial;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class HealthStationDisplayController extends RoomDisplayController implements Initializable {

    @FXML private ToggleButton healthKitBtn;
    @FXML private Button commenceTurn;
    @FXML private ImageView imgView;
    @FXML private Text hpText, atkText, speedText, bombText, missileText, healthKitText;
    
    @FXML private void useHealthKit(ActionEvent event) {
    }

    @FXML private void commenceTurn(ActionEvent event) {
    }

    @FXML private void animateImageView(MouseEvent event) {
    }
    private void displayStats(){
        Room user = Room.getUser();
        try{
            Image img = new Image(getClass().getResourceAsStream("/imgs/rooms/"+user.getImgFileName()));//can be a different image
            imgView.setImage(img);
        }
        catch(Exception e){
            
        }
        Spatial u = Spatial.getUser();
        hpText.setText("HP: " + u.getHP() + "/" + u.getMaxHP());
        atkText.setText(""+u.getAtk());
        speedText.setText(""+u.getSpeed());
        bombText.setText(""+u.getBombCount());
        missileText.setText(""+u.getMissileCount());
        healthKitText.setText(""+u.getHealthKitCount());
    }
    
    
    //location display controller interactions
    public void setCloseFunction(){
        Stage currentStage = (Stage) healthKitBtn.getScene().getWindow();
        //setting on close event
        currentStage.setOnCloseRequest(event -> {
            System.out.println("Close hsdc");
            sdc.setRoomOpened(false);
        });
    }
    public void close(){
        Stage currentStage = (Stage) healthKitBtn.getScene().getWindow();
        currentStage.hide();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayStats();
    }    

}
