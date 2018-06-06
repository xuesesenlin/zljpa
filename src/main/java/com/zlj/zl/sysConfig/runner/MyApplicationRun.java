package com.zlj.zl.sysConfig.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动之后，结束之前执行
 */
@Component
public class MyApplicationRun implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("系统准备完成");
//        Properties sysProperty = System.getProperties(); //系统属性
//        String s = System.getProperty("user.dir");
        // 获取用户名
//        System.out.println("当前用户名:" + sysProperty.getProperty("user.name"));
        // 获取操作系统
//        System.out.println("当前系统:" + sysProperty.getProperty("os.name"));

//        boolean boo = true;
//        do {
//            System.out.println("请输入当前系统的当前用户名:");
//            Scanner scan = new Scanner(System.in);
//            String read = scan.nextLine();
//            if (read.equals(sysProperty.getProperty("user.name"))) {
//                boo = false;
//                System.out.println("密码正确,即将启动完成");
//            } else {
//                boo = true;
//                System.out.println("密码错误");
//            }
//        } while (boo);
    }
}
