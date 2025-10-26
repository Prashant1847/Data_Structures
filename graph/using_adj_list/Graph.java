package graph.using_adj_list;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int vertices;
    private boolean directed;
    private LinkedList<Integer>[] adjList;

    public Graph(int vertices, boolean directed){
        this.vertices = vertices;
        this.directed = directed;
        adjList = new LinkedList[vertices];
         for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest){
        if (src < 0 || dest < 0 || src >= vertices || dest >= vertices) {
            System.out.println("Invalid vertex");
            return;
        }
        adjList[src].add(dest);
        if (!directed)
            adjList[dest].add(src);
    }

    // Remove edge
    public void removeEdge(int src, int dest) {
        if (src < 0 || dest < 0 || src >= vertices || dest >= vertices) {
            System.out.println("Invalid vertex");
            return;
        }
        adjList[src].remove((Integer) dest);
        if (!directed)
            adjList[dest].remove((Integer) src);
    }

     // Check if edge exists
    public boolean hasEdge(int src, int dest) {
        return adjList[src].contains(dest);
    }

    // Display adjacency list
    public void display() {
        System.out.println("Adjacency List:");
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " -> ");
            for (int node : adjList[i])
                System.out.print(node + " ");
            System.out.println();
        }
    }

    // Depth First Search
    public void dfs(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS starting from vertex " + start + ": ");
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int neighbor : adjList[vertex]) {
            if (!visited[neighbor])
                dfsRecursive(neighbor, visited);
        }
    }


    
    // Breadth First Search
    public void bfs(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS starting from vertex " + start + ": ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjList[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // MAIN
    public static void main(String[] args) {
        Graph graph = new Graph(5, false); 

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

        // Edge checks and removal
        System.out.println("Edge between 1 and 3 exists: " + graph.hasEdge(1, 3));
        graph.removeEdge(1, 3);
        System.out.println("Edge between 1 and 3 exists after removal: " + graph.hasEdge(1, 3));

        graph.display();
    }
}
