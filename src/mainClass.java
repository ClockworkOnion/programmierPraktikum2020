import java.util.*;

public class mainClass {
    public static void main(String[] args) {

        MyGraph testGraph = new MyGraph("outmoreno_zebra_zebra.sec");
        System.out.println(testGraph.nodes);
        System.out.println(testGraph.adjacency);

        SearchTree testTree = new SearchTree();

        testTree.removeSingletons(testGraph);
        System.out.println(testTree.solve(testGraph));



    }
}
