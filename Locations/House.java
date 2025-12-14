package Locations;
import Locations.exceptions.*;
/**
 * A type of location that can only donate blood to the nearest Distribution Center
 *
 * @author Cindy and Proud 
 * @version 12/11/2025
 */
public class House extends Location
{
    // instance variables 
    String[] bloodTypes; 

    /**
     * Constructor for objects of class House with 1 bloodType
     */
    public House(String name, String type1)
    {
        // initialise instance variables
        super(name); 
        if (!type1.equals("A") && !type1.equals("B") && !type1.equals("O") && !type1.equals("AB")){
            throw new InvalidTypeException("Blood types are case-sensitive and must be A, B, O, or AB");
        }
        this.bloodTypes = new String[] {type1}; 
        
    }
    
    /**
     * Constructor for objects of class House with 2 bloodTypes
     */
    public House(String name, String type1, String type2)
    {
        // initialise instance variables
        super(name); 
        boolean type1invalid = !type1.equals("A") && !type1.equals("B") && !type1.equals("O") && !type1.equals("AB");
        boolean type2invalid = !type2.equals("A") && !type2.equals("B") && !type2.equals("O") && !type2.equals("AB");
        if (type1invalid || type2invalid){
            throw new InvalidTypeException("Blood types are case-sensitive and must be A, B, O, or AB");
        }
        this.bloodTypes = new String[] {type1, type2}; 
    }
    
    /**
     * Constructor for objects of class House with 3 bloodTypes
     */
    public House(String name, String type1, String type2, String type3)
    {
        // initialise instance variables
        super(name); 
        boolean type1invalid = !type1.equals("A") && !type1.equals("B") && !type1.equals("O") && !type1.equals("AB");
        boolean type2invalid = !type2.equals("A") && !type2.equals("B") && !type2.equals("O") && !type2.equals("AB");
        boolean type3invalid = !type3.equals("A") && !type3.equals("B") && !type3.equals("O") && !type3.equals("AB");
        if (type1invalid || type2invalid || type3invalid){
            throw new InvalidTypeException("Blood types are case-sensitive and must be A, B, O, or AB");
        }
        this.bloodTypes = new String[] {type1, type2, type3}; 
    }
    
    /**
     * Constructor for objects of class House with 4 bloodTypes
     */
    public House(String name, String type1, String type2, String type3, String type4)
    {
        // initialise instance variables
        super(name); 
        boolean type1invalid = !type1.equals("A") && !type1.equals("B") && !type1.equals("O") && !type1.equals("AB");
        boolean type2invalid = !type2.equals("A") && !type2.equals("B") && !type2.equals("O") && !type2.equals("AB");
        boolean type3invalid = !type3.equals("A") && !type3.equals("B") && !type3.equals("O") && !type3.equals("AB");
        boolean type4invalid = !type4.equals("A") && !type4.equals("B") && !type4.equals("O") && !type4.equals("AB");
        if (type1invalid || type2invalid || type3invalid || type4invalid){
            throw new InvalidTypeException("Blood types are case-sensitive and must be A, B, O, or AB");
        }
        this.bloodTypes = new String[] {type1, type2, type3, type4}; 
    }

    /**
     * Makes sure that the type of blood exists in the house
     * 
     * @param type - type of blood that we need
     * @return boolean true if it exists in the house and false otherwise
     */
    public boolean checkType(String type)
    {
        if (type != "A" && type != "B" && type != "O" && type != "AB"){
            throw new InvalidTypeException("Blood types are case-sensitive and must be A, B, O, or AB");
        }
        for(int i = 0; i < bloodTypes.length; i++){
            if (type.equals(bloodTypes[i])) {
                return true; 
            }
        }
        return false; 
    }
    
    /**
     * @return a string representation of a House object
     */
    public String toString(){
        String toReturn = "This house has blood types ";
        for (int i = 0; i < bloodTypes.length - 1; i++){
            toReturn += bloodTypes[i] + ", ";
        }
        toReturn += bloodTypes[bloodTypes.length - 1];
        
        return toReturn;
    }
    
    /**
     * main method for testing
     */
    public static void main(String[] args){
        //test constructor 1
        System.out.println("Creating a new House H1 with blood type A");
        House H1 = new House("H1", "A");
        System.out.println(H1);
        //testing invalid types
        System.out.println("Creating a new House H10 with blood type X. Expect: InvalidTypeException");
        try {
            House H10 = new House("H1", "X");
        } catch (InvalidTypeException e){
            System.out.println(e);
        }
        
        //test constructor 2
        System.out.println("Creating a new House H2 with blood types A, B");
        House H2 = new House("H2", "A", "B");
        System.out.println(H2);
        
        //test constructor 3
        System.out.println("Creating a new House H3 with blood types A, B, O");
        House H3 = new House("H3", "A", "B", "O");
        System.out.println(H3);
        
        //test constructor 4
        System.out.println("Creating a new House H4 with blood types A, B, O, AB");
        House H4 = new House("H4", "A", "B", "O", "AB");
        System.out.println(H4);
        
        //test checkType
        System.out.println("Testing H1.checkType('A').  Expect: true.   Got: " + H1.checkType("A"));
        System.out.println("Testing H1.checkType('B').  Expect: false.   Got: " + H1.checkType("B"));
        System.out.println("Testing H1.checkType('Y').  Expect: InvalidTypeException.   Got: ");
        try {
            H1.checkType("Y");
        } catch (InvalidTypeException e){
            System.out.println(e);
        }
        
    }
    
}
