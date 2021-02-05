import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class foodItem {
    static Scanner in = new Scanner(System.in);
    private static int id = 0;
    private String Fname;
    private int price;
    private int quantity;
    private int discount;
    private final int ID;
    private String category;
    private String restaurant1;
    foodItem(String Fname, int price, int quantity, int Offer, String category )
    {
        this.Fname = Fname;
        this.price = price;
        this.quantity = quantity;
        this.discount = Offer;
        this.category = category;
        id++;
        this.ID = id;
    }

    public String getRestaurant1() {
        return restaurant1;
    }

    public void setRestaurant1(String restaurant1) {
        this.restaurant1 = restaurant1;
    }

    public int getPrice() {
        return price;
    }


    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public int getDiscount() {
        return discount;
    }

    public String getFname() {
        return Fname;
    }


    public void setFname(String fname) {
        Fname = fname;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public int getID() {
        return ID;
    }

    public static void addItem(HashMap<Integer, foodItem> fmap, Restaurant w)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter food item details");
        System.out.println("Food Name: ");
        String n = in.nextLine();
        System.out.println("item price : ");
        int p = in.nextInt();
        System.out.println("item quantity : ");
        int q = in.nextInt();
        System.out.println("item category : ");
        String c = in.next();
        System.out.println("Offer: ");
        int o = in.nextInt();
        foodItem f = new foodItem(n, p, q, o, c);
        f.setRestaurant1(w.getRname());
        fmap.put(f.getID(), f);
        System.out.println(f.getID()+" "+f.getFname()+" "+f.getPrice()+" "+f.getQuantity()+" "+f.getDiscount()+"% off "+ f.getCategory());
    }


    public static void editItem(Restaurant q)
    {
        System.out.println("Choose item by code ");
        Iterator it1 = q.getFood().entrySet().iterator();
        while (it1.hasNext())
        {
            Map.Entry r = (Map.Entry)it1.next();
            foodItem p = (foodItem) r.getValue();
            System.out.println(p.getID()+" "+p.getRestaurant1()+" - "+p.getFname()+" "+p.getPrice()+" "+p.getQuantity()+" "+p.getDiscount()+"% off "+p.getCategory());
        }
        int id = in.nextInt();
        foodItem f1 = (foodItem)q.getFood().get(id);
        System.out.println("Choose an attribute to edit:");
        System.out.println("    1) Name");
        System.out.println("    2) Price");
        System.out.println("    3) Quantity" );
        System.out.println("    4) Category");
        System.out.println("    5) Offer");
        int input = in.nextInt();
        if(input == 1)
        {
            System.out.println("Enter the new name - ");
            f1.setFname(in.next());
        }
        if(input == 2)
        {
            System.out.print("Enter the new price - ");
            f1.setPrice(in.nextInt());
        }
        if(input ==3 )
        {
            System.out.print("Enter the new quantity - ");
            f1.setQuantity(in.nextInt());
        }
        if(input == 4)
        {
            System.out.print("Enter the new Category - ");
            f1.setCategory(in.next());
        }
        if(input == 5)
        {
            System.out.print("Enter the new Offer - ");
            f1.setDiscount(in.nextInt());
        }
        System.out.println(f1.getID()+" "+f1.getRestaurant1()+" "+f1.getFname()+" "+f1.getPrice()+" "+f1.getQuantity()+" "+f1.getDiscount()+"% off "+f1.getCategory());
    }
}
