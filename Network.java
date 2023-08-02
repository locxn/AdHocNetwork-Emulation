import java.util.*;
/**
 * This class represent a network that generate a graph based on the xy axis as well as generating nodes
 * that will connect to each other if they are <=20sqrt(2) away from each other.
 * The class includes two methods where one will create minimum spanning tree using Primm's algorithm, and
 * the other will find the shortest path from one node to another node using the Dijkstra's algoritm.
 * @author Loc Nguyen
 */
public class Network {
    /**
     * The NetGraph representing this network.
     */
    public NetGraph networkGraph;
    /**
     * Constructor that will create another network with amount of nodes and size of graph.
     */
    public Network(){
        Network sampleNetwork=new Network(1000,200);
    }
    /**
     * Constructor to initialize the NetGraph and size of the graph.
     * @param numofNodes The amount of nodes will be in the graph.
     * @param side The length and height of the graph, like x and y axix.
     */
    public Network(int numofNodes,double side){
        ArrayList<AdjacencyListHead> nodesList=new ArrayList<AdjacencyListHead>();
        for (int i = 0; i < numofNodes; i++)
		{
			double xaxisCoordinate = side*Math.random();  
			double yaxisCoordinate = side*Math.random();  
			int id=i;
            String name="node "+i; 
			NetNode node=new NetNode(id,name,xaxisCoordinate,yaxisCoordinate);
            nodesList.add(new AdjacencyListHead(node));
                        
		}
        networkGraph=new NetGraph(nodesList);
        //Iterate over all the node pairs in the graph and connect the nodes with a distance <=20root2  with links
        for(int i = 0; i < nodesList.size(); i++){
            AdjacencyListHead headNode = nodesList.get(i);
            NetNode nodeOfHead = headNode.getNetNode();
            for(int j = i +1; j < nodesList.size(); j++){
                AdjacencyListHead tempHeadNode = nodesList.get(j);
                NetNode nodeOfTempHead = tempHeadNode.getNetNode();
                double distance = euclideanDistance(nodeOfHead, nodeOfTempHead);
                if(distance <= (20 * Math.sqrt(2))){
                    networkGraph.addLink(nodeOfHead, nodeOfTempHead, distance);
                }
            }
        }
        System.out.println(networkGraph.printGraph() + "\n");
        
    }
    
    /**
     * Get the distance between two nodes using the euclidean distance formula.
     * @param node1 The first node.
     * @param node2 The second node.
     * @return The distance from the first node to the second node.
     */
    double euclideanDistance(NetNode node1,NetNode node2){
        double x1 = node1.getX_coordinate();
        double y1 = node1.getY_coordinate();

        double x2 = node2.getX_coordinate();
        double y2 = node2.getY_coordinate();

        return Math.sqrt(Math.pow(x2- x1, 2) + Math.pow(y2 - y1, 2));
    }
    /**
     * Helper method to find the head node based on the node's ID.
     * @param id The head node's ID.
     * @return The head node, also known as AdjacencyListHead node.
     */
    private AdjacencyListHead findHeadNode(int id){
        for(AdjacencyListHead head : networkGraph.getNodesList()){
            if(head.getNetNode().getId() == id) return head;
        }
        return null;
    }
    /**
     * Get a list of NetNode that are neighbors to the requested node.
     * @param node The node to get neighbor list from.
     * @return The list of neighboring NetNode.
     */
    private ArrayList<NetNode> getNeighbors(NetNode node){
        ArrayList<NetNode> neighborsOfNodeList = new ArrayList<NetNode>();
        AdjacencyListHead headNode = findHeadNode(node.getId());

        for(int i = 0; i < headNode.getAdjacencyList().size(); i++){
            neighborsOfNodeList.add(headNode.getAdjacencyList().get(i).getNeighbor());
        }
        return neighborsOfNodeList;
        
    }
    /**
     * This method creates a minimum spanning tree(MST) which is a tree that connects all of the nodes with the least cost or
     * distance total. The MST will be implemented as an adjacency matrix using Prim's greedy algorithm.
     * @return The adjacency matrix as the 2D array of the MST.
     */
    public int[][] minSpanningTree(){	
        //the running time should be <= O(n*n) where n is the number of vertices in the graph
        int n = networkGraph.getNumNodes();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        double[] distance = new double[n];
        Arrays.fill(distance, Double.MAX_VALUE);
        PriorityQueue<NetNode> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> distance[node.getId()]));
        
        // Start with a random node
        NetNode startNode = networkGraph.nodeFromIndex(0);
        distance[startNode.getId()] = 0;
        pq.add(startNode);
        parent[startNode.getId()] = -1;
        
        // Visit all nodes
        while (!pq.isEmpty()) {
            NetNode node = pq.poll();
            visited[node.getId()] = true;
            
            // Update the distances to all neighbors
            for (NetNode neighbor : getNeighbors(node)) {
                int neighborId = neighbor.getId();
                double dist = euclideanDistance(node, neighbor);
                if (!visited[neighborId] && dist < distance[neighborId]) {
                    distance[neighborId] = dist;
                    parent[neighborId] = node.getId();
                    pq.add(neighbor);
                }
            }
        }
        
        // Construct the MST matrix
        int[][] mst = new int[n][n];
        for (int i = 0; i < n; i++) {
            if (parent[i] != -1) {
                mst[i][parent[i]] = 1;
                mst[parent[i]][i] = 1;
            }
        }
        return mst;
    }
    /**
     * This method gets the shortest path between two nodes on the graph.
     * The method will return a list of NetNode starting from node1 to node2 using the Dijkstra's algorithm to
     * find the shortest path.
     * @param node1 The starting node.
     * @param node2 The finishing node.
     * @return The list of nodes from the starting node to the finishing node.
     */
    public ArrayList<NetNode> getShortestPath(NetNode node1, NetNode node2){
        //the running time complexity is O(V lg V) where V is the number of vertices in the graph
        int n = networkGraph.getNumNodes();

        // Initialize the distances array to infinity and the visited array to false
        double[] distances = new double[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);

        // Set the distance from node1 to itself to 0
        distances[node1.getId()] = 0;

        // Create an array to store the previous node in the shortest path
        NetNode[] prev = new NetNode[n];

        // Repeat until all nodes have been visited
        for (int i = 0; i < n; i++) {
            // Find the unvisited node with the smallest distance
            int minDistNode = -1;
            double minDist = Double.POSITIVE_INFINITY;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && distances[j] < minDist) {
                    minDistNode = j;
                    minDist = distances[j];
                }
            }

            // If all remaining unvisited nodes are unreachable, break out of the loop
            if (minDistNode == -1) {
                break;
            }

            // Mark the node as visited
            visited[minDistNode] = true;

            // Update the distances of all adjacent nodes
            NetNode currNode = networkGraph.nodeFromIndex(minDistNode);
            for (Adjacent edge : findHeadNode(currNode.getId()).getAdjacencyList()) {
                NetNode adjNode = edge.getNeighbor();
                double newDist = distances[minDistNode] + edge.getWeight();
                if (newDist < distances[adjNode.getId()]) {
                    distances[adjNode.getId()] = newDist;
                    prev[adjNode.getId()] = currNode;
                }
            }
        }

        // Construct the shortest path from node1 to node2
        ArrayList<NetNode> path = new ArrayList<>();
        NetNode currNode = node2;
        while (currNode != null) {
            path.add(0, currNode);
            currNode = prev[currNode.getId()];
        }

        return path;
    }

}
