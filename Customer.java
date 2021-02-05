import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Customer implements User {
    private final String Cname;
    private final int Cid;
    private final Cart personal;
    private final String address;
    private static int cID = 0;
    private int rewardC;
    private int delivery;
    private double wallet ;
    private String typeC;
    private  int totalReward;
    private final ArrayList<HashMap<foodItem, Integer>> prevOrders;
    private final ArrayList<Double> cartBill;
    private final ArrayList<Integer> deliver;
    private boolean checkedOut;

public Customer(String c, String a )
{
    this.Cname = c;
    cID++;
    this.Cid = cID;
    this.delivery = 40;
    this.rewardC = 0;
    personal = new Cart();
    this.wallet = 1000;
    this.address = a;
    this.typeC = "Rest";
    this.totalReward = 0;
    cartBill = new ArrayList<>();
    deliver = new ArrayList<>();
    prevOrders = new ArrayList<>();
    this.checkedOut = true;
}

    protected void setTypeC(String typeC) {
        this.typeC = typeC;
    }

    protected static int getcID() {
        return cID;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public int getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(int totalReward) {
        this.totalReward = totalReward;
    }

    public ArrayList<HashMap<foodItem, Integer>> getPrevOrders() {
        return prevOrders;
    }

    public String getTypeC() {
        return typeC;
    }

    public String getAddress() {
        return address;
    }

    public double getWallet() {
        return wallet;
    }

    public Cart getPersonal() {
        return personal;
    }

    public int getRewardC() {
        return rewardC;
    }

    public String getCname() {
        return Cname;
    }

    public int getCid() {
        return Cid;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public void setRewardC(int rewardC) {
        this.rewardC = rewardC;
    }


    public static void ChooseCust(HashMap<Integer, Customer> rmap)
    {
        int i = 1;
        Iterator it1 = rmap.entrySet().iterator();
        while (it1.hasNext())
        {
            Map.Entry r = (Map.Entry)it1.next();
            Customer p = (Customer) r.getValue();
            if(p.getTypeC().equals("Rest"))
            System.out.println(i + ") "+ p.getCname());
            else
                System.out.println(i + ") "+ p.getCname() + "("+p.getTypeC()+")");
            i++;
        }
    }

    public double custDiscount(double q1) {
        return q1;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Double> getCartBill() {
        return cartBill;
    }

    public ArrayList<Integer> getDeliver() {
        return deliver;
    }

    @Override
    public void DisplayListDetails()
    {
        if(this.getTypeC().equals("Rest"))
            System.out.println(this.getCname() + ", " + this.getAddress() + ", "+ this.getWallet() + "/-");
        else
            System.out.println(this.getCname() +"(" + this.getTypeC() +  "), " + this.getAddress() + ", "+ this.getWallet() + "/-");
    }
    @Override
    public void displayMenu() {
        System.out.println("Welcome " + this.getCname());
        System.out.println("Customer Menu ");
        System.out.println("    1) Select Restaurant ");
        System.out.println("    2) checkout Cart ");
        System.out.println("    3) Reward won ");
        System.out.println("    4) print the recent orders  ");
        System.out.println("    5) Exit ");
    }

    @Override
    public void printRewardPoints(){
        System.out.print("Reward won by customer is - " );
        System.out.println(this.getRewardC());
    }

}
