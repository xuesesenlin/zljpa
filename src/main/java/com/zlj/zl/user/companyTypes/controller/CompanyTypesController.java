package com.zlj.zl.user.companyTypes.controller;

import com.zlj.zl.user.companyTypes.model.CompanyTypesModel;
import com.zlj.zl.user.companyTypes.service.CompanyTypesService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/companyTypes")
public class CompanyTypesController {

    @Autowired
    private CompanyTypesService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/companyTypes/index");
    }

    @RequestMapping(value = "/companyTypes", method = RequestMethod.GET)
    public ResponseResult<List<CompanyTypesModel>> findList() {
        return service.findList();
    }

    @RequestMapping(value = "/companyTypes", method = RequestMethod.POST)
    public ResponseResult<CompanyTypesModel> soru(@ModelAttribute("form") CompanyTypesModel model) {
        if (model.getUuid().equals(""))
            return service.save(model);
        else
            return service.update(model);
    }
}
