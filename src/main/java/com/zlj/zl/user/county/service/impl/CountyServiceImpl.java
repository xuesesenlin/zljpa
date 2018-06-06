package com.zlj.zl.user.county.service.impl;

import com.zlj.zl.user.county.service.CountyService;
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
public class CountyServiceImpl implements CountyService {
    @Override
    public ResponseResult<String> exp(String basePath, String outPath, String a, String b, String c) throws Exception {
        //excel模板路径
        File fi = new File(basePath);
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
//读取excel模板
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        HSSFSheet sheet = wb.getSheetAt(0);
        if (!a.equals("") && !a.equals("]")) {
            String[] data = a.split("]");
            for (int i = 0; i < data.length; i++) {
                HSSFRow row = sheet.createRow(i + 4);
                for (int j = 0; j < data[i].split(",").length; j++) {
                    row.createCell(j).setCellValue(data[i].split(",")[j]);
                }
            }
        }
        HSSFSheet sheet1 = wb.getSheetAt(1);
        if (!b.equals("") && !b.equals("]")) {
            String[] data = b.split("]");
            for (int i = 0; i < data.length; i++) {
                HSSFRow row = sheet1.createRow(i + 4);
                for (int j = 0; j < data[i].split(",").length; j++) {
                    row.createCell(j).setCellValue(data[i].split(",")[j]);
                }
            }
        }
        HSSFSheet sheet2 = wb.getSheetAt(2);
        if (!c.equals("") && !c.equals("]")) {
            String[] data = c.split("]");
            for (int i = 0; i < data.length; i++) {
                HSSFRow row = sheet2.createRow(i + 4);
                for (int j = 0; j < data[i].split(",").length; j++) {
                    row.createCell(j).setCellValue(data[i].split(",")[j]);
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
