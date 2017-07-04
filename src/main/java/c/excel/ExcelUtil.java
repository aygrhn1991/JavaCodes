/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.excel;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.AttributedString;
import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.SheetUtil;

/**
 *
 * @author Administrator
 */
public class ExcelUtil {

//    public void outPutTable() {
//        long a = System.currentTimeMillis();
//        Connection con = null;
//        PreparedStatement ps_struts = null;
//        ResultSet rs_struts = null;
//        int rowNum = 1;
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            con = dataSource.getConnection();
//            ps_struts = con.prepareStatement("select * from t_item limit 65535");
//            rs_struts = ps_struts.executeQuery();
//            ResultSetMetaData rsm = rs_struts.getMetaData();
//            HSSFSheet sheet = workbook.createSheet("sheet1");
//            int columnCount = rsm.getColumnCount();
//            int[] cell_size = new int[columnCount];
//            int[] max_row = new int[columnCount];
//            HSSFRow row1 = sheet.createRow(0);
//            for (int i = 1; i <= columnCount; i++) {
//                String columnName = rsm.getColumnName(i);
//                cell_size[i - 1] = columnName.getBytes().length;
//                max_row[i - 1] = 0;
//                HSSFCell cell1 = row1.createCell(i - 1);
//                cell1.setCellValue(columnName);
//            }
//
//            while (rs_struts.next()) {
//                HSSFRow row = sheet.createRow(rowNum);
//                for (int i = 0; i < columnCount; i++) {
//                    HSSFCell cell = row.createCell(i);
//                    // 这三句非常耗时间, 尤其是autoSizeColumn不应该在这里调用
//                    // 上面的列是按序取的, 下面的结果按序取就一一对应了没有必要先取name再根据name取值
////                    sheet.autoSizeColumn(i);
////                    String columnName=rs_struts.getMetaData().getColumnName(i+1);
////                    tempo[i]  = rs_struts.getString(columnName);
//                    String value = rs_struts.getString(i + 1);
//                    int byte_len = value.getBytes().length;
//                    if (byte_len > cell_size[i]) {
//                        cell_size[i] = byte_len;
//                        max_row[i] = rowNum;
//                    }
//                    cell.setCellValue(value);
//                }
//                rowNum++;
//            }
//
//            resize(sheet, max_row);
//
//            FileOutputStream fOut = new FileOutputStream("f:/temp/t_item.xls");
//            workbook.write(fOut);
//            fOut.flush();
//            fOut.close();
//        } catch (Exception e) {
//            System.out.println("已运行 outPutTable (): " + e);
//        } finally {
//            if (rs_struts != null) {
//                try {
//                    rs_struts.close();
//                } catch (SQLException e) {
//                }
//            }
//            if (ps_struts != null) {
//                try {
//                    ps_struts.close();
//                } catch (SQLException e) {
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
//        System.out.println("-----------------表开始导出完成!-------------------");
//        System.out.println("共计" + (rowNum - 1) + "行数据，耗时：" + (System.currentTimeMillis() - a) + "毫秒");
//    }
//
//    private static final char defaultChar = '0';
//    private static final FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);
//
//    void resize(Sheet sheet, int[] max_row) {
//        resize(sheet, false, max_row);
//    }
//
//    void resize(Sheet sheet, boolean useMergedCells, int[] max_row) {
//        AttributedString str;
//        TextLayout layout;
//
//        Workbook wb = sheet.getWorkbook();
//        DataFormatter formatter = new DataFormatter();
//        Font defaultFont = wb.getFontAt((short) 0);
//
//        str = new AttributedString(String.valueOf(defaultChar));
//        copyAttributes(defaultFont, str, 0, 1);
//        layout = new TextLayout(str.getIterator(), fontRenderContext);
//        int defaultCharWidth = (int) layout.getAdvance();
//        for (int i = 0; i < max_row.length; i++) {
//            Cell cell = sheet.getRow(max_row[i]).getCell(i);
//            double width = SheetUtil.getCellWidth(cell, defaultCharWidth, formatter, useMergedCells);
//            if (width != -1) {
//                width *= 256;
//                int maxColumnWidth = 255 * 256;
//                if (width > maxColumnWidth) {
//                    width = maxColumnWidth;
//                }
//            }
//            sheet.setColumnWidth(i, (int) width);
//        }
//    }
//
//    private static void copyAttributes(Font font, AttributedString str, int startIdx, int endIdx) {
//        str.addAttribute(TextAttribute.FAMILY, font.getFontName(), startIdx, endIdx);
//        str.addAttribute(TextAttribute.SIZE, (float) font.getFontHeightInPoints());
//        if (font.getBoldweight() == Font.BOLDWEIGHT_BOLD) {
//            str.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, startIdx, endIdx);
//        }
//        if (font.getItalic()) {
//            str.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE, startIdx, endIdx);
//        }
//        if (font.getUnderline() == Font.U_SINGLE) {
//            str.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, startIdx, endIdx);
//        }
//    }
}
