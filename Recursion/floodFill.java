import java.util.*;

public class floodFill {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        // Example: 3x3 Maze
        // 0 = path, 1 = wall
        int[][] maze = {
            {0, 1, 0},
            {0, 0, 0},
            {0, 1, 0}
        };

        int n = maze.length;
        int m = maze[0].length;
        
        // Visited array to prevent infinite loops
        boolean[][] visited = new boolean[n][m];
        
        System.out.println("Paths to reach destination:");
        floodfill(maze, 0, 0, "", visited);
    }

    /**
     * @param maze    The 2D grid
     * @param r       Current Row
     * @param c       Current Column
     * @param asf     Answer So Far (Path string)
     * @param visited Tracking visited cells for the current path
     */
    public static void floodfill(int[][] maze, int r, int c, String asf, boolean[][] visited) {
        // 1. NEGATIVE BASE CASES (Pruning)
        // Check: Out of bounds OR hit a wall OR already visited in this path
        if (r < 0 || c < 0 || r == maze.length || c == maze[0].length || 
            maze[r][c] == 1 || visited[r][c] == true) {
            return;
        }

        // 2. POSITIVE BASE CASE (Success)
        // If we reach the bottom-right corner
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            System.out.println(asf);
            return;
        }

        // 3. THE WORK: Mark the current cell as visited
        visited[r][c] = true;

        // 4. THE FAITH: Try all 4 directions in a specific order
        // Top
        floodfill(maze, r - 1, c, asf + "t", visited);
        // Left
        floodfill(maze, r, c - 1, asf + "l", visited);
        // Down
        floodfill(maze, r + 1, c, asf + "d", visited);
        // Right
        floodfill(maze, r, c + 1, asf + "r", visited);

        // 5. THE UNDO (Backtracking)
        // Unmark this cell so other potential paths can use it
        visited[r][c] = false;
    }
}