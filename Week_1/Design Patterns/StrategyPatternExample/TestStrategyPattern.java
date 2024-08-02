public class TestStrategyPattern {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        paymentContext.setPaymentStrategy(new CreditCardPayment("1234-5678-9101-1121", "John Doe"));
        paymentContext.pay(150.75);


        paymentContext.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        paymentContext.pay(89.99);
    }
}
