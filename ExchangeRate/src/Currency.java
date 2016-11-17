import sun.plugin2.message.GetNameSpaceMessage;

/**
 * Created by bistu on 2016/11/17.
 */
public class Currency {
    private String name;
    private String shortname;

    public Currency(String a,String b){
        this.name =a ;
        this.shortname = b;
    }

    public String getName(){
        return this.name;
    }

    public String getShortname(){
        return this.shortname;
    }
}
