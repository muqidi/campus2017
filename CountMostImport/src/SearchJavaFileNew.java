import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

//貌似是一种新的Java版本的东东
public class SearchJavaFileNew {

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;
        public FindJavaVisitor(List<Path> result){
            this.result = result;
        }
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
            if(file.toString().endsWith(".java")){
                result.add(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }
    public  void readJavaClassFileNew() throws IOException{
        Path folder = Paths.get("E:\\my project\\java Project");// 默认目录
        String keyword = ".java";
        if (!Files.exists(folder)) {// 如果文件夹不存在
            System.out.println("目录不存在：" + folder);
            return;
        }
        List<Path> result = new LinkedList<Path>();
        Files.walkFileTree(folder, new FindJavaVisitor(result));

        for (Path p : result){
            System.out.println(p.toString());
        }
    }

}