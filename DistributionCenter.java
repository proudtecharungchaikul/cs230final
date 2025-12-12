import java.util.Hashtable; 
/**
 * Write a description of class DistributionCenter here.
 *
 * @author Cindy and Proud
 * @version 12/11/2025
 */
public class DistributionCenter extends Location
{
    // instance variables
    Hashtable<String, Integer> bloodBank; 

    /**
     * Constructor for objects of class DistributionCenter
     */
    public DistributionCenter(String name)
    {
        // initialise instance variables
        super(name); 
        this.bloodBank = new Hashtable<String, Integer>(4); 
        this.bloodBank.put("A", 0); 
        this.bloodBank.put("B", 0);
        this.bloodBank.put("O", 0);
        this.bloodBank.put("AB", 0);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param type - type of blood that needs to be transported
     * @param amount - how many units of blood is needed
     * @param destination - name of hospital to transport to 
     */
    public void transportBlood(String type, int amount, Hospital destination)
    {
        //get current value of the blood type we need
        int currentAmount = this.bloodBank.get(type); 
        //subtract needed amount from current value --> if less than zero, print out alert and transport anyways
        if (currentAmount - amount < 0){
            System.out.println("Not enough blood. Transporting " + currentAmount + " to hospital");
            this.bloodBank.put(type, 0);
            System.out.println("ALERT: calling for donors of blood type " + type); 
            destination.addBlood(type, currentAmount);
        } else {
            //if it's enough blood, transport the full amount to the hospital (subtract from distCen bloodBank
            this.bloodBank.put(type, currentAmount - amount);
            //add to hospital inventory
            destination.addBlood(type, amount);
        }
    }
}