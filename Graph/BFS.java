import java.util.*;
public class BFS{
    public static class Edge{
        int src;
        int nbr;
        int wt;
        Edge(int src, int nbr, int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
        @Override
        public String toString() {
            // This formats how the edge appears when printed
            return " (" + src + "-" + nbr + " @ " + wt + ")";
        }
    }
    public static ArrayList<Edge>[] createGraph(int vces){
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for(int i=0; i< vces; i++){
            graph[i]= new ArrayList<>();
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

        return graph;
    }
    public static void display(ArrayList<Edge>[] graph){
        for(int i=0; i<7; i++){
            System.out.print("graph["+i+"] ->");
            for(Edge e: graph[i]){
                System.out.print(e);
            }
            System.out.println();
        }
    }
    public static class Pair{
        int v;
        String psf;
        Pair(int v, String psf){
            this.v = v;
            this.psf = psf;
        }
    }
    /**
     * Breadth-First Search (BFS) explores the graph level by level.
     * It is commonly used to find the shortest path in an unweighted graph.
     * * Logic: R-M-W-A (Remove, Mark, Work, Add)
     */
    public static void breadthFirstSearch(ArrayList<Edge>[] graph, int startVertex) {
        // ArrayDeque is faster than LinkedList for Queue operations in Java
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        
        // 1. Initial State: Add the starting node to the queue
        queue.add(new Pair(startVertex, startVertex + ""));
        
        // Visited array prevents processing the same node multiple times (cycles)
        boolean[] visited = new boolean[graph.length];

        while (queue.size() > 0) {
            // R - REMOVE: Get the front-most element from the queue
            Pair removedPair = queue.removeFirst();

            // M - MARK*: If already visited, this is a redundant path, skip it
            // *Note: In BFS, we mark a node as visited when it is REMOVED from the queue
            if (visited[removedPair.v] == true) {
                continue;
            }
            visited[removedPair.v] = true;

            // W - WORK: Perform your logic (e.g., printing the path)
            System.out.println(removedPair.v + " via " + removedPair.psf);

            // A - ADD: Add all unvisited neighbors to the queue
            for (Edge edge : graph[removedPair.v]) {
                if (visited[edge.nbr] == false) {
                    // Add the neighbor with the updated Path So Far (psf)
                    queue.add(new Pair(edge.nbr, removedPair.psf + edge.nbr));
                }
            }
        }
    }
    public static void main(String[] args){

        ArrayList<Edge>[] graph = createGraph(7);
        //display(graph);
        breadthFirstSearch(graph,2);
    }
}