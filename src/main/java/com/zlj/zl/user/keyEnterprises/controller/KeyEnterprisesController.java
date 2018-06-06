package com.zlj.zl.user.keyEnterprises.controller;

import com.zlj.zl.user.keyEnterprises.model.KeyEnterprisesModel;
import com.zlj.zl.user.keyEnterprises.service.KeyEnterprisesService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/keyEnterprises")
public class KeyEnterprisesController {

    @Autowired
    private KeyEnterprisesService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {
        return new ModelAndView("/keyEnterprises/index");
    }

    @RequestMapping(value = "/keyEnterprises/{id}", method = RequestMethod.GET)
    public ResponseResult<List<KeyEnterprisesModel>> findByArea(@PathVariable("id") String id) {
        return service.list(new KeyEnterprisesModel(null, id, null, null));
    }

    @RequestMapping(value = "/keyEnterprises", method = RequestMethod.POST)
    public ResponseResult<KeyEnterprisesModel> save_data(@Valid @ModelAttribute("form") KeyEnterprisesModel model,
                                                         BindingResult bindingResult) {
        ResponseResult<KeyEnterprisesModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        return service.put(model);
    }
}
