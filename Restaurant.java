import java.util.*;
public class Restaurant implements RewardScheme, User {
    private final String Rname;
    private final String addressR;
    private static int rID = 0;
    private final int Rid;
    private int discountR;
    private int rewardR;
    private final HashMap<Integer, foodItem> food;
    private int orderNo;
    private String type;
    private int rewardPointsClaimed;
    public Restaurant(String s, String a ) {
        rID++;
        this.Rid = rID;
        this.Rname = s;
        food = new HashMap<>();
        this.addressR = a;
        this.orderNo = 0;
        this.type = "Rest";
        this.rewardPointsClaimed = 0;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public int getRewardPointsClaimed() {
        return rewardPointsClaimed;
    }

    public void setRewardPointsClaimed(int rewardPointsClaimed) {
        this.rewardPointsClaimed = rewardPointsClaimed;
    }

    protected static int getrID() {
        return rID;
    }

    public void setDiscountR(int discountR) {
        this.discountR = discountR;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public String getAddressR() {
        return addressR;
    }

    public String getType() {
        return type;
    }

    public int getRewardR() {
        return rewardR;
    }

    public void setRewardR(int rewardR) {
        this.rewardR = rewardR;
    }

    public String getRname() {
        return Rname;
    }

    public HashMap getFood() {
        return food;
    }

    public int getRid() {
        return Rid;
    }

    public void disc(int discountR)
    {
        this.discountR = 0;
    }

    public int getDiscountR() {
        return discountR;
    }
    public static void chooseRest(HashMap<Integer, Restaurant> rmap) {
        int i=1;
        Iterator it1 = rmap.entrySet().iterator();
        System.out.println("Choose Restaurant ");
        while (it1.hasNext()) {
            Map.Entry r = (Map.Entry) it1.next();
            Restaurant p = (Restaurant) r.getValue();
            if(p.getType().equals("Rest"))
            System.out.println("    "+i + ") " + p.getRname());
            else
                System.out.println("    "+i + ") " + p.getRname() + " ("+ p.getType()+")");
            i++;
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("Welcome " + this.getRname());
        System.out.println("    1) Add Item ");
        System.out.println("    2) Edit Item ");
        System.out.println("    3) Print Rewards ");
        System.out.println("    4) Discount on Bill Value ");
        System.out.println("    5) Exit ");
    }

    @Override
    public void DisplayListDetails()
    {
        if(this.getType().equals("Rest"))
        System.out.println(this.getRname() + " " + this.getAddressR() + " "+ "  " + this.getOrderNo());
        else
            System.out.println(this.getRname() + "(" + this.getType() + ") "+ this.getAddressR() + "  " + this.getOrderNo());

    }

    @Override
    public double overallDiscount( double q1 ) {
        return q1;
    }

    @Override
    public void reward(Customer c) {
        double chk = c.getPersonal().returnbill(c, this);
        if ( chk >= 100) {
            c.setRewardC(c.getRewardC() + 5*(int)(chk/100));
            this.setRewardR(this.getRewardR() + 5*(int)(chk/100));
            c.setTotalReward(c.getTotalReward()+5*(int)(chk/100));
        }
    }

    @Override
    public void printRewardPoints()
    {
        System.out.print("Reward points : ");
        System.out.println(this.getRewardR());
    }
}
