import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//通过file类遍历目录以及文件，一种方法吧。


public class SearchJavaFileOld {
    static int countFiles = 0;// 声明统计文件个数的变量
    static int countFolders = 0;// 声明统计文件夹的变量

    private static File[] searchFileOld(File folder, final String keyWord) {// 递归查找包含关键字的文件
        File[] subFolders = folder.listFiles(new FileFilter() {// 运用内部匿名类获得文件
            @Override
            public boolean accept(File pathname) {// 实现FileFilter类的accept方法
                if (pathname.isFile())// 如果是文件
                    countFiles++;
                else
                    countFolders++;// 如果是目录
                return (pathname.isDirectory() || (pathname.isFile() && pathname.getName().toLowerCase().endsWith(keyWord.toLowerCase())));// 目录或文件包含关键字
            }
        });
        List<File> result = new ArrayList<File>();// 声明一个集合
        for (File i : subFolders) {// 循环显示文件夹或文件
            if (i.isFile()) {// 如果是文件则将文件添加到结果列表中
                result.add(i);
            } else {// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                File[] foldResult = searchFileOld(i, keyWord);
                for (File j : foldResult) {// 循环显示文件
                    result.add(j);// 文件保存到集合中
                }
            }
        }
        File files[] = new File[result.size()];// 声明文件数组，长度为集合的长度
        result.toArray(files);// 集合数组化
        return files;
    }

    private static void  countImport(File[] classfile){
        int line = 0;
        for(File f : classfile){
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(f));
                String tempString = null;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    // 显示行号
                    if( tempString.trim().startsWith("import")) {
                        System.out.println("line " + line + ": " + tempString);
                        line++;
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }
    }
    public  void readJavaClassFileOld(String strfolder) {// java程序的主入口处
        File folder = new File(strfolder);// 默认目录
        String keyword = ".java";
        File[] result = searchFileOld(folder, keyword);// 调用方法获得文件数组
        countImport(result);
        //System.out.println("在 " + folder + " 以及所有子文件时查找对象" + keyword);
        System.out.println("查找了" + countFiles + " 个文件，" + countFolders + " 个文件夹，共找到 " + result.length + " 个符合条件的文件：");
       /* for (int i = 0; i < result.length; i++) {// 循环显示文件
            File file = result[i];
            System.out.println(file.getAbsolutePath() + " ");// 显示文件绝对路径
        }*/
    }

}