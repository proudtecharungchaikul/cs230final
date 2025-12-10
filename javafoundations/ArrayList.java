package javafoundations;
import java.lang.RuntimeException;

/**
 * Write a description of class ArrayList here.
 *
 * @author Cindy and Proud
 * @version 3 Dec 2025
 */
public class ArrayList<T>
{
    // instance variables - replace the example below with your own
    private T[] list; 
    private int count; 
    private int DEFAULT_CAPACITY = 10; 

    /**
     * constructor for objects of class ArrayList
     */
    public ArrayList()
    {
        // initialise instance variables
        this.list = (T[]) new Object[DEFAULT_CAPACITY]; 
        this.count = 0; 
    }

    /**
     * returns the size of the list
     */
    public int size(){
        return count; 
    }
    
    /**
     * 
     */
    public boolean isEmpty(){
        return count == 0; 
    }
    
    /**
     * adds element to list and expands the list if it is already at capacity
     */
    public void add(T element){
        if (count == list.length){
            T[] temp = (T[]) new Object[this.count * 2]; 
            for (int i = 0; i < count; i++){
                temp[i] = this.list[i]; 
            }
            this.list = temp; 
        }
        list[count] = element; 
        count++;
    }
    
    /**
     * returns true if element is in the list 
     */
    public boolean contains(T element) {
        boolean result = false; 
        for (int i = 0; i < count; i++){
            if(this.list[i].equals(element)){
                result = true; 
            }
        }
        return result; 
    }
    
    /**
     * returns the index of where the element is
     */
    public int indexOf(T element){
        for (int i = 0; i < count; i++){
            if(list[i].equals(element)){
                return i; 
            }
        }
        return -1; 
    }
    
    /**
     * 
     */
    public T elementAt(int index) throws ArrayIndexOutOfBoundsException{
        if (index > count) {
            throw new ArrayIndexOutOfBoundsException("index out of bounds");
        }
        return list[index]; 
    }
    
    
    /**
     * remove and return the element at the specified index
     */
    public T remove(int index) throws RuntimeException{
        T[] temp = (T[]) new Object[list.length];
        if(index > count || index < 0) {
            throw new RuntimeException("index out of bounds");
        }
        for (int i = 0; i < index; i++) {
            temp[i] = this.list[i];
        }
        for (int i = index + 1; i < count; i++) {
            temp[i-index-1] = this.list[i];
        }
        T toRemove = list[index];
        this.list = temp;
        count--;
        return toRemove;
    }
    
    /**
     * remove and return a specified element if it exists in the list
     */
    public T remove(T element) {
        return this.remove(this.indexOf(element));
    }
    
    /**
     * 
     */
    public String toString() {
        String toReturn = "[";
        for (int i = 0; i < count; i++){
            toReturn += " " + list[i] + ",";
        }
        return toReturn + " ]";
    }
    
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>();
        
        System.out.println("Testing size() : " + list1.size() + " | expect: 0"); 
        System.out.println("Testing isEmpty() : " + list1.isEmpty() + " | expect: true" + "\n"); 
        
        System.out.println("Adding elements A-K");
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        list1.add("E");
        list1.add("F");
        list1.add("G");
        list1.add("H");
        list1.add("I");
        list1.add("J");
        list1.add("K");
        System.out.println(list1);
        
        System.out.println("Testing contains('E') : " + list1.contains("E") + " | expect: true");
        System.out.println("Testing contains('Z') : " + list1.contains("Z") + " | expect: false" + "\n");
        
        System.out.println("Testing indexOf('E') : " + list1.indexOf("E") + " | expect: 4");
        System.out.println("Testing indexOf('Z') : " + list1.indexOf("Z") + " | expect: -1" + "\n");
        
        System.out.println("Testing remove(0) : " + list1.remove(0) + " | expect: 'A'" + "\n");
        //System.out.println("Testing remove(15) : " + list1.remove(15) + " | expect: ");
        //System.out.println("Testing remove(25) : " + list1.remove(25) + " | expect: ");
        
        System.out.println(list1);
        
        System.out.println("Testing remove('B') : " + list1.remove("B") + " | expect: 'B'" + "\n");
        //System.out.println("Testing remove('Z') : " + list1.remove("Z") + " | expect: exception");
        
        System.out.println(list1);
        
        
    }
}