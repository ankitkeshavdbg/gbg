import java.util.*;

public class ConnectedComponents {
    static class Edge {
        int src, nbr, wt;
        Edge(int src, int nbr, int wt) {
            this.src = src; this.nbr = nbr; this.wt = wt;
        }
    }

    // MODULE 1: Graph Formation
    @SuppressWarnings("unchecked")
    public static ArrayList<Edge>[] createGraph(int vces, int[][] edgesData) {
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) graph[i] = new ArrayList<>();

        for (int[] e : edgesData) {
            graph[e[0]].add(new Edge(e[0], e[1], e[2]));
            graph[e[1]].add(new Edge(e[1], e[0], e[2]));
        }
        return graph;
    }
    /*
    public static ArrayList<Edge>[] createGraph(int vces, int[][] edgesData) {
    // 1. Prepare the Array (The Empty Building)
    ArrayList<Edge>[] graph = new ArrayList[vces];
    for (int i = 0; i < vces; i++) {
        graph[i] = new ArrayList<>();
    }

    // 2. Populate the Array (Move in the Furniture)
    for (int[] edge : edgesData) {
        int u = edge[0]; // Start node
        int v = edge[1]; // End node
        int wt = edge[2]; // Weight

        // Add both ways since it's an undirected graph
        graph[u].add(new Edge(u, v, wt));
        graph[v].add(new Edge(v, u, wt));
    }
    
    return graph;
}


    */

    // MODULE 2: The Loop (Manager)
    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph, int vces) {
        ArrayList<ArrayList<Integer>> allComps = new ArrayList<>();
        boolean[] visited = new boolean[vces];

        for (int v = 0; v < vces; v++) {
            if (!visited[v]) {
                ArrayList<Integer> currentComp = new ArrayList<>();
                drawTree(graph, v, currentComp, visited);
                allComps.add(currentComp);
            }
        }
        return allComps;
    }

    // MODULE 3: The Draw Function (Scout)
    public static void drawTree(ArrayList<Edge>[] graph, int src, ArrayList<Integer> currentComp, boolean[] visited) {
        visited[src] = true;
        currentComp.add(src);
        
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                drawTree(graph, e.nbr, currentComp, visited);
            }
        }
    }

    // MODULE 4: Connectivity Status
    public static boolean isConnected(ArrayList<ArrayList<Integer>> allComps) {
        return allComps.size() == 1;
    }

    public static void main(String[] args) {
        int vces = 7;
        int[][] edgesData = {
            {0, 1, 10}, {2, 3, 10}, {4, 5, 10}, {5, 6, 10}, {4, 6, 10}
        };

        // Execution Flow
        ArrayList<Edge>[] graph = createGraph(vces, edgesData);
        ArrayList<ArrayList<Integer>> comps = getConnectedComponents(graph, vces);
        boolean connectedStatus = isConnected(comps);

        // Output
        System.out.println("Components: " + comps);
        System.out.println("Is the graph connected? " + connectedStatus);
    }
}
