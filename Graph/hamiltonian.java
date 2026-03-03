import java.util.*;

public class Hamiltonian {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }

    public static void main(String[] args) {
        // Number of vertices based on your image (0 to 6)
        int vtces = 7;
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        // Building the graph structure from the image
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 3);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 2, 5);
        addEdge(graph, 3, 4);
        addEdge(graph, 4, 5);
        addEdge(graph, 4, 6);
        addEdge(graph, 5, 6);

        int src = 0; // Starting point for the traversal
        HashSet<Integer> visited = new HashSet<>();
        
        System.out.println("Hamiltonian Paths and Cycles starting from " + src + ":");
        // We pass 'src + ""' as the Path So Far (psf) to keep track of the sequence
        solve(graph, src, visited, src + "", src);
    }

    static void addEdge(ArrayList<Edge>[] graph, int u, int v) {
        graph[u].add(new Edge(u, v));
        graph[v].add(new Edge(v, u));
    }

    /**
     * @param graph    Adjacency list representation
     * @param src      The current vertex in the recursion
     * @param visited  Tracks vertices in the current path to ensure 'visit exactly once'
     * @param psf      Path So Far string
     * @param osrc     Original Source to check if the last node can return home (Cycle)
     */
    public static void solve(ArrayList<Edge>[] graph, int src, HashSet<Integer> visited, String psf, int osrc) {
        // Base Case: If we have visited all vertices except the current one
        if (visited.size() == graph.length - 1) {
            System.out.print(psf);

            boolean isCycle = false;
            // Check if there is an edge from the last node back to the original source
            for (Edge e : graph[src]) {
                if (e.nbr == osrc) {
                    isCycle = true;
                    break;
                }
            }

            if (isCycle) {
                System.out.println("* (Cycle)"); // '*' denotes Hamiltonian Cycle
            } else {
                System.out.println(". (Path)");  // '.' denotes Hamiltonian Path
            }
            return;
        }

        // Mark the current node as visited
        visited.add(src);

        // Explore all neighbors
        for (Edge e : graph[src]) {
            if (!visited.contains(e.nbr)) {
                solve(graph, e.nbr, visited, psf + e.nbr, osrc);
            }
        }

        // Backtrack: Unmark the current node so it can be part of other paths
        visited.remove(src);
    }
}