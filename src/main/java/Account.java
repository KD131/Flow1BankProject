import java.util.ArrayList;
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
    
    public void deposit(int amount)
    {
    
    }
}
