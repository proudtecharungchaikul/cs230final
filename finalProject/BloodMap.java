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
    public ArrayList<T> bfs(T source, T dest){
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
            T currentNode = currentPath.elementAt(currentPath.size()); //node we are iterating through (most recent node in stack)
            if (dest.equals(currentNode)){ //if our current node is the destination, return path
                    return currentPath;
                }
            UpdatedLinkedList<T> neighbors = this.arcs.elementAt(this.vertices.indexOf(currentNode)); //linked list of neighbors of currentNode
            for (int j = 0; j < neighbors.size(); j++){ //iterates through each neighboring node of current node
                if (!visited[j]){
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
            return newMap;
        } catch (IOException e){
            System.out.println("IO Exception found"); 
            return null;//????
        }
    }
    
    /**
     * 
     */
    public static void main(String[] args){
        BloodMap<String> map1 = new BloodMap<String>();
        
    }
}