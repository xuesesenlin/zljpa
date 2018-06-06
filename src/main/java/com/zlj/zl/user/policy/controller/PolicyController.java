package com.zlj.zl.user.policy.controller;

import com.zlj.zl.user.policy.model.PolicyModel;
import com.zlj.zl.user.policy.service.PolicyService;
import com.zlj.zl.user.policyType.model.PolicyTypeModel;
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
@RequestMapping("/policy")
public class PolicyController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private PolicyService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/policy/index");
    }

    @RequestMapping(value = "/policy/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<PolicyModel>> page_data(@PathVariable("pageNow") int pageNow,
                                                       @RequestParam(value = "titles", required = false) String titles,
                                                       @RequestParam(value = "types", required = false) String types) {
        return service.page(pageNow, pageSize, titles, types);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("/policy/add");
    }

    @RequestMapping(value = "/policyType", method = RequestMethod.GET)
    public ResponseResult<List<PolicyTypeModel>> findPolicyType() {
        return service.findPolicyType();
    }

    @RequestMapping(value = "/policy", method = RequestMethod.POST)
    public ResponseResult<PolicyModel> save_data(@Valid @ModelAttribute("form") PolicyModel model,
                                                 BindingResult bindingResult) {
        ResponseResult<PolicyModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        return service.save(model);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") String id) {
        return new ModelAndView("/policy/update")
                .addObject("uuid", id);
    }

    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public ResponseResult<PolicyModel> findOne(@PathVariable("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/policy", method = RequestMethod.PUT)
    public ResponseResult<PolicyModel> update_data(@Valid @ModelAttribute("form") PolicyModel model,
                                                   BindingResult bindingResult) {
        ResponseResult<PolicyModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        return service.update(model);
    }

    @RequestMapping(value = "/policy/{id}", method = RequestMethod.DELETE)
    public ResponseResult<PolicyModel> delete_data(@PathVariable("id") String id) {
        return service.delete(id);
    }
}
