public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromServer(filename);
    }

    private void loadFromServer(String filename) {
        System.out.println("\nLoading image from server: " + filename);
       
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}
