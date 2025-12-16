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
    
    /**
     * main method for testing
     */
    public static void main(String[] args){
        //New UpdatedLinkedList
        System.out.println("Creating new UpdatedLinkedList ULL");
        UpdatedLinkedList<String> ULL = new UpdatedLinkedList<String>();
        
        //Adding elems
        System.out.println("Adding elems C, B, A to ULL");
        ULL.insert(0, "A");
        ULL.insert (0, "B");
        ULL.insert(0, "C");
        
        //test contains
        //true
        System.out.println("Testing ULL.contains('A'),  Expect: true.   Got: " + ULL.contains("A"));
        //false
        System.out.println("Testing ULL.contains('Y'),  Expect: false.   Got: " + ULL.contains("Y"));
        
        //test indexOf
        //not in list
        System.out.println("Testing ULL.indexOf('Z'),  Expect: -1.   Got: " + ULL.indexOf("Z"));
        //in list
        System.out.println("Testing ULL.indexOf('C'),  Expect: 0.   Got: " + ULL.indexOf("C"));
        System.out.println("Testing ULL.indexOf('A'),  Expect: 2.   Got: " + ULL.indexOf("A"));
        
    }
}