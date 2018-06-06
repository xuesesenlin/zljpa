package com.zlj.zl.admin.healthMonitoring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/healthMonitoring")
public class HealthMonitoringController {

    //    系统以及磁盘状态
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ModelAndView health() {
        return new ModelAndView("/healthMonitoring/health");
    }

    //    监控
    @RequestMapping(value = "/metrics", method = RequestMethod.GET)
    public ModelAndView metrics() {
        return new ModelAndView("/healthMonitoring/metrics");
    }

    //    最近请求
    @RequestMapping(value = "/trace", method = RequestMethod.GET)
    public ModelAndView trace() {
        return new ModelAndView("/healthMonitoring/trace");
    }
}
