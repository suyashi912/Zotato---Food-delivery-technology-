import java.util.HashMap;
import java.util.Scanner;

public class ParentMenu {
    Scanner in = new Scanner(System.in);

    public static void parentmenu()
    {
        System.out.println("Welcome to Zotato: ");
        System.out.println("    1) Enter as restaurant owner ");
        System.out.println("    2) Enter as Customer ");
        System.out.println("    3) Check User Details ");
        System.out.println("    4) Company Account details ");
        System.out.println("    5) Exit ");
    }

    public void hardCodeRestaurants(HashMap<Integer, Restaurant> rest) {
        Restaurant r1 = new Authentic("Shah", "Delhi");
        Restaurant r2 = new Restaurant("Ravi's", "Mumbai ");
        Restaurant r3 = new Authentic("The Chinese", "Pune");
        Restaurant r4 = new FastFood("Wang's", "Chennai ");
        Restaurant r5 = new Restaurant("Paradise", "Delhi ");
        rest.put(r1.getRid(), r1);
        rest.put(r2.getRid(), r2);
        rest.put(r3.getRid(), r3);
        rest.put(r4.getRid(), r4);
        rest.put(r5.getRid(), r5);
    }

    public void hardCodedCustomers(HashMap<Integer, Customer> cust)
    {
        Customer c1 = new Elite("Ram", "Mumbai ");
        Customer c2 = new Elite("Sam", "Pune ");
        Customer c3 = new Special("Tim", "Delhi ");
        Customer c4 = new Customer("Kim", "Allahabad");
        Customer c5 = new Customer("Jim", "Kolkata");

        cust.put(c1.getCid(), c1);
        cust.put(c2.getCid(), c2);
        cust.put(c3.getCid(), c3);
        cust.put(c4.getCid(), c4);
        cust.put(c5.getCid(), c5);
    }

    public void query1(HashMap<Integer, Restaurant> rest) {
        Restaurant.chooseRest(rest);
        int whichR = in.nextInt();
        Restaurant r1 = rest.get(whichR);
        r1.displayMenu();
        int optionR = in.nextInt();
        while (optionR != 5) {
            if (optionR == 1) {
                foodItem.addItem(r1.getFood(), r1);

            }

            if (optionR == 2)
            {
                foodItem.editItem(r1);
            }
            if (optionR == 3)
            {
                rest.get(whichR).printRewardPoints();
            }
            if (optionR == 4)
            {
                System.out.print("Offer on bill value - ");
                int d = in.nextInt();
                r1.disc(d);
            }
            if (optionR == 5)
            {
                break;
            }
            r1.displayMenu();
            optionR = in.nextInt();
        }
    }

    public void query2 ( HashMap<Integer, Restaurant> rest, HashMap<Integer, Customer> cust)
    {
        Customer.ChooseCust(cust);
        int whichC = in.nextInt();
        Customer C = cust.get(whichC);
        C.displayMenu();
        int optionC = in.nextInt();
       Restaurant w = null; 
        while(optionC!=5)
    {
        if(optionC == 1)
        {
            if (C.isCheckedOut() == true) {
                Restaurant.chooseRest(rest);
                int a = in.nextInt();
                w = rest.get(a);
                C.getPersonal().addCart(w.getFood(), C);
            }
            while(C.isCheckedOut()!= true){
             {
                System.out.println(" 1) Add more items to Restaurant : " + w.getRname());
                System.out.println(" 2) CheckOut Cart ");

                int b = in.nextInt();
                if(b==1) {
                    C.getPersonal().addCart(w.getFood(), C);
                }
                if(b==2)
                {
                    C.getPersonal().CheckOutCart(C, w);
                }
            }
        }

        }
        if(optionC ==2 )
        {
            System.out.println("No items in cart to be checked out. ");
        }
        if(optionC == 3)
        {
           C.printRewardPoints();
        }
        if(optionC == 4)
        {
            C.getPersonal().displayPrevOrders(C);
        }
        if(optionC ==5)
        {
            break;
        }
        C.displayMenu();
        optionC = in.nextInt();
}
    }

    public void query3(HashMap<Integer, Restaurant> rest, HashMap<Integer, Customer> cust)
    {
        System.out.println("1) Customer List ");
        System.out.println("2) Restaurant List ");
        int whichL = in.nextInt();
        if(whichL == 1)
        {
            Customer.ChooseCust(cust);
            int i = in.nextInt();
            cust.get(i).DisplayListDetails();
        }
        if(whichL == 2)
        {
            Restaurant.chooseRest(rest);
            int i = in.nextInt();
            rest.get(i).DisplayListDetails();
        }
    }

    public void query4()
    {
        System.out.println("Total Company balance : INR "+Cart.getCompanyTransaction()+ "/-");
        System.out.println("Total Delivery Charges Collected - INR " +Cart.getDeliveryTotal() + "/-");
    }

}
