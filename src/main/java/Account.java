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
<<<<<<< HEAD

    public User getUser() {
        return user;
    }

    public void deposit(int bank_id, double amount)
=======
    
    public double getBalance()
    {
        double sum = 0;
        for (Transaction t : transactions)
        {
            sum += t.getAmount();
        }
        return sum;
    }
    
    public double deposit(double amount)
>>>>>>> ef5545e538406f548bf9e1ca62ae0589f506572c
    {
        if(amount < 0)
        {
            // TODO: throw exception instead
            System.out.println("Amount cannot be negative");
            return -1;
        }
<<<<<<< HEAD
        transactions.add(new Transaction(bank_id, amount, new Date()));
    }
    
    public void withdraw(int bank_id, double amount)
    {
        // TODO: check if greater than balance
        transactions.add(new Transaction(bank_id, amount, new Date()));
=======
        transactions.add(new Transaction(amount, new Date()));
        return getBalance();
    }
    
    public double withdraw(double amount)
    {
        // TODO: check if greater than balance
        transactions.add(new Transaction(-amount, new Date()));
        return getBalance();
>>>>>>> ef5545e538406f548bf9e1ca62ae0589f506572c
    }
}
