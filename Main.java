import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ParentMenu p = new ParentMenu();
        HashMap<Integer, Restaurant> rest = new HashMap<>();
        HashMap<Integer, Customer> cust = new HashMap<>();
        p.hardCodeRestaurants( rest );
        p.hardCodedCustomers( cust );
        p.parentmenu();
        int option = in.nextInt();
        while(option!= 5)
        {
            if(option == 1)
            {
                p.query1(rest);
            }
            else if(option == 2)
            {
                p.query2(rest, cust);
            }
            else if(option == 3)
            {
                p.query3(rest, cust);
            }

            else if(option == 4)
            {
                p.query4();
            }

            else if(option == 5 )
            {
                System.exit(1);
            }
            p.parentmenu();
            option = in.nextInt();
        }

    }

}
