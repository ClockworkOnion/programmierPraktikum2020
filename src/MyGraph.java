import java.io.*;
import java.util.*;

public class MyGraph implements Graph {
    HashSet<Integer> nodes = new HashSet<>(); // set of vertices of the graph
    HashMap<Integer, HashSet<Integer>> adjacency = new HashMap<>(); // sparse adjacency matrix of the graph
    HashMap<String, Integer> stringsMap = new HashMap<>();

    public MyGraph(String filename) {
        if (filename != "") {
            try {
                File file = new File(filename);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                StringBuffer sb = new StringBuffer();
                String line;
                while((line  = br.readLine()) != null) {

                    String[] lines = line.split(" ");

                    if (!stringsMap.containsKey(lines[0])) {
                        stringsMap.put(lines[0], size());
                        addVertex(size());
                    }
                    if (!stringsMap.containsKey(lines[1])) {
                        stringsMap.put(lines[1],size());
                        addVertex(size());
                    }

                    addEdge(stringsMap.get(lines[0]), stringsMap.get(lines[1]));

                }
                System.out.println(stringsMap);

                fr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void addVertex (Integer v) {
        nodes.add(v);
        adjacency.put(v, new HashSet<Integer>());
    }

    public void addEdge (Integer v, Integer w) {
        if (v == w) return; // No loops!
        adjacency.get(v).add(w);
        adjacency.get(w).add(v);
    }

    public void deleteVertex(Integer v) {
        nodes.remove(v);
        adjacency.remove(v);
        for (Integer toCheck : adjacency.keySet()) {
            adjacency.get(toCheck).remove(v);
        }
    }

    @Override
    public void deleteEdge(Integer u, Integer v) {
        adjacency.get(u).remove(v);
        adjacency.get(v).remove(u);
    }

    @Override
    public boolean contains(Integer v) {
        return nodes.contains(v);
    }

    @Override
    public int degree(Integer v) {
        return adjacency.get(v).size();
    }

    @Override
    public boolean adjacent(Integer v, Integer w) {
        return adjacency.get(v).contains(w);
    }

    @Override
    public Graph getCopy() {
        HashSet<Integer> newNodes = new HashSet<>();
        HashMap<Integer, HashSet<Integer>> newAdjacency = new HashMap<>();

        MyGraph newGraph = new MyGraph("");

        for (Integer toCopy : nodes) {
            newGraph.addVertex(toCopy.intValue());
        }

        for (Integer key : adjacency.keySet()) {
            for (Integer value : adjacency.get(key)) {
                newGraph.addEdge(key.intValue(), value.intValue());
            }
        }
        return newGraph;
    }

    @Override
    public Set<Integer> getNeighbors(Integer v) {
        return adjacency.get(v);
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public int getEdgeCount() {
        int count = 0;
        for (Integer key : adjacency.keySet()) {
            count += adjacency.get(key).size();
        }
        return count/2;
    }

    @Override
    public Set<Integer> getVertices() {
        return nodes;
    }
}
