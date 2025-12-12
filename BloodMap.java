import java.io.IOException;
import java.util.Scanner; 
import javafoundations.*;
import java.io.File;
import Locations.*;

/**
 * BloodMap is a map that calculates the shortest distance from any Hospital and Distribution center of blood.
 *
 * @author Cindy and Proud
 * @version 7 Dec 2025
 */
public class BloodMap extends Map<Location>
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
    public ArrayList<Location> optimalPath(Location source, Location dest){
        int index1 = vertices.indexOf(source);
        int index2 = vertices.indexOf(dest);
        
        if (index1 == -1 || index2 == -1){
            System.out.println("At least one location is invalid");
            return null;
        }
        LinkedQueue<ArrayList<Location>> q = new LinkedQueue<ArrayList<Location>>(); //queue of paths to traverse through
        boolean[] visited = new boolean[this.vertices.size()]; //mark nodes visited/notvisited
        //initialize visited to false
        for (int i = 0; i < visited.length; i++){
            visited[i] = false;
        }
        ArrayList<Location> firstPath = new ArrayList<Location>(); //array of path to be put in queue
        firstPath.add(source); //add source node to an initial stack
        q.enqueue(firstPath); 
        visited[0] = true; // marks source node visited

        while(!q.isEmpty()){ 
            ArrayList<Location> currentPath = q.dequeue(); //path we are iterating through
            Location currentNode = currentPath.elementAt(currentPath.size() - 1); //node we are iterating through (most recent node in stack)
            if (dest.equals(currentNode)){ //if our current node is the destination, return path
                    return currentPath;
                }
            UpdatedLinkedList<Location> neighbors = this.arcs.elementAt(this.vertices.indexOf(currentNode)); //linked list of neighbors of currentNode
            for (int j = 0; j < neighbors.size(); j++){ //iterates through each neighboring node of current node
                if (!visited[vertices.indexOf(neighbors.get(j))]){
                    ArrayList<Location> newPath = new ArrayList<Location>();
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
     * 
     */
    private DistributionCenter nearestDC(Location origin, int closeness){
        int index1 = this.vertices.indexOf(origin);
        int currentCloseness = 0;
        if (index1 == -1){
            System.out.println("Invalid origin");
            return null;
        }
        
        LinkedQueue<ArrayList<Location>> q = new LinkedQueue<ArrayList<Location>>(); //queue of paths to traverse through
        boolean[] visited = new boolean[this.vertices.size()]; //mark nodes visited/notvisited
        
        //initialize visited to false
        for (int i = 0; i < visited.length; i++){
            visited[i] = false;
        }
        
        ArrayList<Location> firstPath = new ArrayList<Location>(); //array of path to be put in queue
        firstPath.add(origin); //add source node to an initial stack
        q.enqueue(firstPath); 
        visited[0] = true; // marks source node visited

        while(!q.isEmpty()){ 
            ArrayList<Location> currentPath = q.dequeue(); //path we are iterating through
            Location currentNode = currentPath.elementAt(currentPath.size() - 1); //node we are iterating through (most recent node in stack)
            if (currentNode instanceof DistributionCenter ){ //if our current node is a Distribution Center, return Distribution Center
                currentCloseness++;
                if (currentCloseness == closeness){
                    return (DistributionCenter) currentNode; 
                }
                }
            UpdatedLinkedList<Location> neighbors = this.arcs.elementAt(this.vertices.indexOf(currentNode)); //linked list of neighbors of currentNode
            for (int j = 0; j < neighbors.size(); j++){ //iterates through each neighboring node of current node
                if (!visited[vertices.indexOf(neighbors.get(j))]){
                    ArrayList<Location> newPath = new ArrayList<Location>();
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
     * @param type - type of blood that needs to be transported
     * @param amount - how many units of blood is needed
     * @param destination - name of hospital to transport to 
     */
    public void transportBlood(String type, int amount, Hospital destination) {
        int closeness = 1;
        //find nearest distribution center to destination
        DistributionCenter nearest = this.nearestDC(destination, closeness);
        // if (nearest.getAmount(type) < amount){
        //     int missingAmount = amount - nearest.getAmount(type);
        //     System.out.println("Not enough blood. Transporting " + nearest.getAmount(type) + " to hospital");
        //     nearest.removeBlood(type, nearest.getAmount(type)); // transports current amount
        //     destination.addBlood(type, nearest.getAmount(type));
        //     System.out.println("Looking at next closest distribution center...");
        //     transportBlood(type, missingAmount, destination, closeness + 1);//fix the closeness param
        // }

        while (nearest.getAmount(type) < amount){
            int missingAmount = amount - nearest.getAmount(type);
            System.out.println("Not enough blood. Transporting " + nearest.getAmount(type) + " to hospital");
            nearest.removeBlood(type, nearest.getAmount(type)); // transports current amount
            destination.addBlood(type, nearest.getAmount(type));
            System.out.println("Looking at next closest distribution center...");
            amount = missingAmount;
            closeness++;
        }

        nearest.removeBlood(type, amount);
        System.out.println("Transporting " + amount + " to hospital.");
        destination.addBlood(type, amount);
    }
    
    /**
     * 
     */
    public void donateBlood(String type, House house) {
        if(house.checkType(type)){
            DistributionCenter nearest = this.nearestDC(house, 1);
            nearest.addBlood(type, 1);
        }
        System.out.println("Wrong blood type. Input valid blood type");
    }
    
    /**
     * 
     * @param args
     */
    public String toString(){
        String result;
       result = "Vertices in the graph: " + "\n";
       for (int i=0; i<vertices.size(); i++){
            result += vertices.elementAt(i).toString();
       }
       result += "\n" + "Edges in the graph: " + "\n" + this.findEdges().toString();
       return result;
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