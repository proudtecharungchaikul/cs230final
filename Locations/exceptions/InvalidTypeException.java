//********************************************************************
//  EmptyCollectionException.java     Java Foundations
//
//  Represents the situation in which an invalid blood type is given.
//********************************************************************

package Locations.exceptions;

public class InvalidTypeException extends RuntimeException
{
  //------------------------------------------------------------------
  //  Sets up this exception with an appropriate message.
  //------------------------------------------------------------------
  public InvalidTypeException (String message)
  {
    super (message);
  }
}