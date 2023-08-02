//This class is complete. Don't modify it
/**
 * This class represents an adjacent or edge from a node to a neigboring node.
 * @author Loc Nguyen
 */
public class Adjacent {
    /**
     * Neighbor holds the neigboring Netnode.
     */
    private NetNode neighbor;
    /**
     * Weight holds the cost or distance between the nodes.
     */
    private double weight; 
    /**
     * Constructor to initialize the edge.
     * @param neighbor the neighboring node.
     * @param weight the cost/distance between the node and its neighbor.
     */
    public Adjacent(NetNode neighbor,double weight){
        this.neighbor=neighbor;
        this.weight=weight;
    }
    /**
     * Set neighbor node.
     * @param neighbor neighboring Netnode.
     */
    public void setNeighbor(NetNode neighbor){
        this.neighbor=neighbor; 
    }
    /**
     * Set the cost/ distance of the edge.
     * @param weight the cost/distance.
     */
    public void setWeight(double weight){
        this.weight=weight;
    }
    /**
     * Return Neigboring Netnode.
     * @return neighbor.
     */
    public NetNode getNeighbor(){
        return neighbor; 
    }
    /**
     * Return edge's cost/distance.
     * @return weight.
     */
    public double getWeight(){
        return weight;
    }  
} 
