import java.util.*;

class Graph {
    int V;
    String[] users;
    int[][] adjMatrix;
    LinkedList<Integer>[] adjList;

    // Constructor
    Graph(int v) {
        V = v;
        users = new String[V];
        adjMatrix = new int[V][V];
        adjList = new LinkedList[V];

        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add users
    void addUsers(Scanner sc) {
        System.out.println("Enter user names:");
        for (int i = 0; i < V; i++) {
            System.out.print("User " + i + ": ");
            users[i] = sc.next();
        }
    }

    // -------- MATRIX --------
    void createMatrix(Scanner sc) {
        System.out.print("Enter number of friendships: ");
        int e = sc.nextInt();

        System.out.println("Enter friendships (u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }
    }

    void bfsMatrix(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        System.out.print("BFS (Matrix): ");

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(users[node] + " ");

            for (int i = 0; i < V; i++) {
                if (adjMatrix[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        System.out.println();
    }

    void dfsMatrix(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS (Matrix): ");
        dfsMatrixUtil(start, visited);
        System.out.println();
    }

    void dfsMatrixUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(users[node] + " ");

        for (int i = 0; i < V; i++) {
            if (adjMatrix[node][i] == 1 && !visited[i]) {
                dfsMatrixUtil(i, visited);
            }
        }
    }

    // -------- LIST --------
    void createList(Scanner sc) {
        System.out.print("Enter number of friendships: ");
        int e = sc.nextInt();

        System.out.println("Enter friendships (u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            adjList[u].add(v);
            adjList[v].add(u);
        }
    }

    void bfsList(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        System.out.print("BFS (List): ");

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(users[node] + " ");

            for (int adj : adjList[node]) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    q.add(adj);
                }
            }
        }
        System.out.println();
    }

    void dfsList(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS (List): ");
        dfsListUtil(start, visited);
        System.out.println();
    }

    void dfsListUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(users[node] + " ");

        for (int adj : adjList[node]) {
            if (!visited[adj]) {
                dfsListUtil(adj, visited);
            }
        }
    }
}

// -------- MAIN --------
public class Assignment5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Create Graph using Adjacency Matrix");
            System.out.println("2. Create Graph using Adjacency List");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            if (choice == 3) break;

            System.out.print("Enter number of users: ");
            int v = sc.nextInt();

            Graph g = new Graph(v);
            g.addUsers(sc);

            if (choice == 1) {
                g.createMatrix(sc);
            } else if (choice == 2) {
                g.createList(sc);
            } else {
                System.out.println("Invalid choice!");
                continue;
            }

            // Traversal Menu
            while (true) {
                System.out.println("\n--- TRAVERSAL MENU ---");
                System.out.println("1. BFS");
                System.out.println("2. DFS");
                System.out.println("3. Back to Main Menu");
                System.out.print("Enter choice: ");

                int t = sc.nextInt();
                if (t == 3) break;

                System.out.print("Enter starting user index: ");
                int start = sc.nextInt();

                if (choice == 1) {
                    if (t == 1) g.bfsMatrix(start);
                    else if (t == 2) g.dfsMatrix(start);
                } else {
                    if (t == 1) g.bfsList(start);
                    else if (t == 2) g.dfsList(start);
                }
            }
        }
        sc.close();
    }
}