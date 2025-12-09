import javafoundations.Map;
import javafoundations.ArrayList;
import java.io.IOException;
import java.util.Scanner; 
import javafoundations.LinkedQueue;
import javafoundations.UpdatedLinkedList;

/**
 * BloodMap is a map that calculates the shortest distance from any Hospital and Distribution center of blood.
 *
 * @author Cindy and Proud
 * @version 7 Dec 2025
 */
public class BloodMap<String> extends Map<String>
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
     * @param parents
     * @param distances
     */
    public void bfs(String source, String[] parents, Integer[] distances){
        //Queue to store nodes to visit
        LinkedQueue<String> queue = new LinkedQueue<String>();
        //Source node distance is 0
        distances[vertices.indexOf(source)] = 0;
        //enqueue source node
        queue.enqueue(source);
        //iterate while queue is not empty
        while (!queue.isEmpty()){
            //pop node from front of queue
            String current = queue.dequeue();
            //explore neighbors of current node - loop through linkedlist of arcs in adjacency list
            UpdatedLinkedList<String> neighbors = arcs.elementAt(vertices.indexOf(current));
            for (int i = 0; i < neighbors.size(); i++){
                int indexOfNeighbor = vertices.indexOf(neighbors.get(i));
                //check if neighbor has been visited
                if (distances[indexOfNeighbor] == null){
                    //mark current node as parent of neighbor
                    parents[indexOfNeighbor] = current;
                    //set distance of neighbor to current distance + 1
                    distances[indexOfNeighbor] = distances[vertices.indexOf(current)] + 1;
                    //insert next neighbor into queue
                    queue.enqueue(neighbors.get(i));
                }
                }
            }
        
    }
    
    /**
     * Finds the shortest distance between a Distr center and a Hospital
     * @param distrCenter the name of the distribution center
     * @param hospital the name of the hospital
     * @return an array of strings representing the path from the distribution center to the hospital
     */
    public String[] shortestPath(String distrCenter, String hospital){
        //Implements breadth-first search algorithm to find shortest path btwn any 2 nodes.
        
        LinkedQueue<String> queue = new LinkedQueue<String>();
        
    }
    
    /**
     * First step is to read from a given file
     * We are thinking about the SaveTGF method in Map
     * Turn it from the file to a graph that we can use
     */
    public void readTGF(String fileName) throws IOException{
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
        } catch (IOException e){
            System.out.println("IO Exception found"); 
            
        }
    }
    
    /**
     * 
     */
}