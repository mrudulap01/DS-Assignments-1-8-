import java.util.Scanner;

class AVLNode {
    String key;
    int height;
    int balanceFactor;
    AVLNode left, right, parent;

    public AVLNode(String key) {
        this.key = key;
        this.height = 1;
        this.balanceFactor = 0;
        this.left = this.right = this.parent = null;
    }
}

class AVLTree {

    private AVLNode root;

    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    private void update(AVLNode node) {
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        node.height = 1 + Math.max(leftHeight, rightHeight);
        node.balanceFactor = leftHeight - rightHeight;
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        if (T2 != null)
            T2.parent = y;

        x.parent = y.parent;
        y.parent = x;

        update(y);
        update(x);

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        if (T2 != null)
            T2.parent = x;

        y.parent = x.parent;
        x.parent = y;

        update(x);
        update(y);

        return y;
    }

    private AVLNode balance(AVLNode node) {
        update(node);

        if (node.balanceFactor > 1) {
            if (node.left.balanceFactor < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (node.balanceFactor < -1) {
            if (node.right.balanceFactor > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode insert(AVLNode node, String key) {
        if (node == null)
            return new AVLNode(key);

        if (key.compareToIgnoreCase(node.key) < 0) {
            node.left = insert(node.left, key);
            node.left.parent = node;
        } else if (key.compareToIgnoreCase(node.key) > 0) {
            node.right = insert(node.right, key);
            node.right.parent = node;
        } else {
            return node;
        }

        return balance(node);
    }

    public void insert(String key) {
        root = insert(root, key);
    }

    private AVLNode minValueNode(AVLNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    private AVLNode delete(AVLNode node, String key) {
        if (node == null)
            return null;

        if (key.compareToIgnoreCase(node.key) < 0) {
            node.left = delete(node.left, key);
        } else if (key.compareToIgnoreCase(node.key) > 0) {
            node.right = delete(node.right, key);
        } else {

            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    return null;
                } else {
                    temp.parent = node.parent;
                    node = temp;
                }
            } else {
                AVLNode temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }

        return balance(node);
    }

    public void delete(String key) {
        root = delete(root, key);
    }

    private void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    private void preorder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    private void postorder(AVLNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public void displayInorder() {
        inorder(root);
        System.out.println();
    }

    public void displayPreorder() {
        preorder(root);
        System.out.println();
    }

    public void displayPostorder() {
        postorder(root);
        System.out.println();
    }
}

public class Assignment3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVLTree tree = new AVLTree();
        int choice;
        String month;

        do {
            System.out.println("\n===== AVL Tree Menu =====");
            System.out.println("1. Insert Month");
            System.out.println("2. Delete Month");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Preorder Traversal");
            System.out.println("5. Postorder Traversal");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Month to insert: ");
                    month = sc.nextLine();
                    tree.insert(month);
                    break;

                case 2:
                    System.out.print("Enter Month to delete: ");
                    month = sc.nextLine();
                    tree.delete(month);
                    break;

                case 3:
                    System.out.println("Inorder Traversal:");
                    tree.displayInorder();
                    break;

                case 4:
                    System.out.println("Preorder Traversal:");
                    tree.displayPreorder();
                    break;

                case 5:
                    System.out.println("Postorder Traversal:");
                    tree.displayPostorder();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}