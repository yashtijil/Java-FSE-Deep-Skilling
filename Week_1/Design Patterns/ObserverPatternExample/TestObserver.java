public class TestObserver {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        MobileApp mobileApp = new MobileApp();
        WebApp webApp = new WebApp();

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setStockPrice(100.50);
        stockMarket.setStockPrice(105.75); 

        stockMarket.deregisterObserver(mobileApp);

        stockMarket.setStockPrice(110.00); 
    }
}
