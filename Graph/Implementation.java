import java.util.*;

public class Implementation {
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
        // Correcting the array initialization for generics
        ArrayList<Edge>[] graph = new ArrayList[vces];
        
        for (int i = 0; i < vces; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        // --- Adding Edges from the Diagram ---

        // Edge 0-3 (Weight 40)
        graph[0].add(new Edge(0, 3, 40));
        graph[3].add(new Edge(3, 0, 40));

        // Edge 0-1 (Weight 10)
        graph[0].add(new Edge(0, 1, 10));
        graph[1].add(new Edge(1, 0, 10));

        // Edge 1-2 (Weight 10)
        graph[1].add(new Edge(1, 2, 10));
        graph[2].add(new Edge(2, 1, 10));

        // Edge 2-3 (Weight 10)
        graph[2].add(new Edge(2, 3, 10));
        graph[3].add(new Edge(3, 2, 10));

        // Edge 3-4 (Weight 2)
        graph[3].add(new Edge(3, 4, 2));
        graph[4].add(new Edge(4, 3, 2));

        // Edge 4-5 (Weight 3)
        graph[4].add(new Edge(4, 5, 3));
        graph[5].add(new Edge(5, 4, 3));

        // Edge 5-6 (Weight 3)
        graph[5].add(new Edge(5, 6, 3));
        graph[6].add(new Edge(6, 5, 3));

        // Edge 4-6 (Weight 8)
        graph[4].add(new Edge(4, 6, 8));
        graph[6].add(new Edge(6, 4, 8));

        System.out.println("Graph created successfully with " + vces + " vertices.");
    }
}