import java.util.*;
/**
 * This class  represents a graph that will contain nodes and the node's edges along with methods to
 * add/remove edges between nodes and methods to get the graph's information.
 * @author Loc Nguyen.
 */
public class NetGraph {
    /**
     * An array list that holds all the nodes existing on the NetGraph.
     */
    private ArrayList<AdjacencyListHead> nodesList;
    /**
     * Constructor to initialize the graph.
     * @param nodesList The array list with nodes.
     */
    public NetGraph(ArrayList<AdjacencyListHead> nodesList){
        this.nodesList=nodesList;
        //This method is complete
    }
    /**
     * Get the nodesList.
     * @return the nodesList.
     */
    public ArrayList<AdjacencyListHead> getNodesList(){
        return nodesList;
        //This method is complete
    }
    /**
     * Get the total number of nodes on the graph.
     * @return The total number of nodes on the graph.
     */
    public int getNumNodes(){
        //Implement this method
        //Should return the number of nodes in the Graph
        return getNodesList().size();
    }
    /**
     * Get the total number of edges between each nodes on the graph.
     * @return Total edges between each nodes.
     */
    public int getNumLinks(){
        //Implement this method
        //returns the number of edges in the graph. Remember this is an undirected graph
        int numberOfEdges = 0;
        for(AdjacencyListHead node : getNodesList()){
            numberOfEdges += node.getAdjacencyList().size();
        }
        numberOfEdges /= 2;
        return numberOfEdges;
    }

    /**
     * Helper method to check if a node already exist in the graph.
     * @param id The node's ID.
     * @return True if node already exist in the graph, false otherwise.
     */
    private boolean nodeExists(int id){
        for(AdjacencyListHead node : getNodesList()){
            if(node.getNetNode().getId() == id){
                return true;
            }
        }
        return false;
    }

    /**
     * Initialize and add a new node to the graph.
     * @param id The new node ID.
     * @param name The new node name.
     * @param x The new node's x-axis position.
     * @param y The new node's y-axis position.
     */
    public void insertNetNode(int id, String name, double x, double y){   
        //Implement this method
        //Adds a new node to the graph. The node is represented by the NetNode class having id, name, x_coordinate, and y_coordinatte instance variables
        //You should check if the nodes already exists in the graph. If this is the case throw an IllegalArgumentException
        if(nodeExists(id) == true) throw new IllegalArgumentException("Error: Node already exist");
        NetNode newNode = new NetNode(id, name, x, y);
        AdjacencyListHead newHead = new AdjacencyListHead(newNode);
        getNodesList().add(newHead);
	}
    /**
     * Helper method to find the head node based on the node's ID.
     * @param id The head node's ID.
     * @return The head node, also known as AdjacencyListHead node.
     */
    private AdjacencyListHead findHeadNode(int id){
        for(AdjacencyListHead head : getNodesList()){
            if(head.getNetNode().getId() == id) return head;
        }
        return null;
    }
    /**
     * Add link or edge between two nodes.
     * @param node1 The first node to be connected.
     * @param node2 The second node to be connected.
     * @param weight The cost/distance of the edge between the two nodes.
     */
    public void addLink(NetNode node1, NetNode node2, double weight){   
        //Implement this method
        //adds a link in the graph between two  nodes node1 and node2.
        //You should check if the nodes exist in the graph and that they are not null or else you should raise an IllegalArgumentException
        if(node1 == null || node2 == null) throw new IllegalArgumentException("Error: null node(s)");
        if(nodeExists(node1.getId()) == false || nodeExists(node2.getId()) == false) throw new IllegalArgumentException("Error: node does not exist");

        Adjacent adjacentToNode2 = new Adjacent(node2, weight);     //  new edge for node2
        AdjacencyListHead headOfNode1 = findHeadNode(node1.getId());
        headOfNode1.getAdjacencyList().add(adjacentToNode2);  

        Adjacent adjacentToNode1 = new Adjacent(node1, weight);// new edge for node1
        AdjacencyListHead headOfNode2 = findHeadNode(node2.getId());
        headOfNode2.getAdjacencyList().add(adjacentToNode1);
    }
    /**
     * Helper method to iterate through neighboring nodes' adjacency lists and remove the requested the edge.
     * @param neigborNode The neighbor node.
     * @param nodeToRemove The node to remove.
     */
    private void removeEdgeFromNeigborNode(AdjacencyListHead neigborNode, NetNode nodeToRemove){
        int i = 0;
        int j = 0;
        for(AdjacencyListHead headNode : getNodesList()){
            if(headNode.equals(neigborNode)){
                for(Adjacent edge : headNode.getAdjacencyList()){
                    if(edge.getNeighbor().equals(nodeToRemove)){
                        getNodesList().get(i).getAdjacencyList().remove(j);
                        return;
                    }
                    j++;
                }
            }
            i++;
        }
    }
    /**
     * Delete a node from the graph.
     * @param node The node to be deleted.
     */
    public void deleteNetNode(NetNode node){
        //Implement this method
        //deletes a particular  node from the NetGraph. Remember to delete all edges containing it from the different adjacency lists
        //You should check if node exists in the graph and that it is not null or else you should raise an IllegalArgumentException
        if(node == null || nodeExists(node.getId()) == false) throw new IllegalArgumentException("Error: node is null or does not exist");
        for(AdjacencyListHead headNode : getNodesList()){   // Find the head node to remove
            if(headNode.getNetNode().equals(node)){
                for(Adjacent edge : headNode.getAdjacencyList()){
                    removeEdgeFromNeigborNode(findHeadNode(edge.getNeighbor().getId()), node);
                }
                getNodesList().remove(headNode);
                return;
            }
        }
    }
    
    /**
     * Remove the edges between two nodes.
     * @param node1 First node to remove edge from second node.
     * @param node2 Second node to remove edge from first node.
     */
    public void removeLink(NetNode node1, NetNode node2){   
        //Implement this method
        //deletes a link between two  nodes in the NetGraph. 
        //You should check if the nodes exist in the graph and that they are not null or else you should raise an IllegalArgumentException
        if(node1 == null || node2 == null || nodeExists(node1.getId()) == false || nodeExists(node2.getId()) == false) throw new IllegalArgumentException("Error: node(s) are null or do not exist");
        
        AdjacencyListHead headNode1 = findHeadNode(node1.getId());
        LinkedList<Adjacent> edgesOfHeadNode1 = headNode1.getAdjacencyList();
        for(int i = 0; i < edgesOfHeadNode1.size(); i++){
            Adjacent currentEdge = edgesOfHeadNode1.get(i);
            if(currentEdge.getNeighbor().equals(node2)){
                edgesOfHeadNode1.remove(i);
            }
        }

        AdjacencyListHead headNode2 = findHeadNode(node2.getId());
        LinkedList<Adjacent> edgesOfHeadNode2 = headNode2.getAdjacencyList();
        for(int i = 0; i < edgesOfHeadNode2.size(); i++){
            Adjacent currentEdge = edgesOfHeadNode2.get(i);
            if(currentEdge.getNeighbor().equals(node1)){
                edgesOfHeadNode2.remove(i);
            }
        }
    }
    /**
     * Get the adjacency list of a node on the graph.
     * @param node The node to get the list from.
     * @return The list of adjacency.
     */
    public LinkedList<Adjacent> getAdjacents(NetNode node){
        //Implement this method
        //returns a LinkedList containing the Adjacent Objects representing the neighbors of a particular node and the weights of the link
        if(node == null || nodeExists(node.getId()) == false) throw new IllegalArgumentException("Error: node is null or does not exist");
        return findHeadNode(node.getId()).getAdjacencyList();
    }
    /**
     * Get the node's index relative to nodeLists.
     * @param node The node to find index.
     * @return The node's index.
     */
    int getNodeIndex(NetNode node){ 
        //Implement this method
        //returns the index in the nodesList ArrayList of a particular node.
        //You should check if node exists in the graph and that it is not null or else you should raise an IllegalArgumentException
        if(node == null || nodeExists(node.getId()) == false) throw new IllegalArgumentException("Error: node is null or does not exist");
        int index = 0;
        for(AdjacencyListHead headNode : getNodesList()){
            if(headNode.equals(findHeadNode(node.getId())))return index;
            index++;
        }
        return -1;
    }
    /**
     * Get the total edges or adjacents of a node.
     * @param node The node to get total edges from.
     * @return The total edges.
     */
    public int degree(NetNode node){ 
        //Implement this method
        //returns the number of adjacent nodes of a particular  node
        //You should check if node exists in the graph and that it is not null or else you should raise an IllegalArgumentException
        if(node == null || nodeExists(node.getId()) == false) throw new IllegalArgumentException("Error: node is null or does not exist");
        return findHeadNode(node.getId()).getAdjacencyList().size();
    }
    /**
     * Get total edges of all nodes.
     * @return The total edges of all nodes.
     */
    public int getGraphMaxDegree(){
        int count = 0;
        for(AdjacencyListHead headNode : getNodesList()){
            count += headNode.getAdjacencyList().size();
        }
        return count;
	}
    /**
     * Get the node based on its index on nodeList.
     * @param index The index of the node on nodeList.
     * @return The NetNode.
     */
    public NetNode nodeFromIndex(int index){
        //Implement this method
        //returns an NetNode object from the index of the node in nodesList ArrayList
        return getNodesList().get(index).getNetNode();
    }
    /**
     * Print the graph.
     * @return The string of the visual of the graph.
     */
    public String printGraph(){
        //Implement this method
        //returns a String representation of the network graph in the adjacency list format: e.g.
        /*
            A: {(B,3), (D,2), (E,0.5)}
            B: {(A,3),(E,2)}
            C:{}
            D:{(A,2)}
            E:{(A,0.5),(B,2)}
        */
        String graphPrint = "";
        for(AdjacencyListHead headNode : getNodesList()){
            graphPrint += headNode.getNetNode().getName() + ": {";
            int index = 0;
            for(Adjacent edge : headNode.getAdjacencyList()){
                graphPrint += "(" + edge.getNeighbor().getName() + "," + String.format("%.1f", edge.getWeight()) + ")";
                if(index != headNode.getAdjacencyList().size()-1){
                    graphPrint += ", ";
                }
                index++;
            }
            graphPrint += "}\n";
        }
        return graphPrint;
    }

}
