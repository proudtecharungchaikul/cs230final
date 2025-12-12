package Locations;

import java.util.Hashtable; 
/**
 * A type of location that can receive donated blood from Houses and distribute to Hospitals
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
     * Removes specific amount of certain blood type from the bloodBank
     *
     * @param type - type of blood to remove
     * @param amount - amount of blood remove
     */
    public void removeBlood(String type, int amount)
    {
        int currentAmount = this.bloodBank.get(type);
        this.bloodBank.put(type, currentAmount - amount); 
    }
    
    /**
     * Adds specific amount of certain blood type to the bloodBank
     * 
     * @param type - type of blood to add
     * @param amount - amount of blood to add
     */
    public void addBlood(String type, int amount){
        int currentAmount = this.bloodBank.get(type);
        this.bloodBank.put(type, currentAmount + amount);
    }
}
