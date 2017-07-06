/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.excel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Administrator
 */
public class ExcelUtil {

    private String filePath = null;
    private String fileName = null;
    private String sheetName = null;
    private HSSFWorkbook workbook = null;
    private DecimalFormat decimalFormat = new DecimalFormat(".00");
    private String[] colFormula = null;

    public ExcelUtil(String filePath, String fileName, String sheetName, String[] colFormula) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.sheetName = sheetName;
        this.colFormula = colFormula;
        workbook = new HSSFWorkbook();
    }

    private CellStyle setBorder(CellStyle style, BorderStyle borderStyle) {
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderTop(borderStyle);
        style.setBorderRight(borderStyle);
        return style;
    }

    private CellStyle setFont(CellStyle style, String fontName, short fontSize, short color, boolean bold) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setFontName(fontName);
        font.setColor(color);
        font.setBold(bold);
        style.setFont(font);
        return style;
    }

    public void writeExcel(String[] columnName, String[] columnValue, int[] columnWidth, List<?> dataList) {
        Sheet sheet = workbook.createSheet(this.sheetName);
        OutputStream out = null;
        try {
            out = new FileOutputStream(filePath + fileName);
            Row titleRow = workbook.getSheet(sheetName).createRow(0);
            HSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle = (HSSFCellStyle) setBorder(titleStyle, BorderStyle.NONE);
            titleStyle = (HSSFCellStyle) setFont(titleStyle, "微软雅黑", (short) 10, (short) 0, true);
            for (int i = 0; i < columnName.length; i++) {
                sheet.setColumnWidth(i, columnWidth[i] * 256);
                org.apache.poi.ss.usermodel.Cell cell = titleRow.createCell(i);
                cell.setCellStyle(titleStyle);
                cell.setCellValue(columnName[i]);
            }
            if (dataList != null && dataList.size() > 0) {
                HSSFCellStyle dataStyle = workbook.createCellStyle();
                dataStyle = (HSSFCellStyle) setBorder(dataStyle, BorderStyle.NONE);
                dataStyle = (HSSFCellStyle) setFont(dataStyle, "微软雅黑", (short) 10, (short) 0, false);
                if (columnValue.length > 0) {
                    for (int rowIndex = 1; rowIndex <= dataList.size(); rowIndex++) {
                        Object obj = dataList.get(rowIndex - 1);
                        Class clsss = obj.getClass();
                        Row dataRow = workbook.getSheet(sheetName).createRow(rowIndex);
                        for (int columnIndex = 0; columnIndex < columnValue.length; columnIndex++) {
                            String title = columnValue[columnIndex].trim();
                            if (!title.isEmpty()) {
                                String UTitle = Character.toUpperCase(title.charAt(0)) + title.substring(1, title.length());
                                String methodName = "get" + UTitle;
                                Method method = clsss.getDeclaredMethod(methodName);
                                Object data = method.invoke(obj) == null ? "" : method.invoke(obj);
                                Cell cell = dataRow.createCell(columnIndex);
                                cell.setCellStyle(dataStyle);
                                if (data != null) {
                                    if (data instanceof Integer) {
                                        cell.setCellValue((int) data);
                                    } else if (data instanceof Long) {
                                        cell.setCellValue((long) data);
                                    } else if (data instanceof Float) {
                                        cell.setCellValue(decimalFormat.format(data));
                                    } else if (data instanceof Double) {
                                        cell.setCellValue(decimalFormat.format(data));
                                    } else if (data instanceof Date) {
                                        Date date = (Date) data;
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                        cell.setCellValue(sdf.format(date));
                                    } else if (data instanceof Boolean) {
                                        cell.setCellValue((boolean) data);
                                    } else {
                                        cell.setCellValue((String) data);
                                    }
                                }
                            } else if (colFormula != null) {
                                String formula = colFormula[columnIndex].replace("@", (rowIndex + 1) + "");
                                Cell cell = dataRow.createCell(columnIndex);
                                cell.setCellStyle(dataStyle);
                                cell.setCellFormula(formula);
                            }
                        }
                    }
                }
            }
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
