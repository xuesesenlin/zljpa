package com.zlj.zl.publics.realcom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ld
 * @name 实时通讯
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/realcom")
public class RealcomController {

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {
        return new ModelAndView("/realcom/index");
    }
}
