import java.util.*;

public class mainClass {
    public static void main(String[] args) {

        MyGraph testGraph = new MyGraph("outmoreno_zebra_zebra.sec");
        System.out.println(testGraph.nodes);
        System.out.println(testGraph.adjacency);

        SearchTree testTree = new SearchTree();

        testTree.TestMe(testGraph);



        /*
        testGraph.addVertex(2);
        testGraph.addVertex(3);
        testGraph.addVertex(4);
        testGraph.addVertex(5);
        testGraph.addVertex(5);
        testGraph.addVertex(6);
        testGraph.addVertex(7);
        testGraph.addVertex(8);

        testGraph.addEdge(2, 5);
        testGraph.addEdge(2, 6);
        // testGraph.addEdge(5, 2);
        // testGraph.addEdge(2,5);
        // testGraph.addEdge(3, 5);
        // testGraph.addEdge(4, 7);
        // testGraph.addEdge(7, 8);
        // testGraph.addEdge(4, 7);
        // testGraph.addEdge(4,8);
        // testGraph.addEdge(8, 2);
        // testGraph.addEdge(6, 7);

        // testGraph.deleteEdge(5, 2);
        String myString = "gsdlg adlfgjk";
        System.out.println(myString.split(" ")[0]);
        System.out.println(myString.split(" ")[1]);

        System.out.println(testGraph.nodes);
        System.out.println(testGraph.adjacency);
        System.out.println(testGraph.getEdgeCount());

         */


    }
}
