package com.zlj.zl.user.patentServiceAgency.controller;

import com.zlj.zl.user.patentServiceAgency.model.PatentServiceAgencyModel;
import com.zlj.zl.user.patentServiceAgency.service.PatentServiceAgencyService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/patentServiceAgency")
public class patentServiceAgencyController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private PatentServiceAgencyService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/patentServiceAgency/index");
    }

    @RequestMapping(value = "/patentServiceAgency/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<PatentServiceAgencyModel>> page_data(@PathVariable("pageNow") int pageNow,
                                                                    @RequestParam(value = "titles", required = false) String titles) {
        PatentServiceAgencyModel model = new PatentServiceAgencyModel();
        if (titles != null)
            model.setTitles(titles);
        return service.page(model, pageNow, pageSize);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("/patentServiceAgency/add");
    }

    @RequestMapping(value = "/patentServiceAgency", method = RequestMethod.POST)
    public ResponseResult<PatentServiceAgencyModel> save_data(@Valid @ModelAttribute("form") PatentServiceAgencyModel model,
                                                              BindingResult bindingResult) {
        ResponseResult<PatentServiceAgencyModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
//        重复名称验证
//        ResponseResult<List<patentServiceAgencyTypeModel>> result1 = service.findByTypeName(model.getTypeName());
//        if (result1.isSuccess()) {
//            result.setSuccess(false);
//            result.setMessage("名称重复");
//            return result;
//        }
        return service.save(model);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") String id) {
        return new ModelAndView("/patentServiceAgency/update")
                .addObject("uuid", id);
    }

    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public ResponseResult<PatentServiceAgencyModel> findOne(@PathVariable("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/patentServiceAgency", method = RequestMethod.PUT)
    public ResponseResult<PatentServiceAgencyModel> update_data(@Valid @ModelAttribute("form") PatentServiceAgencyModel model,
                                                                BindingResult bindingResult) {
        ResponseResult<PatentServiceAgencyModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        //        重复名称验证
//        ResponseResult<List<patentServiceAgencyTypeModel>> result1 = service.findByTypeName(model.getTypeName());
//        if (result1.isSuccess()) {
//            result.setSuccess(false);
//            result.setMessage("名称重复");
//            return result;
//        }
        return service.update(model);
    }

    @RequestMapping(value = "/patentServiceAgency/{id}", method = RequestMethod.DELETE)
    public ResponseResult<PatentServiceAgencyModel> delete_data(@PathVariable("id") String id) {
        return service.delete(id);
    }
}
