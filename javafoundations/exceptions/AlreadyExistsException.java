//********************************************************************
//  AlreadyExistsException.java     Java Foundations
//
//  Represents the situation in which a collection already contains a specified element.
//********************************************************************

package javafoundations.exceptions;

public class AlreadyExistsException extends RuntimeException
{
  //------------------------------------------------------------------
  //  Sets up this exception with an appropriate message.
  //------------------------------------------------------------------
  public AlreadyExistsException (String message)
  {
    super (message);
  }
}
