import java.util.*;

public class IsBipartite {
    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static class Pair {
        int v;
        int color; // 0 for Red, 1 for Blue

        Pair(int v, int color) {
            this.v = v;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        int vertices = 7;
        ArrayList<Edge>[] graph = createGraph(vertices);
        
        System.out.println("Is the graph Bipartite? -> " + isBipartite(graph));
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        // Use an integer array to store colors. Initialize with -1 (No color).
        int[] visited = new int[graph.length];
        Arrays.fill(visited, -1);

        for (int v = 0; v < graph.length; v++) {
            if (visited[v] == -1) {
                if (!checkBipartiteBFS(graph, v, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkBipartiteBFS(ArrayList<Edge>[] graph, int src, int[] visited) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, 0)); // Start coloring with 0

        while (q.size() > 0) {
            // R - REMOVE
            Pair rem = q.removeFirst();

            // M - MARK (and Conflict Detection)
            if (visited[rem.v] != -1) {
                // If already colored, check if current color matches previous color
                // If colors don't match, it means we found an odd cycle!
                if (rem.color != visited[rem.v]) {
                    return false; 
                }
                continue;
            }
            visited[rem.v] = rem.color;

            // A - ADD
            for (Edge e : graph[rem.v]) {
                if (visited[e.nbr] == -1) {
                    // Give the neighbor the OPPOSITE color (1 - current_color)
                    q.add(new Pair(e.nbr, 1 - rem.color));
                }
            }
        }
        return true;
    }

    // Graph creation helper 
    public static ArrayList<Edge>[] createGraph(int vces) {
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) graph[i] = new ArrayList<>();
        
        // Example: This graph has an even cycle (0-1-2-3), so it is Bipartite.
        // If you add an edge like (0, 2), it becomes an odd cycle and returns false.
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
}