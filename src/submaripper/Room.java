package submaripper;

import java.util.ArrayList;

public class Room {
    private static ArrayList<ArrayList<Room>> submarine;
    private static int[] userPos;
    private String name, imgFileName, function;
    private int hp, maxHP, sVal;//sVal will be the special value, ex for turrets it will be damage, for health station it will be health added
    
    public Room(String n, String ifn, int h, int s, String f){
        name = n;
        imgFileName = ifn;
        hp = h;
        maxHP = h;
        sVal = s;
        function = f;
    }
    
    //getters
    public String getName(){
        return name;
    }
    public String getIcon(){
        return imgFileName;
    }
    public int getHP(){
        return hp;
    }
    public int getSVal(){
        return sVal;
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
}
