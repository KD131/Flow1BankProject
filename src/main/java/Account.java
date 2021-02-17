import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Account
{
    private List<Transaction> transactions;
    private User user;
    
    public Account(List<Transaction> transactions, User user)
    {
        this.transactions = transactions;
        this.user = user;
    }
    
    public List<Transaction> getTransactions()
    {
        return transactions;
    }

    public User getUser() {
        return user;
    }
    
    public int getBank_id(){
        return user.getBank_id();
    }
    
    public int getBalance()
    {
        /*
        int sum = 0;
        for (Transaction t : transactions)
        {
            sum += t.getAmount();
        }
        return sum;
        */
        return user.getBalance();
    }
    
    public boolean deposit(int amount)
    {
        if(amount <= 0)
        {
            // TODO: throw exception instead
            System.out.println("Amount cannot be negative or 0.");
            return false;
        }
        transactions.add(new Transaction(this.user.getBank_id(), amount, LocalDateTime.now().toString()));
        return true;
    }
    
    public boolean withdraw(int amount)
    {
        if(amount > getBalance())
        {
            // TODO: throw exception instead
            System.out.println("Amount to withdraw cannot be greater than balance.");
            return false;
        }
        transactions.add(new Transaction(this.user.getBank_id(), -amount, LocalDateTime.now().toString()));
        return true;
    }
}
