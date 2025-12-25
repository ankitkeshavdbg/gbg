import java.util.*;

public class TopologicalSort {
    public static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }

    public static void main(String[] args) {
        int vces = 7;
        ArrayList<Edge>[] graph = createDAG(vces);
        
        // Now main only needs to call one function
        getTopologicalSort(graph);
    }

    // WRAPPER FUNCTION: Manages the setup and execution
    public static void getTopologicalSort(ArrayList<Edge>[] graph) {
        int vces = graph.length;
        boolean[] visited = new boolean[vces];
        Stack<Integer> st = new Stack<>();

        // Handle disconnected components
        for (int v = 0; v < vces; v++) {
            if (visited[v]==false) {
                topologicalSortDFS(graph, v, visited, st);
            }
        }

        // Print in Topological Order
        System.out.println("Topological Sort (Execution Order):");
        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
        System.out.println();
    }

    // RECURSIVE FUNCTION: Does the actual traversal
    public static void topologicalSortDFS(ArrayList<Edge>[] graph, int src, boolean[] visited, Stack<Integer> st) {
        visited[src] = true;

        for (Edge e : graph[src]) {
            if (visited[e.nbr]==false) {
                topologicalSortDFS(graph, e.nbr, visited, st);
            }
        }

        // WORK: Push to stack only AFTER all neighbors are processed
        st.push(src);
    }

    public static ArrayList<Edge>[] createDAG(int vces) {
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) graph[i] = new ArrayList<>();

        // Based on the arrows in your DAG image:
        graph[0].add(new Edge(0, 3));
        graph[0].add(new Edge(0, 1));
        graph[1].add(new Edge(1, 2));
        graph[2].add(new Edge(2, 3));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));
        graph[4].add(new Edge(4, 6));
        graph[5].add(new Edge(5, 6));

        return graph;
    }
}