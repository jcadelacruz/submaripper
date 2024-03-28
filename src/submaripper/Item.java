package submaripper;

public class Item {
    private String name, imgFileName;
    private int hp, damage, cost;
    
    public Item(String n, String ifn, int h, int d, int c){
        name = n;
        imgFileName = ifn;
        hp = h;
        damage = d;
        cost = c;
    }
}
