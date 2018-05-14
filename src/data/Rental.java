package data;


/**
 * Use to save rental of a bookID.
 */
public class Rental {
    private String library;
    private String bookName;
    private String renterName;
    private String fromDate;

    public Rental(String library, String bookName, String renterName, String fromDate) {
        this.library = library;
        this.bookName = bookName;
        this.renterName = renterName;
        this.fromDate = fromDate;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}
