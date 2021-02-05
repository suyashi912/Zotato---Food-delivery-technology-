public final class Authentic extends Restaurant implements RewardScheme {

    public Authentic(String s1 , String a1) {
        super(s1, a1);
        super.setType("Authentic");
    }

    @Override
    public void disc(int discountR)
    {
        super.setDiscountR(discountR);
    }

    @Override
    public double overallDiscount(double q1) {
        q1 = q1 - q1*this.getDiscountR()/100;
        if(q1>100)
        {
            q1 = q1- 50;
        }
        return q1;
    }

    @Override
    public void reward(Customer c) {
        double chk = c.getPersonal().returnbill(c, this);
        if ( chk >= 200) {
            c.setRewardC(c.getRewardC() + 25* (int)(chk/200));
            this.setRewardR(this.getRewardR() + 25 * (int)(chk/200));
            c.setTotalReward(c.getTotalReward()+25*(int)(chk/200));
        }
    }
}
