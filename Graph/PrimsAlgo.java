import java.util.*;

public class PrimsAlgo {
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

    // Pair class for Priority Queue
    public static class Pair implements Comparable<Pair> {
        int v;      // Current vertex
        int p;      // Parent vertex (the node that brought us here)
        int wt;     // Weight of the edge connecting p and v

        Pair(int v, int p, int wt) {
            this.v = v;
            this.p = p;
            this.wt = wt;
        }

        // Min-Priority Queue logic: Bring the smallest edge weight to the top
        public int compareTo(Pair other) {
            return this.wt - other.wt;
        }
    }

    public static void main(String[] args) {
        int vertices = 7;
        ArrayList<Edge>[] graph = createGraph(vertices);
        
        System.out.println("Minimum Spanning Tree Edges:");
        prims(graph, 0);
    }

    public static void prims(ArrayList<Edge>[] graph, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.length];

        // Initial Add: src has no parent (-1) and 0 weight to include itself
        pq.add(new Pair(src, -1, 0));

        while (pq.size() > 0) {
            // R - REMOVE
            Pair rem = pq.remove();

            // M - MARK
            if (visited[rem.v] == true) {
                continue;
            }
            visited[rem.v] = true;

            // W - WORK
            // We don't print the source's connection (parent -1)
            if (rem.p != -1) {
                System.out.println("[" + rem.v + "-" + rem.p + " @ " + rem.wt + "]");
            }

            // A - ADD
            for (Edge e : graph[rem.v]) {
                if (visited[e.nbr] == false) {
                    // Difference from Dijkstra: We only add the edge weight 'e.wt'
                    // NOT the weight so far (wsf).
                    pq.add(new Pair(e.nbr, rem.v, e.wt));
                }
            }
        }
    }

    public static ArrayList<Edge>[] createGraph(int vces) {
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) graph[i] = new ArrayList<>();

        // Using edges from your image
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 4, 6, 8);

        return graph;
    }

    private static void addEdge(ArrayList<Edge>[] graph, int u, int v, int wt) {
        graph[u].add(new Edge(u, v, wt));
        graph[v].add(new Edge(v, u, wt));
    }
}