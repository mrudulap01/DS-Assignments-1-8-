import java.util.*;

class Edge {
    int to, weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Assignment6 {

    static void prims(List<List<Edge>> graph, int V) {
        boolean[] visited = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;   // start from vertex 0
        parent[0] = -1;

        for (int i = 0; i < V - 1; i++) {
            int u = -1;

            // find minimum key vertex not visited
            for (int v = 0; v < V; v++) {
                if (!visited[v] && (u == -1 || key[v] < key[u])) {
                    u = v;
                }
            }

            visited[u] = true;

            // update adjacent vertices
            for (Edge e : graph.get(u)) {
                if (!visited[e.to] && e.weight < key[e.to]) {
                    key[e.to] = e.weight;
                    parent[e.to] = u;
                }
            }
        }

        // Print MST
        int totalCost = 0;
        System.out.println("\nEdges in MST:");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + " weight: " + key[i]);
            totalCost += key[i];
        }

        System.out.println("Total cost of MST: " + totalCost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); // undirected graph
        }

        prims(graph, V);
    }
}