package com.zlj.zl.sysConfig.reuquest;

import com.zlj.zl.publics.stringFilte.model.StringFilterModel;
import com.zlj.zl.util.jdbc.JDBC;
import com.zlj.zl.util.jdbc.JDBCUtils;
import com.zlj.zl.util.uuidUtil.GetUuid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.shiro.web.util.WebUtils.getPathWithinApplication;

/**
 * @author ld
 * @name 自定义request部分功能，字符串过滤
 * @table
 * @remarks
 */
public class MyRequest extends HttpServletRequestWrapper {

    public MyRequest(HttpServletRequest request) {
        super(request);
        String url = getPathWithinApplication(request);
        String message = "";
        if (url.equals("/"))
            message = "打开登录页";
        else {
            String s = url.split("/")[1];
            if (s != null) {
                if (s.equals("policy"))
                    message = "法律法规";
                if (s.equals("policyType"))
                    message = "法律法规类型";
                if (s.equals("development"))
                    message = "开发区介绍";
            }
        }

        new JDBC().logger("insert into logger_table (uuid,logger,thread,systime,message)" +
                " values (?,?,?,?,?)", new String[]{GetUuid.getUUID(), url,
                request.getMethod(), System.currentTimeMillis() + "", message});
    }

    @Override
    public String getParameter(String name) {
        // 返回值之前 先进行过滤
        return filterDangerString(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        // 返回值之前 先进行过滤
        String[] values = super.getParameterValues(name);
        if (values == null || values.length <= 0)
            return super.getParameterValues(name);
        for (int i = 0; i < values.length; i++) {
            values[i] = filterDangerString(values[i]);
        }
        return values;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = super.getParameterMap();
        map.forEach((k, v) -> {
            String[] s1 = map.get(k);
            for (int i = 0; i < s1.length; i++) {
                s1[i] = filterDangerString(s1[i]);
            }
        });
        return map;
    }

    public String filterDangerString(String value) {
        if (value == null) {
            return null;
        }
//        value = value.replaceAll("\\|", "&#166;");
//        value = value.replaceAll("script", "");
//        value = value.replaceAll("'", "&#039;");
//        value = value.replaceAll(">", "&#62;");
//        value = value.replaceAll("<", "&#60;");
//        List<StringFilterModel> list = query("select uuid,yv,tv from string_filter_table");
//        for (StringFilterModel model : list) {
//            value = value.replaceAll(model.getYv(), model.getTv());
//        }
        return value;
    }

    private List<StringFilterModel> query(String sql) {
        StringFilterModel model = null;
        List<StringFilterModel> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            //创建连接
            conn = JDBCUtils.getConnetions();
            //创建prepareStatement对象，用于执行SQL
            ps = conn.prepareStatement(sql);
            //获取查询结果集
            result = ps.executeQuery();
            while (result.next()) {
                model = new StringFilterModel();
                model.setTv(result.getString(2));
                model.setYv(result.getString(3));
                list.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(result, ps, conn);
        }
        return list;
    }
}
