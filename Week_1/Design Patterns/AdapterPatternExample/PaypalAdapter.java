public class PaypalAdapter implements PaymentProcessor {
    private PaypalGateway paypalGateway;

    public PaypalAdapter(PaypalGateway paypalGateway) {
        this.paypalGateway = paypalGateway;
    }

    @Override
    public void processPayment(int amount) {
        paypalGateway.makePayment(amount);
    }
}
