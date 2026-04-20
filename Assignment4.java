import java.util.*;

class HeapADT {

    private ArrayList<Integer> heap = new ArrayList<>();
    private boolean isMinHeap = true;

    public void createHeap(int[] arr, boolean isMin) {
        heap.clear();
        isMinHeap = isMin;

        for (int num : arr) {
            heap.add(num);
        }

        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            downAdjust(i);
        }
    }

    public void insert(int value) {
        heap.add(value);
        upAdjust(heap.size() - 1);
    }

    private void upAdjust(int index) {
        int parent = (index - 1) / 2;

        while (index > 0 && compare(heap.get(index), heap.get(parent))) {
            Collections.swap(heap, index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void downAdjust(int index) {
        int left, right, selected;

        while (true) {
            left = 2 * index + 1;
            right = 2 * index + 2;
            selected = index;

            if (left < heap.size() && compare(heap.get(left), heap.get(selected))) {
                selected = left;
            }

            if (right < heap.size() && compare(heap.get(right), heap.get(selected))) {
                selected = right;
            }

            if (selected != index) {
                Collections.swap(heap, index, selected);
                index = selected;
            } else {
                break;
            }
        }
    }

    private boolean compare(int child, int parent) {
        return isMinHeap ? child < parent : child > parent;
    }

    public void heapSort() {
        ArrayList<Integer> tempHeap = new ArrayList<>(heap);

        System.out.println("Sorted Elements:");
        while (!heap.isEmpty()) {
            System.out.print(deleteRoot() + " ");
        }
        System.out.println();

        heap = tempHeap;
    }

    private int deleteRoot() {
        if (heap.isEmpty())
            return -1;

        int root = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            downAdjust(0);
        }

        return root;
    }

    public void display() {
        if (heap.isEmpty()) {
            System.out.println("Heap is empty.");
            return;
        }

        System.out.print("Heap Elements: ");
        for (int num : heap) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

public class Assignment4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HeapADT heap = new HeapADT();

        int choice;

        do {
            System.out.println("\n--- Heap Menu ---");
            System.out.println("1. Create Heap");
            System.out.println("2. Insert Element");
            System.out.println("3. Display Heap");
            System.out.println("4. Heap Sort");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number of elements: ");
                    int n = sc.nextInt();
                    int[] arr = new int[n];

                    System.out.println("Enter elements:");
                    for (int i = 0; i < n; i++) {
                        arr[i] = sc.nextInt();
                    }

                    System.out.print("Enter 1 for Min Heap, 2 for Max Heap: ");
                    int type = sc.nextInt();

                    heap.createHeap(arr, type == 1);
                    break;

                case 2:
                    System.out.print("Enter element to insert: ");
                    heap.insert(sc.nextInt());
                    break;

                case 3:
                    heap.display();
                    break;

                case 4:
                    heap.heapSort();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}