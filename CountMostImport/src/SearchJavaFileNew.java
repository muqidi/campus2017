import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

//貌似是一种新的Java版本的东东
//这里统计的肯定有问题，完全按照import里面的李荣统计的
//但是这种统计方式肯定是不对的，因为有.*的存在，而且正常情况下，统计的是包，而不是类
//所以有没有一种直接分析代码来寻找使用了哪些类？


public class SearchJavaFileNew {

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;
        public FindJavaVisitor(List<Path> result){
            this.result = result;
        }
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
            if(file.toString().endsWith(".java")){
                result.add(file);
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public  void readJavaClassFileNew(Path folder) throws IOException {
//      DirectoryStream<Path> pathsFilter = Files.newDirectoryStream(folder, "*.java");
        List<Path> result = new LinkedList<Path>();
        Files.walkFileTree(folder, new FindJavaVisitor(result));
        BufferedReader reader = null;
        int line = 0;
        HashMap<String,Integer> count = new HashMap<>();
        for (Path p : result) {
            //BufferedReader reader = Files.newBufferedReader(p, StandardCharsets.UTF_8);
            reader = new BufferedReader(new FileReader(p.toFile()));
            String str = null;
            while ((str = reader.readLine()) != null) {
                if (str.trim().toLowerCase().startsWith("import")) {
                    String classname = str.split(" ")[1];
                   // System.out.println("line " + line + ": " + classname);
                    if (!count.containsKey(classname)) {
                        count.put(classname,1);
                    }
                    else{
                        int num = count.get(classname)+1;
                        count.remove(classname);
                        count.put(classname,num);
                    }
                    line++;
                }
            }
        }
        List<Map.Entry<String, Integer>> list_Data = new ArrayList<Map.Entry<String, Integer>>(count.entrySet());//对保存结果的hashmap进行排序。
        Collections.sort(list_Data, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                if(o2.getValue()!=null&&o1.getValue()!=null&&o2.getValue().compareTo(o1.getValue())>0){
                    return 1;
                }else{
                    return -1;
                }

            }
        });
        System.out.println(list_Data);
    }

}