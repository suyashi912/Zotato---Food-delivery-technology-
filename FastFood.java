
public class FastFood extends Restaurant implements RewardScheme {

    public FastFood(String s1, String a1)
    {
        super(s1, a1);
        super.setType("Fast Food");
    }

    @Override
    public double overallDiscount(double q1) {
        q1 = q1 - q1*this.getDiscountR()/100;
        return q1;
    }

    @Override
    public void disc(int discountR)
    {
        super.setDiscountR(discountR);
    }

    @Override
    public void reward(Customer c) {
        double chk = c.getPersonal().returnbill(c, this);
        if ( chk >= 150)
        {
            c.setRewardC(c.getRewardC() + 10 * (int)(chk/150));
            this.setRewardR(this.getRewardR() + 10 * (int)(chk/150));
            c.setTotalReward(c.getTotalReward()+10*(int)(chk/150));
        }
    }
}
