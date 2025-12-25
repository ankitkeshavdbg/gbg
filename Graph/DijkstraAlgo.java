import java.util.*;

public class DijkstraAlgo {
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
        int v;
        String psf; // Path So Far
        int wsf;    // Weight So Far

        Pair(int v, String psf, int wsf) {
            this.v = v;
            this.psf = psf;
            this.wsf = wsf;
        }

        // Priority Queue uses this to bring the pair with 'less value' (weight) to the top
        public int compareTo(Pair other) {
            return this.wsf - other.wsf;
        }
    }

    public static void main(String[] args) {
        int vertices = 7;
        ArrayList<Edge>[] graph = createGraph(vertices);
        int src = 0;
        
        System.out.println("Shortest Paths from Source " + src + ":");
        dijkstra(graph, src);
    }

    public static void dijkstra(ArrayList<Edge>[] graph, int src) {
        // Priority Queue to always get the minimum weight path
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.length];

        // Initial Add
        pq.add(new Pair(src, src + "", 0));

        while (pq.size() > 0) {
            // R - REMOVE (Smallest wsf pair comes out)
            Pair rem = pq.remove();

            // M - MARK
            if (visited[rem.v]==true) {
                continue;
            }
            visited[rem.v] = true;

            // W - WORK (Print the shortest path to this vertex)
            System.out.println(rem.v + " via " + rem.psf + " @ total weight: " + rem.wsf);

            // A - ADD
            for (Edge e : graph[rem.v]) {
                if (visited[e.nbr]==false) {
                    // Update weight: weight so far + edge weight
                    pq.add(new Pair(e.nbr, rem.psf + "->" + e.nbr, rem.wsf + e.wt));
                }
            }
        }
    }

    public static ArrayList<Edge>[] createGraph(int vces) {
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) graph[i] = new ArrayList<>();

        
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 0, 3, 40); // The "heavy" direct path
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