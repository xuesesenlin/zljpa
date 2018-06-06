package com.zlj.zl.user.PMC.controller;

import com.zlj.zl.user.PMC.model.PMCModel;
import com.zlj.zl.user.PMC.service.PMCService;
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
@RequestMapping("/pmc")
public class PMCController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private PMCService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {
        return new ModelAndView("/pmc/index");
    }

    @RequestMapping(value = "/pmc/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<PMCModel>> init(@PathVariable("pageNow") int pageNow,
                                               @RequestParam(value = "names", required = false) String names,
                                               @RequestParam(value = "types", required = false, defaultValue = "0") int types) {
        return service.findByPageAndParams(new PMCModel(null, names, types, null), pageNow, pageSize);
    }

    @RequestMapping(value = "/pmc/list", method = RequestMethod.GET)
    public ResponseResult<List<PMCModel>> list(@RequestParam(value = "uuid", required = false) String uuid,
                                               @RequestParam(value = "names", required = false) String names,
                                               @RequestParam(value = "types", required = false, defaultValue = "0") int types,
                                               @RequestParam(value = "patentUuid", required = false) String patentUuid) {
        return service.findByPageAndParamsList(new PMCModel(uuid, names, types, patentUuid));
    }

    @RequestMapping(value = "/pmc", method = RequestMethod.POST)
    public ResponseResult<PMCModel> save(@Valid @ModelAttribute("form") PMCModel model,
                                         BindingResult bindingResult) {
        ResponseResult<PMCModel> result = new ResponseResult<>();
        //        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        return service.save(model);
    }

    @RequestMapping(value = "/pmc/{id}", method = RequestMethod.DELETE)
    public ResponseResult<PMCModel> delete(@PathVariable("id") String id) {
        return service.delete(id);
    }

    @RequestMapping(value = "/pmc", method = RequestMethod.PUT)
    public ResponseResult<PMCModel> update(@Valid @ModelAttribute("form") PMCModel model,
                                           BindingResult bindingResult) {
        ResponseResult<PMCModel> result = new ResponseResult<>();
        //        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        return service.update(model);
    }
}
