import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by muqidi on 2016/11/13.
 * 统计行的规则
 * E:\JAVA\MyTest\src\exercise\TryCatch.java
 */
public class  Statistics{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入.java文件路径:");
        try {
            Path filepath = Paths.get(sc.nextLine().trim());
            while (true) {
                if (!filepath.toString().endsWith(".java")) {
                    System.err.println("不是有效的java文件，请重新输入");
                    filepath = Paths.get(sc.nextLine());
                    continue;
                }
                if (!Files.exists(filepath)) {
                    System.err.println("文件不存在，请重新输入");
                    filepath = Paths.get(sc.nextLine());
                    continue;
                }
                break;
            }
            Stream<String> lines = Files.lines(filepath);
            long count = lines.filter((p) -> (!p.trim().matches("(^$)|(^//.*)"))).count();
            String outputinfo = String.format("文件的有效行数是：%d",count);
            System.out.println(outputinfo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
