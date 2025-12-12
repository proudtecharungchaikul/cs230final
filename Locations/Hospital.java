package Locations;

import java.util.Hashtable; 
/**
 * A type of location that can use blood and ask for blood from Distribution Centers
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
     * Removes specific amount of certain blood type from the inventory
     *
     * @param type - type of blood to remove
     * @param amount - amount of blood remove
     */
    public void removeBlood(String type, int amount)
    {
        int currentAmount = this.inventory.get(type);
        this.inventory.put(type, currentAmount - amount); 
    }
    
    /**
     * Adds specific amount of certain blood type to the inventory
     * 
     * @param type - type of blood to add
     * @param amount - amount of blood to add
     */
    public void addBlood(String type, int amount){
        int currentAmount = this.inventory.get(type);
        this.inventory.put(type, currentAmount + amount);
    }
}
