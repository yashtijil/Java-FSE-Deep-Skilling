import java.util.Arrays;

public class Library {
    private Book[] books;

    public Library(Book[] books) {
        this.books = books;
    }

    // Linear Search 
    public Book linearSearch(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary Search 
    public Book binarySearch(String title) {
        Arrays.sort(books, (b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "Percy Jackson", "Rick Riordan"),
            new Book(2, "Harry Potter", "JK Rowling"),
            new Book(3, "Artemis Fowl", "Eoin Colfer"),
            new Book(4, "Heroes of Olympus", "Rick Riordan"),
            new Book(5, "Into the Water", "Paula Hawkins")
        };

        Library library = new Library(books);

        System.out.println("Searching for 'Harry Potter' using linear search:");
        Book foundBook = library.linearSearch("Harry Potter");
        System.out.println(foundBook != null ? foundBook : "Book not found.");

        System.out.println("\nSearching for 'Into the Water' using binary search:");
        foundBook = library.binarySearch("Into the Water");
        System.out.println(foundBook != null ? foundBook : "Book not found.");
    }
}