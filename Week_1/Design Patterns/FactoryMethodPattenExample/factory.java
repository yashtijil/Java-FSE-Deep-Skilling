public class factory {

    public interface Document {
        void open();
    }

    public static class WordDocument implements Document {
        @Override
        public void open() {
            System.out.println("Word document.");
        }
    }

    public static class PdfDocument implements Document {
        @Override
        public void open() {
            System.out.println("PDF document.");
        }
    }

    public static class ExcelDocument implements Document {
        @Override
        public void open() {
            System.out.println("Excel document.");
        }
    }

    public static abstract class DocumentFactory {
        public abstract Document createDocument();
    }

    public static class WordDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new WordDocument();
        }
    }

    public static class PdfDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new PdfDocument();
        }
    }

    public static class ExcelDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new ExcelDocument();
        }
    }

    // Testing
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document word = wordFactory.createDocument();
        word.open();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdf = pdfFactory.createDocument();
        pdf.open();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excel = excelFactory.createDocument();
        excel.open();
    }
}
