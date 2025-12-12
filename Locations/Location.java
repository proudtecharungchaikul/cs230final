package Locations;


/**
 * Write a description of class Location here.
 *
 * @author Cindy and Proud
 * @version 12/11/2025
 */
public abstract class Location
{
    // instance variables
    String name; 

    /**
     * Constructor for objects of class Location
     */
    public Location(String name)
    {
        // initialise instance variables
        this.name = name; 
    }

    /**
     * toString for Location
     * 
     * @return String representation of the location name
     */public String toString(){
        return this.name ;
    }

}