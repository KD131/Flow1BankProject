import java.util.ArrayList;
import java.util.List;

public class Bank
{
    private int id;
    private String name;
    private String city;
    private List<Account> accounts;
    
    public Bank(int id, String name, String city)
    {
        this.id = id;
        this.name = name;
        this.city = city;
        this.accounts = new ArrayList<>();
    }
}
