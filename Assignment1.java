import java.util.Scanner;
import java.util.Stack;

public class Assignment1 {

    static int[] tree;
    static int size;

    static void createTree(Scanner sc) {
        System.out.print("Enter number of nodes: ");
        size = sc.nextInt();

        tree = new int[2 * size + 1];

        System.out.print("Enter root value: ");
        tree[1] = sc.nextInt();

        for (int i = 1; i <= size; i++) {
            if (tree[i] == 0) continue;

            System.out.print("Does " + tree[i] + " have left child? (y/n): ");
            if (sc.next().equalsIgnoreCase("y")) {
                System.out.print("Enter left child value: ");
                tree[2 * i] = sc.nextInt();
            }

            System.out.print("Does " + tree[i] + " have right child? (y/n): ");
            if (sc.next().equalsIgnoreCase("y")) {
                System.out.print("Enter right child value: ");
                tree[2 * i + 1] = sc.nextInt();
            }
        }
    }

    static void displayTree() {
        if (tree == null) {
            System.out.println("Tree is empty!");
            return;
        }

        System.out.println("\nBinary Tree (Array Representation):");
        for (int i = 1; i < tree.length; i++) {
            if (tree[i] != 0) {
                System.out.println("Index " + i + " -> " + tree[i]);
            }
        }
    }

    static void inorderRecursive(int i) {
        if (i < tree.length && tree[i] != 0) {
            inorderRecursive(2 * i);
            System.out.print(tree[i] + " ");
            inorderRecursive(2 * i + 1);
        }
    }

    static void preorderRecursive(int i) {
        if (i < tree.length && tree[i] != 0) {
            System.out.print(tree[i] + " ");
            preorderRecursive(2 * i);
            preorderRecursive(2 * i + 1);
        }
    }

    static void postorderRecursive(int i) {
        if (i < tree.length && tree[i] != 0) {
            postorderRecursive(2 * i);
            postorderRecursive(2 * i + 1);
            System.out.print(tree[i] + " ");
        }
    }

    static void inorderNonRecursive() {
        Stack<Integer> s = new Stack<>();
        int ptr = 1;

        while ((ptr < tree.length && tree[ptr] != 0) || !s.isEmpty()) {
            while (ptr < tree.length && tree[ptr] != 0) {
                s.push(ptr);
                ptr = 2 * ptr;
            }
            ptr = s.pop();
            System.out.print(tree[ptr] + " ");
            ptr = 2 * ptr + 1;
        }
    }

    static int countLeafNodes() {
        int count = 0;
        for (int i = 1; i < tree.length; i++) {
            if (tree[i] != 0) {
                int left = 2 * i;
                int right = 2 * i + 1;

                if ((left >= tree.length || tree[left] == 0) &&
                    (right >= tree.length || tree[right] == 0)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println(
                "\n1. Create Tree" +
                "\n2. Display Tree" +
                "\n3. Inorder Recursive" +
                "\n4. Inorder Non-Recursive" +
                "\n5. Preorder Recursive" +
                "\n6. Postorder Recursive" +
                "\n7. Count Leaf Nodes" +
                "\n8. Exit"
            );

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: createTree(sc); break;
                case 2: displayTree(); break;
                case 3: inorderRecursive(1); break;
                case 4: inorderNonRecursive(); break;
                case 5: preorderRecursive(1); break;
                case 6: postorderRecursive(1); break;
                case 7: System.out.println("Leaf Nodes: " + countLeafNodes()); break;
                case 8: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 8);

        sc.close();
    }
}