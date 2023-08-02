import java.util.*;
/**
 * This class represents the head node on the NetGraph. The head node will hold a Netnode as itself and a
 * linked list containing the edges to neighboring node(s).
 * @author Loc Nguyen
 */
//This class is complete. Don't modify it
public class AdjacencyListHead {
    /**
     * The NetNode of this head node.
     */
    private NetNode node;
    /**
     * The linked list that will hold the edges of this node to any neighboring node(s).
     */
    private LinkedList<Adjacent> adjacencyList;
    /**
     * Constructor to set up the head node with just the NetNode.
     * @param node A NetNode.
     */
    public AdjacencyListHead(NetNode node){
        this.node=node;
        this.adjacencyList=new LinkedList<Adjacent>();
    }
    /**
     * Constructor to set up the head node with the NetNode and the list of edges.
     * @param node The NetNode.
     * @param adjacencyList The list of edges.
     */
    public AdjacencyListHead(NetNode node,LinkedList<Adjacent> adjacencyList){
        this.node=node;
        this.adjacencyList=adjacencyList;
    }
    /**
     * Set the NetNode.
     * @param node The NetNode.
     */
    public void setNetNode(NetNode node){
        this.node=node;
    }
    /**
     * Set the edges list.
     * @param adjacencyList The llinked list containing the edges.
     */
    public void setAdjacencyList(LinkedList<Adjacent> adjacencyList){
        this.adjacencyList=adjacencyList;
    }
    /**
     * Get the NetNode of this head node.
     * @return The NetNode.
     */
    public NetNode getNetNode(){
        return node;
    }
    /**
     * Get the linked list of edges of this node.
     * @return The list of edges of this node.
     */
    public LinkedList<Adjacent> getAdjacencyList(){
        return adjacencyList;
    }
}
