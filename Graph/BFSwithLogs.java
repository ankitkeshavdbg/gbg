import java.util.*;

public class BFSwithLogs {
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
            return "(" + src + "-" + nbr + ")";
        }
    }

    public static class Pair {
        int v;
        String psf;

        Pair(int v, String psf) {
            this.v = v;
            this.psf = psf;
        }

        @Override
        public String toString() {
            return "[" + v + " | " + psf + "]";
        }
    }

    public static void breadthFirstSearch(ArrayList<Edge>[] graph, int startVertex) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(startVertex, startVertex + ""));
        boolean[] visited = new boolean[graph.length];

        int step = 1;
        while (q.size() > 0) {
            System.out.println("\n--- STEP " + step + " ---");
            System.out.println("Current Queue: " + q);

            // R - REMOVE
            Pair fp = q.removeFirst();
            System.out.println("REMOVED: " + fp.v + " (Path: " + fp.psf + ")");

            // M - MARK (Skip if already visited)
            if (visited[fp.v]) {
                System.out.println("SKIPPED: Node " + fp.v + " already visited.");
                continue;
            }
            visited[fp.v] = true;

            // W - WORK
            System.out.println("VISITED: " + fp.v + " via " + fp.psf);

            // A - ADD Neighbors
            System.out.print("ADDING CHILDREN: ");
            boolean addedAny = false;
            for (Edge e : graph[fp.v]) {
                if (!visited[e.nbr]) {
                    Pair child = new Pair(e.nbr, fp.psf + e.nbr);
                    q.add(child);
                    System.out.print(child + " ");
                    addedAny = true;
                }
            }
            if (!addedAny) System.out.print("None");
            System.out.println();
            
            step++;
        }
        System.out.println("\n--- BFS Completed ---");
    }

    public static ArrayList<Edge>[] createGraph(int vces) {
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++) graph[i] = new ArrayList<>();
        
        // Simplified edge adding for the example
        int[][] edges = {{0,1}, {1,2}, {2,3}, {0,3}, {3,4}, {4,5}, {5,6}, {4,6}};
        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[0], e[1], 10));
            graph[e[1]].add(new Edge(e[1], e[0], 10));
        }
        return graph;
    }

    public static void main(String[] args) {
        ArrayList<Edge>[] graph = createGraph(7);
        breadthFirstSearch(graph, 2);
    }
}