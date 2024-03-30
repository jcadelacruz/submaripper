/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.rooms;

import controllers.*;
import java.util.ArrayList;
import javafx.stage.Stage;

/**
 *
 * @author dc_ca
 */
public abstract class RoomDisplayController {
    protected SubmarineDisplayController sdc;
    protected LocationDisplayController ldc;
    protected static ArrayList<RoomDisplayController> activeRooms = new ArrayList<>();
    
    public static ArrayList<RoomDisplayController> getActiveRooms(){
        return activeRooms;
    }
    public static void addToActiveRooms(RoomDisplayController r){
        activeRooms.add(r);
    }
    
    //LocationDisplayController interactions
    public abstract void setCloseFunction();
    public abstract void close();
    public void setSubmarineAndLocationDisplayControllers(SubmarineDisplayController a, LocationDisplayController l) {
        sdc = a;
        ldc = l;
        setCloseFunction();
    }
}
