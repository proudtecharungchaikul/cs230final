import java.io.IOException;
import java.util.Scanner; 
import javafoundations.*;
import java.io.File;
import Locations.*;
import javafoundations.exceptions.*;
import java.util.Hashtable;

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
    private ArrayList<Location> optimalPath(Location source, Location dest){
        int index1 = vertices.indexOf(source);
        int index2 = vertices.indexOf(dest);
        
        if(index1 == index2){
            System.out.println("Source and destination are the same.");
            return null;
        }

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
        firstPath.add(source); //add source node to an initial arraylist
        q.enqueue(firstPath); 
        visited[index1] = true; // marks source node visited

        while(!q.isEmpty()){ 
            ArrayList<Location> currentPath = q.dequeue(); //path we are iterating through
            Location currentNode = currentPath.elementAt(currentPath.size() - 1); //node we are iterating through (most recent node in stack)
            if (dest.equals(currentNode)){ //if our current node is the destination, return path
                return currentPath;
            }
            UpdatedLinkedList<Location> neighbors = this.arcs.elementAt(this.vertices.indexOf(currentNode)); //linked list of neighbors of currentNode
            for (int j = 0; j < neighbors.size(); j++){ //iterates through each neighboring node of current node
                int neighborIndex = this.vertices.indexOf(neighbors.get(j)); //finds index of neighbor
                if (!visited[neighborIndex]){
                    ArrayList<Location> newPath = new ArrayList<Location>();
                    for (int k = 0; k < currentPath.size(); k++){
                        newPath.add(currentPath.elementAt(k));
                    }
                    newPath.add(neighbors.get(j));
                    q.enqueue(newPath);
                    visited[neighborIndex] = true;
                }
            }
        }
        return null;

    }

    /**
     * finds and returns the optimal path between an origin hospital and a distribution center destination
     * @param source    origin hospital 
     * @param dest      distribution center destination
     */
    public ArrayList<Location> optimalDCpath(DistributionCenter source, Hospital dest){
        return optimalPath(source, dest);
    }

    /**
     * reads a tgf file and returns a Map object
     * @param fileName is the name of the tgf file
     * @return a bloodmap
     */
    public static BloodMap readTGF(String fileName){
        try{
            BloodMap newMap = new BloodMap(); 
            Scanner fileScan = new Scanner (new File(fileName)); 
            boolean readingNodes = true;
            Hashtable<String, Location> nodes = new Hashtable<String, Location>();
            while (fileScan.hasNextLine()){
                String line = fileScan.nextLine();
                if (line.equals("#")){
                    readingNodes = false;
                } else if (readingNodes == true){
                    if(line.contains("DistributionCenter")){
                        DistributionCenter newDC = new DistributionCenter(line);
                        newMap.addVertex(newDC);
                        nodes.put(line, newDC);
                    } else if (line.contains("Hospital")) {
                        Hospital newHosp = new Hospital(line);
                        newMap.addVertex(newHosp);
                        nodes.put(line, newHosp);
                    } else if (line.contains("House")){
                        House newHouse = new House(line);
                        newMap.addVertex(newHouse);
                        nodes.put(line, newHouse);
                    } else {
                        System.out.println(line + " does not have a specified location type.");
                    }
                } else {
                    String[] edgeParts = line.split(" ");
                    String from = edgeParts[0];
                    String to = edgeParts[1];
                    Location fromNode = nodes.get(from);
                    Location toNode = nodes.get(to);

                    newMap.addArc(fromNode, toNode);
                }
            }
            fileScan.close();
            return newMap;
        } catch (IOException e){
            System.out.println("IO Exception: File not found"); 
            return null;
        }
    }

    /**
     * Finds the nearest (or second/third/etc. nearest) distribution center from origin node
     * 
     * @param origin    where the algorithm starts looking from
     * @param closeness defines the target distribution center in terms of relative distance from origin node
     * @return target distribution center
     */
    private DistributionCenter nearestDC(Location origin, int closeness) throws ElementNotFoundException{
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
        visited[index1] = true; // marks source node visited

        while(!q.isEmpty()){ 
            ArrayList<Location> currentPath = q.dequeue(); //path we are iterating through
            Location currentNode = currentPath.elementAt(currentPath.size() - 1); //node we are iterating through (most recent node in stack)
            if (currentNode instanceof DistributionCenter){ //if our current node is a Distribution Center, return Distribution Center
                currentCloseness++; // add closeness counter
                if (currentCloseness == closeness){
                    return (DistributionCenter) currentNode; 
                } //return if target closeness is reached
            }
            UpdatedLinkedList<Location> neighbors = this.arcs.elementAt(this.vertices.indexOf(currentNode)); //linked list of neighbors of currentNode
            for (int j = 0; j < neighbors.size(); j++){ //iterates through each neighboring node of current node
                int neighborIndex = this.vertices.indexOf(neighbors.get(j)); //finds index of neighbor
                if (!visited[neighborIndex]){ 
                    //if neighbor hasnt been visited yet
                    ArrayList<Location> newPath = new ArrayList<Location>(); //define a new path for neighbor elem
                    for (int k = 0; k < currentPath.size(); k++){
                        newPath.add(currentPath.elementAt(k)); //copy path from current to new
                    }
                    newPath.add(neighbors.get(j));// add current elem to the path
                    q.enqueue(newPath);
                    visited[neighborIndex] = true; //set current elem as visited
                }
            }
        }
        throw new ElementNotFoundException("Distribution center " + origin + " not found.");
    }

    /**
     * transports specified amount and type of blood from the specified hospital to the nearest destination
     * 
     * @param type - type of blood that needs to be transported
     * @param amount - how many units of blood is needed
     * @param destination - name of hospital to transport to 
     */
    public void transportBlood(String type, int amount, Hospital destination) {
        int closeness = 1;
        ArrayList<DistributionCenter> visitedDCs = new ArrayList<DistributionCenter>();

        // Count how many DCs are in the graph
        int dcCount = 0;
        for (int i = 0; i < this.vertices.size(); i++){
            if (this.vertices.elementAt(i) instanceof DistributionCenter){
                dcCount++;
            }
        }

        //find nearest distribution center to destination
        DistributionCenter nearest;
        try {
            nearest = this.nearestDC(destination, closeness);
        } catch (ElementNotFoundException e){
            System.out.println("There is no distribution center in this map.");
            return;
        }

        while (nearest.getAmount(type) < amount){
            visitedDCs.add(nearest);
            
            int missingAmount = amount - nearest.getAmount(type);
            int bloodToTransport = nearest.getAmount(type);
            System.out.println("Not enough blood. Transporting " + bloodToTransport + " to hospital");
            nearest.removeBlood(type, bloodToTransport);
            destination.addBlood(type, bloodToTransport);
            System.out.println("Looking at next closest distribution center...");
            amount = missingAmount;
            closeness++;
            
            try {
                nearest = this.nearestDC(destination, closeness);
            } catch (ElementNotFoundException e) {
                System.out.println("All Distribution Centers have been visited. No blood of type " + type + " left.");
                return;
            }
        }

        visitedDCs.add(nearest);
        nearest.removeBlood(type, amount);
        System.out.println("Transporting " + amount + " to hospital.");
        destination.addBlood(type, amount);
    }

    /**
     * donates blood of a specified type from a house to a distribution center.
     * 
     * @param type of blood donated
     * @param house that has person who is donating blood
     */
    public void donateBlood(String type, House house) {
        if(house.checkType(type)){
            DistributionCenter nearest = this.nearestDC(house, 1);
            nearest.addBlood(type, 1);
        } else {
            System.out.println("Wrong blood type. Input valid blood type");
        }
    }

    /**
     * 
     * @return a string representation of a BloodMap
     */
    public String toString(){
        String result;
        result = "Vertices in the graph: " + "\n";
        for (int i=0; i<vertices.size(); i++){
            result += vertices.elementAt(i).toString() + "\n";
        }
        result += "\n" + "Edges in the graph: " + "\n" + this.findEdges().toString();
        return result;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args){

        System.out.println("---***--- Testing BloodMap ---***---\n");

        System.out.println("Testing readTGF()");
        BloodMap newMap = readTGF("ExampleTGF.tgf");
        System.out.println("Printing newMap from tgf file");
        System.out.println(newMap);
        System.out.println("Done printing.\n");

        BloodMap map1 = new BloodMap();

        House house1 = new House("house1"); 
        House house2 = new House("house2"); 
        House house3 = new House("house3"); 
        House house4 = new House("house4"); 
        House house5 = new House("house5"); 
        House house6 = new House("house6"); 
        House house7 = new House("house7"); 
        House house8 = new House("house8"); 
        House house9 = new House("house9"); 
        House house10 = new House("house10"); 
        House house11 = new House("house11"); 
        House house12 = new House("house12"); 
        House house13 = new House("house13"); 
        Hospital hospital1 = new Hospital("hospital1");
        Hospital hospital2 = new Hospital("hospital2");
        DistributionCenter DC1 = new DistributionCenter("DC1");
        DistributionCenter DC2 = new DistributionCenter("DC2");

        System.out.println("Adding vertices to map1");
        map1.addVertex(house1);
        map1.addVertex(house2);
        map1.addVertex(house3);
        map1.addVertex(house4);
        map1.addVertex(house5);
        map1.addVertex(house6);
        map1.addVertex(house7);
        map1.addVertex(house8);
        map1.addVertex(house9);
        map1.addVertex(house10);
        map1.addVertex(house11);
        map1.addVertex(house12);
        map1.addVertex(house13);
        map1.addVertex(hospital1);
        map1.addVertex(hospital2);
        map1.addVertex(DC1);
        map1.addVertex(DC2);
        System.out.println(map1);

        System.out.println("\nAdding edges to map1");
        map1.addEdge(house1, DC1);
        map1.addEdge(house1, house5); 
        map1.addEdge(DC1, house2);
        map1.addEdge(house3, hospital1);
        map1.addEdge(hospital1, house4);
        map1.addEdge(house2, house6);
        map1.addEdge(hospital1, house7);
        map1.addEdge(house5, house6);
        map1.addEdge(house5, DC2);
        map1.addEdge(house6, house7);
        map1.addEdge(house7, house8);
        map1.addEdge(house7, house9);
        map1.addEdge(DC2, house8);
        map1.addEdge(DC2, house13);
        map1.addEdge(house9, house10);
        map1.addEdge(house9, house13);
        map1.addEdge(house13, house11);
        map1.addEdge(house13, hospital2);
        map1.addEdge(hospital2, house12);
        System.out.println(map1 + "\n");

        System.out.println("--- Testing optimalPath() ---");
        System.out.println("Optimal path from 'house1' to 'hospital1'. Expect: [house1, house5, house6, house7, hospital1] | Got: " + map1.optimalPath(house1, hospital1));
        System.out.println("Optimal path from 'house3' to 'hospital2'. Expect: [house3, hospital1, house7, house9, house13, hospital2] | Got: " + map1.optimalPath(house3, hospital2) + "\n");
        //doesn't exist
        House house14 = new House("house14");
        System.out.println("Optimal path from 'house3' to 'house14'. Expect: At least one location is invalid., return: null | Got: ");
        System.out.println(map1.optimalPath(house3, house14) + "\n");
        //path doesnt exist
        map1.addVertex(house14);
        System.out.println("Optimal path from 'house3' to 'house14'. Expect: null | Got: " + map1.optimalPath(house3, house14) + "\n");
        //same source and destination
        System.out.println("Optimal path from 'house3' to 'house3'. Expect: Source and destination are the same. Return: null | Got: " + map1.optimalPath(house3, house3) + "\n");
        
        System.out.println("--- Testing optimalDCPath() ---");
        System.out.println("Optimal path from 'DC2' to 'house3' Expect: [DC2, house8, house7, H1, house3] | Got: " + map1.optimalPath(DC2, house3));
        
        System.out.println("--- Testing nearestDC() ---");
        System.out.println("Nearest Distribution center to 'hospital2'. Expect: DC2 | Got: " + map1.nearestDC(hospital2, 1));
        System.out.println("Second nearest Distribution center to 'hospital2'. Expect: DC1 | Got: " + map1.nearestDC(hospital2, 2));

        try {
            System.out.println("Third nearest Distribution center to 'hospital2'. Expect: ElementNotFoundException");
            map1.nearestDC(hospital2, 3);
        } catch (ElementNotFoundException e) {
            System.out.println(e);
        }

        System.out.println("--- Testing transportBlood() ---");
        DC1.addBlood("O", 10);
        System.out.println("Added 10 units of O to DC1");
        System.out.println("Transporting 5 units of O to hospital1");
        map1.transportBlood("O", 5, hospital1);
        System.out.println("DC1 O: " + DC1.getAmount("O") + " | hospital1 O: " + hospital1.getAmount("O") + "\n");

        System.out.println("Testing transportBlood() with multiple DCs");
        DC1.addBlood("A", 3);
        DC2.addBlood("A", 4);
        System.out.println("DC1 A: " + DC1.getAmount("A") + " | DC2 A: " + DC2.getAmount("A"));
        System.out.println("Transporting 6 units of A to hospital2");
        map1.transportBlood("A", 6, hospital2);
        System.out.println("DC1 A : " + DC1.getAmount("A") + " | DC2 A : " + DC2.getAmount("A"));
        System.out.println("hospital2 A: " + hospital2.getAmount("A") + "\n");

        System.out.println("--- Testing donateBlood() ---");
        house1.addBloodType("B");
        System.out.println("Add B to house1 blood types");
        System.out.println("Donating B blood from house1. Expect: DC1 B:1 ");
        map1.donateBlood("B", house1);
        System.out.println("DC1 B: " + DC1.getAmount("B") + "\n");

        System.out.println("Donating AB blood from house1 (has B). Expect: error message");
        map1.donateBlood("AB", house1);
        System.out.println();

    }
}