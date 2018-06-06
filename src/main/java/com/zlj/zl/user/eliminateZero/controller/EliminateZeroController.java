package com.zlj.zl.user.eliminateZero.controller;

import com.zlj.zl.user.eliminateZero.model.EliminateZeroModel;
import com.zlj.zl.user.eliminateZero.service.EliminateZeroService;
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
@RequestMapping("/eliminateZero")
public class EliminateZeroController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private EliminateZeroService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/eliminateZero/index");
    }

    @RequestMapping(value = "/eliminateZero/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<EliminateZeroModel>> page_data(@PathVariable("pageNow") int pageNow,
                                                              @RequestParam(value = "titles", required = false) String titles) {
        return null;//service.page(pageNow, pageSize, titles);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("/eliminateZero/add");
    }

    @RequestMapping(value = "/eliminateZero", method = RequestMethod.POST)
    public ResponseResult<EliminateZeroModel> save_data(@Valid @ModelAttribute("form") EliminateZeroModel model,
                                                        BindingResult bindingResult) {
        ResponseResult<EliminateZeroModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
//        重复名称验证
//        ResponseResult<List<eliminateZeroTypeModel>> result1 = service.findByTypeName(model.getTypeName());
//        if (result1.isSuccess()) {
//            result.setSuccess(false);
//            result.setMessage("名称重复");
//            return result;
//        }
        return null;//service.save(model);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") String id) {
        return new ModelAndView("/eliminateZero/update")
                .addObject("uuid", id);
    }

    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public ResponseResult<EliminateZeroModel> findOne(@PathVariable("id") String id) {
        return null;//service.findOne(id);
    }

    @RequestMapping(value = "/eliminateZero", method = RequestMethod.PUT)
    public ResponseResult<EliminateZeroModel> update_data(@Valid @ModelAttribute("form") EliminateZeroModel model,
                                                          BindingResult bindingResult) {
        ResponseResult<EliminateZeroModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        //        重复名称验证
//        ResponseResult<List<eliminateZeroTypeModel>> result1 = service.findByTypeName(model.getTypeName());
//        if (result1.isSuccess()) {
//            result.setSuccess(false);
//            result.setMessage("名称重复");
//            return result;
//        }
        return null;//service.update(model);
    }

    @RequestMapping(value = "/eliminateZero/{id}", method = RequestMethod.DELETE)
    public ResponseResult<EliminateZeroModel> delete_data(@PathVariable("id") String id) {
        return null;//service.delete(id);
    }
}
