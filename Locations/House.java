package Locations;

/**
 * Write a description of class House here.
 *
 * @author Cindy and Proud 
 * @version 12/11/2025
 */
public class House extends Location
{
    // instance variables 
    String[] bloodTypes; 

    /**
     * Constructor for objects of class House
     */
    public House(String name, String type1)
    {
        // initialise instance variables
        super(name); 
        this.bloodTypes = new String[] {type1}; 
        
    }
    
    /**
     * Constructor for objects of class House
     */
    public House(String name, String type1, String type2)
    {
        // initialise instance variables
        super(name); 
        this.bloodTypes = new String[] {type1, type2}; 
    }
    
    /**
     * Constructor for objects of class House
     */
    public House(String name, String type1, String type2, String type3)
    {
        // initialise instance variables
        super(name); 
        this.bloodTypes = new String[] {type1, type2, type3}; 
    }
    
    /**
     * Constructor for objects of class House
     */
    public House(String name, String type1, String type2, String type3, String type4)
    {
        // initialise instance variables
        super(name); 
        this.bloodTypes = new String[] {type1, type2, type3, type4}; 
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void donateBlood(String type)
    {
        for(int i = 0; i < bloodTypes.length; i++){
            if (type.equals(bloodTypes[i])) {
                //donate blood to nearest distribution center
                
            }
        }
    }
}
