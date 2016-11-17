import javax.crypto.Cipher;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bistu on 2016/11/17.
 */
 //从这里开始运行
public class ExchangeRateMain {
    //"startdate=2016-08-17&enddate=2016-11-17&money_code=USD&type=0&page=2"
    private static String URL = "http://biz.finance.sina.com.cn/forex/forex.php?" ;
    private static String sstartdate="&startdate=";
    private static String senddate="&enddate=";
    private static String smoney_code="&money_code=";

    private static String money=null;
   /* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    System.out.println(df.format(new Date()));// new Date()为获取当前系统时间*/

   private static void setTime(){
       Calendar c = Calendar.getInstance();
       senddate +=  new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
       c.add(Calendar.DAY_OF_YEAR, -30);
       sstartdate +=  new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
   }

   private static void setMoneyCode(){
       System.out.println("请选择查询的货币的代码(输入序号)：\n0.美元/USD，1.欧元/EUR，2.港币/HKD");
       Scanner sc = new Scanner(System.in);
       boolean flag = true;
       while(flag){
            int currency = sc.nextInt();
           switch (currency){
               case 0:money = "USD";flag=false;break;
               case 1:money ="EUR";flag=false;break;
               case 2:money ="HKD";flag=false;break;
               default:
                   System.out.println("输入有误重新输入：");
                   flag = true;
                   break;
           }
       }
   }

   private static void setURL(){
       setTime();
       setMoneyCode();
       URL += sstartdate+senddate+smoney_code+money;
   }

   public static void main(String[] args)throws IOException{
       setURL();
       GetRate crate = new GetRate();
       List ratelist = crate.getRate(URL);
       ExportToExcel export = new ExportToExcel();
       export.outPut(ratelist,money);
   }

}
