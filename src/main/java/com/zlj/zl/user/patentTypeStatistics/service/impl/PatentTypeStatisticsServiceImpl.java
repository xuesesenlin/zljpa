package com.zlj.zl.user.patentTypeStatistics.service.impl;

import com.zlj.zl.user.patentTypeStatistics.service.PatentTypeStatisticsService;
import com.zlj.zl.util.resultJson.ResponseResult;
import com.zlj.zl.util.uuidUtil.GetUuid;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
public class PatentTypeStatisticsServiceImpl implements PatentTypeStatisticsService {

    @Override
    public ResponseResult<String> exp(String basePath, String outPath, String a, String b, String c) throws Exception {
        //excel模板路径
        File fi = new File(basePath);
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
//读取excel模板
        HSSFWorkbook wb = new HSSFWorkbook(fs);
//读取了模板内所有sheet内容
        HSSFSheet sheet = wb.getSheetAt(0);

        String[] data_a = a.split(",");
        HSSFRow row2 = sheet.getRow(2);
        HSSFRow row3 = sheet.getRow(3);
        for (int i = 0; i < data_a.length; i++) {
            row2.createCell(i).setCellValue(data_a[i].split(":")[0]);
            row3.createCell(i).setCellValue(data_a[i].split(":")[1]);
        }

        String[] data_b = b.split(",");
        HSSFRow row5 = sheet.getRow(5);
        HSSFRow row6 = sheet.getRow(6);
        for (int i = 0; i < data_b.length; i++) {
            row5.createCell(i).setCellValue(data_b[i].split(":")[0]);
            row6.createCell(i).setCellValue(data_b[i].split(":")[1]);
        }

        String[] data_c = c.split(",");
        HSSFRow row8 = sheet.getRow(8);
        HSSFRow row9 = sheet.getRow(9);
        for (int i = 0; i < data_c.length; i++) {
            row8.createCell(i).setCellValue(data_c[i].split(":")[0]);
            row9.createCell(i).setCellValue(data_c[i].split(":")[1]);
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
