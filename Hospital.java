import java.util.Hashtable; 
/**
 * Write a description of class Hospital here.
 *
 * @author Cindy and Proud
 * @version 12/11/2025
 */
public class Hospital extends Location
{
    // instance variables
    Hashtable<String, Integer> inventory; 

    /**
     * Constructor for objects of class Hospital
     */
    public Hospital(String name)
    {
        // initialise instance variables
        super(name); 
        this.inventory = new Hashtable<String, Integer>(4); 
        this.inventory.put("A", 0); 
        this.inventory.put("B", 0);
        this.inventory.put("O", 0);
        this.inventory.put("AB", 0);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  type of blood to use
     * @param amount of blood used
     */
    public void useBlood(String type, int amount)
    {
        //get current value of the blood type we need to use
        int currentAmount = this.inventory.get(type);
        int missingAmount = amount - currentAmount; 
        //subtract needed amount from current value --> if less than zero, print out alert and transport anyways
        if (currentAmount - amount < 0){
            System.out.println("Not enough blood. Only have " + currentAmount);
            this.inventory.put(type, 0);
            System.out.println("ALERT: please transport at least " + missingAmount + " of blood type " + type + " from nearest distribution center.");
        } else {
            this.inventory.put(type, currentAmount - amount);
        }
    }
    
    /**
     * @param type of blood
     * @param amount of blood added //FIX: shouldn't be public?
     */
    public void addBlood(String type, int amount){
        int currentAmount = this.inventory.get(type);
        this.inventory.put(type, currentAmount + amount);
    }
}