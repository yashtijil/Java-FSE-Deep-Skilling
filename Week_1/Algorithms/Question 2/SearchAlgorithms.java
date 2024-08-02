public class SearchAlgorithms {

    // Linear Search
    public static Product linearSearch(Product[] products, int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null; 
    }

    // Binary Search
    public static Product binarySearch(Product[] products, int productId) {
        int left = 0, right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].getProductId() == productId) {
                return products[mid];
            } else if (products[mid].getProductId() < productId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; 
    }

    
    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop"),
            new Product(2, "Smartphone"),
            new Product(3, "Coffee Maker"),
            new Product(4, "Desk Chair")
        };

        // Test Linear Search
        Product foundProduct1 = SearchAlgorithms.linearSearch(products, 2);
        System.out.println("Linear Search Result:");
        if (foundProduct1 != null) {
            System.out.println("Product ID: " + foundProduct1.getProductId());
            System.out.println("Product Name: " + foundProduct1.getProductName());
        } else {
            System.out.println("Product not found.");
        }

        // Test Binary Search
        // Sorting products for binary search
        java.util.Arrays.sort(products, (p1, p2) -> Integer.compare(p1.getProductId(), p2.getProductId()));
        Product foundProduct2 = SearchAlgorithms.binarySearch(products, 3);
        System.out.println("\nBinary Search Result:");
        if (foundProduct2 != null) {
            System.out.println("Product ID: " + foundProduct2.getProductId());
            System.out.println("Product Name: " + foundProduct2.getProductName());
        } else {
            System.out.println("Product not found.");
        }
    }
}
