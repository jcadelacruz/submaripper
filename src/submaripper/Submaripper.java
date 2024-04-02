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
        //INITIALIZE
            //location
        Location l1 = new Location("Shallow Waters", "shallowWaters.png");
            Spatial l1s1 = new Spatial("Small Fish", "submarine.png", 2, 1, 1, 0, false, 0, 0, 0, 5, "RANDOM", 4, 3);
            Spatial l1s2 = new Spatial("Small Fish", "submarine.png", 2, 1, 1, 0, false, 0, 0, 0, 5, "RANDOM", 5, 4);
            l1.add(s(0,0), s(0,1), s(0,2), s(1,1), s(0,3), s(0, 4), s(1, 3), s(1, 4), s(2, 4), s(4,4), s(7,0), s(0,5), l1s1, l1s2);//rocks
        
            //locks
        Lock tapBThrice = new Lock("TAP_B", 3);
        Lock tapBTwice = new Lock("TAP_B", 2);
        Lock tapBOnce = new Lock("TAP_B", 1);
        Lock alternateJKTwice = new Lock("ALTERNATE_JK", 4);
        Lock placeholder = new Lock("", -1);
            
            //rooms
        Room r1 = new Room("Navigation", "nav.png", "Navigation", 3, 5, false, tapBTwice);
        Room r2 = new Room("Weapons", "weapons.png", "Weapons", 4, 3, false, alternateJKTwice);
        Room r3 = new Room("Health Station", "hs.png", "HealthStation", 4, 7, false, tapBOnce);
        Room r4 = new Room("Store", "store.png", "Store", 6, 3, false, tapBThrice);
        Room r5 = new Room("Repair Station", "repair.png", "RepairStation", 6, 7, false, tapBThrice);
            
            //products
        Product p1 = new Product("Health Kits", "healthKits.png", 2, arr(0,0,0,0,0,0,0,1), "Adds One Health Kit to your count; one Health Kit restores your whole health in one turn, instead of waiting for your health to regen.");
        Product p2 = new Product("Bombs", "bombs.png", 3, arr(0,0,0,0,0,1,0,0), "Adds One Bomb to your count; one bomb deals 5 damage across a 3x3 area. Takes one turn to land.");
        Product p3 = new Product("Missile", "missile.png", 3, arr(0,0,0,0,0,0,1,0), "Adds One Missile to your count; one missile deals 5*base damage and travels in one of the 8 directions. Has a speed of 5 squares per turn.");
        Product p4 = new Product("Damage", "damage.png", 20, arr(0,0,1,0,0,0,0,0), "Adds One to your base damage.");
        
        //user
            //spatial
        Spatial user = new Spatial("Submarine", "submarine.png", 10, 1, 1, 1, false, 5, 5, 5, 5, "RANDOM", 2, 2);
        Spatial.setUser(user);
            //room
        Room player = new Room("Player", "player.png", " ", 5, 5, false, placeholder);
        Room.setUser(player);
            //user's submarine (set of rooms)
        ArrayList<Room> submarine = new ArrayList<>();
        Collections.addAll(submarine, r1, r2, r3, r4, r(4,4), r(4,5), r(6,4), r(6,5), r(5,5), r(4,6));
        Room.setSubmarine(submarine);
            
        //open menu
        Parent root = FXMLLoader.load(getClass().getResource("/displays/MenuDisplay.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    public Spatial s(int x, int y){//make impermeable rock in map
        Spatial rock = new Spatial(x, y);
        return rock;
    }
    public Room r(int x, int y){//make permeable floor in rooms
        Room floor = new Room(x, y);
        return floor;
    }
    public int[] arr(int a, int b, int c, int d, int e, int f, int g, int h){
        int[] array = {a,b,c,d,e,f,g,h};
        return array;
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
    public static FXMLLoader openFXML(String name, Node n, Class className) throws IOException{
        FXMLLoader loader = null;
        try{
            //get current display
            Scene currentScene = n.getScene();
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
