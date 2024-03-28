package submaripper;

import java.util.ArrayList;
import java.util.Arrays;

public class Location {
    private ArrayList<Spatial> contents;
    private String name, imgFileName;
    private static ArrayList<Location> locationList = new ArrayList<>();
    
    public Location(String n, String ifn){
        name = n;
        imgFileName = ifn;
        contents = new ArrayList<>();
        locationList.add(this);
    }
    //getters
    public String getName(){
        return name;
    }
    public String getImgFileName(){
        return imgFileName;
    }
    public ArrayList<Spatial> getContents() {
        return contents;
    }
    public int[] getRangeOfSurface(){
        //initialize key
        String str = "";
        
        //subtract from key
            //initialize subtract
        boolean[] subtract = new boolean[getSize()[0]];
        for(int b = 0; b<getSize()[0]; b++){
            subtract[b] = false;//initialize subtract
        }
            //set where to subtract
        for(Spatial s : contents){
            if(s.getPosition()[1] == 0){//if y = 0
                subtract[s.getPosition()[0]] = true;//then its x is occupied
            }
        }
        for(int p = 0; p<getSize()[0]; p++){
            if(p == 0){
                if(subtract[p]) str += "" + p;
                continue;
            }
            if(subtract[p]&&!(subtract[p-1]&&subtract[p+1])){
                str += "" + p;
            }
        }
        
        //key to range (string to int array of its characters)
        int[] pos = new int[str.length()];
        for(int i = 0; i<str.length(); i++){
            pos[i] = Integer.parseInt(""+str.charAt(i));
        }
        return pos;
    }
    public int[] getSize(){
        int maxX = 0, maxY = 0;
        for(Spatial s : contents){
            int[] pos = s.getPosition();
            if(pos[0]>maxX) maxX = pos[0];
            if(pos[1]>maxY) maxY = pos[1];
        }
        int[] ret = {maxX, maxY};
        return ret;
    }
        //static
    public static ArrayList<Location> getLocationList(){
        return locationList;
    }
    //setters
    public void add(Spatial s){
        contents.add(s);
    }
    public void add(Spatial... list) {
        this.contents.addAll(Arrays.asList(list));
    }

}
