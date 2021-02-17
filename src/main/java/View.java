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
            intValidation(menu,1,2);
            
            switch (menu)
            {
                case "1": //login
                    login(in);
                    break;
    
                case "2": //exit
                    running = false;
                    break;
    
                default:
                    System.out.println("Invalid input.");
                    return;
            }
        }
    }
    
    private void showMenu() {
        System.out.println(
            "1) Log in" +
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
            "\n5) Delete Accounts" +
            "\n6) Logout"
        );
        String menu = in.nextLine();
        intValidation(menu,1,5);
    }
    
    public void customerSelection(Account account, Scanner in){
        System.out.println(
            "1) Deposit" +
            "\n2) Withdraw" +
            "\n3) Transfer" +
            "\n4) Show Transactions" +
            "\n5) Logout"
        );
        String menu = in.nextLine();
        intValidation(menu,1,4);
    }
    
    //TODO: employees should be able to create accounts
    public int intValidation(String question, int min, int max)
    {
        Scanner in = new Scanner(System.in);
        int input = 0;
        System.out.println(question);
        boolean exitLoop = false;
        do
        {
            input = in.nextInt();
            if (input < min || input > max)
            {
                System.out.println("invalid input please specify a number between " + min + " and " + max);
                exitLoop = false;
            } else
            {
                exitLoop = true;
            
            }
        } while (!exitLoop);
        return input;
    }
    
    public boolean loginVerifier(String username, String password){
        for (int i = 0; i < bank.getAccounts().size(); i++)
        {
            if(username == bank.getAccounts().get(i).getUser().getUsername() &&
               password == bank.getAccounts().get(i).getUser().getPassword())
            {
                loggedIn = bank.getAccounts().get(i);
                return true;
            }
        }
        return false;
    }
}

