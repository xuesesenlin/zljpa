package com.zlj.zl.util.jurisdiction;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
public class JurisdictionUtil {

//    //    判断是否拥有指定的角色
//    public boolean isRole(String role) {
//        //        手动判断权限角色
//        Subject subject = SecurityUtils.getSubject();
//        try {
////            判断角色
//            subject.checkRole("admin");
////            判断权限
////        subject.checkPermissions("add","update");
//            return true;
//        } catch (UnauthorizedException e) {
////            System.out.println("权限不足");
//            return false;
//        }
//    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView Error_403() {
        return new ModelAndView("/403");
    }
}
