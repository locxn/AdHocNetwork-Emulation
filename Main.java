import java.util.*;
public class Main {
    public static void main(String[] args){
        ArrayList<AdjacencyListHead> nodesList = new ArrayList<AdjacencyListHead>();
        NetGraph graph = new NetGraph(nodesList);

        // Add nodes
        NetNode nullNode = null;
        NetNode randomNode1 = new NetNode(0, "rand1", 0, 0);
        NetNode randomNode2 = new NetNode(2, "rand2", 0, 0);



        graph.insertNetNode(1, "Node1", 0.0, 0.0);
        
        graph.insertNetNode(2, "Node2", 1.0, 1.0);
        graph.insertNetNode(3, "Node3", 2.0, 2.0);
        graph.insertNetNode(4, "Node4", 2.0, 1.0);

        graph.addLink(graph.getNodesList().get(0).getNetNode(), graph.getNodesList().get(1).getNetNode() , 1.0);
        // graph.addLink(randomNode1, graph.getNodesList().get(1).getNetNode() , 1.0);
        
        graph.addLink(graph.getNodesList().get(1).getNetNode(), graph.getNodesList().get(3).getNetNode() , 1.0);

        System.out.println("Total Nodes: " + graph.getNumNodes());
        System.out.println(graph.printGraph());

        System.out.println("Testing delete node: --------------");
        graph.deleteNetNode(graph.getNodesList().get(3).getNetNode()); //Deleting Node 4
        System.out.println(graph.printGraph() + "\n");
        graph.deleteNetNode(graph.getNodesList().get(0).getNetNode()); //Deleting Node 1
        System.out.println(graph.printGraph() + "\n");


        System.out.println("Testing delete link: --------------");

        graph.insertNetNode(4, "Node4", 3.0, 1.0);
        graph.insertNetNode(5, "Node5", 2.0, 3.0);


        graph.addLink(graph.getNodesList().get(0).getNetNode(), graph.getNodesList().get(1).getNetNode() , 5.0);
        graph.addLink(graph.getNodesList().get(0).getNetNode(), graph.getNodesList().get(1).getNetNode() , 5.0);

        graph.addLink(graph.getNodesList().get(0).getNetNode(), graph.getNodesList().get(3).getNetNode() , 5.0);

        System.out.println(graph.printGraph() + "\n");

        graph.removeLink(graph.getNodesList().get(0).getNetNode(), graph.getNodesList().get(1).getNetNode());
        System.out.println(graph.printGraph() + "\n");

        System.out.println("Testing getting adjacent/index/(max)degree/NetNode: --------------");
        graph.addLink(graph.getNodesList().get(0).getNetNode(), graph.getNodesList().get(2).getNetNode() , 7.0);
        graph.addLink(graph.getNodesList().get(1).getNetNode(), graph.getNodesList().get(3).getNetNode() , 1.0);
        System.out.println(graph.printGraph() + "\n");

        LinkedList<Adjacent> adjacentListTest = graph.getAdjacents(graph.getNodesList().get(0).getNetNode());
        System.out.println("Equal Adjacent List: " + adjacentListTest.equals(graph.getNodesList().get(0).getAdjacencyList()));
        System.out.println("Equal index: " + (graph.getNodeIndex(graph.getNodesList().get(0).getNetNode()) == 0));
        NetNode nodeTest = graph.nodeFromIndex(0);
        System.out.println("Node at index 0: " + nodeTest.getName());
        System.out.println("Degree of node index 0: " + graph.degree(graph.getNodesList().get(0).getNetNode()));
        System.out.println("Max Degree: " + graph.getGraphMaxDegree());

        System.out.println("Testing Network class: --------------");
        Network network = new Network(20,100);

        int [][] mst = network.minSpanningTree();
        for(int i = 0; i <  mst.length; i++){
            for(int j = 0; j<  mst[i].length; j++){
                System.out.print(mst[i][j] + " ");
            }
            System.out.println();
        }

        ArrayList<NetNode> shortestPath = network.getShortestPath(network.networkGraph.nodeFromIndex(0), network.networkGraph.nodeFromIndex(9));
        for(NetNode node : shortestPath){
            System.out.print(node.getName() + " ");
        }
        System.out.println();

        Network network2 = new Network();
        System.out.println("Network2:");
        // System.out.println(network2.networkGraph.printGraph());



            


        // Add links
        // NetNode node1 = new NetNode(1, "Node 1", 0.0, 0.0);
        // NetNode node2 = new NetNode(2, "Node 2", 1.0, 1.0);
        // NetNode node3 = new NetNode(3, "Node 3", 2.0, 2.0);
        // graph.addLink(node1, node2, 1.0);
        // graph.addLink(node2, node3, 2.0);

        // // Print number of nodes and links
        // System.out.println("Number of nodes: " + graph.getNumNodes());
        // System.out.println("Number of links: " + graph.getNumLinks());

        // graph.printGraph();
    }
}