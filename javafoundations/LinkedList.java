package javafoundations;


public class LinkedList<T> implements LinearList<T> {
    protected LinearNode<T> front;
    protected int count;
    
    public LinkedList() {
        this.front = null;
        this.count = 0;
    }
    
    public boolean isEmpty() {
        return this.count == 0;
    }
    
    public int size() {
        return this.count;
    }

    protected LinearNode<T> getNode(int position) {
        if (position < 0 || position >= this.count) {
            throw new RuntimeException(
                "Asking for element at index " + position 
                + " in a list of size" + this.count
            );
        }
        
        LinearNode<T> current = this.front;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        
        return current;
    }
    
    public T get(int position) {
        LinearNode<T> node = this.getNode(position);
        if (node == null) {
            return null;
        }
        
        return node.getElement();
    }
    
    public void insert(int position, T element) {
        LinearNode<T> node = new LinearNode<T>(element);
        
        if (position == 0) {
            node.setNext(front);
            front = node;
        } else {
            LinearNode<T> before = this.getNode(position - 1);
            node.setNext(before.getNext());
            before.setNext(node);
        }
        
        this.count++;
    }
    
    public T remove(int position) {
        LinearNode<T> current;
        if (position == 0) {
            current = front;
            front = front.getNext();
        } else {
            LinearNode<T> before = this.getNode(position - 1);
            current = before.getNext();
            before.setNext(current.getNext());
        }  
        
        this.count--;
        return current.getElement();
    }
    
    public String toString() {
        String s = "[ ";
        
        LinearNode<T> current = this.front;
        for (int i = 0; i < this.size(); i++) {
            s += current.getElement().toString() + ", ";
            current = current.getNext();
        }
        
        return s + "]";
    }
    
    public static void main(String[] args) {
        LinkedList<Double> l = new LinkedList<Double>();
        System.out.println("Empty: " + l);
        
        l = new LinkedList<Double>();
        for (int i = 0; i < 5; i++) {
            l.insert(0, (double) i);
            System.out.println(l);
        }
        for (int i = 0; i < 5; i++) {
            l.remove(0);
            System.out.println(l);
        }
        System.out.println();
        
        l = new LinkedList<Double>();
        for (int i = 0; i < 5; i++) {
            l.insert(i, (double) i);
            System.out.println(l);
        }
        for (int i = l.size() - 1; i >= 0; i--) {
            l.remove(i);
            System.out.println(l);
        }
        System.out.println();
        
        int[] indices = {5, 4, 3, 2, 1, 0};
        double[] values = {4.5, 3.5, 2.5, 1.5, 0.5, -0.5};
        l = new LinkedList<Double>();
        for (int i = 0; i < 5; i++) {
            l.insert(i, (double) i);
        }
        for (int i = 0; i < indices.length; i++) {
            l.insert(indices[i], values[i]);
        }
        System.out.println(l);
        for (int i = l.size() - 1; i >= 0; i -= 2) {
            l.remove(i);
            System.out.println(l);
        }
        System.out.println();
    }
}
