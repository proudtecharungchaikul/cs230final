import java.io.IOException;
import java.util.Scanner; 
import javafoundations.*;
import java.io.File;

/**
 * BloodMap is a map that calculates the shortest distance from any Hospital and Distribution center of blood.
 *
 * @author Cindy and Proud
 * @version 7 Dec 2025
 */
public class BloodMap<T> extends Map<T>
{
    /**
     * Constructor for objects of class BloodMap
     */
    public BloodMap()
    {
        super();
    }

    /**
     * Breadth-first search to find distances between a source node and all other nodes
     * @param source
     * @param dest
     * @return shortest path btwn source and dest in a array list
     */
    public ArrayList<T> optimalPath(T source, T dest){
        int index1 = vertices.indexOf(source);
        int index2 = vertices.indexOf(dest);
        
        if (index1 == -1 || index2 == -1){
            System.out.println("Invalid locations");
            return null;
        }
        LinkedQueue<ArrayList<T>> q = new LinkedQueue<ArrayList<T>>(); //queue of paths to traverse through
        boolean[] visited = new boolean[this.vertices.size()]; //mark nodes visited/notvisited
        //initialize visited to false
        for (int i = 0; i < visited.length; i++){
            visited[i] = false;
        }
        ArrayList<T> firstPath = new ArrayList<T>(); //array of path to be put in queue
        firstPath.add(source); //add source node to an initial stack
        q.enqueue(firstPath); 
        visited[0] = true; // marks source node visited

        while(!q.isEmpty()){ 
            ArrayList<T> currentPath = q.dequeue(); //path we are iterating through
            T currentNode = currentPath.elementAt(currentPath.size() - 1); //node we are iterating through (most recent node in stack)
            if (dest.equals(currentNode)){ //if our current node is the destination, return path
                    return currentPath;
                }
            UpdatedLinkedList<T> neighbors = this.arcs.elementAt(this.vertices.indexOf(currentNode)); //linked list of neighbors of currentNode
            for (int j = 0; j < neighbors.size(); j++){ //iterates through each neighboring node of current node
                if (!visited[vertices.indexOf(neighbors.get(j))]){
                    ArrayList<T> newPath = new ArrayList<T>();
                    for (int k = 0; k < currentPath.size(); k++){
                        newPath.add(currentPath.elementAt(k));
                    }
                    newPath.add(neighbors.get(j));
                    q.enqueue(newPath);
                    visited[j] = true;
                }
            }
        }
        return null;

    }
    
    /**
     * First step is to read from a given file
     * We are thinking about the SaveTGF method in Map
     * Turn it from the file to a graph that we can use
     */
    public Map<String> readTGF(String fileName) throws IOException{
        try{
            Map<String> newMap = new Map<String>(); 
            Scanner fileScan = new Scanner (new File(fileName)); 
            boolean readingNodes = true;
            while (fileScan.hasNextLine()){
                String line = fileScan.nextLine();
                if (line.equals("#")){
                    readingNodes = false;
                } else if (readingNodes == true){
                    newMap.addVertex(line);
                } else {
                    String[] edgeParts = line.split(" ");
                    String from = edgeParts[0];
                    String to = edgeParts[1];
                    newMap.addArc(from, to);
                }
            }
            fileScan.close();
            return newMap;
        } catch (IOException e){
            System.out.println("IO Exception found"); 
            return null;//????
        }
    }
    
    /**
     * Main method for testing
     */
    public static void main(String[] args){
        BloodMap<String> map1 = new BloodMap<String>();
        
        map1.addVertex("A");
        map1.addVertex("B");
        map1.addVertex("C");
        map1.addVertex("D");
        map1.addVertex("E");
        map1.addVertex("F");
        map1.addVertex("G");
        map1.addVertex("H");
        map1.addVertex("I");
        map1.addVertex("J");
        map1.addVertex("K");
        map1.addVertex("L");
        map1.addVertex("M");
        map1.addVertex("N");
        map1.addVertex("O");
        map1.addVertex("P");
        map1.addVertex("Q");
        System.out.println(map1);
        
        map1.addEdge("A", "B");
        map1.addEdge("A", "G"); 
        map1.addEdge("B", "C");
        map1.addEdge("D", "E");
        map1.addEdge("E", "F");
        map1.addEdge("C", "H");
        map1.addEdge("E", "I");
        map1.addEdge("G", "H");
        map1.addEdge("G", "J");
        map1.addEdge("H", "I");
        map1.addEdge("I", "K");
        map1.addEdge("I", "L");
        map1.addEdge("J", "K");
        map1.addEdge("J", "P");
        map1.addEdge("L", "M");
        map1.addEdge("L", "P");
        map1.addEdge("P", "N");
        map1.addEdge("P", "Q");
        map1.addEdge("Q", "O");
        System.out.println(map1 + "\n"); 
        
        System.out.println("Optimal path from 'A' to 'E' Expect: [A, G, H, I, E,] Got: " + map1.optimalPath("A", "E"));
        System.out.println("Optimal path from 'A' to 'Z' Expect: null Got: " + map1.optimalPath("A", "Z"));
    }
}