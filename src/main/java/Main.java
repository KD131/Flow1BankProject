import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Bank b1 = new Bank(1,"Ebberød Bank","Ebberød"); //commented out since it throws an error
        View view = new View(b1);
        view.menu();
    }
}
