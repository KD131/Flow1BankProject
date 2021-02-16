public class User
{
    private boolean employee;
    private int bank_id;
    private String name;
    private String city;
    private int balance;
    private String username;
    private String password;
    
    User(boolean employee, int bank_id, String name, String city, int balance, String username, String password){
        this.employee = employee;
        this.bank_id = bank_id;
        this.name = name;
        this.city = city;
        this.balance = balance;
        this.username = username;
        this.password = password;
    }
    
    public boolean isEmployee()
    {
        return employee;
    }
    
    public int getBank_id()
    {
        return bank_id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public int getBalance()
    {
        return balance;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
}
