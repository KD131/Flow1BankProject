import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account
{
    
    
    private List<Transaction> transactions;
    private User user;
    
    public Account(List<Transaction> transactions, User user)
    {
        this.transactions = new ArrayList<>();
        this.user = user;
    }
    
    public List<Transaction> getTransactions()
    {
        return transactions;
    }
    
    public void deposit(double amount)
    {
        // TODO: perhaps return balance
        if(amount < 0)
        {
            // TODO: throw exception instead
            System.out.println("Amount cannot be negative");
            return;
        }
        transactions.add(new Transaction(amount, new Date()));
    }
    
    public void withdraw(double amount)
    {
        // TODO: check if greater than balance
        transactions.add(new Transaction(-amount, new Date()));
    }
}
