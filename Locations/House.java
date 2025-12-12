package Locations;

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
        this.bloodTypes = new String[] {type1}; 
        
    }
    
    /**
     * Constructor for objects of class House with 2 bloodTypes
     */
    public House(String name, String type1, String type2)
    {
        // initialise instance variables
        super(name); 
        this.bloodTypes = new String[] {type1, type2}; 
    }
    
    /**
     * Constructor for objects of class House with 3 bloodTypes
     */
    public House(String name, String type1, String type2, String type3)
    {
        // initialise instance variables
        super(name); 
        this.bloodTypes = new String[] {type1, type2, type3}; 
    }
    
    /**
     * Constructor for objects of class House with 4 bloodTypes
     */
    public House(String name, String type1, String type2, String type3, String type4)
    {
        // initialise instance variables
        super(name); 
        this.bloodTypes = new String[] {type1, type2, type3, type4}; 
    }

    /**
     * Makes sure that the type of blood exists in the house
     * 
     * @param type - type of blood that we need
     * @return boolean true if it exists in the house and false otherwise
     */
    private boolean checkType(String type)
    {
        for(int i = 0; i < bloodTypes.length; i++){
            if (type.equals(bloodTypes[i])) {
                return true; 
            }
        }
        return false; 
    }
    
    /**
     * 
     */
    public void donateBlood(String type){
        if (checkType(type)){
            
        }
    }
}
