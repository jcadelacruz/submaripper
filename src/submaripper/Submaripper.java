/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package submaripper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dc_ca
 */
public class Submaripper extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //initialize
        Location l1 = new Location("Shallow Waters", "shallowWaters.png");
            //Spatial l1s1 = new Spatial("Small Fish", "smallFish.png", 2, 1, 2, true, 4, 3);
            l1.add(s(0,0), s(0,1), s(0,2), s(1,1), s(0,3), s(0, 4), s(1, 3), s(1, 4), s(2, 4), s(4,4), s(7,0), s(0,5));//rocks
        
        Room r1 = new Room("Navigation", "nav.png", 0, "NAVIGATION", 4, 3, false);
        Room r2 = new Room("Weapons", "weapons.png", 2, "WEAPONS", 6, 3, false);
        Room r3 = new Room("Health Station", "hs.png", -1, "HEALTH STATION", 4, 7, false);
        ArrayList<Room> submarine = new ArrayList<>();
        Collections.addAll(submarine, r1, r2, r3, r(4,4), r(4,5), r(6,4), r(6,5), r(5,5), r(4,6));
            
        //user
        Spatial user = new Spatial("Submarine", "submarine.png", 10, 1, 5, false, 2, 2);
        Spatial.setUser(user);
        Room player = new Room("Player", "player.png", 0, " ", 5, 5, false);
        Room.setUser(player);
        Spatial.setSubmarine(submarine);
            
        //open menu
        Parent root = FXMLLoader.load(getClass().getResource("/displays/MenuDisplay.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    public Spatial s(int x, int y){//make impermeable rock
        Spatial rock = new Spatial(x, y);
        return rock;
    }
    public Room r(int x, int y){//make permeable floor
        Room floor = new Room(x, y);
        return floor;
    }
    public static FXMLLoader openFXML(String name, Event e, Class className) throws IOException{
        FXMLLoader loader = null;
        try{
            //get current display
            Node node = (Node) e.getSource();
            Scene currentScene = node.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            //get new display
            loader = new FXMLLoader(className.getResource("/displays/" + name + "Display.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //switch displays
            currentStage.hide();
            currentStage.setScene(scene);
            currentStage.show();
        }
        catch(IOException exception){
            //error
        }
        return loader;
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
