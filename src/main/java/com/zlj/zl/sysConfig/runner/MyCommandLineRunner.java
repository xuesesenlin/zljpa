package com.zlj.zl.sysConfig.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ld
 * @name springboot 启动时运行
 * @table
 * @remarks
 */
@Component
//如果有多个要执行的方法@Order 这个注释来规定执行的先后顺序. 数字越小优先级越高
@Order(1)
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("运行中");
    }
}
