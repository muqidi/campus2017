import java.util.Date;
/**
 * Created by bistu on 2016/11/17.
 */
public class Exchangerate {
    private String date;
    private double midprice;

    public Exchangerate(String b,double value){
        this.date = b;
        this.midprice = value;
    }

    public String getDate(){
        return this.date;
    }
    public double getMiddlePrice(){
        return this.midprice;
    }

}
