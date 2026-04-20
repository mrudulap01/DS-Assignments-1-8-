import java.util.Scanner;

class HashTable {
    int[] table;
    int size;

    // Constructor
    HashTable(int size) {
        this.size = size;
        table = new int[size];

        // Initialize with -1 (empty)
        for (int i = 0; i < size; i++) {
            table[i] = -1;
        }
    }

    // Hash Function
    int hashFunction(int key) {
        return key % size;
    }

    // Insert
    void insert(int key) {
        int index = hashFunction(key);

        // Linear probing
        while (table[index] != -1) {
            index = (index + 1) % size;
        }

        table[index] = key;
        System.out.println("Inserted at index " + index);
    }

    // Search
    void search(int key) {
        int index = hashFunction(key);
        int start = index;

        while (table[index] != -1) {
            if (table[index] == key) {
                System.out.println("Key found at index " + index);
                return;
            }
            index = (index + 1) % size;

            if (index == start) break;
        }

        System.out.println("Key not found");
    }

    // Delete
    void delete(int key) {
        int index = hashFunction(key);
        int start = index;

        while (table[index] != -1) {
            if (table[index] == key) {
                table[index] = -1;
                System.out.println("Key deleted");
                return;
            }
            index = (index + 1) % size;

            if (index == start) break;
        }

        System.out.println("Key not found");
    }

    // Display
    void display() {
        System.out.println("Hash Table:");
        for (int i = 0; i < size; i++) {
            if (table[i] != -1)
                System.out.println(i + " -> " + table[i]);
            else
                System.out.println(i + " -> Empty");
        }
    }
}

// Main Class
public class Assignment8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of hash table: ");
        int size = sc.nextInt();

        HashTable ht = new HashTable(size);

        while (true) {
            System.out.println("\n1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                    ht.insert(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Enter key to search: ");
                    ht.search(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter key to delete: ");
                    ht.delete(sc.nextInt());
                    break;

                case 4:
                    ht.display();
                    break;

                case 5:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}