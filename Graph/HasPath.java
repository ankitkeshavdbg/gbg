import java.util.*;

public class HasPath {
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

    /**
     * BVNR Algorithm Logic:
     * 1. Base Case: Check if src == dest
     * 2. Visited: Mark current node to avoid cycles
     * 3. Neighbors: Loop through the adjacency list
     * 4. Recurse: Call hasPath for unvisited neighbors
     */
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
        // 1. Base Case
        if (src == dest) {
            return true;
        }

        // 2. Mark Visited
        visited[src] = true;

        // 3. Neighbor Loop
        for (Edge edge : graph[src]) {
            // 4. Recurse (if not visited)
            if (visited[edge.nbr] == false) {
                boolean hasNbrPath = hasPath(graph, edge.nbr, dest, visited);
                if (hasNbrPath) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int vces = 7;
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        // Hardcoding edges from the test case
        graph[0].add(new Edge(0, 1, 10)); graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 2, 10)); graph[2].add(new Edge(2, 1, 10));
        graph[2].add(new Edge(2, 3, 10)); graph[3].add(new Edge(3, 2, 10));
        graph[0].add(new Edge(0, 3, 10)); graph[3].add(new Edge(3, 0, 10));
        graph[3].add(new Edge(3, 4, 10)); graph[4].add(new Edge(4, 3, 10));
        graph[4].add(new Edge(4, 5, 10)); graph[5].add(new Edge(5, 4, 10));
        graph[5].add(new Edge(5, 6, 10)); graph[6].add(new Edge(6, 5, 10));
        graph[4].add(new Edge(4, 6, 10)); graph[6].add(new Edge(6, 4, 10));

        // --- FIRST CHECK ---
        int src1 = 0, dest1 = 6;
        boolean[] visited = new boolean[vces]; // Fresh array (all false)
        System.out.println("Path from " + src1 + " to " + dest1 + ": " + hasPath(graph, src1, dest1, visited));

        // --- SECOND CHECK (Resetting Logic) ---
        int src2 = 2, dest2 = 5;
        
        // Option A: Create a brand new array
        visited = new boolean[vces]; 
        
        // Option B: Fill existing array with false (more memory efficient)
        // Arrays.fill(visited, false); 
        
        System.out.println("Path from " + src2 + " to " + dest2 + ": " + hasPath(graph, src2, dest2, visited));
    }

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