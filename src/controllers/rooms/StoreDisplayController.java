/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.rooms;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import submaripper.*;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class StoreDisplayController extends RoomDisplayController implements Initializable {

    @FXML private Button prevBtn, nextBtn, buyBtn;
    @FXML private ImageView icon1, icon2, icon3;
    @FXML private Text name1, name2, name3, infoText, updateText, moneyText;
    private int shift = 0, SIZE = 3;
    private ArrayList<ImageView> imageViews;
    private ArrayList<javafx.scene.text.Text> texts;
    private Product selectedProduct;

    @FXML private void prevSet(Event e){
        shift--;
        updateScreen();
    }
    @FXML private void nextSet(Event e){
        shift++;
        updateScreen();
    }
    @FXML private void buyProduct(Event e){
        Spatial.getUser().buyProduct(selectedProduct);
        updateScreen();
    }
    private void updateScreen(){
        //clearSet();
        displaySet();
        checkLimit();
        checkWallet(Product.getOnSale().get(shift).getPrice());
        //display Money
        moneyText.setText("Your Money: " + Spatial.getUser().getMoney() + " coins");
    }
    private void clearAtIndex(int i){
        //for(int i = 0; i<SIZE; i++){
            //image icon
            imageViews.get(i).setImage(null);
            //name
            texts.get(i).setText("");
        //}
        //info
        //infoText.setText("");
    }
    private void displaySet(){
        for(int i = 0; i<SIZE; i++){
            try{
                Product p = Product.getOnSale().get(i+shift-1);
                //image icon
                try{
                    Image img = new Image("/imgs/store/" + p.getImgFileName());
                    imageViews.get(i).setImage(img);
                }
                catch(Exception e){
                    //
                }
                //name
                texts.get(i).setText(p.getName() + " ["+p.getPrice()+"]");
            }
            catch(Exception e){
                clearAtIndex(i);
            }
        }
        //selected Product
        selectedProduct = Product.getOnSale().get(shift);
        //info
        infoText.setText(selectedProduct.getInfo());

    }
    private void checkLimit(){
        prevBtn.setDisable(false);
        nextBtn.setDisable(false);
        if(shift<=0) prevBtn.setDisable(true);
        if(shift>=(Product.getOnSale().size()-1)) nextBtn.setDisable(true);
    }
    private void checkWallet(int price){
        buyBtn.setDisable(false);
        if(price>Spatial.getUser().getMoney()) buyBtn.setDisable(true);
    }
    private void initializeImageViewsAndTexts(){
        imageViews = new ArrayList<>();
        imageViews.addAll(Arrays.asList(icon1, icon2, icon3));
        texts = new ArrayList<>();
        texts.addAll(Arrays.asList(name1, name2, name3));
    }
    //location displayControllerInteractions
    public void setCloseFunction(){
        Stage currentStage = (Stage) prevBtn.getScene().getWindow();
        //setting on close event
        currentStage.setOnCloseRequest(event -> {
            System.out.println("Close storedc");
            sdc.setRoomOpened(false);
        });
    }
    public void close(){
        Stage currentStage = (Stage) prevBtn.getScene().getWindow();
        currentStage.hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addToActiveRooms(this);
        initializeImageViewsAndTexts();
        updateScreen();
    }    
    
}
