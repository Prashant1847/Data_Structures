package graph.using_matrix;
import java.util.*;

public class Graph {
    private int[][] adjMatrix;
    private int vertices;
    private boolean directed;

    public Graph(int vertices, boolean directed){
        this.vertices = vertices;
        this.directed = directed;
        adjMatrix = new int[vertices][vertices];
    }

    public void addEdge(int src, int dest){
        if(src < 0 || dest < 0 || src >= vertices || dest >= vertices){
            System.out.println("Invalid vertex");
            return;
        }

        adjMatrix[src][dest] = 1;
        if(!directed) adjMatrix[dest][src] = 1;
    }

    public void removeEdge(int src, int dest){
         if(src < 0 || dest < 0 || src >= vertices || dest >= vertices){
            System.out.println("Invalid vertex");
            return;
        }

        adjMatrix[src][dest] = 0;
        if(!directed) adjMatrix[src][dest] = 0;
    }

    public boolean hasEdge(int src, int dest){
        return adjMatrix[src][dest] == 1;
    }

    // Display adjacency matrix
    public void display() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS starting from vertex " + start + ": ");
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                dfsRecursive(i, visited);
            }
        }
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS starting from vertex " + start + ": ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5, false); // false = undirected

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // Display
        graph.display();

        // Traversals
        graph.dfs(0);
        graph.bfs(0);

        // Edge checks and deletion
        System.out.println("Edge between 1 and 3 exists: " + graph.hasEdge(1, 3));
        graph.removeEdge(1, 3);
        System.out.println("Edge between 1 and 3 exists after removal: " + graph.hasEdge(1, 3));
        graph.display();
    }

}
