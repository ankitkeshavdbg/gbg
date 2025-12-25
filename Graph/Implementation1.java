import java.util.*;

public class Implementation1 {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void main(String[] args) {
        int vces = 7;
        // Initialization
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        // --- Hardcoded Edges from the Test Case Image ---

        // 0 1 10
        graph[0].add(new Edge(0, 1, 10));
        graph[1].add(new Edge(1, 0, 10));

        // 1 2 10
        graph[1].add(new Edge(1, 2, 10));
        graph[2].add(new Edge(2, 1, 10));

        // 2 3 10
        graph[2].add(new Edge(2, 3, 10));
        graph[3].add(new Edge(3, 2, 10));

        // 0 3 10 
        graph[0].add(new Edge(0, 3, 10));
        graph[3].add(new Edge(3, 0, 10));

        // 3 4 10 
        graph[3].add(new Edge(3, 4, 10));
        graph[4].add(new Edge(4, 3, 10));

        // 4 5 10
        graph[4].add(new Edge(4, 5, 10));
        graph[5].add(new Edge(5, 4, 10));

        // 5 6 10
        graph[5].add(new Edge(5, 6, 10));
        graph[6].add(new Edge(6, 5, 10));

        // 4 6 10 
        graph[4].add(new Edge(4, 6, 10));
        graph[6].add(new Edge(6, 4, 10));

        // Hardcoded Source and Destination
        int src = 0;
        int dest = 6;

        System.out.println("Graph hardcoded with " + vces + " vertices.");
        display(graph);
    }

    // Helper method to see the graph structure
    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print("Vertex " + i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("[" + e.src + "-" + e.nbr + " @ " + e.wt + "] ");
            }
            System.out.println();
        }
    }
}