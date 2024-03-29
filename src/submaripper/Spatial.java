package submaripper;

import java.util.ArrayList;
import java.util.Arrays;

public class Spatial{
    private String name, imgFileName;
    private int hp, maxHP, atk, money, x, y;
    private boolean isPermeable;
    private ArrayList<Item> inventory;
    private static Spatial user;
    
    public Spatial(int x, int y){
        name = "rock";
        imgFileName = "rock.png";
        this.x = x;
        this.y = y;
        isPermeable = false;
        inventory = new ArrayList<>();
    }
    public Spatial(String n, String ifn, int h, int a, int m, boolean p, int x, int y){
        name = n;
        imgFileName = ifn;
        hp = h;
        maxHP = h;
        atk = a;
        money = m;
        this.x = x;
        this.y = y;
        isPermeable = p;
        inventory = new ArrayList<>();
    }

    //getters
    public String getName(){
        return name;
    }
    public String getImgFileName(){
        return imgFileName;
    }
    public int[] getPosition() {
        int[] pos = {this.x, this.y};
        return pos;
    }
    public boolean getIsPermeable() {
        return isPermeable;
    }
    public int getHP(){
        return hp;
    }
    public int getAtk(){
        return atk;
    }
    public int getMoney(){
        return money;
    }
        //static
    public static Spatial getUser(){
        return user;
    }
    
    //setters
        //stats
    public void addStat(int[] i){
        addMaxHP(i[1]);
        addHP(i[0]);
        atk = addUntilZero(atk, i[2]);
        money = addUntilZero(money, i[3]);
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
        //position
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
        //static
    public static void setUser(Spatial u){
        user = u;
    }
        //items
    public void addItem(Item i){
        inventory.add(i);
    }
    public void addItem(Item... list){
        this.inventory.addAll(Arrays.asList(list));
    }
}
