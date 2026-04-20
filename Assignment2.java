import java.util.Scanner;

class BSTNode {
    int data;
    BSTNode left, right;

    BSTNode(int data) {
        this.data = data;
        left = right = null;
    }
}

public class Assignment2 {
    BSTNode root;

    void insert(int key) {
        BSTNode newNode = new BSTNode(key);

        if (root == null) {
            root = newNode;
            return;
        }

        BSTNode current = root;
        BSTNode parent = null;

        while (current != null) {
            parent = current;
            if (key < current.data)
                current = current.left;
            else if (key > current.data)
                current = current.right;
            else {
                System.out.println("Duplicate values not allowed");
                return;
            }
        }

        if (key < parent.data)
            parent.left = newNode;
        else
            parent.right = newNode;
    }

    boolean search(int key) {
        BSTNode current = root;

        while (current != null) {
            if (key == current.data)
                return true;
            else if (key < current.data)
                current = current.left;
            else
                current = current.right;
        }
        return false;
    }

    int findMin() {
        if (root == null)
            throw new RuntimeException("BST is empty");

        BSTNode current = root;
        while (current.left != null)
            current = current.left;

        return current.data;
    }

    int findMax() {
        if (root == null)
            throw new RuntimeException("BST is empty");

        BSTNode current = root;
        while (current.right != null)
            current = current.right;

        return current.data;
    }

    void delete(int key) {
        BSTNode current = root;
        BSTNode parent = null;

        while (current != null && current.data != key) {
            parent = current;
            if (key < current.data)
                current = current.left;
            else
                current = current.right;
        }

        if (current == null) {
            System.out.println("Element not found.");
            return;
        }

        if (current.left != null && current.right != null) {
            BSTNode successorParent = current;
            BSTNode successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.data = successor.data;
            current = successor;
            parent = successorParent;
        }

        BSTNode child = (current.left != null) ? current.left : current.right;

        if (parent == null)
            root = child;
        else if (parent.left == current)
            parent.left = child;
        else
            parent.right = child;
    }

    void inorder(BSTNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    void display() {
        if (root == null)
            System.out.println("BST is empty");
        else {
            System.out.print("BST elements (Inorder): ");
            inorder(root);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Assignment2 bst = new Assignment2(); // ✅ FIXED
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Binary Search Tree Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Find Minimum");
            System.out.println("5. Find Maximum");
            System.out.println("6. Display BST");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter element to insert: ");
                    bst.insert(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Enter element to delete: ");
                    bst.delete(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter element to search: ");
                    int key = sc.nextInt();
                    System.out.println(bst.search(key) ? "Element found" : "Element not found");
                    break;

                case 4:
                    try {
                        System.out.println("Minimum element: " + bst.findMin());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.println("Maximum element: " + bst.findMax());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    bst.display();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}