package com.zlj.zl.user.development.controller;

import com.zlj.zl.user.development.model.DevelopmentModel;
import com.zlj.zl.user.development.service.DevelopmentService;
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
@RequestMapping("/development")
public class DevelopmentController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private DevelopmentService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/development/index");
    }

    @RequestMapping(value = "/development/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<DevelopmentModel>> page_data(@PathVariable("pageNow") int pageNow,
                                                            @RequestParam(value = "titles", required = false) String titles) {
        return service.page(pageNow, pageSize, titles);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("/development/add");
    }

    @RequestMapping(value = "/development", method = RequestMethod.POST)
    public ResponseResult<DevelopmentModel> save_data(@Valid @ModelAttribute("form") DevelopmentModel model,
                                                      BindingResult bindingResult) {
        ResponseResult<DevelopmentModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
//        重复名称验证
//        ResponseResult<List<developmentTypeModel>> result1 = service.findByTypeName(model.getTypeName());
//        if (result1.isSuccess()) {
//            result.setSuccess(false);
//            result.setMessage("名称重复");
//            return result;
//        }
        return service.save(model);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") String id) {
        return new ModelAndView("/development/update")
                .addObject("uuid", id);
    }

    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public ResponseResult<DevelopmentModel> findOne(@PathVariable("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/development", method = RequestMethod.PUT)
    public ResponseResult<DevelopmentModel> update_data(@Valid @ModelAttribute("form") DevelopmentModel model,
                                                        BindingResult bindingResult) {
        ResponseResult<DevelopmentModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        //        重复名称验证
//        ResponseResult<List<developmentTypeModel>> result1 = service.findByTypeName(model.getTypeName());
//        if (result1.isSuccess()) {
//            result.setSuccess(false);
//            result.setMessage("名称重复");
//            return result;
//        }
        return service.update(model);
    }

    @RequestMapping(value = "/development/{id}", method = RequestMethod.DELETE)
    public ResponseResult<DevelopmentModel> delete_data(@PathVariable("id") String id) {
        return service.delete(id);
    }
}
