import java.util.Date;

public class Transaction
{
    private double amount;
    private Date date;
    
    
    public Transaction(double amount, Date date)
    {
        this.amount = amount;
        this.date = date;
    }
    
    public double getAmount(){return amount;}
    
    public Date getDate(){return date; }
    
   
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    public void setDate(Date date){
        this.date = date;
    }


}
