public class Special extends Customer {

    public Special(String n, String ad){
        super(n, ad );
        super.setDelivery(20);
        super.setTypeC("Special");
    }

    @Override
    public double custDiscount(double q1) {
        if(q1>200)
        {
            q1 = q1- 25;
        }
        return q1;
    }

}
