package com.zlj.zl.util.poi;

import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import com.zlj.zl.util.resultJson.ResponseResult;
import com.zlj.zl.util.uuidUtil.GetUuid;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class ExcelModelExportUtils {

    //    读取模版
    public HSSFSheet exportUtils(String basePath) {
        try {
            //excel模板路径
            File fi = new File(basePath);
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
//读取excel模板
            HSSFWorkbook wb = new HSSFWorkbook(fs);
//读取了模板内所有sheet内容
            HSSFSheet sheet = wb.getSheetAt(0);
//            调用组装数据
            return sheet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResponseResult<String> export(String basePath, String outPath, List<PatentDataPutModel> datas) {
        try {
            //excel模板路径
            File fi = new File(basePath);
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
//读取excel模板
            HSSFWorkbook wb = new HSSFWorkbook(fs);
//读取了模板内所有sheet内容
            HSSFSheet sheet = wb.getSheetAt(0);
//            调用组装数据
            boolean b = assemble(sheet, datas);
            if (!b)
                return new ResponseResult<>(false, "数据组装错误,生成excel失败", null);
////创建行设置样式，创建单元格，设置单元格样式
//            sheet.shiftRows(startRow, startRow + 1, 1, true, false);
//            sheet.createRow(startRow);
//            sheet.getRow(startRow).setRowStyle(rowstyle);
//            for (int j = 0; j < 9; j++) {
//                sheet.getRow(startRow).createCell(j);
//            }
//            HSSFCell temp1 = sheet.getRow(startRow).getCell(0);
//            temp1.setCellValue(1);
//            temp1.setCellStyle(style);
            //修改模板内容导出新模板
            String fileName = GetUuid.getUUID();
            FileOutputStream out = new FileOutputStream(outPath + fileName + ".xls");
            wb.write(out);
            out.close();
            out.flush();
            return new ResponseResult<>(true, "生成excel成功", fileName + ".xls");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(false, "生成excel失败", null);
        }
    }

    //    组装数据-专利数据维护导出查询结果
    private boolean assemble(HSSFSheet sheet, List<PatentDataPutModel> datas) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        try {
//        获取异地样总列数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
//        获取第一行每列的数据与datas做对比，对数据进行组装
            for (int i = 0; i < datas.size(); i++) {//数据行数
                HSSFRow row = sheet.createRow(i + 1);
                for (int j = 0; j < cells; j++) {//title列数
                    String s = sheet.getRow(0).getCell(j).getStringCellValue();
                    HSSFCell cell = row.createCell(j);
                    if (s.equals("申请号"))
                        cell.setCellValue(datas.get(i).getApplyCode());
                    if (s.equals("授权入库日")) {
                        long date = datas.get(i).getEmpowermentDate();
                        if (date == 0)
                            cell.setCellValue("");
                        else {
                            String s1 = format.format(date);
                            cell.setCellValue(s1);
                        }
                    }
                    if (s.equals("申请日")) {
                        long date = datas.get(i).getApplyDate();
                        if (date == 0)
                            cell.setCellValue("");
                        else {
                            String s1 = format.format(date);
                            cell.setCellValue(s1);
                        }
                    }
                    if (s.equals("发明名称"))
                        cell.setCellValue(datas.get(i).getInventionName());
                    if (s.equals("代理机构名称"))
                        cell.setCellValue(datas.get(i).getAgentName());
                    if (s.equals("主分类号"))
                        cell.setCellValue(datas.get(i).getMainTypes());
                    if (s.equals("专利人名称"))
                        cell.setCellValue(datas.get(i).getPatentPeoName());
                    if (s.equals("申请人邮编"))
                        cell.setCellValue(datas.get(i).getZipCode());
                    if (s.equals("申请人地址"))
                        cell.setCellValue(datas.get(i).getPeoAddress());
                    if (s.equals("专利类型"))
                        cell.setCellValue(datas.get(i).getPatentTypes());
                    if (s.equals("申请人类型"))
                        cell.setCellValue(datas.get(i).getAppPeoTypes());
                    if (s.equals("省份名称"))
                        cell.setCellValue(datas.get(i).getProvince());
                    if (s.equals("城市名称"))
                        cell.setCellValue(datas.get(i).getCity());
                    if (s.equals("区/县名称"))
                        cell.setCellValue(datas.get(i).getArea());
                    if (s.equals("申请方式名称"))
                        cell.setCellValue(datas.get(i).getApplyMode());
                    if (s.equals("案卷入库日")) {
                        long date = datas.get(i).getFileEnterDate();
                        if (date == 0)
                            cell.setCellValue("");
                        else {
                            String s1 = format.format(date);
                            cell.setCellValue(s1);
                        }
                    }
                    if (s.equals("导入时间")) {
                        long date = datas.get(i).getImpDate();
                        if (date == 0)
                            cell.setCellValue("");
                        else {
                            String s1 = format.format(date);
                            cell.setCellValue(s1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //下载
    public void download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8")));
            response.addHeader("Content-Length", "" + file.length());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //下载
    public void download2(String path, String fileName, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes(), "ISO-8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
