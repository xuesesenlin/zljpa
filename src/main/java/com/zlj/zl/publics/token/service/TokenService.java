package com.zlj.zl.publics.token.service;

import com.zlj.zl.publics.account.model.AccountModel;
import com.zlj.zl.publics.token.model.TokenModel;
import com.zlj.zl.util.resultJson.ResponseResult;

public interface TokenService {

    ResponseResult<TokenModel> add(TokenModel model);

    ResponseResult<TokenModel> add2(TokenModel model);

    ResponseResult<TokenModel> updateToken(String token);

    ResponseResult<TokenModel> updateToken2(String token);

    ResponseResult<TokenModel> getByToken(String token);

    ResponseResult<TokenModel> getByToken2(String token, String token_user);

    ResponseResult<AccountModel> getByToken3(String token);
}
