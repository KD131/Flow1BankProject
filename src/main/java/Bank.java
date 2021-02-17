import java.util.ArrayList;
import java.util.List;

public class Bank
{
    private int id;
    private String name;
    private String city;
    private List<Account> accounts = new ArrayList<>();
    private Database database = new Database();
    
    public Bank(int id, String name, String city)
    {
        this.id = id;
        this.name = name;
        this.city = city;
        database.populateAccountsList(accounts);
    }
    
    public List<Account> getAccounts()
    {
        return accounts;
    }
    
    public Database getDatabase(){
        return database;
    }
    
    public void createAccount(int bank_id, String name, String city, int balance, String username, String password){
        database.addToDatabase(bank_id, name, city, balance, username, password);
        database.populateAccountsList(accounts);
    }
    
    public void deposit(Account account, int amount){
        if(account.deposit(amount)){
            database.updateBalance(accounts,account.getBank_id(),amount);
            database.populateAccountsList(accounts);
        }
    }
    
    public void withdraw(Account account, int amount){
        if(account.withdraw(amount))
        {
            database.updateBalance(accounts,account.getBank_id(),amount);
            database.populateAccountsList(accounts);
        }
    }
}
