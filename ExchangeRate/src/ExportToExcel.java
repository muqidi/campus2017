import org.apache.poi.hssf.usermodel.*;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by bistu on 2016/11/17.
 */
 //导出到excel文件
 
public class ExportToExcel {

    public void outPut(List data, String currency){
        System.out.println("数据正在导出....");
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(currency+"汇率表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("日期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("中间价");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        for (int i = 0; i < data.size(); i++)
        {
            row = sheet.createRow(i+1);
            Exchangerate rate = (Exchangerate) data.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(rate.getDate());
            row.createCell((short) 1).setCellValue((double) rate.getMiddlePrice());
        }
        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream("G:/"+currency+"汇率表"+".xls");
            wb.write(fout);
            fout.close();
            System.out.println("导出完成");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

