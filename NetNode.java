//This class is complete. Don't modify it
/**
 * This class represents a single node of the NetGraph.
 * @author Loc Nguyen
 */
public class NetNode {
    /**
     * The ID of the node.
     */
    private int id;
    /**
     * The name of the node.
     */
    private String name;
    /**
     * The x-axis position of the node on the graph.
     */
    private double xaxisCoordinate;
    /**
     * The y-axix position of the node on the graph.
     */
    private double yaxisCoordinate;
    /**
     * Constructor to initialize the node.
     * @param id Node's ID.
     * @param name Node's name.
     * @param x Node's x-axis position.
     * @param y Node's y-axis postion.
     */
    public NetNode(int id,String name,double x,double y){
        this.id=id;
        this.name=name;
        this.xaxisCoordinate = x;
        this.yaxisCoordinate = y;  
    }
    /**
     * Set ID of node.
     * @param id Node's ID.
     */
    public void setId(int id){
        this.id=id;
    }
    /**
     * Set name of node.
     * @param name Node's name.
     */
    public void setName(String name){
        this.name=name;
    }
    /**
     * Set node's x-axis position.
     * @param x Node's x-axis position.
     */
    public void setX_coordinate(double x){
        this.xaxisCoordinate = x;
    }
    /**
     * Set node's y-axis position.
     * @param y Node's y-axis position.
     */
    public void setY_coordinate(double y){
        this.yaxisCoordinate = y;
    }
    /**
     * Get node's ID.
     * @return Node's ID.
     */
    public int getId(){
        return id;
    }
    /**
     * Get node's name.
     * @return Node's name.
     */
    public String getName(){
        return name;
    }
    /**
     * Get node's x-axis position.
     * @return Node's x-axis position.
     */
    public double getX_coordinate(){
        return xaxisCoordinate;
    }
    /**
     * Get Node's y-axis position.
     * @return Node's y-axis position.
     */
    public double getY_coordinate(){
        return yaxisCoordinate;
    }
}
