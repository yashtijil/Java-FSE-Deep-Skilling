import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Integer, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void updateProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void deleteProduct(int productId) {
        inventory.remove(productId);
    }

    public Product getProduct(int productId) {
        return inventory.get(productId);
    }

    public static void main(String[] args) {
        
        Inventory inventory = new Inventory();

        Product product1 = new Product(1, "Headphones", 1, 1000);
        Product product2 = new Product(2, "Speakers", 1, 2000);

        inventory.addProduct(product1);
        inventory.addProduct(product2);

        // Update
        product1.setPrice(2500);
        inventory.updateProduct(product1);

        // Delete
        inventory.deleteProduct(2);

        // Display
        System.out.println(inventory.getProduct(1));
    }
}
