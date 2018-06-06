package com.zlj.zl.admin.dataBaseBack.controller;

import com.zlj.zl.util.date.Dates2;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */

@Slf4j
@RestController
@RequestMapping("/mysql")
public class DataBaseBackController {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${databaseSavePath}")
    private String savePath;

    @Value("${databaseSavePath}")
    private String dataBaseSavePath;

    @RequiresRoles(value = "admin")
    @RequestMapping(value = "/back", method = RequestMethod.GET)
    public String backupController() {
        try {
            String hostIP = url.split("mysql://")[1].split("/")[0];
            if (exportDatabaseTool(hostIP,
                    userName,
                    password,
                    savePath,
                    Dates2.getDateTimeString2() + ".sql",
                    url.split("\\?")[0].split(hostIP + "/")[1])) {
                log.info("数据库备份成功");
                return "数据库备份成功";
            } else {
                log.error("数据库备份失败");
                return "数据库备份失败";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "数据库备份失败";
        }
    }

    //每天23点触发
    @Scheduled(cron = "0 0 23 * * ?")
    public void backupController2() {
        try {
            String hostIP = url.split("mysql://")[1].split("/")[0];
            if (exportDatabaseTool(hostIP,
                    userName,
                    password,
                    savePath,
                    Dates2.getDateTimeString2() + ".sql",
                    url.split("\\?")[0].split(hostIP + "/")[1])) {
                log.info("数据库备份成功");
            } else
                log.error("数据库备份失败");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //本地备份
    private String backup() {
        try {
            Runtime rt = Runtime.getRuntime();

            Process child = rt.exec("D:\\Program Files (x86)\\MySQL\\MySQL Server 5.0\\bin\\mysqldump -uroot -proot -R -c --set-charset=utf8 zl2");
            InputStream in = child.getInputStream();
            InputStreamReader xx = new InputStreamReader(in, "utf8");
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            FileOutputStream fout = new FileOutputStream(dataBaseSavePath + Dates2.getDateTimeString() + ".sql");
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
            writer.write(outStr);
            writer.flush();

            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
            return "备份成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "备份失败";
        }

    }

//    远程备份

    /**
     * Java代码实现MySQL数据库导出
     *
     * @param hostIP       MySQL数据库所在服务器地址IP
     * @param userName     进入数据库所需要的用户名
     * @param password     进入数据库所需要的密码
     * @param savePath     数据库导出文件保存路径
     * @param fileName     数据库导出文件文件名
     * @param databaseName 要导出的数据库名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException {
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
            Process process = Runtime.getRuntime().exec("mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
            if (process.waitFor() == 0) {//0 表示线程正常终止。
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
