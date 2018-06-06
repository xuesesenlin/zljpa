package com.zlj.zl.sysConfig.initializer;

import com.zlj.zl.sysConfig.jvm.JVMInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ld
 * @name 启动之前，比CommandLineRunner还要之前
 * @table
 * @remarks 需要在src/main/resources/目录中创建META-INF/spring.factories，并在其中添加
 * org.springframework.context.ApplicationContextInitializer=\包名.类名(注意\)
 */
@Slf4j
public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext arg0) {
        log.info("专利局专利信息管理系统");
        log.info("系统适用环境为：java版本：1.8.x");
        log.info("系统适用环境为：java编译版本：52.0");
        log.info("系统当前运行环境");
        new JVMInfo().printSummary();
    }
}
