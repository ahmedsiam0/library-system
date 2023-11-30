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

    public int GetTransactionsBookID() {
        return bookID;
    }

    public int GetTransactionsBorrowerID() {
        return borrowerID;
    }

    public int GetTransactionsBorrowDate() {
        return borrowDate;
    }

    public int GetTransactionsReturnDate() {
        return returnDate;
    }
    public int Fines(){
        return (returnDate-borrowDate)*10;
    }
}
