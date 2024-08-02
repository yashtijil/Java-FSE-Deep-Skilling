public class TestAdapter {
    public static void main(String[] args) {
        PaymentProcessor paypalProcessor = new PaypalAdapter(new PaypalGateway());

        paypalProcessor.processPayment(100);
    }
}
