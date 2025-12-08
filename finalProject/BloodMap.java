import javafoundations.Map;
import java.io.IOException;
import java.util.Scanner; 

/**
 * Write a description of class BloodMap here.
 *
 * @author Cindy and Proud
 * @version 7 Dec 2025
 */
public class BloodMap<String> extends Map<String>
{
    // instance variables
    private int x;

    /**
     * Constructor for objects of class BloodMap
     */
    public BloodMap()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
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