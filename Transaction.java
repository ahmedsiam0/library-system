public class Transactions {

    private int bookID;
    private int borrowerID;
    private int borrowDate;
    private int returnDate;

    Transactions(int bookID ,int borrowerID ,int borrowDate ,int returnDate)
    {
        this.bookID=bookID;
        this.borrowerID=borrowerID;
        this.borrowDate=borrowDate;
        this.returnDate=returnDate;
    }

    public int getTransactionsBookID() {
        return bookID;
    }

    public int getTransactionsBorrowerID() {
        return borrowerID;
    }

    public int getTransactionsBorrowDate() {
        return borrowDate;
    }

    public int getTransactionsReturnDate() {
        return returnDate;
    }
    public int Fines(){
        return (returnDate-borrowDate)*10;
    }
}
