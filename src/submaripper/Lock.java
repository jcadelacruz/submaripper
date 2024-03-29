package submaripper;

/**
 *
 * @author dc_ca
 */
public class Lock {
    private String type;
    private int amount;
    
    public Lock(){}
    public Lock(String t, int a){
        type = t;
        amount = a;
    }
    
    //getters
    public String getType(){
        return type;
    }
    public int getAmount(){
        return amount;
    }
}
