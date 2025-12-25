import java.util.*;

public class IsCyclic {
    // 1. Edge class to represent graph connections
    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }

        @Override
        public String toString() {
            return src + "-" + nbr + "@" + wt;
        }
    }

    // 2. Pair class to store the current node and path in the queue
    public static class Pair {
        int v;
        String psf; // Path So Far

        Pair(int v, String psf) {
            this.v = v;
            this.psf = psf;
        }
    }

    public static void main(String[] args) {
        int vertices = 7;
        ArrayList<Edge>[] graph = createGraph(vertices);
        
        System.out.println("Graph Adjacency List:");
        display(graph);
        
        System.out.println("\nIs the graph cyclic? -> " + isCyclic(graph));
    }

    // Check if any component of the graph has a cycle
    public static boolean isCyclic(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        
        // Loop handles disconnected components
        for (int v = 0; v < graph.length; v++) {
            if (!visited[v]) {
                if (checkCycleBFS(graph, v, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // The core BFS (RMWA) implementation
    public static boolean checkCycleBFS(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        
        // Initial Add
        q.add(new Pair(src, src + ""));

        while (q.size() > 0) {
            // R - REMOVE
            Pair rem = q.removeFirst();

            // M - MARK (and Cycle Detection)
            if (visited[rem.v]) {
                // If we reach a node that was already visited, a cycle exists!
                return true; 
            }
            visited[rem.v] = true;

            // W - WORK (Optional: e.g., System.out.println(rem.v + " @ " + rem.psf);)

            // A - ADD
            for (Edge e : graph[rem.v]) {
                if (!visited[e.nbr]) {
                    q.add(new Pair(e.nbr, rem.psf + e.nbr));
                }
            }
        }
        return false;
    }

    public static ArrayList<Edge>[] createGraph(int vces) {
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) {
            graph[i] = new ArrayList<>();
        }

        // Helper to add undirected edges easily
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 3, 4, 10);
        addEdge(graph, 4, 5, 10);
        addEdge(graph, 5, 6, 10);
        addEdge(graph, 4, 6, 10);

        return graph;
    }

    private static void addEdge(ArrayList<Edge>[] graph, int u, int v, int wt) {
        graph[u].add(new Edge(u, v, wt));
        graph[v].add(new Edge(v, u, wt));
    }

    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print(e + ", ");
            }
            System.out.println();
        }
    }
}