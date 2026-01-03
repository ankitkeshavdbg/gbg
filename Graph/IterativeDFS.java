import java.util.*;

public class IterativeDFS {
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
            return " (" + src + "-" + nbr + " @ " + wt + ")";
        }
    }

    public static class Pair {
        int v;
        String psf;

        Pair(int v, String psf) {
            this.v = v;
            this.psf = psf;
        }
    }

    public static void iterativeDFS(ArrayList<Edge>[] graph, int startVertex) {
        // --- CHANGE: Use a Stack instead of a Queue ---
        Stack<Pair> stack = new Stack<>();
        
        // Initial State
        stack.push(new Pair(startVertex, startVertex + ""));
        
        boolean[] visited = new boolean[graph.length];

        while (stack.size() > 0) {
            // R - REMOVE: Get the top-most element (LIFO)
            Pair removedPair = stack.pop();

            // M - MARK: Check if already visited
            if (visited[removedPair.v] == true) {
                continue;
            }
            visited[removedPair.v] = true;

            // W - WORK: Print the path
            System.out.println(removedPair.v + " via " + removedPair.psf);

            // A - ADD: Add unvisited neighbors to the stack
            for (Edge edge : graph[removedPair.v]) {
                if (visited[edge.nbr] == false) {
                    stack.push(new Pair(edge.nbr, removedPair.psf + edge.nbr));
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Edge>[] graph = createGraph(7);
        System.out.println("Iterative DFS starting from vertex 2:");
        iterativeDFS(graph, 2);
    }

    // Graph creation remains the same as your BFS code
    public static ArrayList<Edge>[] createGraph(int vces) {
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) graph[i] = new ArrayList<>();
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