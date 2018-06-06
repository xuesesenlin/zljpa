package com.zlj.zl.util.jdbc;

import com.zlj.zl.publics.account.model.AccountModel;
import com.zlj.zl.publics.token.model.TokenModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class JDBC {
    public List<TokenModel> queryToken(String sql, String token, String token_user) {
        TokenModel model = null;
        List<TokenModel> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            //创建连接
            conn = JDBCUtils.getConnetions();
            //创建prepareStatement对象，用于执行SQL
            ps = conn.prepareStatement(sql);
            ps.setString(1, token);
            ps.setString(2, token_user);
            //获取查询结果集
            result = ps.executeQuery();
            while (result.next()) {
                model = new TokenModel(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getLong(4),
                        result.getString(5));
                list.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(result, ps, conn);
        }
        return list;
    }

    public List<AccountModel> queryToken2(String sql, String token) {
        AccountModel model = null;
        List<AccountModel> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            //创建连接
            conn = JDBCUtils.getConnetions();
            //创建prepareStatement对象，用于执行SQL
            ps = conn.prepareStatement(sql);
            ps.setString(1, token);
            //获取查询结果集
            result = ps.executeQuery();
            while (result.next()) {
                model = new AccountModel();
                model.setAccount(result.getString(1));
                model.setPassword(result.getString(2));
                model.setTypes(result.getString(3));
                list.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(result, ps, conn);
        }
        return list;
    }

    public int update(String sql, String token) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //创建数据库连接
            conn = JDBCUtils.getConnetions();
            //创建执行SQL的prepareStatement对象
            ps = conn.prepareStatement(sql);
            ps.setString(1, token);
            //用于增删改操作
            int result = ps.executeUpdate();
            return result;
        } catch (Exception e) {
            System.out.println("出现异常1=" + e.toString());
            return 0;
        } finally {
            JDBCUtils.release(ps, conn);
        }
    }

    public int update2(String sql, String uuid, String account, String token, long endTimes, String isUse) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //创建数据库连接
            conn = JDBCUtils.getConnetions();
            //创建执行SQL的prepareStatement对象
            ps = conn.prepareStatement(sql);
            ps.setString(1, uuid);
            ps.setString(2, account);
            ps.setString(3, token);
            ps.setLong(4, endTimes);
            ps.setString(5, isUse);
            //用于增删改操作
            int result = ps.executeUpdate();
            return result;
        } catch (Exception e) {
            System.out.println("出现异常1=" + e.toString());
            return 0;
        } finally {
            JDBCUtils.release(ps, conn);
        }
    }

    public int logger(String sql, String[] data) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //创建数据库连接
            conn = JDBCUtils.getConnetions();
            //创建执行SQL的prepareStatement对象
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < data.length; i++) {
                ps.setString(i + 1, data[i]);
            }
            //用于增删改操作
            int result = ps.executeUpdate();
            return result;
        } catch (Exception e) {
            System.out.println("出现异常1=" + e.toString());
            return 0;
        } finally {
            JDBCUtils.release(ps, conn);
        }
    }
}
