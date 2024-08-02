public class FinancialForecasting {

    public static double predictFutureValue(double initialValue, double growthRate, int years) {
        // Base case
        if (years == 0) {
            return initialValue;
        }

        // Calculate the value for the current year and continue for remaining years
        double newValue = initialValue * (1 + growthRate);
        return predictFutureValue(newValue, growthRate, years - 1);
    }

    public static void main(String[] args) {
        double initialValue = 1000.0; 
        double growthRate = 0.05;     
        int years = 5;               

        double futureValue = predictFutureValue(initialValue, growthRate, years);
        System.out.println("The predicted future value after " + years + " years is: Rs " + futureValue);
    }
}
