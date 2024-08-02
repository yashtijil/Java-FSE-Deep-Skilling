
public class Computer {
    private final String CPU;
    private final String RAM;
    private final String Storage;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
    }

    public static class Builder {
        private String CPU;
        private String RAM;
        private String Storage;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(String Storage) {
            this.Storage = Storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer [CPU:" + CPU + ", RAM:" + RAM + ", Storage:" + Storage + "]\n";
    }
    public static void main(String[] args) {
    }
}
