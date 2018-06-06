package com.zlj.zl.admin.permissionsInitialization.controller;

import com.zlj.zl.admin.jurisdiction.jpa.JurisdictionJpa;
import com.zlj.zl.admin.jurisdiction.model.JurisdictionModel;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
public class PermissionsInitializationController {

    @Autowired
    private JurisdictionJpa jpa;

//    @RequiresRoles(value = "superAdmin")
//    @RequestMapping(value = "/PermissionsInitialization", method = RequestMethod.GET)
//    public String init() throws Exception {
//        jpa.deleteAll();
//        File path = new File(ResourceUtils.getURL("classpath:").getPath());
//        if (!path.exists()) path = new File("");
//
//        File jsonPath = new File(path.getAbsolutePath(), "static/json");
//        if (!jsonPath.exists())
//            jsonPath.mkdirs();
//
//        ObjectMapper objMapper = new ObjectMapper();
//        String jsonText = jsonPath.getAbsolutePath() + "/jurisdiction_table.json";
//        JsonNode rootNode = objMapper.readTree(new File(jsonText));
//        List<JurisdictionModel> list = new ArrayList<>();
//        rootNode.forEach(k -> {
//            JurisdictionModel model = new JurisdictionModel();
//            model.setName(k.findValue("name").asText());
//            model.setIdentification(k.findValue("identification").asText());
//            model.setJurType(k.findValue("jur_type").asInt());
//            model.setParents(k.findValue("parents").asText());
//            model.setSysType(k.findValue("sys_type").asText());
//            list.add(model);
//        });
//        jpa.save(list);
//        return new String("权限初始化导入成功".getBytes("UTF-8"), "UTF-8");
//    }

    @RequiresRoles(value = "superAdmin")
    @RequestMapping(value = "/PermissionsInitialization", method = RequestMethod.GET)
    public String init() throws Exception {
        jpa.deleteAll();
        List<JurisdictionModel> list = new ArrayList<>();
        list.add(new JurisdictionModel("专利数据导入主页", "patentDataImport:data:page_data"));
        list.add(new JurisdictionModel("专利数据维护删除", "patentDataPut:data:delete_data"));
        list.add(new JurisdictionModel("专利数据维护修改", "patentDataPut:data:update_data"));
        list.add(new JurisdictionModel("专利数据维护主页", "patentDataPut:data:page_data"));
        list.add(new JurisdictionModel("专利数据维护新增", "patentDataPut:data:save_data"));
        list.add(new JurisdictionModel("专利数据维护查询结果导出", "patentDataPut:data:export_data"));
        list.add(new JurisdictionModel("专利数据维护查询结果删除", "patentDataPut:data:delete_export_data"));
        list.add(new JurisdictionModel("专利数据查询主页", "patentDataQuery:data:page_data"));
        list.add(new JurisdictionModel("专利数据查询 查询结果导出", "patentDataQuery:data:export_data"));
        list.add(new JurisdictionModel("专利数据锁定主页", "patentDataLocking:data:page_data"));
        list.add(new JurisdictionModel("用户管理新增", "people:data:save_data"));
        list.add(new JurisdictionModel("用户管理新增管理员", "people:data:save_admin_data"));
        list.add(new JurisdictionModel("用户管理主页", "people:data:page_data"));
        list.add(new JurisdictionModel("用户管理删除", "people:data:delete_data"));
        list.add(new JurisdictionModel("开发区（介绍）主页", "development:data:page_data"));
        list.add(new JurisdictionModel("开发区（介绍）修改", "development:data:update_data"));
        list.add(new JurisdictionModel("开发区（介绍）新增", "development:data:save_data"));
        list.add(new JurisdictionModel("开发区（介绍）删除", "development:data:delete_data"));
        list.add(new JurisdictionModel("省市县查询", "pmc:data:list_data"));
        list.add(new JurisdictionModel("省市县主页", "pmc:data:page_data"));
        list.add(new JurisdictionModel("省市县修改", "pmc:data:update_data"));
        list.add(new JurisdictionModel("省市县删除", "pmc:data:delete_data"));
        list.add(new JurisdictionModel("省市县新增", "pmc:data:save_data"));
        list.add(new JurisdictionModel("地域代码维护新增", "regionCode:data:save_data"));
        list.add(new JurisdictionModel("地域代码维护主页", "regionCode:data:page_data"));
        list.add(new JurisdictionModel("地域代码维护删除", "regionCode:data:delete_data"));
        list.add(new JurisdictionModel("政策法规类别修改", "policyType:data:update_data"));
        list.add(new JurisdictionModel("政策法规主页", "policy:data:page_data"));
        list.add(new JurisdictionModel("政策法规新增", "policy:data:save_data"));
        list.add(new JurisdictionModel("政策法规类别删除", "policyType:data:delete_data"));
        list.add(new JurisdictionModel("政策法规修改", "policy:data:update_data"));
        list.add(new JurisdictionModel("政策法规删除", "policy:data:delete_data"));
        list.add(new JurisdictionModel("政策法规类别主页", "policyType:data:page_data"));
        list.add(new JurisdictionModel("政策法规类别新增", "policyType:data:save_data"));
        list.add(new JurisdictionModel("专利服务机构主页", "patentServiceAgency:data:page_data"));
        list.add(new JurisdictionModel("专利服务机构新增", "patentServiceAgency:data:save_data"));
        list.add(new JurisdictionModel("专利服务机构修改", "patentServiceAgency:data:update_data"));
        list.add(new JurisdictionModel("专利服务机构删除", "patentServiceAgency:data:delete_data"));
        jpa.save(list);
        return new String("权限初始化导入成功".getBytes("UTF-8"), "UTF-8");
    }
}
