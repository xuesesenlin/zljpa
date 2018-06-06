package com.zlj.zl.user.monthlyReport.service.impl;

import com.zlj.zl.user.monthlyReport.jpa.MonthlyReportJpa;
import com.zlj.zl.user.monthlyReport.service.MonthlyReportService;
import com.zlj.zl.util.resultJson.ResponseResult;
import com.zlj.zl.util.uuidUtil.GetUuid;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
@Transactional
public class MonthlyReportServiceImpl implements MonthlyReportService {

    @Autowired
    private MonthlyReportJpa jpa;

    @Override
    public ResponseResult<Map<String, List<Object[]>>> findDate(String impDate) {
//        本月
        List<Object[]> list1 = jpa.findDate1(impDate);
//        本年
        List<Object[]> list2 = jpa.findDate2(impDate.split("-")[0]);
//        1985年4月以来总累计
        List<Object[]> list3 = jpa.findDate3("1985-04");

        Map<String, List<Object[]>> map = new HashMap<>();
        if (list1.size() > 0)
            map.put("a", list1);
        if (list2.size() > 0)
            map.put("b", list2);
        if (list3.size() > 0)
            map.put("c", list3);
        if (map.size() <= 0)
            return new ResponseResult<>(false, "未查询到数据", null);
        return new ResponseResult<>(true, "成功", map);
    }

    @Override
    public ResponseResult<Map<String, List<Object[]>>> findDate2(String impDate) {
//        本月
        List<Object[]> list1 = jpa.findDate4(impDate);
//        本年
        List<Object[]> list2 = jpa.findDate5(impDate.split("-")[0]);

        Map<String, List<Object[]>> map = new HashMap<>();
        if (list1.size() > 0)
            map.put("a", list1);
        if (list2.size() > 0)
            map.put("b", list2);
        if (map.size() <= 0)
            return new ResponseResult<>(false, "未查询到数据", null);
        return new ResponseResult<>(true, "成功", map);
    }

    @Override
    public ResponseResult<String> exp(String basePath, String outPath, String data) throws Exception {
        //excel模板路径
        File fi = new File(basePath);
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
//读取excel模板
        HSSFWorkbook wb = new HSSFWorkbook(fs);
//读取了模板内所有sheet内容
        HSSFSheet sheet = wb.getSheetAt(0);
        String[] datas = data.split("]");
        for (int i = 5; i < datas.length + 5; i++) {
            HSSFRow row = sheet.getRow(i);
            String[] d = datas[i - 5].split(",");
            String join = StringUtils.join(d);
            if (!join.equals("[")) {
                row.getCell(3).setCellValue(d[0] == null ? "0" : (d[0].equals("[") ? "0" : d[0]));
                row.getCell(4).setCellValue(d[1] == null ? "0" : (d[1].equals("[") ? "0" : d[1]));
                row.getCell(5).setCellValue(d[2] == null ? "0" : (d[2].equals("[") ? "0" : d[2]));
                row.getCell(6).setCellValue(d[3] == null ? "0" : (d[3].equals("[") ? "0" : d[3]));
                row.getCell(7).setCellValue(d[4] == null ? "0" : (d[4].equals("[") ? "0" : d[4]));
                row.getCell(8).setCellValue(d[5] == null ? "0" : (d[5].equals("[") ? "0" : d[5]));
                row.getCell(9).setCellValue(d[6] == null ? "0" : (d[6].equals("[") ? "0" : d[6]));
                row.getCell(10).setCellValue(d[7] == null ? "0" : (d[7].equals("[") ? "0" : d[7]));
                row.getCell(11).setCellValue(d[8] == null ? "0" : (d[8].equals("[") ? "0" : d[8]));
                row.getCell(12).setCellValue(d[9] == null ? "0" : (d[9].equals("[") ? "0" : d[9]));
            } else {
                row.getCell(3).setCellValue("0");
                row.getCell(4).setCellValue("0");
                row.getCell(5).setCellValue("0");
                row.getCell(6).setCellValue("0");
                row.getCell(7).setCellValue("0");
                row.getCell(8).setCellValue("0");
                row.getCell(9).setCellValue("0");
                row.getCell(10).setCellValue("0");
                row.getCell(11).setCellValue("0");
                row.getCell(12).setCellValue("0");
            }
        }
        //修改模板内容导出新模板
        String fileName = GetUuid.getUUID();
        FileOutputStream out = new FileOutputStream(outPath + fileName + ".xls");
        wb.write(out);
        out.close();
        out.flush();
        return new ResponseResult<>(true, "成功", fileName + ".xls");
    }

    @Override
    public ResponseResult<String> exp2(String basePath, String outPath, String data) throws Exception {
        //excel模板路径
        File fi = new File(basePath);
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
//读取excel模板
        HSSFWorkbook wb = new HSSFWorkbook(fs);
//读取了模板内所有sheet内容
        HSSFSheet sheet = wb.getSheetAt(0);
        String[] datas = data.split("]");
        for (int i = 1; i < datas.length + 1; i++) {
            HSSFRow row = sheet.getRow(i);
            String[] d = datas[i - 1].split(",");
            String join = StringUtils.join(d);
            if (!join.equals("[")) {
                for (int j = 1; j < 27; j++) {
                    row.getCell(j).setCellValue(d[j - 1] == null ? "0" : (d[j - 1].equals("[") ? "0" : d[j - 1]));
                }
            } else {
                for (int j = 1; j < 27; j++) {
                    row.getCell(j).setCellValue("0");
                }
            }
        }
        //修改模板内容导出新模板
        String fileName = GetUuid.getUUID();
        FileOutputStream out = new FileOutputStream(outPath + fileName + ".xls");
        wb.write(out);
        out.close();
        out.flush();
        return new ResponseResult<>(true, "成功", fileName + ".xls");
    }
}
