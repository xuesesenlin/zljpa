package com.zlj.zl.user.xsqxlsj.controller;

import com.zlj.zl.user.PMC.service.PMCService;
import com.zlj.zl.user.applyPeoTypes.service.ApplyPeoTypesService;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutQueryModel;
import com.zlj.zl.user.patentDataPut.service.PatentDataPutService;
import com.zlj.zl.user.patentTypes.service.PatentTypesService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/xsqxlsj")
public class XsqxlsjController {

    @Value("${page.pageSize}")
    private int pageSize;

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
        return new ModelAndView("/xsqxlsj/index");
    }

    @RequestMapping(value = "/xsqxlsj/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<PatentDataPutModel>> findByArea(@PathVariable("pageNow") int pageNow,
                                                               @ModelAttribute(value = "form") PatentDataPutQueryModel model) {
        return service.page(model, pageNow, pageSize);
    }
}
