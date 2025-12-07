package javafoundations;
/**
 * Represents a node, and the root of a subtree, in a heap.
 */
public class HeapNode<T extends Comparable<T>> extends BTNode<T>
{
   HeapNode<T> parent;

   /**
    * Constructor. Creates a new heap node with the specified data.
    * 
    * @param element The element that will be the node of the new tree.
    */
   public HeapNode (T element)
   {
      super(element);
      parent = null;
   }

   /**
    * Returns the parent node of this node.
    * 
    * @return the parent node of this node.
    */
   public HeapNode<T> getParent()
   {
      return parent;
   }

   /**
    * Sets the parent node of this node
    * 
    * @param The node to be set as the parent.
    */
   public void setParent(HeapNode<T> parent)
   {
      this.parent = parent;
   }

   /**
    * Returns the node that will be the parent of the new node.
    * 
    * @param the most recently added leaf. 
    * 
    * @return the node that will be the parent of the new node.
    */
   public HeapNode<T> getParentAdd (HeapNode<T> last)
   {
      HeapNode<T> result = last;

      while ((result.parent != null) && (result.parent.left != result))
         result = result.parent;

      if (result.parent != null)
         if (result.parent.right == null)
            result = result.parent;
         else
         {
            result = (HeapNode<T>) result.parent.right;
            while (result.left != null)
               result = (HeapNode<T>) result.left;
         }
      else
         while (result.left != null)
            result = (HeapNode<T>) result.left;

      return result;
   }

   /**
    * Moves a newly added leaf up the tree as far as appopriate to
    * reestablish the heap.
    * 
    * @param last A newly added leaf.
    */
   public void heapifyAdd (HeapNode<T> last)
   {
      T temp;
      HeapNode<T> current = last;

      while ((current.parent != null) &&
         ((current.element).compareTo(current.parent.element) > 0))
      {
         temp = current.element;
         current.element = current.parent.element;
         current.parent.element = temp;
         current = current.parent;
      }
   }

   /**
    * Returns the node that will be the new last node after a remove.
    * 
    * @param the current leaf to be removed. ????
    * 
    * @return the node that will be the new last node after a remove.
    */
   public HeapNode<T> getNewLastNode(HeapNode<T> last)
   {
      HeapNode<T> result = last;

      while ((result.parent != null) && (result.parent.left == result))
         result = result.parent;

      if (result.parent != null)
         result = (HeapNode<T>) result.parent.left;

      while (result.right != null)
         result = (HeapNode<T>) result.right;

      return result;
   }

   /**
    * 
    * @param root The root element to be removed. 
    */
   public void heapifyRemove(HeapNode<T> root)
   {
      T temp;
      HeapNode<T> current = root;
      HeapNode<T> next = largerChild (current);

      while (next != null && next.element.compareTo(current.element) > 0)
      {
         temp = current.element;
         current.element = next.element;
         next.element = temp;

         current = next;
         next = largerChild (current);
      }
   }

   /**
    * Returns the larger of the two children of the specified node.
    * 
    * @param node The parent node of two children that will be compared.
    * @return the larger of the two children of the specified node.
    */
   public HeapNode<T> largerChild (HeapNode<T> node)
   {
      HeapNode<T> larger = null;

      if (node.left == null && node.right == null)
         larger = null;
      else if (node.left == null)
         larger = (HeapNode<T>)node.right;
      else if (node.right == null)
         larger = (HeapNode<T>)node.left;
      else if (((HeapNode<T>)node.left).element.compareTo(((HeapNode<T>)node.right).element) > 0)
         larger = (HeapNode<T>)node.left;
      else
         larger = (HeapNode<T>)node.right;

      return larger;
   }
}
