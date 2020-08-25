import java.util.HashMap;
import java.util.HashSet;

public class SearchTree {

    class Instance {
        MyGraph graph;
        int k;

        public Instance (MyGraph graph, int k) {
            this.graph = graph;
            this.k = k;
        }
    }

    // MyGraph newGraph = new MyGraph("testfile.sec");
    // Instance myInstance = new Instance(newGraph, 2);

    public void TestMe(MyGraph testGraph) {
        System.out.println("SearchTree Test:");
        Instance testInstance = new Instance(testGraph, 20);
        System.out.println(solve(testInstance));
        removeSingletons(testGraph);
        System.out.println(testGraph.nodes);
        System.out.println(testGraph.adjacency);
    }

    private boolean solve(Instance i) {
        if (i.k < 0) {
            return false;
        }

        if (i.graph.getEdgeCount() == 0) {
            return true;
        }

        // Sonst Rekursion:
        MyGraph recursiveGraph1 = (MyGraph) i.graph.getCopy(); // Kopie des Graphen
        MyGraph recursiveGraph2 = (MyGraph) i.graph.getCopy(); // Kopie des Graphen

        // int neighbor = 0;

        for (Integer toCheck : recursiveGraph1.nodes) {
            if (recursiveGraph1.degree(toCheck) > 0) {
                int neighbor = recursiveGraph1.getNeighbors(toCheck).iterator().next();
                recursiveGraph1.deleteVertex(toCheck);
                recursiveGraph2.deleteVertex(neighbor);
                break;
            }
        }

        // Erste Node raussuchen die einen Nachbarn hat und diese loschen
        /*
        for (int j = 0; j < recursiveGraph1.size(); j++) {
           if (recursiveGraph1.contains(j) && (recursiveGraph1.degree(j) > 0) ) {
               int neighbor = recursiveGraph1.getNeighbors(j).iterator().next();
               recursiveGraph1.deleteVertex(j);
               recursiveGraph2.deleteVertex(neighbor);
               break; // nicht unnoetig weiter suchen
           }
        }

         */

        // Rekursiver Aufruf mit neuem Graphen und k-1
        Instance recursive1 = new Instance(recursiveGraph1, i.k-1);
        Instance recursive2 = new Instance(recursiveGraph2, i.k-1);
        if (solve(recursive1)) return true;
        if (solve (recursive2)) return true;
        return false;
    }

    public int solve(MyGraph graph) {
        int tryK = 0;
        Instance tryInstance = new Instance(graph, tryK);
        solve(tryInstance)

    }

    private void removeSingletons(MyGraph graph) {
        for (Integer entry : graph.nodes) {
            if (graph.getNeighbors(entry).isEmpty()) {
                graph.deleteVertex(entry);
            }
        }

        /* alte Implementierung, geht auch da keys immer fortlaufend vergeben werden
        for (int j = 0; j < graph.size(); j++) {
            if (graph.degree(j) == 0) {
                graph.deleteVertex(j);
            }
        }
         */
    }
}
