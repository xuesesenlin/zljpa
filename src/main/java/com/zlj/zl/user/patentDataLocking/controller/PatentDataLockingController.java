package com.zlj.zl.user.patentDataLocking.controller;

import com.zlj.zl.user.patentDataLocking.service.PatentDataLockingService;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import com.zlj.zl.user.patentDataPut.service.PatentDataPutService;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/patentDataLocking")
public class PatentDataLockingController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private PatentDataLockingService service;
    @Autowired
    private PatentDataPutService patentDataPutService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/patentDataLocking/index");
    }

    @RequestMapping(value = "/patentDataLocking/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<List<Object[]>> findByArea(@PathVariable("pageNow") int pageNow,
                                                     @RequestParam("drnyStar") String drnyStar,
                                                     @RequestParam("drnyEnd") String drnyEnd) {
        return service.findByImpDate(pageNow, pageSize, drnyStar, drnyEnd);
    }

    @RequestMapping(value = "/patentDataLocking/sd/{jssd}/{time}", method = RequestMethod.GET)
    public ResponseResult<PatentDataPutModel> jssd(@PathVariable("jssd") int jssd,
                                                   @PathVariable("time") long time) {
        ResponseResult<List<PatentDataPutModel>> result = patentDataPutService.findByImpDate(time);
        if (result.isSuccess()) {
            result.getData().forEach(k -> {
                if (jssd == 1)
                    k.setLocking("Y");
                else
                    k.setLocking("N");
            });
            return patentDataPutService.updates(result.getData(), 2);
        } else
            return new ResponseResult<>(false, "未查询到数据", null);
    }
}
