package submaripper;

import java.util.ArrayList;

public class Room {
    private static ArrayList<Room> submarine;
    private static ArrayList<ArrayList<Room>> setUps;
    private static Room user;
    private String name, imgFileName, fxmlFileName, function;
    private int hp, maxHP, sVal, x, y;//sVal will be the special value, ex for turrets it will be damage, for health station it will be health added
    private boolean isPermeable;
    private ArrayList<Lock> locks = new ArrayList<>();
    
    public Room(String n, String ifn, String ffn, int s, String f, int x, int y, boolean p, Lock l){
        name = n;
        imgFileName = ifn;
        fxmlFileName = ffn;
        hp = 10;
        maxHP = 10;
        sVal = s;
        function = f;
        this.x = x;
        this.y = y;
        isPermeable = p;
        locks.add(l);
    }
    public Room(int x, int y){//make walkable floor
        name = "floor";
        imgFileName = "floor.png";
        hp = 10;
        maxHP = 10;
        sVal = -1;
        function = " ";
        this.x = x;
        this.y = y;
        isPermeable = true;
    }
    
    //getters
        //stats
    public String getName(){
        return name;
    }
    public String getImgFileName(){
        return imgFileName;
    }
    public String getFXMLFileName(){
        return fxmlFileName;
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
    public static ArrayList<Room> getSubmarine(){
        return submarine;
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
    public static void setSubmarine(ArrayList<Room> s){
        submarine = s;
    }
        //position
    public void setX(int i) {
        this.x = i;
    }
    public void setY(int i) {
        this.y = i;
    }
        //locks
    public Lock getLock(){
        return locks.get(0);
    }
}
