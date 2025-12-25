import java.util.*;

public class PrintAllPaths {
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
     * BVNR + Backtracking logic:
     * 1. Base Case: If src == dest, print the path collected so far.
     * 2. Visited: Mark true to prevent cycles in the CURRENT path.
     * 3. Neighbors: Loop and recurse.
     * 4. BACKTRACK: Mark visited false so other paths can use this node.
     */
    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {
        // 1. Base Case: Path found!
        if (src == dest) {
            System.out.println(psf); // Print path so far 
            return;
        }

        // 2. Mark Visited
        visited[src] = true;

        // 3. Neighbor Loop
        for (Edge edge : graph[src]) {
            if (!visited[edge.nbr]) {
                // 4. Recurse with neighbor and update path string
                printAllPaths(graph, edge.nbr, dest, visited, psf + " -> " +edge.nbr );
            }
        }

        // --- THE BACKTRACKING STEP ---
        // Unmark this node so it can be part of a DIFFERENT path
        visited[src] = false;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int vces = 7;
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        // Hardcoding edges from your test case
        graph[0].add(new Edge(0, 1, 10)); graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 2, 10)); graph[2].add(new Edge(2, 1, 10));
        graph[2].add(new Edge(2, 3, 10)); graph[3].add(new Edge(3, 2, 10));
        graph[0].add(new Edge(0, 3, 10)); graph[3].add(new Edge(3, 0, 10));
        graph[3].add(new Edge(3, 4, 10)); graph[4].add(new Edge(4, 3, 10));
        graph[4].add(new Edge(4, 5, 10)); graph[5].add(new Edge(5, 4, 10));
        graph[5].add(new Edge(5, 6, 10)); graph[6].add(new Edge(6, 5, 10));
        graph[4].add(new Edge(4, 6, 10)); graph[6].add(new Edge(6, 4, 10));

        int src = 0, dest = 6;
        boolean[] visited = new boolean[vces];
        
        System.out.println("All possible paths from " + src + " to " + dest + ":");
        // Start with an empty "Path So Far" (psf)
        printAllPaths(graph, src, dest, visited, src+ "");
    }
}
