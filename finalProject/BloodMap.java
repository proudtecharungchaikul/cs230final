import javafoundations.Map;
import java.io.IOException;
import java.util.Scanner; 

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