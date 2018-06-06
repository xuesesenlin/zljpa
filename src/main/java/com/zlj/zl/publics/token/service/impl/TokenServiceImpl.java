package com.zlj.zl.publics.token.service.impl;

import com.zlj.zl.publics.account.model.AccountModel;
import com.zlj.zl.publics.token.jpa.TokenJpa;
import com.zlj.zl.publics.token.model.TokenModel;
import com.zlj.zl.publics.token.service.TokenService;
import com.zlj.zl.util.jdbc.JDBC;
import com.zlj.zl.util.resultJson.ResponseResult;
import com.zlj.zl.util.uuidUtil.GetUuid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenJpa jpa;

    @Override
    public ResponseResult<TokenModel> add(TokenModel model) {
        ResponseResult<TokenModel> result = new ResponseResult<>();
        jpa.save(model);
        result.setSuccess(true);
        result.setData(model);
        return result;
    }

    @Override
    public ResponseResult<TokenModel> add2(TokenModel model) {
        ResponseResult<TokenModel> result = new ResponseResult<>();
        JDBC jdbc = new JDBC();
        int i = jdbc.update2("insert into token_table (uuid,account,token,end_time,is_use)" +
                " values (?,?,?,?,?)", GetUuid.getUUID(), model.getAccount(), model.getToken(), model.getEndTimes(), model.getIsUse());
        if (i > 0) {
            result.setSuccess(true);
        } else
            result.setSuccess(false);
        return result;
    }

    @Override
    public ResponseResult updateToken(String token) {
        ResponseResult<TokenModel> result = new ResponseResult<>();
        jpa.updateToken(token);
        result.setSuccess(true);
        result.setData(null);
        return result;
    }

    @Override
    public ResponseResult updateToken2(String token) {
        ResponseResult<TokenModel> result = new ResponseResult<>();
        JDBC jdbc = new JDBC();
//        int i = jdbc.update("update token_table set is_use = 'Y' where token = '" + token + "'");
        int i = jdbc.update("update token_table set is_use = 'Y' where token = ?", token);
        if (i > 0) {
            result.setSuccess(true);
        } else
            result.setSuccess(false);
        return result;
    }

    @Override
    public ResponseResult getByToken(String token) {
        ResponseResult<TokenModel> result = new ResponseResult<>();
        TokenModel model = jpa.getByToken(token);
        if (model != null) {
            result.setSuccess(true);
            result.setData(model);
        } else {
            result.setSuccess(false);
            result.setData(null);
        }
        return result;
    }

    @Override
    public ResponseResult<TokenModel> getByToken2(String token, String token_user) {
        ResponseResult<TokenModel> result = new ResponseResult<>();
        JDBC jdbc = new JDBC();
        List<TokenModel> models = jdbc.queryToken("select uuid,account,token,end_time endTimes,is_use isUse " +
                "from token_table where token=? and account = ? ORDER BY end_time desc LIMIT 1", token, token_user);

        if (models.size() > 0) {
            result.setSuccess(true);
            result.setData(models.get(0));
        } else
            result.setSuccess(false);
        return result;
    }

    @Override
    public ResponseResult<AccountModel> getByToken3(String token) {
        ResponseResult<AccountModel> result = new ResponseResult<>();
        JDBC jdbc = new JDBC();
        List<AccountModel> models = jdbc.queryToken2("select account,password,types from account_table a left join token_table t " +
                "on t.account = a.account where t.token = ?", token);

        if (models.size() > 0) {
            result.setSuccess(true);
            result.setData(models.get(0));
        } else
            result.setSuccess(false);
        return result;
    }
}
