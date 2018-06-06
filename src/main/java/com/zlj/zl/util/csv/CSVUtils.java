package com.zlj.zl.util.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class CSVUtils {

    public Map<Long, Map<String, String>> readCSV(String filePath) {
        Map<Long, Map<String, String>> map = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
//            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            String[] title = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i == 0)
                    title = line.split(",");
                else {
                    String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                    String last = item[item.length - 1];//这就是你要的数据了
                    //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                    System.out.println(last);
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
