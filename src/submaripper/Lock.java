package submaripper;

/**
 *
 * @author dc_ca
 */
public class Lock {
    private String type, instructions;
    private int amount;
    
    public Lock(String t, int a){
        type = t;
        amount = a;
        setInstuctions();
    }
    
    //setters
    private void setInstuctions(){
        switch(this.type){
            case "TAP_B":
                this.instructions = "Press B " + this.amount + " times to unlock.";
                break;
            case "ALTERNATE_JK":
                this.instructions = "Press J then press K " + this.amount + " times to unlock.";
                break;
            default:
                this.instructions = "Press to unlock.";
        }
    }
    
    //getters
    public String getType(){
        return type;
    }
    public String getInstructions(){
        return instructions;
    }
    public int getAmount(){
        return amount;
    }
}
