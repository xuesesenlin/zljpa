package com.zlj.zl.publics.weather.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlj.zl.api.WeatherInterface;
import com.zlj.zl.util.feign.FeignUtils;
import com.zlj.zl.util.resultJson.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @RequestMapping(value = "/weather/{cityName}", method = RequestMethod.GET)
    public ResponseResult<JsonNode> weather(@PathVariable("cityName") String cityName) {
        ResponseResult<JsonNode> result = new ResponseResult<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WeatherInterface weatherInterface = FeignUtils.feign()
                    .target(WeatherInterface.class, "https://www.sojson.com");
            JsonNode node = weatherInterface.weather(cityName);
            String s = objectMapper.writeValueAsString(node);
            result.setSuccess(true);
            result.setMessage("成功");
//            {"date":"20180515","message":"Success !","status":200,"city":"石家庄市","count":1554,
// "data":{"shidu":"80%","pm25":46.0,"pm10":101.0,"quality":"良","wendu":"24","ganmao":"极少数敏感人群应减少户外活动",
// "yesterday":{"date":"14日星期一","sunrise":"05:14","high":"高温 32.0℃","low":"低温 21.0℃",
// "sunset":"19:24","aqi":168.0,"fx":"南风","fl":"4-5级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},
// "forecast":[{"date":"15日星期二","sunrise":"05:13","high":"高温 30.0℃","low":"低温 21.0℃",
// "sunset":"19:24","aqi":88.0,"fx":"南风","fl":"3-4级","type":"雷阵雨","notice":"带好雨具，别在树下躲雨"},
// {"date":"16日星期三","sunrise":"05:12","high":"高温 29.0℃","low":"低温 21.0℃","sunset":"19:25","aqi":84.0,
// "fx":"东风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"17日星期四","sunrise":"05:11",
// "high":"高温 22.0℃","low":"低温 17.0℃","sunset":"19:26","aqi":71.0,"fx":"东北风","fl":"<3级","type":"中雨",
// "notice":"记得随身携带雨伞哦"},{"date":"18日星期五","sunrise":"05:10","high":"高温 24.0℃","low":"低温 17.0℃",
// "sunset":"19:27","aqi":68.0,"fx":"北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},
// {"date":"19日星期六","sunrise":"05:10","high":"高温 25.0℃","low":"低温 17.0℃","sunset":"19:28","aqi":86.0,
// "fx":"北风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"}]}}
            result.setData(node);
//            result.setData(nodeList.toArray().toString());
//            String weather = weather1;
//        jackjson转换工具
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(weather);
//            String asText = jsonNode.asText("data");
//            JsonNode node = objectMapper.readTree(asText);
//            result.setData(node.asText("notice"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("失败");
            result.setData(null);
            return result;
        }
        return result;
    }
}
