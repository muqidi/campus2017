import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by bistu on 2016/11/14.
 */
 //从网站爬取数据
 
public class GetRate {
    private static String MATCH_DOUBLE ="[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*";
    private static String MATCH_DATE = "\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}";


    public  List getCurrency(String url){//获取货币代码表
        List currencylist =new ArrayList();
        try {
            Document doc;
            doc = Jsoup.connect(url).get();
            Elements options = doc.select("#money_code > option");
            for (Element op : options) {
                String name = op.text().trim();
                String shortname = op.attr("value");
                currencylist.add(new Currency(name,shortname));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencylist;
    }


    public List getRate(String url) {
/*        // 直接从字符串中输入 HTML 文档
        String html = "<html><head><title>开源中国社区</title></head>"
                + "<body><p>这里是 jsoup 项目的相关文章</p></body></html>";
        Document doc = Jsoup.parse(html);

// 从URL直接加载 HTML 文档
        Document doc = Jsoup.connect("http://www.oschina.net/").get();
        String title = doc.title();
        // 从文件中加载 HTML 文档
    File input = new File("D:/test.html");
    Document doc = Jsoup.parse(input,"UTF-8","http://www.oschina.net/");

    //通过post访问html
    Document doc = Jsoup.connect("http://www.oschina.net/")
            .data("query", "Java")   //请求参数
            .userAgent("I’m jsoup") //设置User-Agent
            .cookie("auth", "token") //设置cookie
            .timeout(3000)           //设置连接超时时间
            .post();
*/

        List list =new ArrayList();
        try {
            Document doc;
            int i = 0;
            while(true) {
                i++;
                url += "&page="+i;
                System.out.printf("正在获取%d页数据....\n",i);
                doc = Jsoup.connect(url).get();
                Elements ListTable = doc.getElementsByAttributeValue("class", "list_table");
                for (Element element : ListTable) {
                    Elements tr = element.getElementsByTag("tr");
                    if(tr.size()<=1){
                        return list;
                    }
                    for (Element td : tr) {
                        //String linkHref = link.attr("href");
                        Elements value = td.getElementsByTag("td");
                        String time = value.first().text().trim();
                        String price = value.last().text().trim();
                        if (time.matches(MATCH_DATE) && price.matches(MATCH_DOUBLE)) {
                            Exchangerate rate = new Exchangerate(time, Double.parseDouble(price));
                            list.add(rate);
                        }
                    }
                }

                //sleep(5000);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("获取数据完成。");
        return list;
    }
}
