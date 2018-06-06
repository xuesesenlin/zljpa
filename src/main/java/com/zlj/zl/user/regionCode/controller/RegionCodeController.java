package com.zlj.zl.user.regionCode.controller;

import com.zlj.zl.user.PMC.model.PMCModel;
import com.zlj.zl.user.PMC.service.PMCService;
import com.zlj.zl.user.regionCode.model.RegionCodeModel;
import com.zlj.zl.user.regionCode.service.RegionCodeService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/regionCode")
public class RegionCodeController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private RegionCodeService service;
    @Autowired
    private PMCService pmcService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/regionCode/index");
    }

    @RequestMapping(value = "/regionCode/{id}", method = RequestMethod.GET)
    public ResponseResult<List<RegionCodeModel>> findByArea(@PathVariable("id") String id) {
        return service.list(new RegionCodeModel(null, id, null, null));
    }

    @RequestMapping(value = "/pmc/{id}", method = RequestMethod.GET)
    public ResponseResult<List<PMCModel>> query_data(@PathVariable("id") String id) {
        return pmcService.findByPageAndParamsList(new PMCModel(null, null, 0, id));
    }

    @RequestMapping(value = "/regionCode", method = RequestMethod.POST)
    public ResponseResult<RegionCodeModel> save_data(@Valid @ModelAttribute("form") RegionCodeModel model,
                                                     BindingResult bindingResult) {
        ResponseResult<RegionCodeModel> result = new ResponseResult<>();
//        数据验证
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        return service.put(model);
    }

    @RequestMapping(value = "/regionCode/{id}", method = RequestMethod.DELETE)
    public ResponseResult<RegionCodeModel> delete_data(@PathVariable("id") String id) {
        return service.delete(id);
    }
}
