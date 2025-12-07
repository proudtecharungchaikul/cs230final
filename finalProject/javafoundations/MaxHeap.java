package javafoundations;

/**
 * Defines the interface to a max heap.
 */
public interface MaxHeap<T extends Comparable<T>> extends BinaryTree<T>
{
   /**
    * Adds the specified object to the heap.
    * 
    * @param obj The specified object to be added. 
    */
   public void add (T obj);

   /**
    * Returns a reference to the element with the highest value in the heap.
    * 
    * @return a reference to the element with the highest value in the heap.
    */
   public T getMax ();

   /**
    * Removes and returns the element with the highest value in the heap.
    * 
    * @return the element with the highest value in the heap.
    */
   public T removeMax ();
}
