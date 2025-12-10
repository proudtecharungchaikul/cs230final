package javafoundations;


public interface LinearList<T> {
    /**
     * Checks if the list is empty
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty();
    
    /**
     * Returns the size of the list
     * 
     * @return the size (or length) of the list
     */
    public int size();

    /**
     * Returns the element at the specified position from the list
     *
     * @param index of the element in the list
     * @return the element to be returned
     */
    public T get(int position);

    /**
     * Inserts an element at the given position in the list.
     * 
     * @param the index of the element to be added
     * @param the element to be added
     */
    public void insert(int position, T element);
    
    /**
     * Removes the element at the specified position from the list
     * 
     * @return the element to be returned
     */
    public T remove(int position);
    
    /**
     * Generates a String representation of list; 
     * first element in the representation is the front
     * 
     * @return a String representation of the list
     */
    public String toString();
}
