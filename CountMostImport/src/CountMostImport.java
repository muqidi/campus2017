import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//从这个文件运行
public class CountMostImport {

    public   static  void main(String[] args )throws IOException{

        System.out.println("请输入路径名：");//E:\my project\java Project
        Scanner sc = new Scanner(System.in);
        Path folder = Paths.get(sc.nextLine());// 默认目录
        while (!Files.exists(folder)) {// 如果文件夹不存在
            System.out.println("目录不存在：" + folder);
            folder = Paths.get(sc.nextLine());
        }

        SearchJavaFileNew newmethod = new SearchJavaFileNew();
        newmethod.readJavaClassFileNew(folder);
/*
        System.out.println("*//************************************************************************//*");

        SearchJavaFileOld oldmethod = new SearchJavaFileOld();
        oldmethod.readJavaClassFileOld(folder.toString());*/
    }
}