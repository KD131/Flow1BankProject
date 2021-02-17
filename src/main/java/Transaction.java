import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Transaction
{
    private int bank_id; //necessary to facilitate transactions, so we'll have to pass it in somehow (this is the same
                         //as the user's "bank_id"
    private int amount;
    private String date; //this is automatically set by the database when an entry is made, so this might not be necessary
    
    
    public Transaction(int bank_id, int amount, String date)
    {
        this.bank_id = bank_id;
        this.amount = amount;
        this.date = date;
    }

    public int getBank_id() {
        return bank_id;
    }

    public int getAmount(){return amount;}
    
    public String getDate(){return date; }

   
    public void setAmount(int amount){
        this.amount = amount;
    }


}
