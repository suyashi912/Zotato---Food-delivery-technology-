import java.util.*;
public class Cart {
    private final HashMap<foodItem, Integer> cart;
    private static double companyTransaction = 0;
    private static int deliveryTotal = 0;
    Scanner in = new Scanner(System.in);

    public Cart() {
        cart = new HashMap<>();
    }

    public static double getCompanyTransaction() {
        return companyTransaction;
    }

    public static int getDeliveryTotal() {
        return deliveryTotal;
    }


    public static void setCompanyTransaction(double companyTransaction) {
        Cart.companyTransaction = companyTransaction;
    }

    public static void setDeliveryTotal(int deliveryTotal) {
        Cart.deliveryTotal = deliveryTotal;
    }

    public double returnbill(Customer c, Restaurant b) {
        return this.bill(c, b);
    }

    public void addCart(HashMap<Integer, foodItem> fmap, Customer c) {
        System.out.println("Choose item by code ");
        Iterator it1 = fmap.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry r = (Map.Entry) it1.next();
            foodItem p = (foodItem) r.getValue();
            System.out.println(p.getID() + " " + p.getRestaurant1() + " - " + p.getFname() + " " + p.getPrice() + " " + p.getQuantity() + " " + p.getDiscount() + "% off " + p.getCategory());
        }
        int item = in.nextInt();
        System.out.println("Enter item quantity - ");
        int q = in.nextInt();
        if(q<=fmap.get(item).getQuantity())
        {
            System.out.println("Items added to cart");
            foodItem f = fmap.get(item);
            c.getPersonal().cart.put(f, q);
            c.setCheckedOut(false);
            fmap.get(item).setQuantity(fmap.get(item).getQuantity()-q);
        }
        else {
            System.out.println("More number of items than available. Please try again ! ");
        }
    }

    public void CheckOutCart(Customer c, Restaurant b) {
        Iterator it1 = cart.entrySet().iterator();
        int count = 0;
        System.out.println("Items in cart - ");
        while (it1.hasNext()) {
            Map.Entry r = (Map.Entry) it1.next();
            foodItem p = (foodItem) r.getKey();
            System.out.println(p.getID() + " " + p.getRestaurant1() + " - " + p.getFname() + " - " + p.getPrice() + " - " + (int) r.getValue() + " - " + p.getDiscount() + "% off ");
            count = count + (int) r.getValue();
        }
        double totalbill = this.bill(c, b) + c.getDelivery();
        while (totalbill > (c.getWallet() + c.getRewardC()) && count >0) {
            this.deleteItems(totalbill, c, b);
            totalbill = this.bill(c, b) + c.getDelivery();
            count = this.count(c);
        }
        if(totalbill - c.getDelivery()>0) {
            System.out.println("Delivery Charge - " + c.getDelivery() + "/-");
            System.out.println(" Total Order Value - INR " + (double) Math.round(totalbill*100)/100 + "/-");
            System.out.println(" 1) Proceed to CheckOut ");
        }
        else
        {
            System.out.println("No items in cart to check out ");
            System.out.println("    1) Go to menu ");
            c.setCheckedOut(true);
        }

        int i = in.nextInt();
        double leftbill = totalbill;
        if (c.getRewardC() <= totalbill) {
            leftbill = totalbill - c.getRewardC();
            b.setRewardPointsClaimed(b.getRewardPointsClaimed() + c.getRewardC());
            c.setRewardC(0);
        }
        else
            {
                c.setRewardC(c.getRewardC() - (int) totalbill);
                b.setRewardPointsClaimed(b.getRewardPointsClaimed() + (int)totalbill);
                leftbill = 0 ;
        }
            if(totalbill - c.getDelivery()>0 && leftbill <= c.getWallet()) {

                c.setWallet(c.getWallet() - leftbill);
                b.reward(c);
                c.setCheckedOut(true);
                b.setOrderNo(b.getOrderNo() + 1);
                System.out.println(count + " items successfully bought for INR " + (double) Math.round(totalbill*100)/100 + "/-");
                setCompanyTransaction(getCompanyTransaction() + this.bill(c, b) * 0.01);
                setDeliveryTotal(getDeliveryTotal() + c.getDelivery());
                this.prevOrderList(c, c.getDelivery(), this.bill(c, b));
            }

    }

    public double bill(Customer c, Restaurant b) {
        Iterator it1 = c.getPersonal().cart.entrySet().iterator();
        double V1 = 0;
        while (it1.hasNext()) {
            Map.Entry r = (Map.Entry) it1.next();
            foodItem p = (foodItem) r.getKey();
            double eachItem = (int) r.getValue() * p.getPrice();
            V1 = V1 + eachItem;
            eachItem = eachItem * p.getDiscount() / 100;
            V1 = V1 - eachItem;
        }
        double V2 = b.overallDiscount(V1);
        double V3;
        V3 = c.custDiscount(V2);
        return V3;
    }

    public void deleteItems(double l, Customer c, Restaurant b) {
        if (l > c.getWallet() + c.getRewardC()) {
            System.out.println("Delivery Charge - " + c.getDelivery() + "/-");
            System.out.println(" Total Order Value - " + l + "/-");
            System.out.println(" Insufficient balance! Choose an item (id) to be deleted - ");
            Iterator it1 = c.getPersonal().cart.entrySet().iterator();
            int count = 0;
            while (it1.hasNext()) {
                Map.Entry r = (Map.Entry) it1.next();
                foodItem p = (foodItem) r.getKey();
                count = count + (int)r.getValue();
                System.out.println(p.getID() + " " + p.getRestaurant1() + " - " + p.getFname() + " - " + p.getPrice() + " - " +(int)r.getValue()+" - "+p.getDiscount() + "% off ");
            }
            int del = in.nextInt();
            Iterator it = c.getPersonal().cart.entrySet().iterator();
            foodItem f = null;
            while (it.hasNext()) {
                Map.Entry r = (Map.Entry) it.next();
                foodItem p = (foodItem) r.getKey();
                if (p.getID() == del) {
                    f = p;
                    break;
                }
            }
            foodItem u = (foodItem)b.getFood().get(del);
            u.setQuantity(u.getQuantity()+f.getQuantity());
            c.getPersonal().cart.remove(f);
            System.out.println("Chosen item has been deleted from cart. ");
        }
    }

    public void prevOrderList(Customer c, int d, double f) {
        int len = c.getPrevOrders().size();
        if (len >= 10) {
            c.getPrevOrders().remove(0);
            c.getDeliver().remove(0);
            c.getCartBill().remove(0);
        }
        HashMap<foodItem, Integer> newmap = (HashMap<foodItem, Integer>) c.getPersonal().cart.clone();
        c.getPrevOrders().add(newmap);
        c.getCartBill().add(f);
        c.getDeliver().add(d);
        c.getPersonal().cart.clear();
    }

    public void displayPrevOrders(Customer c)
    {
        int order=1;
        for (int i = 0; i < c.getPrevOrders().size(); i++) {
            Iterator it1;
            it1 = c.getPrevOrders().get(i).entrySet().iterator();
            foodItem f = null;
            System.out.print("Order " + order++ + ") ");
            while (it1.hasNext()) {
                Map.Entry r = (Map.Entry) it1.next();
                foodItem p = (foodItem) r.getKey();
                f = p;
                System.out.println("Bought Item " + p.getFname() + ", quantity: " + r.getValue() );
            }
            System.out.println( " from Restaurant " + f.getRestaurant1() + " for RS " + c.getCartBill().get(i) + " and Delivery Charge: " + c.getDeliver().get(i));
        }
    }
    public int count(Customer c)
    {
        Iterator it1 = c.getPersonal().cart.entrySet().iterator();
        int count = 0;
        while (it1.hasNext()) {
            Map.Entry r = (Map.Entry) it1.next();
            count = count + (int)r.getValue();
        }
        return count;
    }
}


