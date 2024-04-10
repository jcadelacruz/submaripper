/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import controllers.rooms.RoomDisplayController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import submaripper.*;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class LocationDisplayController implements Initializable {

    @FXML private Text nameText, instructions;
    @FXML private Button optionsBtn;
    @FXML private VBox optionsVBox;
    @FXML private GridPane locGrid;
    private Location currLoc;
    private int ROW=5, COL=5, startX, startY, direction = 0, turn = 0;
    private ArrayList<ArrayList<ImageView>> imageViews;
    private Spatial user;
    private boolean submarineOpened;
    private SubmarineDisplayController sdc;
    private Room latestRoom;

    //options tab
    @FXML private void displayOptions(ActionEvent event) {
        //toggle options button function
        optionsBtn.setOnAction(e -> {
            optionsVBox.getChildren().clear();
            optionsBtn.setOnAction(eve -> {
                displayOptions(eve);
            });
        });
        
        //add elements
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> {
            try{
                FXMLLoader loader = Submaripper.openFXML("Menu", e, this.getClass());
            }
            catch(Exception ex){
                //
            }
        });
        optionsVBox.getChildren().add(exitBtn);
    }
    private void hideOptions(){
        optionsBtn.setDisable(false);
    }
    
    //main portion
        //set location
    public void setLocation(Location l){
        nameText.setText(l.getName());
        currLoc = l;
        Location.setCurrentLocation(l);
    }
        //display
    public void updateScreen(){
        //System.out.println("perform updateScreen");
        Location l = currLoc;
        setBackground(l);
        for(Spatial s : l.getContents()){
            //System.out.println(" on spatial: "+s.getName());
            int x = s.getPosition()[0];
            int y = s.getPosition()[1];
            //System.out.println("   x: "+x+"  y: "+y);
            if((startX <= x) && (x < (COL + startX))){
                if((startY <= y) && (y < (ROW + startY))){
                    Image img = new Image(getClass().getResourceAsStream("/imgs/spatials/"+ s.getImgFileName()));
                    ImageView iv = imageViews.get(x-startX).get(y-startY);
                    iv.setImage(img);
                }
            }
        }
    }
    public void setBackground(Location l){
        Image img = new Image(getClass().getResourceAsStream("/imgs/locations/"+ l.getImgFileName()));
        for(ArrayList<ImageView> a : imageViews){
            for(ImageView iv : a){
                iv.setImage(img);
            }
        }
    }
        //functionality
    @FXML public void moveShip(KeyEvent e){
        KeyCode keyCode = e.getCode();
        System.out.println("Key pressed: " + keyCode);
        if(keyCode.equals(KeyCode.ENTER)){
            attemptPlayerMove(direction);
            commenceTurn();
        }
        if(keyCode.getName().equals("E")){
            if(!submarineOpened) openSubmarine();
        }
        if(keyCode.getName().equals("F")){
            if(latestRoom!=null){
                openSubmarine();
                sdc.openRoom(latestRoom);
            }
        }
        RoomDisplayController.getActiveRooms().get(0).update();
    }
    public void setDirection(int i){
        direction = i;
    }
    private void attemptPlayerMove(int direction){
        //System.out.println("perform commenceMove, direction: " + direction);
        Spatial u = Spatial.getUser();
        if(checkOpenSpace(u, direction)){
            //System.out.println(" distance from edge:" + checkDistanceFromEdge(direction));
            int key = checkSpatialDistanceFromEdge(u, direction);
            if(checkSpatialDistanceFromEdge(u, direction+2)<2) key = 1;
            switch(key){//distance before movement
                case 0://at edge
                    //should be impossible
                    break;
                case 1://one away from edge
                    moveSpatialPos(u, direction);
                    break;
                case 2://at far position, but moves into edges
                    moveSpatialPos(u, direction);
                    break;
                default://at far position, three away from edge or more
                    moveSpatialPos(u, direction);
                    moveStartPos(direction);
            }
        }
        else{
            //not open space
        }
        //updateScreen();
    }
    private int checkSpatialDistanceFromEdge(Spatial u, int direction){
        //System.out.print("perform checkDistanceFromEdge: ");
        if(direction>3){
            int subtract = (direction)/4;
            direction -= subtract*4;
        }
        //System.out.println(" direction: "+ direction);
        int distance = 0;
        switch(direction){
            case 0://north
                distance = u.getPosition()[1];//+ startY
                break;
            case 1://east
                distance = currLoc.getSize()[0] - (u.getPosition()[0]);// + startX);
                break;
            case 2://south
                distance = currLoc.getSize()[1] - (u.getPosition()[1]);// + startY);
                break;
            case 3://west
                distance = u.getPosition()[0];//startX + 
                break;
            default:
                //System.out.println("Direction not found");
        }
        //System.out.println("  " + distance);
        return distance;
    }
    private void moveSpatialPos(Spatial s, int direction){
        //System.out.println("perform moveSpatialPos");
        if(direction>3){
            int subtract = (direction)/4;
            direction -= subtract*4;
        }
        int[] pos = s.getPosition();
        /*System.out.println(" s pos before: " + s.getPosition()[0] + ", " + s.getPosition()[1]);
        System.out.println(" pos before: " + pos[0] + ", " + pos[1]);
        System.out.println(" direction: " + direction);*/
        switch(direction){
            case 0:
                s.setY(pos[1]-1);
                break;
            case 1:
                s.setX(pos[0]+1);
                break;
            case 2:
                //System.out.println("" + pos[0] + " and " + pos[1]);
                s.setPos(pos[0], pos[1]+1); 
                break;
            case 3:
                s.setX(pos[0]-1);
                break;
        }
        //System.out.println(" s pos after: " + s.getPosition()[0] + ", " + s.getPosition()[1]);
    }
    private void moveStartPos(int direction){
        //System.out.println("perform moveStartPos");
        switch(direction){
            case 0:
                startY--;
                break;
            case 1:
                startX++;
                break;
            case 2:
                startY++;
                break;
            case 3:
                startX--;
                break;
        }
        //System.out.println(" start pos: " + startX + ", " + startY);
    }
    private boolean checkOpenSpace(Spatial r, int direction){
        boolean open = true;
        Location l = currLoc;
        
        if(checkSpatialDistanceFromEdge(r, direction)<=0) return false;
        
        moveSpatialPos(r, direction);
        for(Spatial s : l.getContents()){
            if( (s.getPosition()[0] == r.getPosition()[0]) && (s.getPosition()[1] == r.getPosition()[1]) && (s!=r)){
                if(!s.getIsPermeable()){
                    open = false;
                }
            }
        }
        moveSpatialPos(r, direction+2);
        
        return open;
    }
            //turn system
    public void commenceTurn(){
        Location l = this.currLoc;
        Spatial u = Spatial.getUser();
        
        for(Spatial s : l.getContents()){
            if(s==u || s.getName().equals("rock")){}
            else{
                int speed = s.getSpeed();
                if(speed<u.getSpeed()){//speed is slower
                    if(turn%u.getSpeed()==0){
                        commenceSpatialTurn(s);
                    }
                }
                else{//speed is equal to or faster
                    for(int i = 0; i<(speed/u.getSpeed()); i++){
                        commenceSpatialTurn(s);
                    }
                }
            }
        }
        u.turnUpdate();
        
        //display
        updateScreen();
    }
    private void commenceSpatialTurn(Spatial s){
        int agro = -1;
        String ag = s.getAggression();
        //determining agro
        if(ag.equals("RANDOM")) agro = 0;
        if(ag.equals("CHASE")) agro = 2;
        //turn update (regen, 
        s.turnUpdate();
        //movement
        switch(agro){//s.getAggression()){
            case 0://random move, no atk
                double d = Math.random() * 4;
                int dir = (int) d;
                if(checkOpenSpace(s, dir)) moveSpatialPos(s, dir);
                break;
            default:
                System.out.println("aggression not found");
        }
        //attack
        boolean userIsDead = false;
        if(isPlayerNearby(s)) userIsDead = s.attackAndGetIsDead(Spatial.getUser());
        switch(agro){
            case 2:
                if(isPlayerNearby(s)) userIsDead = s.attackAndGetIsDead(Spatial.getUser());
                break;
            default:
        }
        if(userIsDead) spatialDeath(Spatial.getUser());
    }
    private void spatialDeath(Spatial s){
        ArrayList<Spatial> contents = Location.getCurrentLocation().getContents();
        contents.remove(s);
        if(s==Spatial.getUser()){
            //death stuff if player
        }
    }
    private boolean isPlayerNearby(Spatial s){
        boolean nearby = false;
        Spatial u = Spatial.getUser();
        int[] uPos = u.getPosition(), sPos = s.getPosition();
        
        if( ((sPos[0]==(uPos[0]-1)) || (sPos[0]==(uPos[0]+1))) && (uPos[1]==sPos[1]) ){
            nearby = true;
        }
        if( ((sPos[1]==(uPos[1]-1)) || (sPos[1]==(uPos[1]+1))) && (uPos[0]==sPos[0]) ){
            nearby = true;
        }
        return nearby;
    }
    
    //submarine
        //room
    public void setLatestRoom(Room r){
        latestRoom = r;
    }
    public void openSubmarine(){
        try{
            //close active rooms
            RoomDisplayController.closeActiveRooms();
            //get new display
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/displays/SubmarineDisplay.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //open display
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
            //set submarine display controller functions
            sdc = loader.getController();
            sdc.setLocationDisplayController(this);
                //set synchro closed
            setCloseFunction(sdc);
            //submarine opened
            submarineOpened = true;
        }
        catch(IOException e){
            //
        }
    }
    public void setSubmarineOpened(boolean opened){
        submarineOpened = opened;
    }
        //synchronized close
    public void setCloseFunction(SubmarineDisplayController s){
        Stage currentStage = (Stage) locGrid.getScene().getWindow();
        //setting on close event
        currentStage.setOnCloseRequest(event -> {
            s.close();
        });
    }
    public void setCloseFunction(RoomDisplayController rdc){
        Stage currentStage = (Stage) locGrid.getScene().getWindow();
        //setting on close event
        currentStage.setOnCloseRequest(event -> {
            rdc.close();
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println("initialize location display controller");
        //initialize
        startX = 0;
        startY = 0;
        COL = 5;
        ROW = 5;
        setImageViews();
        setLocation(Location.getLocationList().get(0));
        user = Spatial.getUser();
        currLoc.add(user);
        submarineOpened = false;
        //set display 
        updateScreen();
        setInstructions();
    }
        //initialize imageviews
    private void setImageViews(){
        //System.out.println("perform setImageViews");
        imageViews = new ArrayList();
        
        locGrid.getColumnConstraints().clear(); // Clear column constraints
        locGrid.getRowConstraints().clear(); // Clear row constraints
        
        for(int c = 0; c<COL; c++){
            //System.out.println(" first for: " + c);
            ArrayList<ImageView> ar = new ArrayList();
            imageViews.add(ar);
            for(int r = 0; r<ROW; r++){
                //System.out.println("  second for: " + r);
                ImageView iv = (ImageView) getChildByColRowIndex(c, r, locGrid);
                iv.setPreserveRatio(true);
                iv.setFitWidth(74);
                iv.setFitHeight(74);
                imageViews.get(c).add(iv);
            }
            ColumnConstraints column = new ColumnConstraints(74); // Set the width of each column to 74 pixels
            locGrid.getColumnConstraints().add(column);
        }
        for(int r = 0; r<ROW; r++){
            RowConstraints row = new RowConstraints(74); // Set the height of each row to 74 pixels
            locGrid.getRowConstraints().add(row);
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
    //instructions
    public void setInstructions(){
        String s = "ENTER to move\nE to open submarine\nF to open latest room";
        instructions.setText(s);
        instructions.setFont(Font.font("Arial", 16));
    }
}
