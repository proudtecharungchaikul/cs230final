package javafoundations;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import javafoundations.exceptions.*;

/**
 * Write a description of class Map here.
 *
 * @author Cindy and Proud
 * @version 3 Dec 2025
 */
public class Map<T> implements Graph<T>
{   
    //instance variables
    protected ArrayList<T> vertices; 
    protected ArrayList<UpdatedLinkedList<T>> arcs; 
    
    /**
     * Constuctor for objects of class Map
     */
    public Map(){
        //initialize instance variables
        this.vertices = new ArrayList<T>();
        this.arcs = new ArrayList<UpdatedLinkedList<T>>(); 
    }
    
    /** 
     * Returns true if this graph is empty, false otherwise
     * 
     * @return true if graph is empty, false otherwise
     */
    public boolean isEmpty(){
       return vertices.isEmpty(); 
    }

    /** 
     * Returns the number of vertices in this graph
     * 
     * @return number of vertices
     */
    public int getNumVertices(){
        return vertices.size();
    }

    /** 
     * Returns the number of arcs in this graph
     * 
     * @return number of arcs
     */
    public int getNumArcs(){
       int count = 0; 
       for (int i = 0; i < arcs.size(); i++){
           count += arcs.elementAt(i).size(); 
       } 
       return count; 
    }
    
    /** 
     * Returns true iff a directed edge exists b/w given vertices 
     * 
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if and only if there is an arc between the 2 vertices
     */
    public boolean isArc (T vertex1, T vertex2){
       boolean result = false; 
       int index1 = vertices.indexOf(vertex1); 
       UpdatedLinkedList<T> vert1Arcs = arcs.elementAt(index1); 
       
       if (vertices.contains(vertex1)){
           for (int i = 0; i < vert1Arcs.size(); i++){
               if (vert1Arcs.get(i).equals(vertex2)){
                  result = true;  
               }
           }
       }
       
       return result; 
       
    }

    /** 
     * Returns true iff an edge exists between two given vertices which means that two corresponding arcs exist in the graph 
     * 
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if and only if there is an edge between the 2 vertices
     */
    public boolean isEdge (T vertex1, T vertex2){
        return (this.isArc(vertex1, vertex2) && this.isArc(vertex2, vertex1)); 
    }

    /** 
     * Adds a vertex to this graph, associating object with vertex
     * If the vertex already exists, nothing is inserted
     * 
     * @param vertex vertex to be added
     */
    public void addVertex (T vertex) throws AlreadyExistsException{
        if (vertices.contains(vertex)){
            throw new AlreadyExistsException("Vertex already exists");
            }
        
        vertices.add(vertex); 
        arcs.add(new UpdatedLinkedList<T>());
    }

    /** 
     * Removes a single vertex with the given value from this graph
     * If the vertex does not exist, it does not change the graph
     * 
     * @param vertex vertex to be removed 
     */
    public void removeVertex (T vertex){
        if (vertices.contains(vertex)){
            for (int i = 0; i < arcs.size(); i++){
                for (int j=0; j < arcs.elementAt(i).size(); j++){
                    if (arcs.elementAt(i).get(j).equals(vertex)){
                        arcs.elementAt(i).remove(j);
                    }
                }
            }
            arcs.remove(vertices.indexOf(vertex));
            vertices.remove(vertex);
        } else {
            throw new ElementNotFoundException("Vertex does not exist.");
        }
    }

    /** 
     * Inserts an arc between two vertices of this graph, if the vertices exist 
     * Else it does not change the graph
     * 
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void addArc (T vertex1, T vertex2) throws ElementNotFoundException, AlreadyExistsException {
        int index1 = vertices.indexOf(vertex1); //gives us the index of the linked list of vertex1
        int index2 = vertices.indexOf(vertex2);
        if (index1 == -1 || index2 == -1) {
            throw new ElementNotFoundException("Invalid vertices");
        }
        UpdatedLinkedList<T> vert1Arcs = arcs.elementAt(index1);
        if (vert1Arcs.contains(vertex2)){
            throw new AlreadyExistsException("Arc already exists");
            }
        UpdatedLinkedList<T> arcList = arcs.elementAt(index1); 
       
        arcList.insert(0, vertex2); 
       
    }

    /** 
     * Removes an arc between two vertices of this graph, if the vertices exist
     * Else it does not change the graph 
     * 
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void removeArc (T vertex1, T vertex2) throws ElementNotFoundException{
        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);
        
        if (index1 == -1 || index2 == -1) {
            throw new ElementNotFoundException("Arc does not exist");
        }
        UpdatedLinkedList<T> vert1Arcs = arcs.elementAt(index1);
        if (!vert1Arcs.contains(vertex2)) {
            throw new ElementNotFoundException("Arc does not exist");
        }
        
        int vert2InVert1List = vert1Arcs.indexOf(vertex2); 
        
        arcs.elementAt(index1).remove(vert2InVert1List);
    }

    /**
     * Inserts an edge between two vertices of this graph,if the vertices exist
     * Else does not change the graph
     * 
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void addEdge (T vertex1, T vertex2) throws ElementNotFoundException, AlreadyExistsException{
        try{
            this.addArc(vertex1, vertex2);
            this.addArc(vertex2, vertex1); 
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Invalid vertices");
        } catch (AlreadyExistsException e){
            throw new AlreadyExistsException("Edge already exists.");
        }
    }

    /**
     * Removes an edge between two vertices of this graph, if the vertices exist
     * Else does not change the graph
     * 
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void removeEdge (T vertex1, T vertex2) throws ElementNotFoundException {
        try{
            this.removeArc(vertex1, vertex2);
            this.removeArc(vertex2, vertex1); 
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Edge does not exist");
        }
    }
    
    /**
     * Finding the ordered pairs of edges 
     * Helper method for toString
     * 
     * @return ArrayList of string containing all the edges
     */
    public ArrayList<String> findEdges(){
       ArrayList<String> edges = new ArrayList<String>();
       for (int i = 0; i < arcs.size(); i++){
           //for loop to go through all the arcs
           T vert1 = vertices.elementAt(i);
           for (int j = 0; j < arcs.elementAt(i).size(); j++){
               T vert2 = arcs.elementAt(i).get(j);
               String orderedPair = "(" + vert1 + ", " + vert2 + ")"; //make the arc an ordered pair
               String reverseOrderedPair = "(" + vert2 + ", " + vert1 + ")"; //make the other arc that would make the arc an edge
               if (isArc(vert1, vert2) && isArc(vert2, vert1)){
                   if (!edges.contains(orderedPair) && !edges.contains(reverseOrderedPair)){
                       edges.add(orderedPair);
                   }
               }
           }
       }
       return edges;
    }
    
    /**
     * Returns a string representation of the graph
     */
    public String toString(){
       String result;
       result = "Vertices in the graph: " + "\n" + vertices.toString();
       result += "\n" + "Edges in the graph: " + "\n" + this.findEdges().toString();
       //result += "Arcs in the graph: ";
       //for (int i = 0; i < arcs.size(); i++){
           //result += arcs.elementAt(i).toString();
       //}
       return result;
    }
    

    /**
     * Saves the current graph into a .tgf file
     * If it cannot write the file, a message is printed
     * 
     * @param tgf_file_name name of the file to save content to
     */
    public void saveTGF(String tgf_file_name){
       try {
           PrintWriter writer = new PrintWriter(new File(tgf_file_name)); 
           
           for (int i = 0; i < vertices.size(); i++) {
               writer.print((i+1) + " " + vertices.elementAt(i)); 
               writer.println(""); 
           }
           
           writer.println("#"); 
           
           for (int i = 0; i < arcs.size(); i++) {
               for (int j = 0; j < arcs.elementAt(i).size(); j++) {
                   writer.println((i+1) + " " + (j+1)); 
               }
           }
           
           writer.close(); 
       } catch (IOException ex) {
           System.out.println("***ERROR***" + tgf_file_name + "could not be written");
       }
    }
   
    /**
     * Testing Map
     */
    public static void main(String[] args){
        Map<String> graph = new Map<String>();
        
        System.out.println("---***--- Testing Map ---***---"); 
        System.out.println("Testing isEmpty(). Expect: true | Got: " + graph.isEmpty());
        System.out.println("Testing getNumVertices(). Expect: 0: | Got: " + graph.getNumVertices());
        System.out.println("Testing getNumArcs() Expect: 0 | Got: " + graph.getNumArcs() + "\n");
        System.out.println(graph);
        
        System.out.println("\n" + "Testing addVertex() | Adding vertices A, B, C, D, D");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        try{
            graph.addVertex("D"); 
        } catch (AlreadyExistsException e){
            System.out.println(e);
        }
        System.out.println(graph);
        
        System.out.println("\n" + "Testing addEdges() | Adding edges (A, B), (A, C), (A, D), (B, C), (C, D), (A, B), (X, B)");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        try {
            graph.addEdge("A", "B");
        } catch (AlreadyExistsException e){
            System.out.println(e);
        }
        try {
            graph.addEdge("X", "B");
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        System.out.println(graph);
        
        System.out.println("\n" + "--- Testing isArc() ---"); 
        System.out.println("Is (A, B) an arc? Expect: true | Got: " + graph.isArc("A", "B"));
        System.out.println("Is (B, A) an arc? Expect: true | Got: " + graph.isArc("B", "A"));
        System.out.println("Is (B, D) an arc? Expect: false | Got: " + graph.isArc("B", "D"));
        System.out.println(graph); 
        
        System.out.println("\n" + "Testing isEdge() | Is (B, D) an edge? Expect: false | Got: " + graph.isEdge("B", "D")); 
        
        System.out.println("\n" + "Testing addArc() | Adding arc (B, D)"); 
        graph.addArc("B", "D"); 
        System.out.println("Is (B, D) an arc? Expect: true | Got: " + graph.isArc("B", "D")); 
        System.out.println("Is (B, D) an edge? Expect: false | Got: " + graph.isEdge("B", "D") + "\n"); 
        System.out.println("\n" + "Testing addArc() | Adding arc (B, Z). Expect: ElementNotFoundException"); 
        try {
            graph.addArc("B", "Z");
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        System.out.println("Testing removeArc() | Removing arc (B, D)"); 
        graph.removeArc("B", "D");
        System.out.println("Is (B, D) an arc? Expect: false | Got: " + graph.isArc("B", "D")); 
        System.out.println(graph);
        System.out.println("Testing removeArc() | Removing arc (B, D). Expect: ElementNotFoundException"); 
        try {
            graph.removeArc("B", "D");
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        System.out.println("\n" + "Testing removeVertex() | Removing vertex A.");
        graph.removeVertex("A");
        System.out.println(graph);
        System.out.println("\nTesting removeVertex() | Removing vertex Y. Expect: ElementNotFoundException");
        try {
            graph.removeVertex("Y");
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        
        System.out.println("\n" + "Testing removeEdge() | Removing edge from B to C");
        graph.removeEdge("B", "C");
        System.out.println(graph);
        
        System.out.println("\n" + "Testing removeEdge() | Removing edge from X to Y | Expect: ElementNotFoundException");
        try {
            graph.removeEdge("X", "Y");
        } catch (ElementNotFoundException e){
            System.out.println(e);
        }
        
        
    }
}