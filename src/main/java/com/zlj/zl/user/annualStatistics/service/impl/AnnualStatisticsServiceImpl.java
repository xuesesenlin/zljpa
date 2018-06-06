package com.zlj.zl.user.annualStatistics.service.impl;

import com.zlj.zl.user.annualStatistics.service.AnnualStatisticsService;
import com.zlj.zl.util.resultJson.ResponseResult;
import com.zlj.zl.util.uuidUtil.GetUuid;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
@Transactional
public class AnnualStatisticsServiceImpl implements AnnualStatisticsService {

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

        HSSFRow row = sheet.getRow(2);
        HSSFRow row2 = sheet.getRow(3);
        String[] d = datas[0].split(",");
        String join = StringUtils.join(d);
        if (!join.equals("[")) {
            for (int i = 0; i < d.length; i++) {
                HSSFCell cell = row.createCell(i);
                HSSFCell cell2 = row2.createCell(i);
                cell.setCellValue(d[i].split(":")[0]);
                cell2.setCellValue(d[i].split(":")[1]);
            }
        }

        HSSFRow row5 = sheet.getRow(5);
        HSSFRow row6 = sheet.getRow(6);
        String[] d1 = datas[1].split(",");
        String join1 = StringUtils.join(d1);
        if (!join1.equals("[")) {
            for (int i = 0; i < d1.length; i++) {
                HSSFCell cell5 = row5.createCell(i);
                HSSFCell cell6 = row6.createCell(i);
                cell5.setCellValue(d1[i].split(":")[0]);
                cell6.setCellValue(d1[i].split(":")[1]);
            }
        }

        HSSFRow row8 = sheet.getRow(8);
        HSSFRow row9 = sheet.getRow(9);
        String[] d2 = datas[2].split(",");
        String join2 = StringUtils.join(d2);
        if (!join2.equals("[")) {
            for (int i = 0; i < d2.length; i++) {
                HSSFCell cell8 = row8.createCell(i);
                HSSFCell cell9 = row9.createCell(i);
                cell8.setCellValue(d2[i].split(":")[0]);
                cell9.setCellValue(d2[i].split(":")[1]);
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
