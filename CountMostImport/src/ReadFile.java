import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by bistu on 2016/11/16.
 */
public class ReadFile {

    private void readFile(File file) throws IOException {
        BufferedReader reader = null;
        int line = 0;
        reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            // 显示行号
            if (tempString.trim().startsWith("import")) {
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
        }
        reader.close();
    }

}
