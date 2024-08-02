import java.util.Arrays;

public class SortingAlgorithms {

    // Bubble Sort
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        Order pivot = orders[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() < pivot.getTotalPrice()) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order(1, "John Doe", 150.0),
            new Order(2, "Peter Parker", 300.0),
            new Order(3, "Alice Johnson", 50.0),
            new Order(4, "Tony Stark", 200.0)
        };

        // Bubble Sort
        SortingAlgorithms.bubbleSort(orders);
        System.out.println("Orders sorted by Bubble Sort:");
        Arrays.stream(orders).forEach(System.out::println);

        // Quick Sort
        Order[] quickSortOrders = {
            new Order(1, "John Doe", 150.0),
            new Order(2, "Peter Parker", 300.0),
            new Order(3, "Alice Johnson", 50.0),
            new Order(4, "Tony Stark", 200.0)
        };
        SortingAlgorithms.quickSort(quickSortOrders, 0, quickSortOrders.length - 1);
        System.out.println("\nOrders sorted by Quick Sort:");
        Arrays.stream(quickSortOrders).forEach(System.out::println);
    }
}
