package com.zlj.zl.user.patentTypes.controller;

import com.zlj.zl.user.patentTypes.model.PatentTypesModel;
import com.zlj.zl.user.patentTypes.service.PatentTypesService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/patentTypes")
public class PatentTypesController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private PatentTypesService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/patentTypes/index");
    }

    @RequestMapping(value = "/patentTypes/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<PatentTypesModel>> page(@PathVariable("pageNow") int pageNow) {
        return service.page(new PatentTypesModel(), pageNow, pageSize);
    }
}
