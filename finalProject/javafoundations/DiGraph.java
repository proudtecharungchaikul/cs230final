package javafoundations;

/**
 *  DiGraph.java
 *  
 *  Defines the Interface for a directed graph.
 *
 * @author (SK)
 * @version (17/3/2021)
 */

public interface DiGraph<T>
{
    /**
     * Returns true if the graph is empty and false otherwise. 
     * @return boolean True iff the graph is empty
     */
    public boolean isEmpty();

    /**
     * Returns the number of vertices in the graph
     * @return int The number of vertices in the graph
     */
    public int getNumVertices();

    /**
     * Returns the number of arcs in the graph
     * @return int The number of arcs in the graph
     */
    public int getNumArcs();

    /**
     * Returns true iff a directed connection exists between the two input vertices
     * @param T the first vertex
     * @param T the second vertex
     * @return boolean true iff a directed connection 
     * exists from the first vertex to the second
     */
    public boolean isArc (T vertex1, T vertex2);

    /**
     * Adds the input vertex to the graph.  
     * If the vertex already exists in the graph, the graph is not changed.
     * @param T the vertex to be added to the graph
     */
    public void addVertex (T vertex);

    /**
     * Removes the input vertex from the graph. 
     * If the input vertex does not belong in the graph, the graph is not changed.
     *
     * @param T The vertex to be removed.
     */
    public void removeVertex (T vertex);

    /**
     * Adds an arc to the graph, from source to destination. 
     * If source or destination do not exist in the graph,
     * the graph is not changed. 
     * 
     * @param T the source of the arc 
     * @param T the destination of the arc 
     */
    public void addArc (T source, T destination);

    /**
     * Removes the arc between v1 and v2.
     * If v1 or v2, or the arc from v1 to v2 does not exist,
     * the graph does not change. 
     * 
     * @param T the source of the arc to be removed
     * @param T the destination of the arc to be removed
     */
    public void removeArc (T vertex1, T vertex2);

    /**
     *  Returns a string representation of the graph. 
     *  
     *  @return String a string representation of this graph
     */
    public String toString();

    /**
     * Saves the current graph into a .tgf file.
     * @param the name of the file to write to 
     */
    public void saveToTGF(String tgf_file_name);

}
