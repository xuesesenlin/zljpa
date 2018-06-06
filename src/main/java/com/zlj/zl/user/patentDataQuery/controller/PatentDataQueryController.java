package com.zlj.zl.user.patentDataQuery.controller;

import com.zlj.zl.user.PMC.model.PMCModel;
import com.zlj.zl.user.PMC.service.PMCService;
import com.zlj.zl.user.applyPeoTypes.model.ApplyPeoTypesModel;
import com.zlj.zl.user.applyPeoTypes.service.ApplyPeoTypesService;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutQueryModel;
import com.zlj.zl.user.patentDataPut.service.PatentDataPutService;
import com.zlj.zl.user.patentTypes.model.PatentTypesModel;
import com.zlj.zl.user.patentTypes.service.PatentTypesService;
import com.zlj.zl.util.poi.ExcelModelExportUtils;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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
@RequestMapping("/patentDataQuery")
public class PatentDataQueryController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Value("${upLoad.path}")
    private String upLoadPath;
    @Autowired
    private PatentDataPutService service;
    @Autowired
    private PMCService pmcService;
    @Autowired
    private PatentTypesService patentTypesService;
    @Autowired
    private ApplyPeoTypesService applyPeoTypesService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/patentDataQuery/index");
    }

    @RequestMapping(value = "/patentDataQuery/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<PatentDataPutModel>> findByArea(@PathVariable("pageNow") int pageNow,
                                                               @ModelAttribute(value = "form") PatentDataPutQueryModel model) {
        return service.page(model, pageNow, pageSize);
    }

    @RequestMapping(value = "/pmc/{name}", method = RequestMethod.GET)
    public List<PMCModel> findByCityName(@PathVariable("name") String name) {
        return pmcService.findByCityName(name);
    }

    @RequestMapping(value = "/patentTypes", method = RequestMethod.GET)
    public ResponseResult<List<PatentTypesModel>> patentTypes() {
        return patentTypesService.list(new PatentTypesModel());
    }

    @RequestMapping(value = "/applyPeoTypes", method = RequestMethod.GET)
    public ResponseResult<List<ApplyPeoTypesModel>> applyPeoTypes() {
        return applyPeoTypesService.list(new ApplyPeoTypesModel());
    }

    @RequestMapping(value = "/pmc/ssx/{names}", method = RequestMethod.GET)
    public ResponseResult<List<PMCModel>> findPMCByPatentUuid(@PathVariable("names") String names) {
        if (names.equals("0")) {
            PMCModel model = new PMCModel();
            model.setPatentUuid("0");
            return pmcService.findByPageAndParamsList(model);
        } else {
            List<PMCModel> list = pmcService.findByCityName(names);
            if (list.size() > 0)
                return new ResponseResult<>(true, "成功", list);
            else
                return new ResponseResult<>(false, "成功", null);
        }
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public String export(@ModelAttribute(value = "form") PatentDataPutQueryModel model,
                         HttpServletResponse response) {
        ResponseResult<List<PatentDataPutModel>> result = service.list(model);
        if (result.isSuccess()) {
//            组装excel
            ResponseResult<String> export = new ExcelModelExportUtils().export(upLoadPath + "/专利数据维护模版.xls",
                    upLoadPath,
                    result.getData());
            if (export.isSuccess())
                new ExcelModelExportUtils().download(upLoadPath + export.getData(), response);
            else
                return export.getMessage();
        } else
            return "未查询到数据";
        return "下载失败";
    }
}
