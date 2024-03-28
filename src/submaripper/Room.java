package submaripper;

import java.util.ArrayList;

public class Room {
    private static ArrayList<ArrayList<Room>> submarine;
    private static int[] userPos;
    private static ArrayList<ArrayList<Room>> setUps;
    private String name, imgFileName, function;
    private int hp, maxHP, sVal, x, y;//sVal will be the special value, ex for turrets it will be damage, for health station it will be health added
    private boolean isPermeable;
    private static Room user;
    
    public Room(String n, String ifn, int s, String f, int x, int y, boolean p){
        name = n;
        imgFileName = ifn;
        hp = 10;
        maxHP = 10;
        sVal = s;
        function = f;
        this.x = x;
        this.y = y;
        isPermeable = p;
    }
    
    //getters
        //stats
    public String getName(){
        return name;
    }
    public String getImgFileName(){
        return imgFileName;
    }
    public int getHP(){
        return hp;
    }
    public int getSVal(){
        return sVal;
    }
    public boolean getIsPermeable() {
        return isPermeable;
    }
        //position
    public int[] getPosition() {
        int[] pos = {this.x, this.y};
        return pos;
    }
        //static
    public static ArrayList<ArrayList<Room>> getSetUps() {
        return setUps;
    }
    public static Room getUser() {
        return user;
    }
    
    //setters
        //stats
    public void addStat(int[] i){
        addMaxHP(i[1]);
        addHP(i[0]);
        sVal = addUntilZero(sVal, i[2]);
    }
    public void addHP(int h){
        int health = hp + h;
        if(health>maxHP) health = maxHP;
        if(health<0) health = 0;
        hp = health;
    }
    public void addMaxHP(int h){
        maxHP += h;
        addHP(0);
    }
    public int addUntilZero(int currVal, int add){
        int sum = currVal + add;
        if(sum<0) sum = 0;
        return sum;
    }
        //static
    public static void setUser(Room u) {
        user = u;
    }
        //position
    public void setX(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void setY(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
