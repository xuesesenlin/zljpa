package com.zlj.zl.sysConfig.jvm;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
public class JVMInfo {
    private NumberFormat fmtI = new DecimalFormat("###,###", new DecimalFormatSymbols(Locale.CHINESE));
    private NumberFormat fmtD = new DecimalFormat("###,##0.000", new DecimalFormatSymbols(Locale.CHINESE));

    private final int Kb = 1024;

    public void printSummary() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
        log.info("虚拟机名称:" + runtime.getVmName() + " 版本:" + runtime.getVmVersion());
        log.info("java版本:" + System.getProperty("java.version"));
        log.info("供应商:" + runtime.getVmVendor());
        log.info("运行时间:" + toDuration(runtime.getUptime()));
        log.info("线程生存数量:" + threads.getThreadCount());
        log.info("线程守护进程数量:" + threads.getDaemonThreadCount());
        log.info("线程峰值:" + threads.getPeakThreadCount());
        log.info("线程总数:" + threads.getTotalStartedThreadCount());
        log.info("堆当前值:" + mem.getHeapMemoryUsage().getUsed() / Kb);
        log.info("堆最大值:" + mem.getHeapMemoryUsage().getMax() / Kb);
        log.info("堆提交值:" + mem.getHeapMemoryUsage().getCommitted() / Kb);
        log.info("操作系统名称:" + os.getName() + "版本:" + os.getVersion());
        log.info("操作系统架构:" + os.getArch());
        log.info("操作系统内核:" + os.getAvailableProcessors());
        log.info("clsCurrLoaded:" + cl.getLoadedClassCount());
        log.info("clsLoaded:" + cl.getTotalLoadedClassCount());
        log.info("clsUnloaded:" + cl.getUnloadedClassCount());

    }

    protected String printSizeInKb(double size) {
        return fmtI.format((long) (size / 1024)) + " kbytes";
    }

    protected String toDuration(double uptime) {
        uptime /= 1000;
        if (uptime < 60) {
            return fmtD.format(uptime) + " seconds";
        }
        uptime /= 60;
        if (uptime < 60) {
            long minutes = (long) uptime;
            String s = fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            return s;
        }
        uptime /= 60;
        if (uptime < 24) {
            long hours = (long) uptime;
            long minutes = (long) ((uptime - hours) * 60);
            String s = fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
            if (minutes != 0) {
                s += " " + fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            }
            return s;
        }
        uptime /= 24;
        long days = (long) uptime;
        long hours = (long) ((uptime - days) * 24);
        String s = fmtI.format(days) + (days > 1 ? " days" : " day");
        if (hours != 0) {
            s += " " + fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
        }
        return s;
    }
}
