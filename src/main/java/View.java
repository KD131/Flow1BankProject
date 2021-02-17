import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    
    private Bank bank;
    private Account loggedIn;
    boolean running = true;
    
    public View(Bank bank)
    {
        this.bank = bank;
    }
    
    public void menu(){
        
        while(running) {
            
            Scanner in = new Scanner(System.in);
            showMenu();
            
            String menu = in.nextLine();
            
            switch (menu)
            {
                case "1": //login
                    login(in);
                    break;
    
                case "2": //exit
                    System.out.println("Goodbye.");
                    running = false;
                    break;
    
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }
    
    private void showMenu() {
        System.out.println(
            "--- Welcome to Ellerød Bank ---" +
            "\n1) Log in" +
            "\n2) Shutdown"
        );
    }
    
    public void login(Scanner in){ //handles login
        System.out.println("Enter Username:");
        String username = in.nextLine();
        System.out.println("Enter Password:");
        String password = in.nextLine();
        
        if(loginVerifier(username,password)){ //returns true if there's a match
            if(loggedIn.getUser().isEmployee()){
                employeeSelection(loggedIn, in);
            }else{
                customerSelection(loggedIn, in);
            }
        } else{
            System.out.println("Incorrect Username or Password!");
            menu();
        }
    }
    
    public void employeeSelection(Account account, Scanner in){
        System.out.println(
            "1) Create Account" +
            "\n2) Facilitate Transfer" + //withdraw money from one bank_id and deposit on another
            "\n3) Show Transactions" +
            "\n4) Show Accounts" +
            "\n5) Logout"
        );
        String menu = in.nextLine();
        
        switch(menu){
            case "1": //Create account
                System.out.println("--- Creating an Account ---\n");
                try
                {
                    System.out.println("Enter Account Number (8-digits):");
                    int bank_id = in.nextInt();
                    in.nextLine();      // to remove the new-line after nextInt().
                    System.out.println("Enter Name:");
                    String name = in.nextLine();
                    System.out.println("Enter City:");
                    String city = in.nextLine();
                    System.out.println("Enter Username:");
                    String username = in.nextLine();
                    System.out.println("Enter Password:");
                    String password = in.nextLine();
    
                    bank.createAccount(bank_id,name,city,username,password);
                }
                catch(InputMismatchException ex){
                    System.out.println("Incorrect input.");
                }
                employeeSelection(account, in);
                break;
                
            case "2": //Facilitate transfer
                System.out.println("--- Transfer from Account to Account ---\n");
                try
                {
                    System.out.println("Enter Account to transfer from:");
                    int id1 = in.nextInt();
                    in.nextLine();      // to remove the new-line after nextInt().
                    bank.showUsersBalance(id1);
                    System.out.println("Enter Account to transfer to:");
                    int id2 = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter amount:");
                    int amount = in.nextInt();
                    in.nextLine();
    
                    bank.transfer(id1,id2,amount);
                }
                catch(InputMismatchException ex){
                    System.out.println("Incorrect input.");
                }
                employeeSelection(account, in);
                break;
                
            case "3": //Show all transactions
                bank.showAllTransactions();
                employeeSelection(account, in);
                break;
                
            case "4": //Show single account and its transactions
                System.out.println("Enter Account ID to show:");
                int id = in.nextInt();
                in.nextLine();
                bank.showUserTransactions(id);
                employeeSelection(account, in);
                break;
    
            case "5": //Logout
                System.out.println("Logging out...");
                break;
                
            default:
                System.out.println("Invalid input.");
                employeeSelection(account, in);
                break;
        }
    
    }
    
    public void customerSelection(Account account, Scanner in){
        System.out.println(
            "1) Deposit" +
            "\n2) Withdraw" +
            "\n3) Show Transactions" +
            "\n4) Logout"
        );
        String menu = in.nextLine();
    
        switch(menu){
            case "1": //Deposit
                try
                {
                    System.out.print("Amount to deposit: ");
                    int amount = in.nextInt();
                    bank.deposit(account,amount);
                }
                catch (InputMismatchException ex){
                    System.out.println("Invalid input.");
                }
                in.nextLine();      // to remove the new-line after nextInt().
                customerSelection(account, in);
                break;
        
            case "2": //Withdraw
                System.out.print("Amount to withdraw: ");
                try
                {
                    int amount = in.nextInt();
                    bank.withdraw(account,amount);
                }
                catch (InputMismatchException ex){
                    System.out.println("Invalid input.");
                }
                in.nextLine();      // to remove the new-line after nextInt().
                customerSelection(account, in);
                break;
        
            case "3": //Show transactions
                bank.showUserTransactions(account);
                customerSelection(account, in);
                break;
        
            case "4": //Logout
                System.out.println("Logging out...");
                break;
        
            default:
                System.out.println("Invalid input.");
                employeeSelection(account, in);
                break;
        }
    }
    
    
    public boolean loginVerifier(String username, String password){
        for (int i = 0; i < bank.getAccounts().size(); i++)
        {
            if(username.equals(bank.getAccounts().get(i).getUser().getUsername()) &&
               password.equals(bank.getAccounts().get(i).getUser().getPassword()))
            {
                loggedIn = bank.getAccounts().get(i);
                return true;
            }
        }
        return false;
    }
}

