/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import submaripper.*;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class SubmarineDisplayController implements Initializable {

    @FXML private GridPane subGrid;
    private int COL, ROW;
    private ArrayList<ArrayList<ImageView>> imageViews;
    private LocationDisplayController ldc;
    private ArrayList<Room> rooms;
    private Room user;
    private boolean[] allowMoveButtons = new boolean[4];//in order: north, east, south, west

    //main portion
        //set location
    public void setRooms(ArrayList<Room> r){
        rooms = r;
    }
        //display
    public void updateScreen(){
        //System.out.println("perform updateScreen");
        ArrayList<Room> r = rooms;
        setBackground();
        for(Room s : r){
            //System.out.println(" on spatial: "+s.getName());
            int x = s.getPosition()[0];
            int y = s.getPosition()[1];
            //System.out.println("   x: "+x+"  y: "+y);
            Image img = new Image(getClass().getResourceAsStream("/imgs/rooms/"+ s.getImgFileName()));
            ImageView iv = imageViews.get(x).get(y);
            iv.setImage(img);
        }
    }
    public void setBackground(){
        Image img = new Image(getClass().getResourceAsStream("/imgs/rooms/blank.png"));
        for(ArrayList<ImageView> a : imageViews){
            for(ImageView iv : a){
                iv.setImage(img);
            }
        }
    }
        //functionality
    @FXML public void moveUser(KeyEvent e){
        KeyCode keyCode = e.getCode();
        System.out.println(" Key pressed: " + keyCode);
        if(keyCode.getName().equals("W")){
            commenceMove(0);
        }
        if(keyCode.getName().equals("D")){
            commenceMove(1);
        }
        if(keyCode.getName().equals("S")){
            commenceMove(2);
        }
        if(keyCode.getName().equals("A")){
            commenceMove(3);
        }
        checkKeyEvent(keyCode);
    }
    private void checkKeyEvent(KeyCode k){
        Room u = user;
        int[] pos = u.getPosition();
        if(k.getName().equals("T")){// && ( (pos[0]==room.getPosition()[0]) && (pos[1]==room.getPosition()[1]) )){
            //open room
        }
    }
    private void commenceMove(int direction){
        System.out.println("perform commenceMove, direction: " + direction);
        if(checkOpenSpace(direction)){
            //System.out.println(" distance from edge:" + checkDistanceFromEdge(direction));
            movePlayerPos(direction);
        }
        else{
            //not open space
        }
        updateScreen();
    }
    private void movePlayerPos(int direction){
        //System.out.println("perform movePlayerPos");
        if(direction>3){
            int subtract = (direction)/4;
            direction -= subtract*4;
        }
        int[] pos = user.getPosition();
        /*System.out.println(" user pos before: " + user.getPosition()[0] + ", " + user.getPosition()[1]);
        System.out.println(" pos before: " + pos[0] + ", " + pos[1]);
        System.out.println(" direction: " + direction);*/
        switch(direction){
            case 0:
                user.setY(pos[1]-1);
                break;
            case 1:
                user.setX(pos[0]+1);
                break;
            case 2:
                user.setY(pos[1]+1); 
                break;
            case 3:
                user.setX(pos[0]-1);
                break;
        }
        //System.out.println(" user pos after: " + user.getPosition()[0] + ", " + user.getPosition()[1]);
    }
    private boolean checkOpenSpace(int direction){
        boolean open = false;
        //Room l = user;
        
        if(checkDistanceFromEdge(direction)<=0) return false;
        
        movePlayerPos(direction);
        for(Room s : rooms){
            if( (s.getPosition()[0] == user.getPosition()[0]) && (s.getPosition()[1] == user.getPosition()[1]) && (s!=user)){
                if(s.getIsPermeable()){
                    open = true;
                }
            }
        }
        movePlayerPos(direction+2);
        
        return open;
    }
    private int checkDistanceFromEdge(int direction){
        System.out.print("perform checkDistanceFromEdge: ");
        if(direction>3){
            int subtract = (direction)/4;
            direction -= subtract*4;
        }
        //System.out.println(" direction: "+ direction);
        int distance = 0;
        switch(direction){
            case 0://north
                distance = user.getPosition()[1];
                break;
            case 1://east
                distance = (COL-1) - (user.getPosition()[0]);
                break;
            case 2://south
                distance = (ROW-1) - (user.getPosition()[1]);
                break;
            case 3://west
                distance = user.getPosition()[0];
                break;
            default:
                //System.out.println("Direction not found");
        }
        //System.out.println("  " + distance);
        return distance;
    }
    
    //initialize imageviews
    private void setImageViews(){
        //System.out.println("perform setImageViews");
        imageViews = new ArrayList();
        
        subGrid.getColumnConstraints().clear(); // Clear column constraints
        subGrid.getRowConstraints().clear(); // Clear row constraints
        
        for(int c = 0; c<COL; c++){
            ArrayList<ImageView> ar = new ArrayList();
            imageViews.add(ar);
            for(int r = 0; r<ROW; r++){
                ImageView iv = (ImageView) getChildByColRowIndex(c, r, subGrid);
                iv.setPreserveRatio(true);
                iv.setFitWidth(40); // width of image
                iv.setFitHeight(40); // height of image
                imageViews.get(c).add(iv);
            }
            ColumnConstraints column = new ColumnConstraints(40); // Set the width of each column to 74 pixels
            subGrid.getColumnConstraints().add(column);
        }
        for(int r = 0; r<ROW; r++){
            RowConstraints row = new RowConstraints(40); // Set the height of each row to 74 pixels
            subGrid.getRowConstraints().add(row);
        }
    }
    private Node getChildByColRowIndex(int col, int row, GridPane gridPane){
        //System.out.println("perform getChildByColRowIndex");
        Node result = null;

        for (Node node : gridPane.getChildren()) {
            //get location in gridpane
            Integer gRow = GridPane.getRowIndex(node);
            Integer gCol = GridPane.getColumnIndex(node);
            if (gRow == null) gRow = 0;
            if (gCol == null) gCol = 0;
            //see if matches;
            if (gRow == row && gCol == col) {
                result = node;
                break;
            }
        }

        //System.out.println(result);
        return result;
    }
    //LocationDisplayController interactions
    private void setCloseFunction(){
        Stage currentStage = (Stage) subGrid.getScene().getWindow();
        //setting on close event
        currentStage.setOnCloseRequest(event -> {
            System.out.println("Close sd");
            ldc.setSubmarineOpened(false);
        });
        subGrid.getScene().setOnKeyPressed(e -> {
            moveUser(e);
        });
    }
    public void setLocationDisplayController(LocationDisplayController l){
        ldc = l;
        setCloseFunction();
    }
    public void close(){
        Stage currentStage = (Stage) subGrid.getScene().getWindow();
        currentStage.hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize submarine display controller");
        //initialize
            //numbers
        COL = 10;
        ROW = 10;
            //submarine
        rooms = Spatial.getSubmarine();
        rooms.add(Room.getUser());
        user = Room.getUser();
            //iv
        setImageViews();
        
        updateScreen();
        System.out.println("done initializing submarine display controller");
    }    
    
}
