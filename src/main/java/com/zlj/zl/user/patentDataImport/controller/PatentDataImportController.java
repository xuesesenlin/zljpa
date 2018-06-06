package com.zlj.zl.user.patentDataImport.controller;

import com.zlj.zl.user.PMC.model.PMCModel;
import com.zlj.zl.user.PMC.service.PMCService;
import com.zlj.zl.user.applyPeoTypes.model.ApplyPeoTypesModel;
import com.zlj.zl.user.applyPeoTypes.service.ApplyPeoTypesService;
import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import com.zlj.zl.user.patentDataPut.service.PatentDataPutService;
import com.zlj.zl.user.patentTypes.model.PatentTypesModel;
import com.zlj.zl.user.patentTypes.service.PatentTypesService;
import com.zlj.zl.user.regionCode.model.RegionCodeModel;
import com.zlj.zl.user.regionCode.service.RegionCodeService;
import com.zlj.zl.util.csv.CSVUtils;
import com.zlj.zl.util.files.FileTool;
import com.zlj.zl.util.poi.ExcelUtils;
import com.zlj.zl.util.resultJson.ResponseResult;
import com.zlj.zl.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author ld
 * @name 专利数据导入
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/patentDataImport")
public class PatentDataImportController {

    @Value("${upLoad.path}")
    private String path;
    @Autowired
    private PatentDataPutService service;
    @Autowired
    private PMCService pmcService;
    @Autowired
    private RegionCodeService regionCodeService;
    @Autowired
    private PatentTypesService patentTypesService;
    @Autowired
    private ApplyPeoTypesService applyPeoTypesService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init() {
        return new ModelAndView("/patentDataImport/index");
    }

    @RequestMapping(value = "/patentDataImport", method = RequestMethod.POST)
    public ResponseResult<String> upLoadFile(@RequestParam("file") MultipartFile file) {
        try {
            File file1 = new File(path);
//            如果目录存在则创建
            if (!file1.exists()) {
                try {
                    file1.mkdir();
                } catch (Exception e) {
                    return new ResponseResult<>(false, "目录创建失败", null);
                }
            }
//上传的文件名
            String filename = file.getOriginalFilename();
            String s = filename.substring(filename.lastIndexOf("."), filename.length());
            String s1 = filename.split("_")[1];
            String lx = "";
            if (filename.indexOf("申请") > 0)
                lx = "申请";
            if (filename.indexOf("授权") > 0)
                lx = "授权";
            if (filename.indexOf("有效") > 0)
                lx = "有效";

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            long time = format.parse(s1 + "01").getTime();
            if (s.equals(".xls") || s.equals(".csv")) {
//                new ResponseResult<>(false, "上传文件失败,只支持.xls文件", null);
//            写入服务器
                String file2Name = GetUuid.getUUID() + s;
                File file2 = new File(path + file2Name);
                file.transferTo(file2);
                return jxwj(file2Name, s, time, lx);
            } else if (s.equals(".csv")) {
//            写入服务器
//                String file2Name = GetUuid.getUUID() + s;
//                File file2 = new File(path + file2Name);
//                file.transferTo(file2);
//                return jxwj2(file2Name);
                return new ResponseResult<>(false, "上传文件失败,.csv解析服务暂时不可用", null);
            } else {
                return new ResponseResult<>(false, "上传文件失败,只支持.xls或.csv文件", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(false, "上传文件失败", null);
        }
    }

    //    解析文件
    public ResponseResult<String> jxwj(String fileName, String s, long impDate, String lx) {
        try {
//导入
            Map<Long, Map<String, String>> map = new ExcelUtils().readExcelXls(path + "/" + fileName);
//            if (s.equals(".xls"))
//                map = new ExcelUtils().readExcelXls(file2);
//            else
//                map = new ExcelUtils().readExcelXlsx(file2);
            if (map == null || map.size() <= 0)
                return new ResponseResult<>(false, "未解析到数据,导入失败", null);

            List<PatentDataPutModel> list = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            map.forEach((k1, v1) -> {
                PatentDataPutModel model = new PatentDataPutModel();
                v1.forEach((k, v) -> {
                    if (k == null || k.trim().equals(""))
                        return;
                    try {
//                判断数据库有无此申请号，无则导入
                        if (k.equals("申请号")) {
                            ResponseResult<List<PatentDataPutModel>> result1 = service.findByApplyCode(v);
                            if (result1.isSuccess())
                                return;
//                申请号applyCode
                            model.setApplyCode(v);
                        }
//                授权入库日empowermentDate
                        if (k.equals("授权入库日")) {
                            if (v != null && !v.trim().equals(""))
                                model.setEmpowermentDate(format.parse(v).getTime());
                        }
//                申请日applyDate
                        if (k.equals("申请日")) {
                            if (v != null && !v.trim().equals(""))
                                model.setApplyDate(format.parse(v).getTime());
                        }
//                发明名称inventionName
                        if (k.equals("发明名称"))
                            model.setInventionName(v);
//                        代理机构代码
                        if (k.equals("代理机构代码"))
                            model.setAgentCode(v);
//                代理机构名称agentName
                        if (k.equals("代理机构名称"))
                            model.setAgentName(v);
//                主分类号mainTypes
                        if (k.equals("主分类号"))
                            model.setMainTypes(v);
//                专利权人名称patentPeoName
                        if (k.equals("专利权人名称"))
                            model.setPatentPeoName(v);
//                专利权人邮编zipCode
                        if (k.equals("专利权人邮编") || k.equals("申请人邮编"))
                            model.setZipCode(v);
//                专利权人地址peoAddress
                        if (k.equals("专利权人地址") || k.equals("申请人地址"))
                            model.setPeoAddress(v);
//                专利类型patentTypes
                        if (k.equals("专利类型")) {
                            ResponseResult<List<PatentTypesModel>> result = patentTypesService.list(new PatentTypesModel(null, v));
                            if (!result.isSuccess()) {
                                List<PatentTypesModel> list3 = new ArrayList<>();
                                PatentTypesModel typesModel = new PatentTypesModel(null, v);
                                list3.add(typesModel);
                                ResponseResult<PatentTypesModel> saves = patentTypesService.saves(list3);
                                if (!saves.isSuccess())
                                    return;
                            }
                            model.setPatentTypes(v);
                        }
//                专利权人类型appPeoTypes ApplyPeoTypes
                        if (k.equals("专利权人类型") || k.equals("申请人类型")) {
                            ResponseResult<List<ApplyPeoTypesModel>> result2 = applyPeoTypesService.list(new ApplyPeoTypesModel(null, v));
                            if (!result2.isSuccess()) {
                                List<ApplyPeoTypesModel> list3 = new ArrayList<>();
                                ApplyPeoTypesModel typesModel = new ApplyPeoTypesModel(null, v);
                                list3.add(typesModel);
                                ResponseResult<ApplyPeoTypesModel> saves = applyPeoTypesService.saves(list3);
                                if (!saves.isSuccess())
                                    return;
                            }
                            model.setAppPeoTypes(v);
                        }
//                省份名称province
                        if (k.equals("省份名称"))
                            model.setProvince(v);
//                城市名称city
                        if (k.equals("城市名称"))
                            model.setCity(v);
//                区/县名称area,根据城市名称查询
//                        if (k.equals("区/县名称")) {
//                            if (v != null && !v.trim().equals(""))
//                                model.setArea(v);
//                            else {
//                                String area = autoMatching(model.getCity(), model.getPeoAddress());
//                                model.setArea(area);
//                            }
//                        }
//                    申请方式
                        if (k.equals("申请方式名称"))
                            model.setApplyMode(v);
                        //                案卷入库日
                        if (k.equals("案卷入库日")) {
                            if (v != null && !v.trim().equals(""))
                                model.setFileEnterDate(format.parse(v).getTime());
                        }
//                导入时间
//                        if (k.equals("导入时间")) {
//                            if (v == null || v.trim().equals(""))
//                                model.setImpDate(System.currentTimeMillis());
//                            else
//                                model.setImpDate(format.parse(v).getTime());
//                        }
                    } catch (Exception e) {
                        return;
                    }
                });
//                区县
                String area = autoMatching(model.getCity(), model.getPeoAddress());
                model.setArea(area);

                model.setApplyAuthorization(lx);
                model.setImpDate(impDate);
                model.setFlag("0");
                list.add(model);
            });
            if (list.size() > 0)
                service.saves(list);
            return new ResponseResult<>(true, "导入成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(false, "导入失败", null);
        }
    }

    public ResponseResult<String> jxwj2(String fileName) {
        new CSVUtils().readCSV(path + fileName);
        return null;
    }

    //    根据城市名称和地址自动匹配所属区/县
    private String autoMatching(String ctName, String address) {
        StringJoiner area = new StringJoiner("");
        //                该城市下的所有区/县
        List<PMCModel> list1 = pmcService.findByCityName(ctName);
//                根据区/县获取所有的关键字
        list1.forEach(k -> {
            if (!area.toString().equals(""))
                return;
            RegionCodeModel model = new RegionCodeModel();
            model.setArea(k.getUuid());
            ResponseResult<List<RegionCodeModel>> result = regionCodeService.list(model);
            if (result.isSuccess()) {
                result.getData().forEach(j -> {
//                    匹配第一级
                    String[] split = j.getKeyworda().split(";");
                    for (int i = 0; i < split.length; i++) {
                        if (address.contains(split[i])) {
                            area.add(k.getNames());
                            return;
                        }
                    }
//                    匹配二级
//                    判断是否需要匹配二级
                    if (area.toString().equals("")) {
                        String[] split2 = j.getKeywordb().split(";");
                        for (int i = 0; i < split2.length; i++) {
                            if (address.contains(split2[i])) {
                                area.add(k.getNames());
                                return;
                            }
                        }
                    }
                });
            }
        });
        return area.toString();
    }

    //下载模版
    @RequestMapping(value = "/dow", method = RequestMethod.GET)
    public void dow(HttpServletResponse response) throws Exception {
        String fileName = new String("/专利数据维护模版.xls".getBytes("UTF-8"));
        File file = new File(path, fileName);
        FileTool.downloadFile(file.getCanonicalPath(), response);
    }
}
