package com.zlj.zl.user.patentTypeStatistics.controller;

import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutQueryModel;
import com.zlj.zl.user.patentDataPut.service.PatentDataPutService;
import com.zlj.zl.user.patentTypeStatistics.service.PatentTypeStatisticsService;
import com.zlj.zl.util.poi.ExcelModelExportUtils;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/patentTypeStatistics")
public class PatentTypeStatisticsController {

    @Value("${upLoad.path}")
    private String upLoadPath;

    @Autowired
    private PatentDataPutService patentDataPutService;
    @Autowired
    private PatentTypeStatisticsService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {
        return new ModelAndView("/patentTypeStatistics/index");
    }

    @RequestMapping(value = "/patentTypeStatistics", method = RequestMethod.POST)
    public ResponseResult<List<PatentDataPutModel>> init_date(@ModelAttribute("form") PatentDataPutQueryModel model) {
        return patentDataPutService.list(model);
    }

    @RequestMapping(value = "/patentTypeStatistics/exp", method = RequestMethod.POST)
    public void init_date_exp(@RequestParam("a") String a,
                              @RequestParam("b") String b,
                              @RequestParam("c") String c,
                              HttpServletResponse response) throws Exception {
        ResponseResult<String> result = service.exp(upLoadPath + "/专利数据按专利类型统计.xls", upLoadPath, a, b, c);
        if (result.isSuccess())
            new ExcelModelExportUtils().download2(upLoadPath + "/" + result.getData(), "专利数据按专利类型统计.xls", response);
        else
            response.getWriter().print(new String("下载失败".getBytes("UTF-8"), "UTF-8"));
    }
}
