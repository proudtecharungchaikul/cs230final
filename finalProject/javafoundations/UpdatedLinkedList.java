package javafoundations;


/**
 * Write a description of class UpdatedLinkedList here.
 *
 * @author Cindy and Proud
 * @version 5 Dec 2025
 */
public class UpdatedLinkedList<T> extends LinkedList<T>
{
    /**
     * Constructor for objects of class UpdatedLinkedList
     */
    public UpdatedLinkedList()
    {
        super();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean contains(T element) {
        LinearNode<T> temp = front; 
        while (temp != null) {
            if (temp.getElement().equals(element)) {
                return true; 
            }
            temp = temp.getNext();
        }
        return false; 
    }
    
    /**
     * 
     */
    public int indexOf(T element) {
        LinearNode<T> temp = front; 
        for (int i = 0; i < this.count; i++) {
            if (temp.getElement().equals(element)) {
                return i; 
            }
            temp = temp.getNext();
        }
        return -1; 
    }
}