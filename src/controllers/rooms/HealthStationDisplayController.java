/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.rooms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML private Text hpText, atkText, regenRateText, speedText, bombText, missileText, healthKitText;
    private boolean useHKThisTurn = false;
    
    @FXML private void useHealthKit(ActionEvent event) {
        if(useHKThisTurn) useHKThisTurn = false;
        else useHKThisTurn = true;
    }
    private void checkLimits(){
        Spatial u = Spatial.getUser();
        if(u.getHealthKitCount()<=0||u.getHP()==u.getMaxHP()){
            healthKitBtn.setDisable(true);
        }
        else healthKitBtn.setDisable(false);
    }
    @FXML private void commenceTurn(ActionEvent event) {
        if(useHKThisTurn){
            Spatial.getUser().addItem(0, 0, -1);
            Spatial.getUser().addHP(Spatial.getUser().getMaxHP());
        }
        useHKThisTurn = false;
        healthKitBtn.setSelected(false);
        
        ldc.commenceTurn();
        update();
    }

    @FXML private void animateImageView(MouseEvent event) {
    }
    public void displayStats(){
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
        regenRateText.setText(""+u.getRegenRate());
        speedText.setText(""+u.getSpeed());
        bombText.setText(""+u.getBombCount());
        missileText.setText(""+u.getMissileCount());
        healthKitText.setText(""+u.getHealthKitCount());
    }
    
    
    //location display controller interactions
    @Override
    public void update(){
        displayStats();
        checkLimits();
    }
    @Override
    public void setCloseFunction(){
        Stage currentStage = (Stage) healthKitBtn.getScene().getWindow();
        //setting on close event
        currentStage.setOnCloseRequest(event -> {
            System.out.println("Close hsdc");
            sdc.setRoomOpened(false);
        });
    }
    @Override
    public void close(){
        Stage currentStage = (Stage) healthKitBtn.getScene().getWindow();
        currentStage.hide();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        update();
    }    

}
