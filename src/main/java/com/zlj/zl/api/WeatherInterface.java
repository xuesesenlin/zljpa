package com.zlj.zl.api;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Param;
import feign.RequestLine;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface WeatherInterface {

    @RequestLine("GET /open/api/weather/json.shtml?city={city}")
    JsonNode weather(@Param("city") String city);

//    @RequestLine("POST /api/account/update")
//    @Body("pass={pass}&token={token}")
//    ResponseResult<String> updatePWD(@Param("pass") String pass, @Param("token") String token);
//
//    @RequestLine("POST /api/register/register")
//    @Body("json={json}")
//    ResponseResult<String> register(@Param("json") String json);
}
