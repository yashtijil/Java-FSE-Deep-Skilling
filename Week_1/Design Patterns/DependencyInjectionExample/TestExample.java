
public class TestExample {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Inject the repository into the service
        CustomerService service = new CustomerService(repository);

        // Use the service to find a customer
        String customer = service.getCustomer(1);
        System.out.println(customer);
    }
}
