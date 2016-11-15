import java.io.*;

public class CountMostImport {

    public   static  void main(String[] args )throws IOException{
        SearchJavaFileNew newmethod = new SearchJavaFileNew();
        newmethod.readJavaClassFileNew();
        System.out.println("/************************************************************************/");
        SearchJavaFileOld oldmethod = new SearchJavaFileOld();
        oldmethod.readJavaClassFileOld();
    }
}