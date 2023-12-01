public class Transaction 
{

    private int bookId;
    private int borrowerId;
    private int borrowDate;
    private int returnDate;

    Transaction(int bookId ,int borrowerId ,int borrowDate ,int returnDate)
    {
        this.bookId=bookId;
        this.borrowerId=borrowerId;
        this.borrowDate=borrowDate;
        this.returnDate=returnDate;
    }

    public int getBookId() 
    {
        return bookId;
    }

    public int getBorrowerId() 
    {
        return borrowerId;
    }

    public int getBorrowDate() 
    {
        return borrowDate;
    }

    public int getReturnDate() 
    {
        return returnDate;
    }
    public int getFines()
    {
        return (returnDate-borrowDate)*10;
    }
}
