package com.zlj.zl.user.policyType.controller;

import com.zlj.zl.user.policyType.model.PolicyTypeModel;
import com.zlj.zl.user.policyType.service.PolicyTypeService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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
@RequestMapping("/policyType")
public class PolicyTypeController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private PolicyTypeService policyTypeService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/policyType/index");
    }

    @RequestMapping(value = "/policyType/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<PolicyTypeModel>> page_data(@PathVariable("pageNow") int pageNow) {
        return policyTypeService.page(pageNow, pageSize);
    }

    @RequestMapping(value = "/policyType", method = RequestMethod.POST)
    public ResponseResult<PolicyTypeModel> save_data(@Valid @ModelAttribute("form") PolicyTypeModel model,
                                                     BindingResult bindingResult) {
        ResponseResult<PolicyTypeModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
//        重复名称验证
        ResponseResult<List<PolicyTypeModel>> result1 = policyTypeService.findByTypeName(model.getTypeName());
        if (result1.isSuccess()) {
            result.setSuccess(false);
            result.setMessage("名称重复");
            return result;
        }
        return policyTypeService.add(model);
    }

    @RequestMapping(value = "/policyType", method = RequestMethod.PUT)
    public ResponseResult<PolicyTypeModel> update_data(@Valid @ModelAttribute("form") PolicyTypeModel model,
                                                       BindingResult bindingResult) {
        ResponseResult<PolicyTypeModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        //        重复名称验证
        ResponseResult<List<PolicyTypeModel>> result1 = policyTypeService.findByTypeName(model.getTypeName());
        if (result1.isSuccess()) {
            result.setSuccess(false);
            result.setMessage("名称重复");
            return result;
        }
        return policyTypeService.update(model);
    }

    @RequestMapping(value = "/policyType/{id}", method = RequestMethod.DELETE)
    public ResponseResult<PolicyTypeModel> delete_data(@PathVariable("id") String id) {
        return policyTypeService.del(id);
    }
}
