package com.zlj.zl.util.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ld
 * @name poi操作excel, 支持xls和xlsx
 * @table
 * @remarks
 */
//抑制弃用警告
@SuppressWarnings("deprecation")
public class ExcelUtils {

    /**
     * 读取单个excel.Xlsx
     *
     * @param file
     * @return
     */
    public Map<Long, Map<String, String>> readExcelXlsx(File file) {
//        转换成buffer流
        BufferedInputStream bf = null;
        try {
            bf = new BufferedInputStream(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(bf);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

//        每行数据装入map
        Map<Long, Map<String, String>> map = new HashMap<>();

        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            if (row == null || rowIndex == 0) {
                continue;
            }
            Map<String, String> rowMap = new HashMap<>();
            XSSFCell cell = null;
            for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                String value = "";
                cell = row.getCell(columnIndex);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyyMMdd")
                                            .format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("0").format(cell
                                        .getNumericCellValue());
                            }
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA:
                            // 导入时如果为公式生成的数据则无值
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            } else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() ? "Y" : "N");
                            break;
                        default:
                            value = "";
                    }
                }
                if (!rowMap.containsKey(sheet.getRow(0).getCell(columnIndex).getStringCellValue()))
                    rowMap.put(sheet.getRow(0).getCell(columnIndex).getStringCellValue(), value);
            }
            if (rowMap.size() > 0)
                map.put(Integer.toUnsignedLong(rowIndex), rowMap);
        }
        return map;
    }

    /**
     * 读取单个excel.Xls
     *
     * @param filePath
     * @return
     */
    public Map<Long, Map<String, String>> readExcelXls(String filePath) throws Exception {
//        转换成buffer流
        BufferedInputStream bf = null;
        try {
            File file = new File(filePath);
            bf = new BufferedInputStream(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(bf);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
//        每行数据装入map
        Map<Long, Map<String, String>> map = new HashMap<>();
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            HSSFRow row = sheet.getRow(rowIndex);
            if (row == null || rowIndex == 0) {
                continue;
            }
//            每行一个map
            Map<String, String> rowMap = new HashMap<>();
            HSSFCell cell = null;
            for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                String value = "";
                cell = row.getCell(columnIndex);
                if (sheet.getRow(0).getCell(columnIndex) == null
                        || sheet.getRow(0).getCell(columnIndex).getStringCellValue().trim().equals(""))
                    continue;
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyyMMdd")
                                            .format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("0").format(cell
                                        .getNumericCellValue());
                            }
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA:
                            // 导入时如果为公式生成的数据则无值
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            } else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() ? "Y" : "N");
                            break;
                        default:
                            value = "";
                    }
                }
                if (!rowMap.containsKey(sheet.getRow(0).getCell(columnIndex).getStringCellValue()))
                    rowMap.put(sheet.getRow(0).getCell(columnIndex).getStringCellValue(), value);
            }
            if (rowMap.size() > 0)
                map.put(Integer.toUnsignedLong(rowIndex), rowMap);
        }
        return map;
    }
}
