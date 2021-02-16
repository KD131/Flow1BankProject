import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database
{
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;
    
    Database()
    {
        try
        {
            //Kasper's locahost username/pass: root / 1234
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ellerød_bank",
                    "root", "h4bI1tAt10n");
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
//----------------------------------------------------------------------------------------------------------------------
    //ADD TO DATABASE (overloaded)
        //ADD ACCOUNT WITH ALL FIELDS
    public void addToDatabase(boolean employee, String name, int balance, String username, String password){
        try {
            PreparedStatement pstmt = null;
            String sql = "INSERT INTO accounts (employee, `name`, balance, username, `password`) VALUES (?, ?, ?, ?, ?)";
        
            pstmt = connection.prepareStatement(sql);
        
            pstmt.setBoolean(1, employee);
            pstmt.setString(2, name);
            pstmt.setInt(3, balance);
            pstmt.setString(4, username);
            pstmt.setString(5, password);
        
            pstmt.executeUpdate();
        
            pstmt.close();
        
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
        //ADD ACCOUNT THAT ISN'T EMPLOYEE
    public void addToDatabase(int bank_id, String name, String city, int balance, String username, String password){
        try {
            PreparedStatement pstmt = null;
            String sql = "INSERT INTO accounts (bank_id, `name`, city, balance, username, `password`) VALUES (?, ?, ?, ?, ?, ?)";
            
            pstmt = connection.prepareStatement(sql);
    
            pstmt.setInt(1, bank_id);
            pstmt.setString(2, name);
            pstmt.setString(3, city);
            pstmt.setInt(4, balance);
            pstmt.setString(5, username);
            pstmt.setString(6, password);
            
            pstmt.executeUpdate();
            
            pstmt.close();
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
        //ADD ACCOUNT WITH ONLY BANK_ID, NAME, CITY, AND BALANCE
    public void addToDatabase(int bank_id, String name, String city, int balance)
    {
        try
        {
            PreparedStatement pstmt = null;
            String sql = "INSERT INTO accounts (bank_id, `name`, city, balance) VALUES (?, ?, ?, ?)";
            
            pstmt = connection.prepareStatement(sql);
    
            pstmt.setInt(1, bank_id);
            pstmt.setString(2, name);
            pstmt.setString(3, city);
            pstmt.setInt(4, balance);
            
            pstmt.executeUpdate();
            
            pstmt.close();
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
        //ADD ACCOUNT WITH ONLY BANK_ID, NAME, AND BALANCE
    public void addToDatabase(int bank_id, String name, int balance)
    {
        try
        {
            PreparedStatement pstmt = null;
            String sql = "INSERT INTO accounts (bank_id, `name`, balance) VALUES (?, ?, ?)";
    
            pstmt = connection.prepareStatement(sql);
    
            pstmt.setInt(1,bank_id);
            pstmt.setString(2, name);
            pstmt.setInt(3, balance);
    
            pstmt.executeUpdate();
    
            pstmt.close();
    
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    //POPULATE USER ARRAYLIST
    public void populateAccountsList(ArrayList<Account> arrayList){
        try {
            List<User> users = new ArrayList<>();
            List<Transaction> transactionList = new ArrayList<>();

            PreparedStatement pstmt = null;

            //USERS
            String sql = "SELECT * FROM accounts ORDER BY `created_at` ASC;";

            pstmt = connection.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User(
                    rs.getBoolean("employee"),
                    rs.getInt("bank_id"),
                    rs.getString("name"),
                    rs.getString("city"),
                    rs.getInt("balance"),
                    rs.getString("username"),
                    rs.getString("password")
                );

                users.add(user);
            }

            //TRANSACTIONS
            sql = "SELECT * FROM transactions ORDER BY `date` ASC;";

            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getInt("bank_id"),
                        rs.getInt("amount"),
                        rs.getDate("date")
                );

                transactionList.add(transaction);
            }

            rs.close();
            pstmt.close();



            for (int i = 0; i < users.size(); i++) {
                List<Transaction> userSpecificTransactionList = new ArrayList<>();
                for (int j = 0; j < transactionList.size(); j++) {

                    //take specific instances where bank_ids are equal and add them to a new list
                    if(transactionList.get(j).getBank_id() == users.get(i).getBank_id()) {
                        userSpecificTransactionList.add(transactionList.get(j));
                    }


                }
                Account account = new Account(userSpecificTransactionList, users.get(i));
                arrayList.add(account);
            }

            }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    //VIEW LIST (takes in accounts arraylist and sometimes other specifying information)
    public void viewUsersAll(ArrayList<Account> accounts){
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(
                "User "+(i+1)+": \""+accounts.get(i).getUser().getName()+
                "\" -- Bank ID: "+accounts.get(i).getUser().getBank_id()+
                " -- Balance: "+accounts.get(i).getUser().getBalance()
            );
        }
    }
    public void viewUsersNotEmployees(ArrayList<Account> accounts){
        for (int i = 0; i < accounts.size(); i++) {
            if(!accounts.get(i).getUser().isEmployee()) {
                System.out.println(
                    "User " + (i + 1) + ": \"" + accounts.get(i).getUser().getName() +
                    "\" -- Bank ID: " + accounts.get(i).getUser().getBank_id() +
                    " -- Balance: " + accounts.get(i).getUser().getBalance()
                );
            }
        }
    }
    public void viewAllTransactions(ArrayList<Account> accounts){
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 0; j < accounts.get(i).getTransactions().size(); j++) {
                System.out.println(
                    "User: \""+accounts.get(i).getUser().getName()+
                    "\" -- Account num: "+accounts.get(i).getUser().getBank_id()+
                    " -- Balance: "+accounts.get(i).getUser().getBalance()+
                    " -- Transactions num "+j+": "+accounts.get(i).getTransactions().get(j).getAmount()
                );
            }
        }
    }
    public void viewUserTransaction(ArrayList<Account> accounts, int bank_id){ //requires specific bank_id
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUser().getBank_id() == bank_id) {
                System.out.println(
                    "User: \"" + accounts.get(i).getUser().getName() +
                    "\" -- Account ID: " + accounts.get(i).getUser().getBank_id() +
                    " -- Balance: " + accounts.get(i).getUser().getBalance()
                );
                for (int j = 0; j < accounts.get(i).getTransactions().size(); j++) {
                    System.out.println(
                        "Transaction " + (j+1) + ": " + accounts.get(i).getTransactions().get(j).getAmount()
                    );
                }
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
}
