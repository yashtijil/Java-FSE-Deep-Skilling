
public class testSingleton {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("hello");
        logger2.log("hi");

        // Check if both logger1 and logger2 refer to the same instance
        if (logger1 == logger2) {
            System.out.println("Both instances are the same.");
        } else {
            System.out.println("Instances are different.");
        }
    }
}
