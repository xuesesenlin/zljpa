package com.zlj.zl.user.monthlyReport.controller;

import com.zlj.zl.user.monthlyReport.service.MonthlyReportService;
import com.zlj.zl.util.poi.ExcelModelExportUtils;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/monthlyReport")
public class MonthlyReportController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Value("${upLoad.path}")
    private String upLoadPath;

    @Autowired
    private MonthlyReportService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {
        return new ModelAndView("/monthlyReport/index");
    }

    @RequestMapping(value = "/monthlyReport", method = RequestMethod.GET)
    public ResponseResult<Map<String, List<Object[]>>> init_date(@RequestParam("tjny") String tjny) {
        return service.findDate(tjny);
    }

    @RequestMapping(value = "/monthlyReport2", method = RequestMethod.GET)
    public ResponseResult<Map<String, List<Object[]>>> init_date2(@RequestParam("tjny") String tjny) {
        return service.findDate2(tjny);
    }

    @RequestMapping(value = "/monthlyReport/export/{lx}", method = RequestMethod.GET)
    public void exp_lx(@PathVariable("lx") String lx,
                       @RequestParam("data") String data,
                       HttpServletResponse response) throws Exception {
        ResponseResult<String> result;
        String fileName;
        if (lx.equals("1"))
            result = service.exp(upLoadPath + "/" + (fileName = "专利数据按月度报表类型.xls"), upLoadPath, data);
        else
            result = service.exp2(upLoadPath + "/" + (fileName = "专利数据按月度报表区域.xls"), upLoadPath, data);

        if (result.isSuccess())
            new ExcelModelExportUtils().download2(upLoadPath + "/" + result.getData(), fileName, response);
        else
            response.getWriter().print(new String("下载失败".getBytes("UTF-8"), "UTF-8"));
    }
}
