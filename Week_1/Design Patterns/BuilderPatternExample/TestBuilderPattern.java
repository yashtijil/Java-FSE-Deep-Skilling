public class TestBuilderPattern {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .build();

        Computer officePC = new Computer.Builder()
                .setCPU("Intel")
                .setRAM("16GB")
                .setStorage("256GB SSD")
                .build(); 

        System.out.println("Gaming PC: " + gamingPC);
        System.out.println("Office PC: " + officePC);
    }
}
