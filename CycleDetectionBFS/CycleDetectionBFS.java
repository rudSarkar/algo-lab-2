import java.util.*;

class Graph {
    private int V;
    private List<List<Integer>> adj;

    Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    boolean isCyclic() {
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];

        for (int i = 0; i < V; i++) {
            visited[i] = false;
            parent[i] = -1;
        }

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, parent)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isCyclicUtil(int v, boolean[] visited, int[] parent) {
        visited[v] = true;

        for (Integer neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                parent[neighbor] = v;
                if (isCyclicUtil(neighbor, visited, parent)) {
                    return true;
                }
            } else if (parent[v] != neighbor) {
                return true;
            }
        }

        return false;
    }
}

public class CycleDetectionBFS {
    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);

        if (graph.isCyclic()) {
            System.out.println("Cycle exists in the graph.");
        } else {
            System.out.println("No cycle in the graph.");
        }
    }
}
