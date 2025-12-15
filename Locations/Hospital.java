package Locations;
import Locations.exceptions.*;
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
        this.inventory.put("A", 0); //inputs value 0 for each key representing blood types
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
    public void removeBlood(String type, int amount) throws EmptyCollectionException, NullPointerException 
    {
        try{
            int currentAmount = this.inventory.get(type);
            if(currentAmount - amount < 0){ 
                // if removing more than amount available
                throw new EmptyCollectionException("BloodBank for type " + type + " has " + amount + " units.");
            } 
            this.inventory.put(type, currentAmount - amount); //subtracts amount from value for specified key
        } catch (NullPointerException e){
            throw new ElementNotFoundException("This is not a valid blood type. Bloodtypes: A, B, O, AB are case sensitive.");
        } 
    }
    
    /**
     * Adds specific amount of certain blood type to the inventory
     * 
     * @param type - type of blood to add
     * @param amount - amount of blood to add
     */
    public void addBlood(String type, int amount) throws ElementNotFoundException{
        try{
            int currentAmount = this.inventory.get(type);
            this.inventory.put(type, currentAmount + amount); //adds amount to value for specified key
        } catch (NullPointerException e){
            throw new ElementNotFoundException("This is not a valid blood type. Bloodtypes: A, B, O, AB are case sensitive.");
        }
    }
    
    /**
     * Returns the amount of a certain blood type in the inventory
     * 
     * @return int of the amount
     */
    public int getAmount(String type){
        try{
            return this.inventory.get(type);
        } catch (NullPointerException e){
            throw new ElementNotFoundException("This is not a valid blood type. Bloodtypes: A, B, O, AB are case sensitive.");
        }
    }
    
    /**
     * @return string representation of a hospital object
     */
    public String toString(){
        String toReturn = "Hospital " + name + " has inventory:";
        toReturn += this.inventory.toString(); //use hashtable toString
        return toReturn;
    }
    
    /**
     * main method for testing
     */
    public static void main(String[] args){
        System.out.println("Creating a new Hostpital H1");
        Hospital H1 = new Hospital("H1");
        System.out.println(H1);
        
        //addBlood
        System.out.println("Testing addBlood('A', 5). Expect: {O=0, B=0, AB=0, A=5}. Got: ");
        H1.addBlood("A", 5);
        System.out.println(H1);
        //type of blood that doesn't exist
        System.out.println("Testing addBlood('X', 1). Expect: ElementNotFoundException Got: ");
        try {
            H1.addBlood("X", 1);
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        //addBlood w/ lowercase
        System.out.println("Testing addBlood('ab', 2). Expect: ElementNotFoundExceptionGot: ");
        try {
            H1.addBlood("ab", 2);
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        //removeBlood
        System.out.println("Testing removeNBlood('A', 1). Expect: {O=0, B=0, AB=0, A=4}. Got: ");
        H1.removeBlood("A", 1);
        System.out.println(H1);
        
        System.out.println("Testing removeNBlood('x', 2). Expect: ElementNotFoundException. Got: ");
        try {
            H1.removeBlood("x", 2);
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        System.out.println("Testing removeNBlood('a', 2). Expect: ElementNotFoundException. Got: ");
        try {
            H1.removeBlood("a", 2);
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        //getAmount
        System.out.println("Testing getAmount('A'). Expect: 4   Got: " + H1.getAmount("A"));
        System.out.println("Testing getAmount('B'). Expect: 0   Got: " + H1.getAmount("B"));
        System.out.println("Testing getAmount('O'). Expect: 0   Got: " + H1.getAmount("O"));
        System.out.println("Testing getAmount('AB'). Expect: 0   Got: " + H1.getAmount("AB"));
        
        System.out.println("Testing getAmount('x'). Expect:   ElementNotFoundException Got: ");
        try {
            H1.getAmount("x");
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        //remove more than is in the bank
        System.out.println("Testing removeNBlood('A', 5). Expect: EmptyCollectionException Got: ");
        try {
            H1.removeBlood("A", 5);
        } catch (EmptyCollectionException e){
            System.out.println(e);
        }
    }
}
