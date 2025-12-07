package javafoundations;


import java.util.Iterator;
import javafoundations.BTNode;
import javafoundations.exceptions.EmptyCollectionException;
import javafoundations.exceptions.ElementNotFoundException;
/**
 * Implements a binary tree using a linked representation.
 * 
 */
public class LinkedBinaryTree<T> implements BinaryTree<T>
{
    protected BTNode<T> root;

    /**
     * Creates an empty binary tree.
     * 
     */
    public LinkedBinaryTree()
    {
        root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     * 
     * @param the element to be stored at the root of this tree
     */
    public LinkedBinaryTree (T element)
    {
        root = new BTNode<T>(element);
    }

    /**
     * Creates a binary tree with the two specified subtrees.
     * @param the element to be stored at the root of this tree
     * @param the left subtree of this tree
     * @param the right subtree of this tree
     * 
     */
    public LinkedBinaryTree (T element, LinkedBinaryTree<T> left,
    LinkedBinaryTree<T> right)
    {
        root = new BTNode<T>(element);
        root.setLeft(left.root);
        root.setRight(right.root);
    }

    /**
     * Returns the element stored in the root of the tree.
     * Throws an EmptyCollectionException if the tree is empty.
     * 
     * @return the element stored in the root of the tree
     * 
     * @throws EmptyCollectionException if the tree is empty
     */
    public T getRootElement()
    {
        if (root == null)
            throw new EmptyCollectionException ("Get root operation "
                + "failed. The tree is empty.");

        return root.getElement();
    }

    /**
     * Returns the left subtree of the root of this tree.
     * 
     * @return the left subtree of the tree.
     */
    public LinkedBinaryTree<T> getLeft()
    {
        if (root == null)
            throw new EmptyCollectionException ("Get left operation "
                + "failed. The tree is empty.");

        LinkedBinaryTree<T> result = new LinkedBinaryTree<T>();
        result.root = root.getLeft();

        return result;
    }

    /**
     * Returns the element in this binary tree that matches the
     * specified target. 
     * Throws a ElementNotFoundException if the target is not found.
     * 
     * @param the element to look for in this tree
     * @return the element in this tree that matches the specified element
     * @throws ElementNotFoundException if the target is not found
     */
    public T find (T target)
    {
        BTNode<T> node = null;

        if (root != null)
            node = root.find(target);

        if (node == null)
            throw new ElementNotFoundException("Find operation failed. "
                + "No such element in tree.");

        return node.getElement();
    }

    /**
     * Returns the number of elements in this binary tree.
     * 
     * @return the number of elements in this binary tree.
     */
    public int size()
    {
        int result = 0;

        if (root != null)
            result = root.count();

        return result;
    }

    /**
     * Populates and returns an iterator containing the elements in
     * this binary tree using an inorder traversal.
     * 
     * @return an iterator containing the elements 
     * of this tree visited during an inorder traversal
     */
    public Iterator<T> inorder()
    {
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (root != null)
            root.inorder (iter);

        return iter;
    }

    /**
     * Populates and returns an iterator containing the elements in
     * this binary tree using a levelorder traversal.
     * 
     * @return an iterator containing the elements 
     * of this tree visited during a level order traversal
     */
    public Iterator<T> levelorder()
    {
        LinkedQueue<BTNode<T>> queue = new LinkedQueue<BTNode<T>>();
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (root != null)
        {
            queue.enqueue(root);
            while (!queue.isEmpty())
            {
                BTNode<T> current = queue.dequeue();

                iter.add (current.getElement());

                if (current.getLeft() != null)
                    queue.enqueue(current.getLeft());
                if (current.getRight() != null)
                    queue.enqueue(current.getRight());
            }
        }

        return iter;
    }

    /**
     * Satisfies the Iterable interface using an inorder traversal.
     * 
     * @return the inorder iterator on this tree
     */
    public Iterator<T> iterator()
    {
        return inorder();
    }

    //  The following methods are left as programming projects.

    /**
     * Returns the right subtree of this tree.
     * 
     * @return the right subtree of this tree.
     */
    public LinkedBinaryTree<T> getRight() 
    {}
        return null;
    }

    /**
     * Returns true if the binary tree contains an element that
     * matches the specified element and false otherwise.
     * 
     * @param the element to look for in the tree
     * 
     * @return true if the binary tree contains the specified element,
     * false otherwise
     */
    public boolean contains (T target) 
    {
        return true;
    }

    /**
     * Returns true if the binary tree contains no elements, and
     * false otherwise.
     * 
     * @return true if the tree has no nodes, false otherwise
     */
    public boolean isEmpty() 
    {
        return true;
    }

    /**
     * Returns a string representation of the binary tree:
     * The elements of this tree in inorder
     * 
     * @return the elements of this binary tree in inorder.
     */
    public String toString() 
    {
        return "";
    }

    /**
     * Returns a preorder traversal on the binary tree.
     * 
     * @return an iterator containing the elements 
     * of this tree visited during an preorder traversal
     */
    public Iterator<T> preorder() 
    {

        return null;
    }

    /**
     * Returns an postorder traversal on the binary tree.
     * @return an iterator containing the elements 
     * of this tree visited during an postorder traversal
     */
    public Iterator<T> postorder() 
    {
        return null;
    }


}

