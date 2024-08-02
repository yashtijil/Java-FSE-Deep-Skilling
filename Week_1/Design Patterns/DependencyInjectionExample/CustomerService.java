
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String getCustomer(int id) {
        return customerRepository.findCustomerById(id);
    }

    public static void main(String[] args) {
        
    }
}
