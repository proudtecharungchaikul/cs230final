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
    int count = 0; //num of blood types

    /**
     * Constructor for objects of class House with 1 bloodType
     */
    public House(String name)
    {
        // initialise instance variables
        super(name); 
        this.bloodTypes = new String[4]; 
        
    }
    
    /**
     * adds new blood type to house
     * @param type of blood
     */
    public void addBloodType(String type) throws InvalidTypeException{
        boolean bloodTypesContainsType;
        if(type != "A" || type!="B" || type!="O" || type!="AB"){
            for (int i = 0; i < bloodTypes.length; i++){
                if (type == bloodTypes[i]){
                    bloodTypesContainsType = true;
                } else {
                    return;
                }
            } 
            bloodTypes[count] = type;
        } else {
            throw new InvalidTypeException("Blood types are case-sensitive and must be A, B, O, or AB");
        }
        
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
            //for each blood type listed in the list
            if (type.equals(bloodTypes[i])) {
                //if the type of the blood that is needed exists, return true
                return true; 
            }
        }
        return false; 
    }
    
    /**
     * @return a string representation of a House object
     */
    public String toString(){
        String toReturn = this.name + " (Blood types: ";
        for (int i = 0; i < bloodTypes.length; i++){
            if (bloodTypes[i] != null){
                toReturn += bloodTypes[i] + ", ";
            }
        }
        toReturn += ")";
        return toReturn;
    }
    
    /**
     * main method for testing
     */
    public static void main(String[] args){
        //test constructor 1
        System.out.println("Creating a new House H1 with blood type A");
        House H1 = new House("H1");
        System.out.println(H1);
        
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
