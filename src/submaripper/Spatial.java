package submaripper;

public class Spatial{
    private String name, imgFileName, aggression;
    private int hp, maxHP, atk, speed, regenRate, money, x, y, bombCount, missileCount, healthKitCount, turnsSinceAttacked=0;
    private boolean isPermeable;
    private static Spatial user;
    private static int REGENDELAY=5;
    
    public Spatial(int x, int y){
        name = "rock";
        imgFileName = "rock.png";
        this.x = x;
        this.y = y;
        isPermeable = false;
    }
    public Spatial(String n, String ifn, int h, int a, int s, int r, boolean p, int m, int bc, int mc, int hkc, String agro, int x, int y){
        name = n;
        imgFileName = ifn;
        hp = h;
        maxHP = h;
        atk = a;
        speed = s;
        regenRate = r;
        isPermeable = p;
        money = m;
        bombCount = bc;
        missileCount = mc;
        healthKitCount = hkc;
        aggression = agro;
        this.x = x;
        this.y = y;
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
    public int getMaxHP(){
        return maxHP;
    }
    public int getAtk(){
        return atk;
    }
    public int getSpeed(){
        return speed;
    }
    public int getRegenRate(){
        return regenRate;
    }
        //items
    public int getMoney(){
        return money;
    }
    public int getBombCount(){
        return bombCount;
    }
    public int getMissileCount(){
        return missileCount;
    }
    public int getHealthKitCount(){
        return healthKitCount;
    }
        //agro
    public String getAggression(){
        return aggression;
    }
        //static
    public static Spatial getUser(){
        return user;
    }
    
    //setters
        //stats
    public void addStat(int a, int b, int c, int d, int e, int f){
        addMaxHP(b);
        addHP(a);
        atk = addUntilZero(atk, c);
        speed = addUntilZero(speed, d);
        regenRate = addUntilZero(regenRate, e);
        money = addUntilZero(money, f);
    }
    public void addStat(int[] i){
        addMaxHP(i[1]);
        addHP(i[0]);
        atk = addUntilZero(atk, i[2]);
        speed = addUntilZero(speed, i[3]);
        regenRate = addUntilZero(regenRate, i[4]);
        money = addUntilZero(money, i[5]);
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
        //item counts
    public void addItem(int bomb, int missile, int hk){
        bombCount = addUntilZero(bombCount, bomb);
        missileCount = addUntilZero(missileCount, missile);
        healthKitCount = addUntilZero(healthKitCount, hk);
    }
    
    //methods
    public void buyProduct(Product p){
        int e[] = p.getEffects();
        this.addStat(e[0], e[1], e[2], e[3], e[4], -p.getPrice());
        this.addItem(e[5], e[6], e[7]);
    }
    public void turnUpdate(){
        turnsSinceAttacked++;
        regenerate();
    }
    public boolean attackAndGetIsDead(Spatial victim){
        victim.addHP(-this.getAtk());
        //turnsSinceAttacked
        victim.turnsSinceAttacked = 0;
        boolean victimIsDead = false;
        if(victim.getHP()==0){
            victimIsDead = true;
        }
        return victimIsDead;
    }
    public void regenerate(){
        if(turnsSinceAttacked>REGENDELAY) this.addHP(this.regenRate);
    }
}
