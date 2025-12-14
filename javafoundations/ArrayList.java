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
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("index out of bounds");
        }
        if (index >= count) {
            throw new ArrayIndexOutOfBoundsException("index out of bounds");
        }
        return list[index]; 
    }
    
    
    /**
     * remove and return the element at the specified index
     */
    public T remove(int index) throws RuntimeException{
        T[] temp = (T[]) new Object[list.length];
        if(index >= count || index < 0) {
            throw new RuntimeException("index out of bounds");
        }
        for (int i = 0; i < index; i++) {
            temp[i] = this.list[i];
        }
        for (int i = index + 1; i < count; i++) {
            temp[i-1] = this.list[i];
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
        
        System.out.println("---***--- Testing ArrayList ---***---" + "\n"); 
        
        System.out.println("Testing size(). Expect: 0 | Got: " + list1.size()); 
        System.out.println("Testing isEmpty(). Expect: true | Got: " + list1.isEmpty() + "\n"); 
        
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
        System.out.println("\n" + list1);
        
        System.out.println("Testing size(). Expect: 11 | Got: " + list1.size()); 
        System.out.println("Testing isEmpty(). Expect: false | Got: " + list1.isEmpty() + "\n"); 
        
        System.out.println("Testing contains('E'). Expect: true | Got: " + list1.contains("E"));
        System.out.println("Testing contains('Z'). Expect: false | Got: " + list1.contains("Z") + "\n");
        
        System.out.println("Testing indexOf('E'). Expect: 4 | Got: " + list1.indexOf("E"));
        System.out.println("Testing indexOf('Z'). Expect: -1 | Got: " + list1.indexOf("Z") + "\n");
        
        System.out.println("Testing elementAt(8). Expect: I | Got: " + list1.elementAt(8));
        try {
            System.out.println("Testing elementAt(15). Expect: java.lang.ArrayIndexOutOfBoundsException: index out of bounds | Got: " + list1.elementAt(15));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Testing elementAt(15). Expect: java.lang.ArrayIndexOutOfBoundsException: index out of bounds | Got: " + e); 
        }
        try {
            System.out.println("Testing elementAt(-1). Expect: java.lang.ArrayIndexOutOfBoundsException: index out of bounds | Got: " + list1.elementAt(-1));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Testing elementAt(-1). Expect: java.lang.ArrayIndexOutOfBoundsException: index out of bounds | Got: " + e); 
        }
        
        System.out.println(); 
        
        System.out.println("Testing remove(2). Expect: 'C' | Got: " + list1.remove(2));
        try {
            System.out.println("Testing remove(10). Expect: java.lang.RuntimeException: index out of bounds | Got: " + list1.remove(10));
        } catch (RuntimeException e) {
            System.out.println("Testing remove(10). Expect: java.lang.RuntimeException: index out of bounds | Got: " + e); 
        }
        try {
            System.out.println("Testing remove(-3). Expect: java.lang.RuntimeException: index out of bounds | Got: " + list1.remove(-3));
        } catch (RuntimeException e) {
            System.out.println("Testing remove(-3). Expect: java.lang.RuntimeException: index out of bounds | Got: " + e); 
        }
        
        System.out.println(); 
        
        System.out.println(list1 + "\n");
        
        System.out.println("Testing remove('F') : " + list1.remove("F") + " | expect: 'F'");
        try {
            System.out.println("Testing remove('Z'). Expect: java.lang.RuntimeException: index out of bounds | Got: " + list1.remove("Z"));
        } catch (RuntimeException e) {
            System.out.println("Testing remove('Z'). Expect: java.lang.RuntimeException: index out of bounds | Got: " + e); 
        }
        
        System.out.println(); 
        
        System.out.println(list1);
    }
}