public class Elite extends Customer {

    public Elite(String n, String ad){
        super(n, ad);
        super.setDelivery(0);
        super.setTypeC("Elite");
    }

    @Override
    public double custDiscount(double q1) {
        if(q1>200)
        {
            q1 = q1- 50;
        }
        return q1;
    }
}
