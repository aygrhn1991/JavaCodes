/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.excel;

import c.models.ExcelExportModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class ExcelSimpleUtil {

    public static List<ExcelExportModel> readExcel(String path) {
        InputStream input = null;
        try {
            input = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(input);
            ExcelExportModel item = null;
            List<ExcelExportModel> list = new ArrayList<>();
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                item = new ExcelExportModel();
                HSSFCell cell0 = row.getCell(0);
                item.setId((int) cell0.getNumericCellValue());
                HSSFCell cell1 = row.getCell(1);
                item.setStr(cell1.getStringCellValue());
                HSSFCell cell2 = row.getCell(2);
                item.setBool(cell2.getBooleanCellValue());
                HSSFCell cell3 = row.getCell(3);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(cell3.getStringCellValue());
                item.setDate(date);
                HSSFCell cell4 = row.getCell(4);
                item.setNum1((long) cell4.getNumericCellValue());
                HSSFCell cell5 = row.getCell(5);
                item.setNum2(Float.parseFloat(cell5.getStringCellValue()));
                HSSFCell cell6 = row.getCell(6);
                item.setNum3(Double.parseDouble(cell6.getStringCellValue()));
                list.add(item);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return null;
    }

    public static void writeExcel(List<ExcelExportModel> dataList, String path) {
        OutputStream out = null;
        try {
            int columnCount = ExcelExportModel.class.getDeclaredFields().length;
            HSSFWorkbook workbook = new HSSFWorkbook();
            ExcelExportModel item = null;
            HSSFSheet sheet = workbook.createSheet("sheetA");
            HSSFRow titleRow = sheet.createRow(0);
            HSSFCell[] titleCells = new HSSFCell[columnCount];
            String[] names = new String[columnCount];
            names[0] = "列1";
            names[1] = "列2";
            names[2] = "列3";
            names[3] = "列4";
            names[4] = "列5";
            names[5] = "列6";
            names[6] = "列7";
            for (int i = 0; i < columnCount; i++) {
                titleCells[i] = titleRow.createCell(i);
                titleCells[i].setCellValue(new HSSFRichTextString(names[i]));
            }
            for (int i = 0; i < dataList.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                item = dataList.get(i);
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(item.getId());
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(item.getStr());
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(item.getBool());
                HSSFCell cell3 = row.createCell(3);
                Date date = item.getDate(); 
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cell3.setCellValue(sdf.format(date));
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(item.getNum1());
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(item.getNum2());
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(item.getNum3());
            }
            out = new FileOutputStream(path);
            workbook.write(out);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
