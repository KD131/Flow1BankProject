import java.util.Scanner;

public class View {
    
    boolean running = true;
    
    
    public void menu(){
        
        while(running) {
            
            Scanner in = new Scanner(System.in);
            ShowMenu();
            String menu = in.nextLine();
            
            
            switch(menu){
                
                
                case "1":
                
                
                case"2":
                
                case"3":
                return;
                
                
            }
            
            
            
            
        }
        
        
        
    }
    
    private void ShowMenu() {
        
        System.out.println("1) Log in as staff");
        System.out.println("2) Log in as Customer");
        
    }
    
    
}
