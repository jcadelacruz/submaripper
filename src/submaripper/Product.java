/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package submaripper;

import java.util.ArrayList;

/**
 *
 * @author dc_ca
 */
public class Product {
    
    private String name, imgFileName, info;
    private int price, hp, maxHP, atk, speed, regenRate, bomb, missile, firstAidKit;
    private static ArrayList<Product> onSale = new ArrayList<>();

    public Product(String n, String ifn, int p, int[] e, String i){
        name = n;
        imgFileName = ifn;
        price = p;
            //info
        info = i;
            //effects
        hp = e[0];
        maxHP = e[1];
        atk = e[2];
        speed = e[3];
        regenRate = e[4];
        bomb = e[5];
        missile = e[6];
        firstAidKit = e[7];
            //static
        onSale.add(this);
    }

    //getters
    public String getName() {
        return name;
    }
    public String getImgFileName() {
        return imgFileName;
    }
    public String getInfo() {
        return info;
    }
    public int getPrice() {
        return price;
    }
    public int[] getEffects(){
        int[] effect = {hp, maxHP, atk, speed, regenRate, bomb, missile, firstAidKit};
        return effect;
    }
        //static
    public static ArrayList<Product> getOnSale() {
        return onSale;
    }
}
