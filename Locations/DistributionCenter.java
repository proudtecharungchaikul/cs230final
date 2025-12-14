package Locations;
import Locations.exceptions.*;
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
        this.bloodBank.put("A", 0); //add keys for each blood type
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
        try{
            int currentAmount = this.bloodBank.get(type);
            if(currentAmount - amount < 0){ 
                throw new EmptyCollectionException("BloodBank for type " + type + " has " + amount + " units.");
            } // if removing more than amount available
            this.bloodBank.put(type, currentAmount - amount); //subtracts amount from value for specified key
        } catch (NullPointerException e){
            throw new ElementNotFoundException("This is not a valid blood type. Bloodtypes: A, B, O, AB are case sensitive.");
        } 
    }
    
    /**
     * Adds specific amount of certain blood type to the bloodBank
     * 
     * @param type - type of blood to add 
     * @param amount - amount of blood to add (assumes positive integer)
     */
    public void addBlood(String type, int amount){
        try{
            int currentAmount = this.bloodBank.get(type);
            this.bloodBank.put(type, currentAmount + amount); //adds amount to value for specified key
        } catch (NullPointerException e){
            throw new ElementNotFoundException("This is not a valid blood type. Bloodtypes: A, B, O, AB are case sensitive.");
        }
    }
    
    /**
     * Returns the amount of a certain blood type in the bloodBank
     * 
     * @return int of the amount
     */
    public int getAmount(String type){
        try{
            return this.bloodBank.get(type);
        } catch (NullPointerException e){
            throw new ElementNotFoundException("This is not a valid blood type. Bloodtypes: A, B, O, AB are case sensitive.");
        }
    }
    
    /**
     * @return a string representation of a DistributionCenter object
     */
    public String toString(){
        String toReturn = "Distribution center " + name + " has inventory:";
        toReturn += this.bloodBank.toString(); //use hashtable toString
        return toReturn;
    }
    
    /**
     * main method for testing
     */
    public static void main(String[] args){
        System.out.println("Creating a new DistributionCenter DC1");
        DistributionCenter DC1 = new DistributionCenter("DC1");
        System.out.println(DC1);
        
        //addBlood
        System.out.println("Testing addBlood('A', 5). Expect: {O=0, B=0, AB=0, A=5}. Got: ");
        DC1.addBlood("A", 5);
        System.out.println(DC1);
        //type of blood that doesn't exist
        System.out.println("Testing addBlood('X', 1). Expect: ElementNotFoundException Got: ");
        try {
            DC1.addBlood("X", 1);
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        //addBlood w/ lowercase
        System.out.println("Testing addBlood('ab', 2). Expect: ElementNotFoundExceptionGot: ");
        try {
            DC1.addBlood("ab", 2);
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        //getAmount
        System.out.println("Testing getAmount('A'). Expect: 5   Got: " + DC1.getAmount("A"));
        System.out.println("Testing getAmount('B'). Expect: 0   Got: " + DC1.getAmount("B"));
        System.out.println("Testing getAmount('O'). Expect: 0   Got: " + DC1.getAmount("O"));
        System.out.println("Testing getAmount('AB'). Expect: 0   Got: " + DC1.getAmount("AB"));
        
        System.out.println("Testing getAmount('x'). Expect:   ElementNotFoundException Got: ");
        try {
            DC1.getAmount("x");
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        //removeBlood
        System.out.println("Testing removeNBlood('A', 1). Expect: {O=0, B=0, AB=0, A=4}. Got: ");
        DC1.removeBlood("A", 1);
        System.out.println(DC1);
        
        System.out.println("Testing removeNBlood('x', 2). Expect: ElementNotFoundException. Got: ");
        try {
            DC1.removeBlood("x", 2);
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        System.out.println("Testing removeNBlood('a', 2). Expect: ElementNotFoundException. Got: ");
        try {
            DC1.removeBlood("a", 2);
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        //remove more than is in the bank
        System.out.println("Testing removeNBlood('A', 5). Expect: EmptyCollectionException Got: ");
        try {
            DC1.removeBlood("A", 5);
        } catch (EmptyCollectionException e){
            System.out.println(e);
        }
        
        //getAmount
        System.out.println("Testing getAmount('A'). Expect: 4 Got: " + DC1.getAmount("A"));
        
    }
}

