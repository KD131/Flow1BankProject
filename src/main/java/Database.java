import java.sql.*;
import java.util.ArrayList;

public class Database
{
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;
    
    Database()
    {
        try
        {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ellerød_bank",
                    "root", "1234");
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
            String sql = "INSERT INTO highscorers (employee, `name`, balance, username, `password`) VALUES (?, ?, ?, ?, ?)";
        
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
            String sql = "INSERT INTO highscorers (bank_id, `name`, city, balance, username, `password`) VALUES (?, ?, ?, ?, ?, ?)";
            
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
            String sql = "INSERT INTO highscorers (bank_id, `name`, city, balance) VALUES (?, ?, ?, ?)";
            
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
            String sql = "INSERT INTO highscorers (bank_id, `name`, balance) VALUES (?, ?, ?)";
    
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
public void populateUserList(ArrayList<User> arrayList){
    try {
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM acounts ORDER BY `name` DESC;";
        
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
            arrayList.add(user);
        }
        rs.close();
        pstmt.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
