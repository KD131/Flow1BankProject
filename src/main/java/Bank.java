import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bank
{
    private String name;
    private String city;
    private List<Account> accounts = new ArrayList<>();
    private Database database = new Database();
    
    public Bank(String name, String city)
    {
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
    
    public void showUsersBalance(int bank_id){
        for (int i = 0; i < accounts.size(); i++) {
            if(bank_id == accounts.get(i).getUser().getBank_id())
            {
                System.out.println(
                    "User " + (i + 1) + ": \"" + accounts.get(i).getUser().getName() +
                    "\" -- Bank ID: " + accounts.get(i).getUser().getBank_id() +
                    " -- Balance: " + accounts.get(i).getUser().getBalance()
                );
            }
        }
    }
    
    public void createAccount(int bank_id, String name, String city, String username, String password){
        database.addToDatabase(bank_id, name, city, username, password);
        database.populateAccountsList(accounts);
    }
    
    public void deposit(Account account, int amount){
        if(account.deposit(amount))
        {
            database.updateBalance(accounts,account.getBank_id(),amount);
            database.populateAccountsList(accounts);
        }
    }
    
    public void withdraw(Account account, int amount){
        if(account.withdraw(amount))
        {
            database.updateBalance(accounts,account.getBank_id(),-amount);
            database.populateAccountsList(accounts);
        }
    }
    
    public void showUserTransactions(Account account)
    {
        database.viewUserTransaction(accounts, account.getBank_id());
    }
    public void showUserTransactions(int bank_id)
    {
        database.viewUserTransaction(accounts, bank_id);
    }
    
    public void showAllTransactions()
    {
        database.viewAllTransactions(accounts);
    }
    
    public void transfer(int bank_id1, int bank_id2, int amount) //lotsa brackets
    {
        for (int i = 0; i < accounts.size(); i++)
        {
            if(accounts.get(i).getBank_id() == bank_id1){
                if(amount > accounts.get(i).getBalance())
                {
                    // TODO: throw exception instead
                    System.out.println("Amount to withdraw cannot be greater than balance.");
                }else{
                    for (int j = 0; j < accounts.size(); j++){
                        if(accounts.get(j).getBank_id() == bank_id2)
                        {
                            withdraw(accounts.get(i), amount);
                            deposit(accounts.get(j), amount);
                        }
                    }
                }
            }
        }
    }
}
